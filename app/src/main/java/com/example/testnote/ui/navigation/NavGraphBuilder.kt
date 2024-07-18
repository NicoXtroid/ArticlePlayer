package com.example.testnote.ui.navigation

import android.annotation.SuppressLint
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import com.example.testnote.data.model.RemoteArticulo
import com.example.testnote.presentation.ArticleViewModel
import com.example.testnote.ui.article.ArticleScreen
import com.example.testnote.ui.articlelist.ArticleListScreen

@SuppressLint("ComposableDestinationInComposeScope")
fun NavGraphBuilder.articlesListScreen(
    viewModel: ArticleViewModel,
    navGo: NavGo
) = composable(route = NavRoutes.ArticlesListScreen.routes) {
    ArticleListScreen(
        viewModel = viewModel,
        navGo = navGo
    )
}

@SuppressLint("ComposableDestinationInComposeScope")
fun NavGraphBuilder.articleScreen(
    navController: NavHostController,
    navGo: NavGo
) = composable(route = NavRoutes.ArticlesScreen.routes) {

    val title = navController.previousBackStackEntry?.savedStateHandle?.get<String>("title").orEmpty()
    val description = navController.previousBackStackEntry?.savedStateHandle?.get<String>("description").orEmpty()
    val author = navController.previousBackStackEntry?.savedStateHandle?.get<String>("author").orEmpty()
    val imageUrl = navController.previousBackStackEntry?.savedStateHandle?.get<String>("imageUrl").orEmpty()
    val videoUrl = navController.previousBackStackEntry?.savedStateHandle?.get<String>("videoUrl").orEmpty()
    val selectedArticle = RemoteArticulo(title =  title, description = description, author = author, imageUrl = imageUrl, videoUrl = videoUrl)

    ArticleScreen(
        article = selectedArticle,
        navGo = navGo
    )
}