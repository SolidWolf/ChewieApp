package com.dango.commands

import android.text.Editable
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import models.CommandsApiModelItem
import models.CommandsModel

class CommandsViewModel : ViewModel() {
    private val filteredCommandsData = MutableLiveData<ArrayList<CommandsApiModelItem>>()
    val permissionType = mapOf<Int, String>(1 to "Viewer", 2 to "Subscriber", 3 to "Moderator", 4 to "Bot", 5 to "Admin", 6 to "Broadcaster")
    val commandType = mapOf<Int, String>(0 to "Text", 1 to "Alias", 2 to "System")
    private val _text = MutableLiveData<String>().apply {
        value = "No Data Was Found"
    }
    val text: LiveData<String> = _text

    fun filterData(
        searchedText:String,
        data: ArrayList<CommandsApiModelItem>?
    ): ArrayList<CommandsApiModelItem> {
        var filteredList: ArrayList<CommandsApiModelItem> = ArrayList()
        data?.forEach{
            if(it.commandName.lowercase().contains(searchedText.lowercase()) ||
                permissionType[it.minUserLevel]?.lowercase()?.contains(searchedText.lowercase()) == true ||
                    commandType[it.type]?.lowercase()?.contains(searchedText.lowercase()) == true ||
                    it.content.lowercase().contains(searchedText.lowercase())){
                filteredList.add(it)
            }
        }
        return filteredList
    }

    fun setFilterList(
        filteredList: ArrayList<CommandsApiModelItem>,
        data: ArrayList<CommandsApiModelItem>?,
        s: Editable
    ) {
        if(s.isNotEmpty()){
            filteredCommandsData.postValue(filteredList)
        } else {
            filteredCommandsData.postValue(data)
        }
    }

    fun getFilteredData(): MutableLiveData<ArrayList<CommandsApiModelItem>>{
        return filteredCommandsData
    }

    fun postFiliteredCommandsList(data: ArrayList<CommandsApiModelItem>?) {
        filteredCommandsData.postValue(data)
    }
}