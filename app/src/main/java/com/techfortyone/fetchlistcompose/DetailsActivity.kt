package com.techfortyone.fetchlistcompose

import android.os.Build
import android.os.Bundle
import android.os.PersistableBundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.Composable
import com.techfortyone.fetchlistcompose.data.model.PostsResponse
import com.techfortyone.fetchlistcompose.data.model.PostsResponseItem

class DetailsActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val selectedPost =
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
                    intent.getParcelableExtra("", PostsResponseItem::class.java)
                } else {
                    intent.getParcelableExtra<PostsResponseItem>("")

                }
            ShowDetails(selectedPostItem = selectedPost)

        }
    }

    @Composable
    fun ShowDetails(selectedPostItem: PostsResponseItem?) {
        selectedPostItem?.let {
            Log.d("TAGS", "detail activity selected post details ${it.toString()}")
        }
    }
}