package com.example.a081124

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import com.example.a081124.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding: ActivityMainBinding =
            DataBindingUtil.setContentView(this, R.layout.activity_main)

        setContentView(binding.root)
        val ViewModel = ViewModelProvider(this).get(CustomViewModel::class.java)
        ViewModel.initcontext(this)
        binding.viewModel = ViewModel
        binding.lifecycleOwner = this
    }
}