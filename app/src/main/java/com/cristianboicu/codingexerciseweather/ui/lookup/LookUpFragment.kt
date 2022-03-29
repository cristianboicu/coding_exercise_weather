package com.cristianboicu.codingexerciseweather.ui.lookup

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.cristianboicu.codingexerciseweather.R
import com.cristianboicu.codingexerciseweather.databinding.FragmentLookUpBinding
import com.cristianboicu.codingexerciseweather.util.EventObserver
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class LookUpFragment : Fragment() {

    private val viewModel: LookUpViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        val binding: FragmentLookUpBinding =
            DataBindingUtil.inflate(inflater, R.layout.fragment_look_up, container, false)

        binding.lifecycleOwner = this
        binding.viewModel = viewModel

        viewModel.navigateToResults.observe(viewLifecycleOwner, EventObserver {
            this.findNavController().navigate(
                LookUpFragmentDirections.actionLookUpFragmentToResultsFragment()
            )
        })

        return binding.root
    }

}