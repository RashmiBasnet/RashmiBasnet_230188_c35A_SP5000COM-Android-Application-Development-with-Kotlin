package com.example.outfitternepal.repository

import com.example.outfitternepal.model.UserModel
import com.google.firebase.auth.FirebaseUser

interface UserRepository {

    //{
    //"success" : true
    //"message" : "login Successful"
    //"statusCode" : 200
    // }

    fun login(email : String, password : String, callback:(Boolean, String) -> Unit)

    //{
    //"success" : true
    //"message" : "register Successful"
    //"userId" : "1234"
    // }

    fun signUp(email : String, password : String, callback: (Boolean, String, String) -> Unit)

    fun addUserToDatabase(userId : String, userModel: UserModel, callback : (Boolean,String) -> Unit)

    fun forgetPassword(email : String, callback: (Boolean, String) -> Unit)

    fun getCurrentUser() : FirebaseUser?
}