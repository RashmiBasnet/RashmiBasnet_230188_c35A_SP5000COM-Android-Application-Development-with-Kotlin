package com.example.outfitternepal.viewmodel

import com.example.outfitternepal.model.UserModel
import com.example.outfitternepal.repository.UserRepository
import com.google.firebase.auth.FirebaseUser

class UserViewModel (val repo : UserRepository) {

    fun login(email : String, password : String, callback:(Boolean, String) -> Unit){
        repo.login(email,password,callback)
    }

    fun signUp(email : String, password : String, callback: (Boolean, String, String) -> Unit){
        repo.signUp(email,password,callback)
    }

    fun addUserToDatabase(userId : String, userModel: UserModel, callback : (Boolean, String) -> Unit){
        repo.addUserToDatabase(userId,userModel,callback)
    }

    fun forgetPassword(email : String, callback: (Boolean, String) -> Unit){
        repo.forgetPassword(email,callback)
    }

    fun getCurrentUser() : FirebaseUser?{
        return repo.getCurrentUser()
    }

}
