package com.example.testnote.data

import android.util.Log
import com.example.testnote.data.model.RemoteArticle
import com.example.testnote.data.model.RemoteArticulo
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.tasks.await

class ArticleRepository {


    private val db = FirebaseFirestore.getInstance()
    private val articleCollection = db.collection("articleplayer")

    suspend fun getArticles(): List<RemoteArticulo> {
        return try {
            val snapshot = articleCollection.get().await()
            snapshot.toObjects(RemoteArticulo::class.java)
        } catch (e: Exception) {
            emptyList()
        }
    }



    private val databaseReference =  FirebaseDatabase.getInstance().getReference("articleplayer")

    fun getArticlesa(callback: (List<RemoteArticulo>) -> Unit){
        databaseReference.addValueEventListener(object: ValueEventListener{
            override fun onDataChange(snapshot: DataSnapshot) {
                val articleList = mutableListOf<RemoteArticulo>()
                for (articleSnapshot in snapshot.children){
                    val article = articleSnapshot.getValue(RemoteArticulo::class.java)
                    article?.let { articleList.add(it) }
                }
                callback(articleList)
            }

            override fun onCancelled(error: DatabaseError) {
                Log.d("TEST", "Fallo Fatal")
            }
        })
    }
}