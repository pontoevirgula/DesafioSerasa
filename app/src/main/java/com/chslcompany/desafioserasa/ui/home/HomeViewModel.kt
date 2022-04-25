package com.chslcompany.desafioserasa.ui.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.chslcompany.desafioserasa.core.util.State
import com.chslcompany.desafioserasa.domain.model.CharactersResponse
import com.chslcompany.desafioserasa.domain.model.Results
import com.chslcompany.desafioserasa.domain.usecase.dbcrud.InsertCrudDbUseCase
import com.chslcompany.desafioserasa.domain.usecase.getcharacters.GetCharactersUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject
import javax.inject.Named

@HiltViewModel
class HomeViewModel @Inject constructor(
    @Named("io") private val ioDispatcher: CoroutineDispatcher,
    private val getCharactersUseCase: GetCharactersUseCase,
    private val insertCrudDbUseCase: InsertCrudDbUseCase
) : ViewModel() {

    private val _response = MutableLiveData<State<CharactersResponse>>()
    val response: LiveData<State<CharactersResponse>> = _response

    private val _insert = MutableLiveData<State<Boolean>>()
    val insert: LiveData<State<Boolean>> = _insert

    fun getCharacters(ts: Long, apiKey: String, hash: String) {
        viewModelScope.launch {
            try {
                _response.value = State.loading(true)
                val response = withContext(ioDispatcher){
                    getCharactersUseCase(ts, apiKey, hash)
                }

                _response.value = State.success(response)

            }catch (throwable: Throwable){
                _response.value = State.error(throwable)
            }
        }
    }

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