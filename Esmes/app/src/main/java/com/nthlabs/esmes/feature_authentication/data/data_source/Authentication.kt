package com.nthlabs.esmes.feature_authentication.data.data_source

import com.google.firebase.auth.FirebaseUser
import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.tasks.await
import kotlinx.coroutines.withContext
import androidx.lifecycle.ViewModel

class Authentication: ViewModel() {
    val currentUser: FirebaseUser? = Firebase.auth.currentUser

    fun hasUser():Boolean = Firebase.auth.currentUser != null

    fun getUserId():String = Firebase.auth.currentUser?.uid.orEmpty()

    // Creating new user
    suspend fun createUser(
        email: String,
        password: String,
        onComplete:(Boolean) -> Unit
    ): AuthResult = withContext(Dispatchers.IO) {
        Firebase.auth
            .createUserWithEmailAndPassword(email, password)
            .addOnCompleteListener {
                if (it.isSuccessful) {
                    onComplete.invoke(true)
                } else {
                    onComplete.invoke(false)
                }
            }.await()
    }

    // Login existing user
    suspend fun login(
        email: String,
        password: String,
        onComplete:(Boolean) -> Unit
    ): AuthResult = withContext(Dispatchers.IO) {
        Firebase.auth
            .signInWithEmailAndPassword(email, password)
            .addOnCompleteListener {
                if (it.isSuccessful) {
                    onComplete.invoke(true)
                } else {
                    onComplete.invoke(false)
                }
            }.await()
    }
}