package com.pacenow.exam.ui.settings.viewModels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.*
import com.pacenow.exam.core.extensions.SingleLiveEvent
import com.pacenow.exam.core.extensions.launch
import com.pacenow.exam.core.network.enums.TaskStatus
import com.pacenow.exam.core.network.repositories.AppRepository
import com.pacenow.exam.core.providers.CoroutineContextProvider
import com.pacenow.exam.core.room.entities.Country
import com.pacenow.exam.core.room.repositories.CountryRepository
import com.pacenow.exam.ui.settings.entities.CountryItem
import com.pacenow.exam.ui.settings.factories.SettingsFactory
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class SettingsViewModel @Inject constructor(
    private val appRepository: AppRepository,
    private val coroutineContextProvider: CoroutineContextProvider,
    private val countryRepository: CountryRepository
) : ViewModel() {

    val countriesEvent = SingleLiveEvent<TaskStatus<Any>>()

    val allCountries: Flow<PagingData<CountryItem>> = Pager(config = PagingConfig(pageSize = 15)) {
        countryRepository.getAllCountries()
    }.flow.map { pagingData ->
        pagingData.map { SettingsFactory.getCountryItem(it) }
    }.cachedIn(viewModelScope)

    fun getCountries() {
        viewModelScope.launch(
            coroutineContextProvider = coroutineContextProvider,
            work = {
                if (countryRepository.count() == 0) {
                    delay(250)
                    countriesEvent.postValue(TaskStatus.loading())
                    val countries = appRepository.getCountries().map {
                        Country(
                            code = it.code,
                            country = it.country,
                            region = it.region
                        )
                    }.toTypedArray()
                    countryRepository.insert(*countries)
                }
            },
            onSuccess = {
                countriesEvent.value = TaskStatus.success()
            },
            onFailure = {
                countriesEvent.value = TaskStatus.error(it)
            }
        )
    }
}