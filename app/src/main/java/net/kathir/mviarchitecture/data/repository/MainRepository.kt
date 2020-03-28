package net.kathir.mviarchitecture.data.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import net.kathir.mviarchitecture.data.api.ApiHelper
import net.kathir.mviarchitecture.ui.main.dataholder.MainDataHolder
import net.kathir.mviarchitecture.util.Resource

class MainRepository(private val apiHelper: ApiHelper)
{
    fun getUsers(compositeDisposable: CompositeDisposable) : LiveData<Resource<MainDataHolder>>
    {
        var userLiveData = MutableLiveData<Resource<MainDataHolder>>()
        userLiveData.postValue(Resource.loading(null))
        compositeDisposable.add(apiHelper.getUsers().subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(
            { userList -> userLiveData.postValue(Resource.success(MainDataHolder(userList)))},
            { userLiveData.postValue(Resource.error("Something Went Wrong",null)) }
        ))

        return userLiveData
    }
}