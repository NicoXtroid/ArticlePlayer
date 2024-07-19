package com.example.testnote.ui.article

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.media3.common.util.UnstableApi
import com.example.testnote.data.model.RemoteArticulo
import com.example.testnote.ui.article.components.ImageViewer
import com.example.testnote.ui.article.components.VideoComponent
import com.example.testnote.ui.article.components.VideoPlayerExo
import com.example.testnote.ui.article.components.YouTubePlayer
import com.example.testnote.ui.navigation.NavGo

@androidx.annotation.OptIn(UnstableApi::class)
@OptIn(ExperimentalAnimationApi::class)
@Composable
fun ArticleScreen(article: RemoteArticulo, navGo: NavGo) {
    Scaffold { it ->
        Column(
            modifier = Modifier
                .padding(horizontal = 22.dp)
                .padding(top = 42.dp, bottom = 0.dp)
                .verticalScroll(rememberScrollState())
        ) {
            Text(
                text = article.title,
                fontSize = 28.sp,
                fontWeight = FontWeight.W600,
                fontStyle = FontStyle.Italic
            )
            Text(
                text = article.author,
                modifier = Modifier.padding(vertical = 10.dp),
                fontSize = 14.sp,
                fontWeight = FontWeight.W600
            )

            ImageViewer(article.imageUrl)

            Text(
                text = article.description,
                modifier = Modifier.padding(top = 10.dp),
                fontSize = 16.sp,
                fontWeight = FontWeight.W400
            )
            Box(
                modifier = Modifier
                    .height(200.dp)
                    .padding(top = 15.dp)
            ) {
                //YouTubePlayer(youtubeVideoId = "BH8kbhph8IM", lifecycleOwner = LocalLifecycleOwner.current)
                  VideoPlayerExo(videoUrl = "https://commondatastorage.googleapis.com/gtv-videos-bucket/sample/BigBuckBunny.mp4")  
            //VideoComponent(article.videoUrl)
                //VideoPlayer(article.videoUrl)
                //MediaPlayer("http://commondatastorage.googleapis.com/gtv-videos-bucket/big_buck_bunny_1080p.mp4")
            }
            Spacer(modifier = Modifier.height(100.dp))
        }
    }
}