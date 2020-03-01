package com.vmoskvyak.bowlingleague.viewmodel

import android.text.Editable
import android.text.TextWatcher
import android.view.View
import androidx.databinding.ObservableField

class PlayersFragmentBindingModel {
    var name = ObservableField<String>()

    fun onAddPlayerClick(view: View) {
        name.get()
    }

    var watcher: TextWatcher = object : TextWatcher {
        override fun afterTextChanged(s: Editable?) {
            name.set(s.toString())
        }

        override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}

        override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}
    }
}