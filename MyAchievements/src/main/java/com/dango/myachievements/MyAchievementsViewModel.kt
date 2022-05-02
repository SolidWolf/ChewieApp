package com.dango.myachievements

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class MyAchievementsViewModel : ViewModel() {

    private val _text = MutableLiveData<String>().apply {
        value = "This is My Achievements Fragment"
    }
    val text: LiveData<String> = _text
}