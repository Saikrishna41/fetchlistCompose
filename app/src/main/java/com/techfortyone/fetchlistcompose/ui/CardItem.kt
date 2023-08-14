package com.techfortyone.fetchlistcompose.ui

import android.content.Context
import android.content.Intent
import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.techfortyone.fetchlistcompose.DetailsActivity
import com.techfortyone.fetchlistcompose.data.model.PostsResponseItem
import dagger.hilt.android.qualifiers.ApplicationContext

@Composable
fun PostLists(posts: List<PostsResponseItem>) {
    val context = LocalContext.current
    val onItemClick : (PostsResponseItem) -> Unit = {post ->
        Log.d("TAGS", "item clicked is ${post.title}")
        val intent = Intent(context, DetailsActivity::class.java)
        intent.putExtra("selectedPost", post)
        context.startActivity(intent)
    }
    LazyColumn {
        itemsIndexed(posts) { index, post ->
            /**
             * index == 0: This part of the condition checks if the current item is the first item in the list.
             * If it is, a section header is displayed because there is no previous item to compare the userId with.
             *
             * post.userId != posts[index - 1].userId: This part of the condition checks if the userId of the current item
             * is different from the userId of the previous item. If the condition is true, it means that the userId
             * has changed, and a section header should be displayed to separate items with different userId values.
             */

            if (index == 0 || post.userId != posts[index - 1].userId) {
                SectionHeader(userId = post.userId)
            }
            PostItem(post = post, onItemClick)
        }
    }
}

@Composable
fun SectionHeader(userId: Int) {
    Text(
        text = "User Id : $userId",
        style = TextStyle(fontWeight = FontWeight.Bold),
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
            .background(Color.Red)
    )
}

@Composable
fun PostItem(post: PostsResponseItem, onItemClick : (PostsResponseItem) -> Unit) {
    Column(modifier = Modifier
        .fillMaxWidth()
        .clickable { onItemClick(post) }
        .padding(16.dp)) {
        Text(text = "Title: ${post.title}", fontWeight = FontWeight.Bold)
        Spacer(modifier = Modifier.height(8.dp))
        Text(text = "Body: ${post.body}")
    }
}