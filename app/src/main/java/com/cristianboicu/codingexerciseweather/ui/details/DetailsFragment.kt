package com.cristianboicu.codingexerciseweather.ui.details

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.cristianboicu.codingexerciseweather.R
import com.cristianboicu.codingexerciseweather.databinding.FragmentDetailsBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DetailsFragment : Fragment() {

    private val viewModel: DetailsViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        val binding: FragmentDetailsBinding =
            DataBindingUtil.inflate(inflater, R.layout.fragment_details, container, false)

        binding.lifecycleOwner = this

        val forecastId = DetailsFragmentArgs.fromBundle(requireArguments()).id
        viewModel.start(forecastId)

        viewModel.forecastDetails.observe(viewLifecycleOwner) {
            it?.let {
                binding.domainForecast = it
            }
        }

        return binding.root
    }

}