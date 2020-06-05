package com.nikunj.kotlindemo.ui.fragment

import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.fragment.findNavController
import com.nikunj.kotlindemo.databinding.FragmentPersonBinding
import com.nikunj.kotlindemo.viewmodel.person.OverviewViewModel
import com.nikunj.kotlindemo.ui.adapter.PhotoGridAdapter

class PersonFragment : Fragment() {

    private val viewModel: OverviewViewModel by lazy {
        ViewModelProviders.of(this).get(OverviewViewModel::class.java)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = FragmentPersonBinding.inflate(inflater)
        binding.setLifecycleOwner(this)
        binding.viewModel = viewModel

        binding.photosGrid.adapter =
            PhotoGridAdapter(PhotoGridAdapter.OnClickListener {
                viewModel.displayPropertyDetails(it)
            })

        viewModel.navigateToSelectedProperty.observe(this, Observer {
            if (null != it) {
                this.findNavController().navigate(
                    PersonFragmentDirections.actionShowDetail(
                        it
                    )
                )
                viewModel.displayPropertyDetailsComplete()
            }
        })

        return binding.root
    }
}
