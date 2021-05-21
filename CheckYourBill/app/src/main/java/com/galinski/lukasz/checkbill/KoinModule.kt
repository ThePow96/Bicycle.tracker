package com.galinski.lukasz.checkbill

import com.galinski.lukasz.checkbill.presentation.MainViewModel
import org.koin.dsl.module

val mainModule = module{
    MainViewModel()
}
