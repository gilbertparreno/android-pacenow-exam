package com.pacenow.exam.ui.authentication.managers

import android.util.Base64
import com.pacenow.exam.BuildConfig
import com.pacenow.exam.core.room.repositories.UserRepository
import com.pacenow.exam.core.security.Encryption
import com.pacenow.exam.core.sharedPreferences.UserSharedPreferences
import com.pacenow.exam.ui.authentication.exceptions.AuthenticationException
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class AuthenticationManager @Inject constructor(
    private val userRepository: UserRepository,
    private val userSharedPreferences: UserSharedPreferences
) {

    @Throws(AuthenticationException::class)
    suspend fun authenticateUser(
        email: String,
        password: ByteArray,
        rememberMe: Boolean
    ) {
        val user = userRepository.findUser(email = email)
        if (
            user != null &&
            decryptPassword(
                salt = user.passwordSalt,
                iv = user.passwordIv,
                encrypted = user.passwordEncrypted
            ).toByteArray().contentEquals(password)
        ) {
            userSharedPreferences.userId = user.id
            userSharedPreferences.rememberMe = rememberMe
            return
        }
        throw AuthenticationException("User not found.")
    }

    private fun decryptPassword(
        salt: String,
        iv: String,
        encrypted: String
    ): String {
        return String(
            Encryption.decrypt(
                hashMapOf(
                    "salt" to Base64.decode(salt, Base64.NO_WRAP),
                    "iv" to Base64.decode(iv, Base64.NO_WRAP),
                    "encrypted" to Base64.decode(encrypted, Base64.NO_WRAP)
                ),
                BuildConfig.MASTER_PASSWORD.toCharArray()
            )!!,
            Charsets.UTF_8
        )
    }
}