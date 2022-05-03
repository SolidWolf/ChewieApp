package com.dango.songlist

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class SongListViewModel : ViewModel() {

    private val _text = MutableLiveData<String>().apply {
        value = "This is SongListFragment"
    }
    val text: LiveData<String> = _text
}