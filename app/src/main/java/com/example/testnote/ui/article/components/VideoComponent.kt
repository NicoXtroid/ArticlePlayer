package com.example.testnote.ui.article.components

import android.annotation.SuppressLint
import android.net.Uri
import android.util.Log
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.lifecycle.LifecycleOwner
import androidx.media3.common.util.UnstableApi
import com.google.android.exoplayer2.ExoPlayer
import com.google.android.exoplayer2.MediaItem
import com.google.android.exoplayer2.ui.PlayerView
import com.google.android.exoplayer2.ui.StyledPlayerView
import com.google.firebase.Firebase
import com.google.firebase.storage.storage
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.YouTubePlayer
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.listeners.AbstractYouTubePlayerListener
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.views.YouTubePlayerView

@Composable
fun VideoComponent(videoUrl: String) {

    AndroidView(factory = { context ->
        WebView(context).apply {
            settings.javaScriptEnabled = true
            settings.loadWithOverviewMode = true
            settings.useWideViewPort = true
            webViewClient = WebViewClient()
            loadUrl(videoUrl)
        }
    })
}

@SuppressLint("OpaqueUnitKey")
@Composable
fun VideoPlayer(videoUrl: String){
    val context = LocalContext.current
    val exoPlayer = remember {
        ExoPlayer.Builder(context).build().apply {
            val mediaItem = MediaItem.fromUri(Uri.parse(videoUrl))
            setMediaItem(mediaItem)
            prepare()
        }
    }

    Box(modifier = Modifier.fillMaxSize(0.5f)) {
        DisposableEffect(
            AndroidView(factory = {
                StyledPlayerView(it).apply {
                    player = exoPlayer
                }
            })
        ) {
            onDispose { exoPlayer.release() }
        }
    }
}

@OptIn(UnstableApi::class)
@SuppressLint("OpaqueUnitKey")
@ExperimentalAnimationApi
@Composable
fun MediaPlayer(videoUrl: String) {

    val context = LocalContext.current
    val exoPlayer = ExoPlayer.Builder(context).build()

    val mediaSource = remember(videoUrl) {
        MediaItem.fromUri(videoUrl)
    }

    LaunchedEffect(mediaSource) {
        exoPlayer.setMediaItem(mediaSource)
        exoPlayer.prepare()
    }

    DisposableEffect(Unit) {
        onDispose {
            exoPlayer.release()
        }
    }

    AndroidView(
        factory = { ctx ->
            PlayerView(ctx).apply {
                player = exoPlayer
            }
        },
        modifier = Modifier
            .fillMaxWidth()
            .height(200.dp)
    )
}

@Composable
fun YouTubePlayer(
    youtubeVideoId: String,
    lifecycleOwner: LifecycleOwner
){
    AndroidView(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
            .clip(RoundedCornerShape(16.dp)),
        factory = { context->
            YouTubePlayerView(context = context).apply {
                lifecycleOwner.lifecycle.addObserver(this)

                addYouTubePlayerListener(object: AbstractYouTubePlayerListener(){
                    override fun onReady(youTubePlayer: YouTubePlayer) {
                        youTubePlayer.loadVideo(youtubeVideoId, 0f)
                    }
                })
            }
        }
    )
}

@Composable
fun VideoPlayerExo(
    videoUrl: String
) {
    val context = LocalContext.current
    val player = ExoPlayer.Builder(context).build().apply {
        setMediaItem(MediaItem.fromUri(videoUrl))
    }
    val playerView = PlayerView(context)
    val playWhenReady by rememberSaveable {
        mutableStateOf(true)
    }

    playerView.player = player

    LaunchedEffect(player) {
        player.prepare()
        player.playWhenReady = playWhenReady
    }

    AndroidView(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
            .clip(RoundedCornerShape(16.dp)),
        factory = {
            playerView
        })

}

@Composable
fun VideoPlayerFromFirebaseStorage() {
    val context = LocalContext.current
    var videoUrl by remember { mutableStateOf("") }


    LaunchedEffect(Unit) {
        val storage = Firebase.storage
        val storageRef = storage.reference.child("https://firebasestorage.googleapis.com/v0/b/article-player.appspot.com/o/Duel%20of%20Fates%20-%20Star%20Wars.mp4?alt=media&token=020ded3a-653e-447c-8650-f607c2a7160a")
        //val storageRef = storage.reference.child("gs://article-player.appspot.com/Duel of Fates - Star Wars.mp4")
        storageRef.downloadUrl.addOnSuccessListener { uri ->
            videoUrl = uri.toString()
        }.addOnFailureListener {
            Log.d("ERROR", "URL Not Valid")
        }
    }

    if (videoUrl.isNotEmpty()) {
        val exoPlayer = remember(context) {
            ExoPlayer.Builder(context).build().apply {
                val mediaItem = MediaItem.fromUri(videoUrl)
                setMediaItem(mediaItem)
                prepare()
            }
        }

        DisposableEffect(
            AndroidView(
                factory = {
                    PlayerView(context).apply {
                        player = exoPlayer
                    }
                },
                modifier = Modifier
                    .fillMaxSize()
                    .padding(16.dp)
            )
        ) {
            onDispose { exoPlayer.release() }
        }
    }
}