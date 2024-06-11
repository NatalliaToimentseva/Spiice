package com.example.spiice.utils

import android.graphics.Typeface
import android.text.Spannable
import android.text.SpannableString
import android.text.style.StyleSpan
import android.widget.TextView

fun createSpanForView(textView: TextView) {
    val textForSpan = textView.text.toString()
    val spannable = SpannableString(textForSpan)

    spannable.setSpan(StyleSpan(Typeface.BOLD), 22, 28, Spannable.SPAN_INCLUSIVE_INCLUSIVE)
    textView.text = spannable
}