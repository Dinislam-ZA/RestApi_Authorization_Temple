package com.example.tiktaktoeapi.repositories

import com.example.tiktaktoeapi.models.User
import org.springframework.data.repository.CrudRepository


interface UserRepository: CrudRepository<User,Long> {
    fun findByUsername(name:String): User?
}