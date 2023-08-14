package com.techfortyone.fetchlistcompose.data.remote

import com.techfortyone.fetchlistcompose.data.model.PostsResponse
import com.techfortyone.fetchlistcompose.data.model.PostsResponseItem
import io.reactivex.rxjava3.core.Observable
import retrofit2.http.GET

interface PostApiService {

    @GET("posts")
     fun getPosts() : Observable<List<PostsResponseItem>>

}