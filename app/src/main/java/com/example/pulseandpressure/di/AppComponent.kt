package com.example.pulseandpressure.di

import com.example.pulseandpressure.databinding.DialogFragmentAddDataBinding
import com.example.pulseandpressure.ui.add_data.AddDataDialogFragment
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [FirestoreModule::class, BindsModule::class])
interface AppComponent {
    fun inject(fragment: AddDataDialogFragment)
}