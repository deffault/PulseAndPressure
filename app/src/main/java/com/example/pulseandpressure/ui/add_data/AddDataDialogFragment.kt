package com.example.pulseandpressure.ui.add_data

import android.app.Dialog
import android.content.Context
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import by.kirich1409.viewbindingdelegate.CreateMethod
import by.kirich1409.viewbindingdelegate.viewBinding
import com.example.pulseandpressure.R
import com.example.pulseandpressure.appComponent
import com.example.pulseandpressure.databinding.DialogFragmentAddDataBinding
import com.example.pulseandpressure.domain.MainData
import com.example.pulseandpressure.utils.getDate
import com.example.pulseandpressure.utils.getTime
import kotlinx.coroutines.launch
import javax.inject.Inject

class AddDataDialogFragment : DialogFragment(R.layout.dialog_fragment_add_data) {
    private val binding: DialogFragmentAddDataBinding by viewBinding(createMethod = CreateMethod.INFLATE)

    @Inject
    lateinit var viewModelFactory: dagger.Lazy<AddDataViewModelFactory>

    private val addDataViewModel: AddDataViewModel by viewModels { viewModelFactory.get() }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        context.appComponent.inject(this)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        collectData()
        initSaveButton()
        initCancelButton()
    }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        return AlertDialog.Builder(requireContext())
            .setCancelable(false)
            .setView(binding.root)
            .create()
    }

    private fun collectData() {
        viewLifecycleOwner.lifecycleScope.launch {
            repeatOnLifecycle(state = Lifecycle.State.STARTED) {
                addDataViewModel.flow.collect {
                    Toast.makeText(requireContext(), it, Toast.LENGTH_SHORT).show()
                }
            }
        }
    }

    private fun initSaveButton() {
        binding.btnSave.setOnClickListener {
            val topPressure = binding.edTopPressure.text.toString()
            val bottomPressure = binding.edBottomPressure.text.toString()

            val pulse = binding.edPulse.text.toString()

            val data = MainData(
                topPressure = topPressure.toLong(),
                bottomPressure = bottomPressure.toLong(),
                pulse = pulse.toLong(),
                time = getTime(),
                date = getDate()
            )

            addDataViewModel.addData(data=data)
            dismiss()
        }
    }

    private fun initCancelButton() {
        binding.btnCancel.setOnClickListener {
            dismiss()
        }
    }
}