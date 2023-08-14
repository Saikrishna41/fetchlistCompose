package com.techfortyone.fetchlistcompose.data.remote

import com.techfortyone.fetchlistcompose.data.model.PostsResponseItem
import io.reactivex.rxjava3.core.Observable
import javax.inject.Inject

class PostApiServiceImpl @Inject constructor(private val postApiService: PostApiService): PostapiServiceHelper {
    override  fun getPosts(): Observable<List<PostsResponseItem>> {
        return postApiService.getPosts()
    }
}