package com.example.bicycletracker.tools

import android.content.Context
import android.widget.ImageButton
import android.widget.ScrollView
import androidx.constraintlayout.widget.ConstraintLayout
import com.example.bicycletracker.R
import com.example.bicycletracker.view.NORMAL_VISIBILITY
import com.example.bicycletracker.view.REDUCED_VISIBILITY

private const val PREFERENCES_BACKGROUND_LABEL = "Background"
private var defaultBackground = 0
private fun setNotChosenBackgroundAlpha(darkGrayBtn: ImageButton, lightGrayBtn: ImageButton, pinkBtn: ImageButton){
    darkGrayBtn.alpha = REDUCED_VISIBILITY
    lightGrayBtn.alpha = REDUCED_VISIBILITY
    pinkBtn.alpha = REDUCED_VISIBILITY
}

fun setAppDefaultBackground(context: Context, background: Int){
    val editor = context.getSharedPreferences(PREFERENCES_BACKGROUND_LABEL, Context.MODE_PRIVATE).edit()
    editor.putInt(PREFERENCES_BACKGROUND_LABEL, background)
    editor.apply()
}

fun showChosenBackground(darkGrayBtn: ImageButton, lightGrayBtn: ImageButton, pinkBtn: ImageButton){
    when (defaultBackground){
        R.color.dark_gray -> {
            setNotChosenBackgroundAlpha(darkGrayBtn, lightGrayBtn, pinkBtn)
            darkGrayBtn.alpha = NORMAL_VISIBILITY
        }
        R.color.litleGrey -> {
            setNotChosenBackgroundAlpha(darkGrayBtn, lightGrayBtn, pinkBtn)
            lightGrayBtn.alpha = NORMAL_VISIBILITY
        }
        else -> {
            setNotChosenBackgroundAlpha(darkGrayBtn, lightGrayBtn, pinkBtn)
            pinkBtn.alpha = NORMAL_VISIBILITY
        }
    }
}

fun loadAppBackground(context: Context, mainLayout: ConstraintLayout) {
    val preferences = context.getSharedPreferences(PREFERENCES_BACKGROUND_LABEL, Context.MODE_PRIVATE)
    defaultBackground = preferences.getInt(PREFERENCES_BACKGROUND_LABEL, R.drawable.background_gradient)
    mainLayout.setBackgroundResource(defaultBackground)
}

fun loadAppBackground(context: Context, mainLayout: ScrollView) {
    val preferences = context.getSharedPreferences(PREFERENCES_BACKGROUND_LABEL, Context.MODE_PRIVATE)
    defaultBackground = preferences.getInt(PREFERENCES_BACKGROUND_LABEL, R.drawable.background_gradient)
    mainLayout.setBackgroundResource(defaultBackground)
}

fun showChosenBackgroundButton(darkGrayBtn: ImageButton, lightGrayBtn: ImageButton, pinkBtn: ImageButton, def: Int){
    when (def){
        R.color.dark_gray -> {
            setNotChosenBackgroundAlpha(darkGrayBtn, lightGrayBtn, pinkBtn)
            darkGrayBtn.alpha = NORMAL_VISIBILITY
        }
        R.color.litleGrey -> {
            setNotChosenBackgroundAlpha(darkGrayBtn, lightGrayBtn, pinkBtn)
            lightGrayBtn.alpha = NORMAL_VISIBILITY
        }
        else -> {
            setNotChosenBackgroundAlpha(darkGrayBtn, lightGrayBtn, pinkBtn)
            pinkBtn.alpha = NORMAL_VISIBILITY
        }
    }
}