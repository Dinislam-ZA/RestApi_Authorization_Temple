package com.example.tiktaktoeapi.services

import com.example.tiktaktoeapi.models.User

interface UserService {

    fun attemptRegistration(user:User):User
    fun listUsers(currentUser:User):List<User>
    fun retrieveUserData(id:Long):User?
    fun retrieveUserData(username:String):User?
    fun isExist(username: String):Boolean
}