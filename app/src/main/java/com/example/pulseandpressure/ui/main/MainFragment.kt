package com.example.pulseandpressure.ui.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import by.kirich1409.viewbindingdelegate.viewBinding
import com.example.pulseandpressure.R
import com.example.pulseandpressure.databinding.FragmentMainBinding

class MainFragment : Fragment(R.layout.fragment_main) {
    private val binding: FragmentMainBinding by viewBinding()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initFab()
    }

    private fun initFab() {
        binding.fabShowDialog.setOnClickListener {
            findNavController().navigate(R.id.action_mainFragment_to_addDataDialogFragment)
        }
    }
}