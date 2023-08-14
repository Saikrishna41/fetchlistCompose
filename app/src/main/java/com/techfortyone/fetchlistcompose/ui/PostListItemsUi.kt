package com.techfortyone.fetchlistcompose.ui

import android.graphics.Paint.Style
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.techfortyone.fetchlistcompose.data.model.PostsResponseItem

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun PostListItems(posts: List<PostsResponseItem>) {
    LazyColumn {
        itemsIndexed(posts) { index, post ->
            if (index == 0 || post.userId != posts[index - 1].userId) {
                    SectionHeader2(userId = post.userId)
            }
            PostItemCard(postItem = post)
        }

    }
}


@Composable
fun SectionHeader2(userId: Int) {
    Text(
        text = "$userId",
        style = TextStyle(fontWeight = FontWeight.Bold),
        modifier = Modifier
            .fillMaxWidth()
            .background(Color.Yellow)
            .padding(12.dp)
    )
}

@Composable
fun PostItemCard(postItem: PostsResponseItem) {
    Text(text = "user id --->  ${postItem.userId}")
    Spacer(modifier = Modifier.height(8.dp))
    Text(text = "title --->  ${postItem.title}")
    Spacer(modifier = Modifier.height(8.dp))
    Text(text = "body --->  ${postItem.body}")
}
