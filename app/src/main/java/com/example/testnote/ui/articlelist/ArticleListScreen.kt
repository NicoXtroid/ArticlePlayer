package com.example.testnote.ui.articlelist

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.testnote.presentation.ArticleViewModel
import com.example.testnote.ui.articlelist.components.ArticleComponent
import com.example.testnote.ui.navigation.NavGo
import androidx.compose.runtime.livedata.observeAsState

@Composable
fun ArticleListScreen(viewModel: ArticleViewModel, navGo: NavGo) {

    val articles = viewModel.articles.observeAsState(initial = emptyList())
    Column(
        modifier = Modifier
            .verticalScroll(rememberScrollState())
            .fillMaxSize()
            .padding(top = 30.dp),
    ) {
        for (article in articles.value) {
            ArticleComponent(article = article, navGo = navGo)
        }
    }

    /*
    articles?.let { articlesList ->
        Column(modifier = Modifier.verticalScroll(rememberScrollState())
            .fillMaxSize()
            .padding(top = 30.dp), ) {
            for (article in articlesList) {
                ArticleComponent(article = article, navGo = navGo)
            }
        }
    }
     */

}