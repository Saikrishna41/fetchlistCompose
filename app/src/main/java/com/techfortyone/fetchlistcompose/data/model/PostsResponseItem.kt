package com.techfortyone.fetchlistcompose.data.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize


@Parcelize
data class PostsResponseItem(
    val body: String,
    val id: Int,
    val title: String,
    val userId: Int
) : Parcelable