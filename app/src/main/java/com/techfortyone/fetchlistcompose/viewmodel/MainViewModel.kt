package com.techfortyone.fetchlistcompose.viewmodel

import android.util.Log
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.key.Key.Companion.T
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.techfortyone.fetchlistcompose.data.model.PostsResponseItem
import com.techfortyone.fetchlistcompose.data.repository.PostRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.Scheduler
import io.reactivex.rxjava3.disposables.Disposable
import io.reactivex.rxjava3.schedulers.Schedulers
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class MainViewModel @Inject constructor(private val repository: PostRepository) : ViewModel() {

   // private val _postsData = MutableLiveData<List<PostsResponseItem>>()
//
    var postDataState by mutableStateOf<List<PostsResponseItem>>(emptyList())
        private set

//    val posts: LiveData<List<PostsResponseItem>>
//        get() = _postsData


    private lateinit var rxresult : Disposable
    init {
        fetchPosts()
    }

    private fun fetchPosts()  {
        rxresult = repository.getPosts().subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe { postLists ->
                Log.d("TAGS", "posts list values $postLists")
                postDataState = postLists
            // _postsData.postValue(postLists)
            }
    }


    override fun onCleared() {
        super.onCleared()
        rxresult.dispose()
    }
}