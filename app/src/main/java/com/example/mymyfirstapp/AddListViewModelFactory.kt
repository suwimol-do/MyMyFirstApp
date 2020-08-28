package com.example.mymyfirstapp

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.mymyfirstapp.database.AddListDao
import com.example.mymyfirstapp.databinding.FragmentContactBinding

class AddListViewModelFactory (
    private val dataSource: AddListDao,
    private val binding: FragmentContactBinding,
    private val application: Application
) : ViewModelProvider.Factory{

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(AddListViewModel::class.java)) {
            return AddListViewModel(dataSource, binding, application) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}