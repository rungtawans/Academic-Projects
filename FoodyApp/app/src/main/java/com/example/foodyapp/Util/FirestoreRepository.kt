package com.example.foodyapp.Util

import com.google.firebase.firestore.CollectionReference
import com.google.firebase.firestore.FirebaseFirestore

class FirestoreRepository {
    private val firestoreDB: FirebaseFirestore by lazy {
        FirebaseFirestore.getInstance()
    }

    fun getFoodStoreFromDatabase(): CollectionReference {
        var collectionReference = firestoreDB.collection("FoodStore")
        return collectionReference
    }
}