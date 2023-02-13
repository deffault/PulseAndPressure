package com.example.pulseandpressure.ui.main

import android.content.Context
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView.ItemDecoration
import by.kirich1409.viewbindingdelegate.viewBinding
import com.example.pulseandpressure.R
import com.example.pulseandpressure.appComponent
import com.example.pulseandpressure.databinding.FragmentMainBinding
import com.example.pulseandpressure.utils.Resource
import kotlinx.coroutines.launch
import javax.inject.Inject

class MainFragment : Fragment(R.layout.fragment_main) {
    private val binding: FragmentMainBinding by viewBinding()

    @Inject
    lateinit var viewModelFactory: dagger.Lazy<MainViewModelFactory>

    private val mainViewModel: MainViewModel by viewModels { viewModelFactory.get() }

    private val dataAdapter = DataAdapter()

    override fun onAttach(context: Context) {
        super.onAttach(context)
        context.appComponent.inject(this)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initRecycler()
        initFab()

        collectData()
        mainViewModel.getData()
    }

    private fun initRecycler() {
        binding.rvData.apply {
            layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
            adapter = dataAdapter
            addItemDecoration(DividerItemDecoration(requireContext(), DividerItemDecoration.VERTICAL))
        }
    }

    private fun initFab() {
        binding.fabShowDialog.setOnClickListener {
            findNavController().navigate(R.id.action_mainFragment_to_addDataDialogFragment)
        }
    }

    private fun collectData() {
        viewLifecycleOwner.lifecycleScope.launch {
            repeatOnLifecycle(state = Lifecycle.State.RESUMED) {
                mainViewModel.listFlow.collect { state ->
                    checkState(state = state)
                }
            }
        }
    }

    private fun checkState(state: Resource) {
        when(state) {
            is Resource.Loading -> {
                with(binding) {
                    rvData.isVisible = false
                    progress.isVisible = true
                }
            }
            is Resource.Error -> {
                with(binding) {
                    rvData.isVisible = false
                    progress.isVisible = false
                }
                Toast.makeText(requireContext(), state.message, Toast.LENGTH_SHORT).show()
            }
            is Resource.Success -> {
                with(binding) {
                    progress.isVisible = false
                    rvData.isVisible = true
                }
                dataAdapter.submitList(state.data)
            }
        }
    }
}