package com.example.testnote.ui.navigation

import androidx.navigation.NavHostController
import com.example.testnote.data.model.RemoteArticle
import com.example.testnote.data.model.RemoteArticulo

class NavGo(navHostController:NavHostController) {

    val articleDetail: (RemoteArticulo) -> Unit = { article ->
        navHostController.currentBackStackEntry?.savedStateHandle?.set("title", article.title)
        navHostController.currentBackStackEntry?.savedStateHandle?.set("description", article.description)
        navHostController.currentBackStackEntry?.savedStateHandle?.set("author", article.author)
        navHostController.currentBackStackEntry?.savedStateHandle?.set("imageUrl", article.imageUrl)
        navHostController.currentBackStackEntry?.savedStateHandle?.set("videoUrl", article.videoUrl)
        navHostController.navigate(NavRoutes.ArticlesScreen.routes)
    }

    val popBackStack: () -> Unit = {
        navHostController.popBackStack()
    }
}