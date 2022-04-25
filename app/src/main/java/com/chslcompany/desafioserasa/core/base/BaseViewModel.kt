package com.chslcompany.desafioserasa.core.base

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

abstract class BaseViewModel : ViewModel() {

    protected fun <T> MutableLiveData<Resource<T>>.success(data : T){
        postValue(Resource.Success(data))
    }

    protected fun <T> MutableLiveData<Resource<T>>.error(throwable: Throwable?){
        value = Resource.Error(throwable)
    }

    protected fun <T> MutableLiveData<Resource<T>>.loading(isLoading: Boolean){
        value = Resource.Loading(isLoading)
    }
}