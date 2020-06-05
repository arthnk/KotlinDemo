package com.nikunj.kotlindemo.viewmodel.persondetail

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.nikunj.kotlindemo.network.PersonProperty

class PersonDetailViewModelFactory(
    private val personProperty: PersonProperty,
    private val application: Application) : ViewModelProvider.Factory {
    @Suppress("unchecked_cast")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(PersonDetailViewModel::class.java)) {
            return PersonDetailViewModel(
                personProperty,
                application
            ) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}
