package com.dango.mycards

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class MyCardsViewModel : ViewModel() {

    private val _text = MutableLiveData<String>().apply {
        value = "This is My Cards Fragment"
    }
    val text: LiveData<String> = _text
}