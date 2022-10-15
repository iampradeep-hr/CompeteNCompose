package com.pradeep.taskmanager

import android.annotation.SuppressLint
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.remote.FirestoreChannel

@SuppressLint("StaticFieldLeak")
object Firebase {

    val db=FirebaseFirestore.getInstance()
    val auth=FirebaseAuth.getInstance()
}