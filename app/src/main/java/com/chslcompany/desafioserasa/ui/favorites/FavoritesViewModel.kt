package com.chslcompany.desafioserasa.ui.favorites

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.chslcompany.desafioserasa.core.util.State
import com.chslcompany.desafioserasa.domain.model.Results
import com.chslcompany.desafioserasa.domain.usecase.dbcrud.DeleteCrudDbUseCase
import com.chslcompany.desafioserasa.domain.usecase.dbcrud.GetCrudDbUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject
import javax.inject.Named

@HiltViewModel
class FavoritesViewModel @Inject constructor(
    @Named("io") private val ioDispatcher: CoroutineDispatcher,
    private val getCrudDbUseCase: GetCrudDbUseCase,
    private val deleteCrudDbUseCase: DeleteCrudDbUseCase
) : ViewModel() {

    private val _delete = MutableLiveData<State<Boolean>>()
    val delete: LiveData<State<Boolean>>
        get() = _delete

    fun getCharacters() = getCrudDbUseCase.invoke()

    fun deleteCharacters(results: Results) = viewModelScope.launch {
        try {
            _delete.value = State.loading(true)
            withContext(ioDispatcher) {
                deleteCrudDbUseCase.deleteCharactersDb(results)
            }

            _delete.value = State.success(true)
        } catch (ex: Exception) {
            _delete.value = State.error(ex)
        }

    }

}