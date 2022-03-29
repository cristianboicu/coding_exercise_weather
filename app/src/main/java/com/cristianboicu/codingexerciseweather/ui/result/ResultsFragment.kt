package com.cristianboicu.codingexerciseweather.ui.result

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.cristianboicu.codingexerciseweather.R
import com.cristianboicu.codingexerciseweather.databinding.FragmentResultsBinding
import com.cristianboicu.codingexerciseweather.ui.adapter.ForecastAdapter
import com.cristianboicu.codingexerciseweather.ui.adapter.ForecastListener
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ResultsFragment : Fragment() {

    private val viewModel: ResultViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        val binding: FragmentResultsBinding =
            DataBindingUtil.inflate(inflater, R.layout.fragment_results, container, false)

        binding.lifecycleOwner = this
        binding.viewModel = viewModel

        val adapter = ForecastAdapter(ForecastListener {
            viewModel.onForecastClicked(it)
        })

        binding.rvResult.adapter = adapter

        viewModel.forecastData.observe(viewLifecycleOwner) {
            it?.let {
                adapter.submitList(it)
            }
        }

        return binding.root
    }

}