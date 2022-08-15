package com.example.lugares.viewModel

import android.app.Application
import androidx.lifecycle.*
import com.example.lugares.data.LugarDao
import com.example.lugares.model.Lugar
import com.example.lugares.repository.LugarRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class LugarViewModel(application: Application) : AndroidViewModel(application)
{
    private val getAllData : MutableLiveData<List<Lugar>>
    private val repository : LugarRepository = LugarRepository(LugarDao())

    init {
        getAllData = repository.getAllData
    }

    fun addLugar(lugar: Lugar) { viewModelScope.launch(Dispatchers.IO) { repository.addLugar(lugar) }}
    fun updateLugar(lugar: Lugar) { viewModelScope.launch(Dispatchers.IO) { repository.updateLugar(lugar) }}
    fun deleteLugar(lugar: Lugar) { viewModelScope.launch(Dispatchers.IO) { repository.deleteLugar(lugar) }}

}