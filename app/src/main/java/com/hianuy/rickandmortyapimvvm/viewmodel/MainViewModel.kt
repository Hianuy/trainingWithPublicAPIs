package com.hianuy.rickandmortyapimvvm.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.hianuy.rickandmortyapimvvm.model.Result
import com.hianuy.rickandmortyapimvvm.repository.CharacterRepository
import kotlinx.coroutines.launch
import retrofit2.HttpException
import java.net.UnknownHostException

class MainViewModel(private val repository: CharacterRepository) : ViewModel() {


    private var _results = MutableLiveData<List<Result>>()
    val results: LiveData<List<Result>>
        get() = _results

    private val _loading = MutableLiveData<Boolean>()
    val loading: LiveData<Boolean>
        get() = _loading

    private val _errorMessage = MutableLiveData<String>()
    val errorMessage: LiveData<String>
        get() = _errorMessage

    fun init() = getAllMovies()
    private fun getAllMovies() = viewModelScope.launch {
        _loading.postValue(true)
        try {
            repository.getCharacterService().let { characterResponse ->
                _results.postValue(characterResponse.results)
                _loading.postValue(false)


            }
        } catch (exception: Exception) {
            Log.e("Error", "getAllMovies: $exception")
            _loading.postValue(false)
            handleError(exception)

        }
//        results.postValue(repository.getCharacterService())

    }
//
//        try {
//
//        } catch (exception: Exception) {
//            Log.e("Error", "getAllMovies: $exception")
//        }
//        Thread {
//            try {
//
//                mutableLiveDataMovieList.postValue(movieRepository.getAllMovies())
//            } catch (exception: Exception) {
//                exception.message?.let { Log.i("Error", it) }
//            }
//        }.start()

    private fun handleError(error: Throwable) {
        when(error){
            is HttpException -> _errorMessage.postValue("Erro de conexão código: ${error.code()}")
            is UnknownHostException -> _errorMessage.postValue("Verifique a sua conexão")
        }
    }
}