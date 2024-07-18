package com.example.testnote.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.testnote.data.ArticleRepository
import com.example.testnote.data.MockArticleRepository

class ArticleViewModelFactory(
    private val articleRepository: MockArticleRepository,
    private val repository: ArticleRepository
) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(ArticleViewModel::class.java)) {
            return ArticleViewModel(articleRepository, repository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}