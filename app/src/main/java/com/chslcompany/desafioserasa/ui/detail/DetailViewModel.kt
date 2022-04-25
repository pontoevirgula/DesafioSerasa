package com.chslcompany.desafioserasa.ui.detail

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.chslcompany.desafioserasa.core.util.State
import com.chslcompany.desafioserasa.domain.model.Results
import com.chslcompany.desafioserasa.domain.usecase.dbcrud.InsertCrudDbUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject
import javax.inject.Named

@HiltViewModel
class DetailViewModel @Inject constructor(
    @Named("io") private val ioDispatcher: CoroutineDispatcher,
    private val insertCrudDbUseCase: InsertCrudDbUseCase
) : ViewModel() {

    private val _insert = MutableLiveData<State<Boolean>>()
    val insert: LiveData<State<Boolean>>
        get() = _insert

    fun insert(results: Results) {
        viewModelScope.launch {
            try {
                _insert.value = State.loading(true)
                withContext(ioDispatcher) {
                    insertCrudDbUseCase(results)
                }

                _insert.value = State.success(true)
            } catch (ex: Exception) {
                _insert.value = State.error(ex)
            }
        }
    }


}