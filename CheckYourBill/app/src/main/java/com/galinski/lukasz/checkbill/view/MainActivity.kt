package com.galinski.lukasz.checkbill.view

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.galinski.lukasz.checkbill.databinding.MainMenuBinding
import com.galinski.lukasz.checkbill.presentation.MainViewModel
import devs.mulham.horizontalcalendar.HorizontalCalendar
import org.koin.androidx.viewmodel.ext.android.viewModel
import java.util.*

class MainActivity : AppCompatActivity() {
    private lateinit var binding: MainMenuBinding
    private val viewModel by viewModel<MainViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = MainMenuBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        val startDate = Calendar.getInstance()
        startDate.add(Calendar.MONTH, -1)

        val endDate = Calendar.getInstance()
        endDate.add(Calendar.MONTH, 1)

        HorizontalCalendar.Builder(this, binding.horizontalCalendar.id)
                .range(startDate, endDate)
                .datesNumberOnScreen(3)
                .build()

    }
}