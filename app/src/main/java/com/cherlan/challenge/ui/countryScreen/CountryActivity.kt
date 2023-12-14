package com.cherlan.challenge.ui.countryScreen

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.cherlan.challenge.databinding.ActivityMainBinding
import com.cherlan.challenge.domain.Result
import com.cherlan.challenge.ui.common.SimpleDividerItemDecoration
import org.koin.android.viewmodel.ext.android.viewModel


class CountryActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private val viewModel: CountryViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        viewModel.countryList.observe(this) { uiState ->
            binding.pBar.visibility = View.INVISIBLE
            when (uiState) {
                is Result.Success -> {
                    binding.rvCountry.apply {
                        layoutManager = LinearLayoutManager(context)
                        adapter = CountryAdapter(uiState.data)
                        addItemDecoration(SimpleDividerItemDecoration(context))
                    }
                    binding.pBar.visibility = View.GONE
                }

                is Result.Failure -> {
                    Toast.makeText(this, uiState.error, Toast.LENGTH_LONG).show()
                    binding.pBar.visibility = View.GONE
                }

                else -> {
                    binding.pBar.visibility = View.VISIBLE
                }
            }
        }


    }

}