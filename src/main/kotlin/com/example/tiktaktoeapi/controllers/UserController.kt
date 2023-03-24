package com.example.tiktaktoeapi.controllers

import com.example.tiktaktoeapi.models.User
import com.example.tiktaktoeapi.repositories.UserRepository
import com.example.tiktaktoeapi.services.UserServiceImpl
import org.springframework.http.HttpRequest
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Controller
import org.springframework.validation.annotation.Validated
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import javax.servlet.http.HttpServletRequest

@RestController
@RequestMapping("/users")
class UserController(val userServiceImpl: UserServiceImpl, val userRepository: UserRepository) {

    @PostMapping
    @RequestMapping("/registrations")
    fun create(@Validated @RequestBody userDetails: User): ResponseEntity<User>{
        val user = userServiceImpl.attemptRegistration(userDetails)
        return ResponseEntity.ok(user)
    }

    @GetMapping
    @RequestMapping("/{user_id}")
    fun getById(@PathVariable("user_id") userId: Long): ResponseEntity<User>{
        val user = userServiceImpl.retrieveUserData(userId)
        return ResponseEntity.ok(user)
    }

    @GetMapping
    fun index(request: HttpServletRequest): ResponseEntity<List<User>>{
        val user = userServiceImpl.retrieveUserData(request.userPrincipal.name)
        val users = user?.let { userServiceImpl.listUsers(it) }
        return ResponseEntity.ok(users)
    }


}