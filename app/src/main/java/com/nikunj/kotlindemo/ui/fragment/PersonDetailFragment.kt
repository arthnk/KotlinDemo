package com.nikunj.kotlindemo.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import com.nikunj.kotlindemo.databinding.FragmentPersonDetailBinding
import com.nikunj.kotlindemo.viewmodel.persondetail.PersonDetailViewModel
import com.nikunj.kotlindemo.viewmodel.persondetail.PersonDetailViewModelFactory

class PersonDetailFragment : Fragment() {
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {

        val application = requireNotNull(activity).application
        val binding = FragmentPersonDetailBinding.inflate(inflater)
        binding.setLifecycleOwner(this)

        val personProperty = PersonDetailFragmentArgs.fromBundle(arguments!!
        ).selectedProperty
        val viewModelFactory =
            PersonDetailViewModelFactory(
                personProperty,
                application
            )
        binding.viewModel = ViewModelProviders.of(
                this, viewModelFactory).get(PersonDetailViewModel::class.java)

        return binding.root
    }
}