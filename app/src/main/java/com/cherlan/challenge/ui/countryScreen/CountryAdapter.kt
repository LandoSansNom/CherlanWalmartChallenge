package com.cherlan.challenge.ui.countryScreen

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.cherlan.challenge.R
import com.cherlan.challenge.data.model.Country
import com.cherlan.challenge.databinding.ItemCountryBinding


class CountryAdapter(private val countries: List<Country>): RecyclerView.Adapter<CountryAdapter.ViewHolder>() {

    class ViewHolder(view: View): RecyclerView.ViewHolder(view) {
        private val binding = ItemCountryBinding.bind(view)
        fun updateUI(country: Country) {
            var countryInfo = country.name + ", " + country.region + ", " + country.code
            binding.apply {
                tvCountry.text = countryInfo
                tvCountryCapital.text = country.capital

            }
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.item_country,parent,false)

        )
    }

    override fun getItemCount(): Int {
        return countries.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        countries[position]?.let { holder.updateUI(it) }

    }


}