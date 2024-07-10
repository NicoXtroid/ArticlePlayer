package com.example.testnote.ui.article

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.testnote.data.model.RemoteArticle
import com.example.testnote.ui.navigation.NavGo

@Composable
fun ArticleScreen(article: RemoteArticle, navGo: NavGo) {
    Scaffold { it ->
        Column(
            modifier = Modifier
                .padding(horizontal = 22.dp)
                .padding(top = 42.dp)
                .verticalScroll(rememberScrollState())
        ) {
            Text(text = article.title, fontSize = 28.sp, fontWeight = FontWeight.W600, fontStyle = FontStyle.Italic)
            Text(text = article.date, fontSize = 14.sp, fontWeight = FontWeight.W400)
            Text(text = article.author, fontSize = 14.sp, fontWeight = FontWeight.W600)
            Text(
                text = article.note,
                modifier = Modifier.padding(top = 10.dp),
                fontSize = 16.sp,
                fontWeight = FontWeight.W400
            )
            Box(modifier = Modifier
                .height(200.dp)
                .padding(top = 15.dp) ) {
               VideoComponent(article.videoUrl)
            }
        }
    }
}