package net.kathir.mviarchitecture.ui.base

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import net.kathir.mviarchitecture.data.api.ApiHelper
import net.kathir.mviarchitecture.data.repository.MainRepository
import net.kathir.mviarchitecture.ui.main.viewmodel.MainViewModel
import java.lang.IllegalArgumentException

class ViewModelFactory(private val apiHelper: ApiHelper) : ViewModelProvider.Factory
{
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if(modelClass.isAssignableFrom(MainViewModel::class.java))
        {
            return MainViewModel(MainRepository(apiHelper)) as T
        }

        throw IllegalArgumentException("Unknown class name")
    }

}