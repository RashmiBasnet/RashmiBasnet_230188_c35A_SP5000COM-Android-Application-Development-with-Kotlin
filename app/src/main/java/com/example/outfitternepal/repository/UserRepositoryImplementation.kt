package com.example.outfitternepal.repository

import android.util.Log
import com.example.outfitternepal.model.UserModel
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

class UserRepositoryImplementation : UserRepository{

    var auth: FirebaseAuth = FirebaseAuth.getInstance()

    var database: FirebaseDatabase = FirebaseDatabase.getInstance()
    var ref: DatabaseReference = database.reference.child("users")

    override fun login(email: String, password: String, callback: (Boolean, String) -> Unit) {
        auth.signInWithEmailAndPassword(email, password).addOnCompleteListener{
            if(it.isSuccessful){
                callback(true,"Login Successful")
            } else{
                callback(false, it.exception?.message.toString())
            }
        }
    }

    override fun signUp(
        email: String,
        password: String,
        callback: (Boolean, String, String) -> Unit
    ) {
        auth.createUserWithEmailAndPassword(email, password).addOnCompleteListener{
            if(it.isSuccessful){
                callback(true,"Login Successful", auth.currentUser?.uid.toString())
            } else{
                callback(false, it.exception?.message.toString(),"")
            }
        }
    }

    override fun addUserToDatabase(
        userId: String,
        userModel: UserModel,
        callback: (Boolean, String) -> Unit
    ) {
        ref.child(userId).setValue(userModel).addOnCompleteListener{
            if(it.isSuccessful){
                callback(true, "Registration Successful")
            } else{
                callback(false, it.exception?.message.toString())
            }
        }
    }

    override fun forgetPassword(email: String, callback: (Boolean, String) -> Unit) {
        ref.orderByChild("email").equalTo(email).get()
            .addOnSuccessListener { snapshot ->
                if (snapshot.exists()) {
                    auth.sendPasswordResetEmail(email).addOnCompleteListener {
                        callback(it.isSuccessful, it.exception?.message ?: "Password reset email sent!")
                    }
                } else {
                    callback(false, "Email not found in database!")
                }
            }
            .addOnFailureListener { e ->
                Log.e("FirebaseError", "Error checking email: ${e.message}", e)
                callback(false, "Error checking email: ${e.message}")
            }
    }

    override fun getCurrentUser(): FirebaseUser? {
        return auth.currentUser
    }




}







