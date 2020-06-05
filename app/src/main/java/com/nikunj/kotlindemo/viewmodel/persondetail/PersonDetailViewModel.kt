package com.nikunj.kotlindemo.viewmodel.persondetail

import android.app.Application
import androidx.lifecycle.*
import com.nikunj.kotlindemo.network.PersonProperty

class PersonDetailViewModel(personProperty: PersonProperty,
                            app: Application) : AndroidViewModel(app) {

    // The internal MutableLiveData for the selected property
    private val _selectedProperty = MutableLiveData<PersonProperty>()

    // The external LiveData for the SelectedProperty
    val selectedProperty: LiveData<PersonProperty>
        get() = _selectedProperty

    // Initialize the _selectedProperty MutableLiveData
    init {
        _selectedProperty.value = personProperty
    }
}

