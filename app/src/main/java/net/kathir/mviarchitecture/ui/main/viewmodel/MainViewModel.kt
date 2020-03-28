package net.kathir.mviarchitecture.ui.main.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import io.reactivex.disposables.CompositeDisposable
import net.kathir.mviarchitecture.data.model.User
import net.kathir.mviarchitecture.data.repository.MainRepository
import net.kathir.mviarchitecture.ui.main.dataholder.MainDataHolder
import net.kathir.mviarchitecture.ui.main.viewevent.MainEvent
import net.kathir.mviarchitecture.util.Resource

class MainViewModel(private val mainRepository: MainRepository) : ViewModel()
{
    private val eventValue : MutableLiveData<MainEvent> = MutableLiveData()
    val viewState : MutableLiveData<MainDataHolder> = MutableLiveData()
    private val compositeDisposable = CompositeDisposable()

    val dataValueState : LiveData<Resource<MainDataHolder>> = Transformations.switchMap(eventValue)
    {
        eventValue -> eventValue?.let { handleEvent(it) }
    }

    private fun handleEvent(event: MainEvent): LiveData<Resource<MainDataHolder>> {
        when(event)
        {
            is MainEvent.UserLoadEvent -> {
                return mainRepository.getUsers(compositeDisposable)
            }
        }
    }

    fun loadUser(users : List<User>)
    {
        val state = MainDataHolder()
        state.users = users
        viewState.postValue(state)
    }

    fun setEventValue(e: MainEvent)
    {
        val event : MainEvent = e
        eventValue.postValue(event)
    }

    override fun onCleared() {
        super.onCleared()
        compositeDisposable.dispose()
    }

}
