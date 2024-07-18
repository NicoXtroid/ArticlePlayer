package com.example.testnote

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Scaffold
import androidx.compose.ui.Modifier
import com.example.testnote.data.ArticleRepository
import com.example.testnote.data.MockArticleRepository
import com.example.testnote.presentation.ArticleViewModel
import com.example.testnote.presentation.ArticleViewModelFactory
import com.example.testnote.ui.navigation.NavController
import com.example.testnote.ui.theme.TestnoteTheme
import com.google.firebase.FirebaseApp

class MainActivity : ComponentActivity() {

    //private lateinit var viewModel: ArticleViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        FirebaseApp.initializeApp(this)
        enableEdgeToEdge()

        val articleRepository = MockArticleRepository(this)
        val repository = ArticleRepository()
        val viewModelFactory = ArticleViewModelFactory(articleRepository,repository)
        val viewModel: ArticleViewModel by viewModels { viewModelFactory }

        //viewModel = ViewModelProvider(this, ArticleViewModelFactory(articleRepository, repository))
        //    .get(ArticleViewModel::class.java)

        setContent {
            TestnoteTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    val x = innerPadding
                    NavController(articleListViewModel = viewModel)

                }
            }
        }
        viewModel.fetchArticles()
    }
}
