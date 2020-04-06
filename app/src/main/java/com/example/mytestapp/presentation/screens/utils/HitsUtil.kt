package com.example.mytestapp.presentation.screens.utils

import android.content.Context
import com.example.mytestapp.R
import com.example.mytestapp.data.model.Hits
import java.text.SimpleDateFormat
import java.util.*

fun Hits.getCreatedAtTime(context: Context): String {
    val parser = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'", Locale.getDefault())
    val formatter = SimpleDateFormat("dd.MM.yyyy HH:mm", Locale.getDefault())

    return context.getString(
        R.string.created_at_format,
        formatter.format(parser.parse(created_at)!!)
    )
}