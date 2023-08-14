package com.techfortyone.fetchlistcompose

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewmodel.compose.viewModel
import com.techfortyone.fetchlistcompose.data.model.PostsResponseItem
import com.techfortyone.fetchlistcompose.data.repository.PostRepository
import com.techfortyone.fetchlistcompose.ui.PostItem
import com.techfortyone.fetchlistcompose.ui.PostListItems
import com.techfortyone.fetchlistcompose.ui.PostLists
import com.techfortyone.fetchlistcompose.ui.theme.FetchListcomposeTheme
import com.techfortyone.fetchlistcompose.viewmodel.MainViewModel
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject


@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    @Inject
    lateinit var repository: PostRepository

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            FetchListcomposeTheme {
                val mViewModel = viewModel<MainViewModel>(
                    factory = object : ViewModelProvider.Factory {
                        override fun <T : ViewModel> create(modelClass: Class<T>): T {
                            return MainViewModel(repository) as T
                        }
                    }
                )
                val  postLists = mutableListOf<PostsResponseItem>()
                // A surface container using the 'background' color from the theme
                PostLists(posts = mViewModel.postDataState)

            }
        }
    }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    FetchListcomposeTheme {
        Greeting("Android")
    }
}