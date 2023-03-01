package com.example.gcashinterviewapp.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.gcashinterviewapp.databinding.ItemWeatherBinding
import com.example.gcashinterviewapp.models.WeatherItem

class WeatherItemAdapter(val onClickListener: OnClickListener) : androidx.recyclerview.widget.ListAdapter<WeatherItem,WeatherItemAdapter.WeatherViewHolder>(WeatherDiffCallBack()){

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): WeatherViewHolder {
            val layoutInflater = LayoutInflater.from(parent.context)
            val binding = ItemWeatherBinding.inflate(layoutInflater, parent, false)
            return WeatherViewHolder(binding)
        }

        override fun onBindViewHolder(holder: WeatherViewHolder, position: Int) {
            val weatherItem = getItem(position)
            holder.binding.weatherItem = weatherItem
            holder.itemView.setOnClickListener {
                onClickListener.onClick(weatherItem)
            }
            holder.binding.executePendingBindings()
        }

        class WeatherViewHolder(val binding: ItemWeatherBinding) :
            RecyclerView.ViewHolder(binding.root) {

        }

        class WeatherDiffCallBack : DiffUtil.ItemCallback<WeatherItem>() {
            override fun areItemsTheSame(oldItem: WeatherItem, newItem: WeatherItem): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(oldItem: WeatherItem, newItem: WeatherItem): Boolean {
                return oldItem == newItem
            }
        }

        class OnClickListener(val clickListener: (weatherItem: WeatherItem) -> Unit) {
            fun onClick(weatherItem: WeatherItem) = clickListener(weatherItem)
        }
    }

