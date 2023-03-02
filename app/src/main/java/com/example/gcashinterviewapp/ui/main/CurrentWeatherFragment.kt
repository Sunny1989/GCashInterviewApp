package com.example.gcashinterviewapp.ui.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.gcashinterviewapp.GCashApplication
import com.example.gcashinterviewapp.MainActivity
import com.example.gcashinterviewapp.databinding.FragmentCurrentWeatherBinding
import com.example.gcashinterviewapp.repository.Response
import com.example.gcashinterviewapp.viewmodel.AbstractViewModelFactory
import com.example.gcashinterviewapp.viewmodel.WeatherVM
import javax.inject.Inject

class CurrentWeatherFragment : Fragment(){
    private lateinit var binding: FragmentCurrentWeatherBinding

    private var weatherVM: WeatherVM? = null

    @Inject
    lateinit var abstractViewModelFactory: AbstractViewModelFactory

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentCurrentWeatherBinding.inflate(inflater, container, false)

        val weatherComponent = (activity?.application as GCashApplication)
            .appComponent.getWeatherComponent()
        weatherComponent.injectWeatherFragment(this)
        weatherVM = ViewModelProvider(this, abstractViewModelFactory)[WeatherVM::class.java]
        weatherVM!!.weatherLiveData.observe(viewLifecycleOwner) {
            when (it) {
                is Response.Success -> {
                    binding.weatherItem = it.data1
                    binding.executePendingBindings()
                }
                is Response.Error -> Toast.makeText(
                    activity,
                    it.errorMsg1,
                    Toast.LENGTH_SHORT
                ).show()
                is Response.Loading -> {}
            }

        }

        binding.container.setOnRefreshListener {
            binding.container.isRefreshing = false
            (activity as MainActivity).checkAndCallLocation()
        }
        return binding.root
    }

    fun callWeatherApi(lat: Double?, lon: Double?, apiKey: String){
        if(weatherVM != null) {
            lat?.let { lon?.let { it1 -> weatherVM!!.callWeatherDetails(it, it1, apiKey) } }
        }
    }

    companion object {
        /**
         * Returns a new instance of this fragment for the given section
         * number.
         */
        @JvmStatic
        fun newInstance(): CurrentWeatherFragment {
            return CurrentWeatherFragment()
        }
    }
}