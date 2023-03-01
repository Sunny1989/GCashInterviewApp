package com.example.gcashinterviewapp.ui.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.gcashinterviewapp.GCashApplication
import com.example.gcashinterviewapp.adapter.WeatherItemAdapter
import com.example.gcashinterviewapp.databinding.FragmentMainBinding
import com.example.gcashinterviewapp.repository.Response
import com.example.gcashinterviewapp.viewmodel.AbstractViewModelFactory
import com.example.gcashinterviewapp.viewmodel.WeatherListVM
import com.example.gcashinterviewapp.viewmodel.WeatherVM
import javax.inject.Inject

class WeatherListFragment : Fragment() {

    private lateinit var binding: FragmentMainBinding

    private lateinit var weatherListVM: WeatherListVM

    @Inject
    lateinit var abstractViewModelFactory: AbstractViewModelFactory

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentMainBinding.inflate(inflater, container, false)

        val weatherComponent = (activity?.application as GCashApplication)
            .appComponent.getWeatherComponent()
        weatherComponent.injectWeatherListFragment(this)
        weatherListVM = ViewModelProvider(this, abstractViewModelFactory)[WeatherListVM::class.java]

        var weatherItemAdapter = WeatherItemAdapter(WeatherItemAdapter.OnClickListener {
            //Toast.makeText(activity, it.name + " " + it.phone, Toast.LENGTH_SHORT).show()
        })

        binding.rvList.adapter = weatherItemAdapter
        weatherListVM.weatherListLiveData.observe(viewLifecycleOwner, Observer {
            when (it) {
                is Response.Success ->{
                    weatherItemAdapter.submitList(it.data1)
                    binding.executePendingBindings()
                }

                is Response.Error -> Toast.makeText(
                    activity,
                    it.errorMsg1,
                    Toast.LENGTH_SHORT
                ).show()
                is Response.Loading -> {}
            }
        })

        return binding.root
    }

    companion object {
        /**
         * Returns a new instance of this fragment for the given section
         * number.
         */
        @JvmStatic
        fun newInstance(): WeatherListFragment {
            return WeatherListFragment()
        }
    }

}