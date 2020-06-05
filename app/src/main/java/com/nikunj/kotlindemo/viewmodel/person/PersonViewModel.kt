package com.nikunj.kotlindemo.viewmodel.person

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.nikunj.kotlindemo.network.PersonApi
import com.nikunj.kotlindemo.network.PersonProperty
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

enum class PersonApiStatus { LOADING, ERROR, DONE }

class OverviewViewModel : ViewModel() {

    private val _status = MutableLiveData<PersonApiStatus>()

    val status: LiveData<PersonApiStatus>
        get() = _status

    private val _properties = MutableLiveData<List<PersonProperty>>()

    val properties: LiveData<List<PersonProperty>>
        get() = _properties

    private val _navigateToSelectedProperty = MutableLiveData<PersonProperty>()
    val navigateToSelectedProperty: LiveData<PersonProperty>
        get() = _navigateToSelectedProperty

    private var viewModelJob = Job()

    private val coroutineScope = CoroutineScope(viewModelJob + Dispatchers.Main)

    init {
        getPersonProperties()
    }

    private fun getPersonProperties() {
        coroutineScope.launch {
            var getPropertiesDeferred = PersonApi.retrofitService.getProperties()
            try {
                _status.value =
                    PersonApiStatus.LOADING
                val listResult = getPropertiesDeferred.await()
                _status.value =
                    PersonApiStatus.DONE
                _properties.value = listResult
            } catch (e: Exception) {
                _status.value =
                    PersonApiStatus.ERROR
                _properties.value = ArrayList()
            }
        }
    }

    override fun onCleared() {
        super.onCleared()
        viewModelJob.cancel()
    }

    fun displayPropertyDetails(personProperty: PersonProperty) {
        _navigateToSelectedProperty.value = personProperty
    }

    fun displayPropertyDetailsComplete() {
        _navigateToSelectedProperty.value = null
    }
}
