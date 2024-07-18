package com.example.testnote.presentation

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.testnote.data.ArticleRepository
import com.example.testnote.data.MockArticleRepository
import com.example.testnote.data.model.RemoteArticleList
import com.example.testnote.data.model.RemoteArticulo
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class ArticleViewModel(
    private val articleRepository: MockArticleRepository,
    private val repository: ArticleRepository
) : ViewModel() {

    private val _articleList = MutableStateFlow<RemoteArticleList?>(null)
    val articleList: StateFlow<RemoteArticleList?> get() = _articleList

    private val _articles = MutableLiveData<List<RemoteArticulo>>()
    val articles: MutableLiveData<List<RemoteArticulo>> get() = _articles

    init {
        viewModelScope.launch {
            //_articleList.value = articleRepository.getArticleList()
            fetchArticles()
        }
    }

    fun fetchArticles() {
        viewModelScope.launch {
            _articles.value = repository.getArticles()
        }
    }
    /*
    fun fetchArticlesa() {
        repository.getArticles { articlesLista ->
            _articles.postValue(articlesLista)
        }
    }

     */
}