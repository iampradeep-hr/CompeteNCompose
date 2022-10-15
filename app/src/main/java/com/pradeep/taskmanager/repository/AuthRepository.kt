package com.pradeep.taskmanager.repository

import com.google.firebase.auth.FirebaseUser
import com.pradeep.taskmanager.Firebase
import kotlinx.coroutines.*

class AuthRepository {

    val auth=Firebase.auth
    val currentUser:FirebaseUser? = auth.currentUser

    fun isUserExist():Boolean = auth.currentUser!=null
    fun getUserId():String = auth.currentUser?.uid.orEmpty()

    suspend fun createUser(
        email:String,
        password:String,
        onComplete:(Boolean)->Unit,
    ){
        val job=GlobalScope.async {
            auth.createUserWithEmailAndPassword(email,password)
                .addOnCompleteListener {
                    if (it.isSuccessful){
                        onComplete.invoke(true)
                    }else{
                        onComplete.invoke(false)
                    }
                }
        }
        job.await()

    }

    suspend fun login(
        email:String,
        password:String,
        onComplete:(Boolean)->Unit,
    ){
        val job=GlobalScope.async {
            auth.signInWithEmailAndPassword(email,password)
                .addOnCompleteListener {
                    if (it.isSuccessful){
                        onComplete.invoke(true)
                    }else{
                        onComplete.invoke(false)
                    }
                }
        }
        job.await()

    }

}