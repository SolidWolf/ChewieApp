package com.dango.commands

import android.text.Editable
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import models.CommandsModel

class CommandsViewModel : ViewModel() {
    private val filteredCommandsData = MutableLiveData<ArrayList<CommandsModel>>()
    private val _text = MutableLiveData<String>().apply {
        value = "No Data Was Found"
    }
    val text: LiveData<String> = _text

    fun filterData(
        searchedText:String,
        data: ArrayList<CommandsModel>?
    ): ArrayList<CommandsModel> {
        var filteredList: ArrayList<CommandsModel> = ArrayList()
        data?.forEach{
            if(it.commandName.lowercase().contains(searchedText.lowercase()) ||
                    it.permissionType.lowercase().contains(searchedText.lowercase()) ||
                    it.commandType.lowercase().contains(searchedText.lowercase()) ||
                    it.content.lowercase().contains(searchedText.lowercase())){
                filteredList.add(it)
            }
        }
        return filteredList
    }

    fun setFilterList(
        filteredList: ArrayList<CommandsModel>,
        data: ArrayList<CommandsModel>?,
        s: Editable
    ) {
        if(s.isNotEmpty()){
            filteredCommandsData.postValue(filteredList)
        } else {
            filteredCommandsData.postValue(data)
        }
    }

    fun getFilteredData(): MutableLiveData<ArrayList<CommandsModel>>{
        return filteredCommandsData
    }

    fun postFiliteredCommandsList(data: ArrayList<CommandsModel>?) {
        filteredCommandsData.postValue(data)
    }
}