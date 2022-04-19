package com.example.viewed.ui.fragments.search.listeners

import android.text.Editable
import android.text.TextWatcher

class QueryChangedListener(
    private val switchQuery: (s: String) -> Unit
) : TextWatcher {

    override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {

    }

    override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
        if (!p0.isNullOrBlank()) {
            switchQuery.invoke(p0.toString())
        }
    }

    override fun afterTextChanged(p0: Editable?) {

    }
}
