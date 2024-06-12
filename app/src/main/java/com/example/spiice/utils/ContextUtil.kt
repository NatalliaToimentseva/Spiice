package com.example.spiice.utils

import android.content.Context
import android.widget.Toast

fun makeToast(context: Context, string: String) {
    Toast.makeText(context, string, Toast.LENGTH_SHORT).show()
}