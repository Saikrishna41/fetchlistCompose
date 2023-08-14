package com.techfortyone.fetchlistcompose.data.repository

import com.techfortyone.fetchlistcompose.data.model.PostsResponseItem
import com.techfortyone.fetchlistcompose.data.remote.PostapiServiceHelper
import io.reactivex.rxjava3.core.Observable
import javax.inject.Inject

class PostRepository @Inject constructor(private val postapiServiceHelper: PostapiServiceHelper) {
      fun getPosts(): Observable<List<PostsResponseItem>> {
        return postapiServiceHelper.getPosts()
    }
}