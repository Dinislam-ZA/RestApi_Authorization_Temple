package com.example.tiktaktoeapi.services

import com.example.tiktaktoeapi.exceptions.InvalidUserIdException
import com.example.tiktaktoeapi.models.User
import com.example.tiktaktoeapi.repositories.UserRepository
import org.springframework.stereotype.Service
import com.example.tiktaktoeapi.exceptions.UsernameUnavailableException

@Service
class UserServiceImpl(val repository: UserRepository): UserService {

    @Throws(UsernameUnavailableException::class)
    override fun attemptRegistration(userDetails: User): User {
        if(!isExist(userDetails.username)){
            val user = User();
            user.username = userDetails.username;
            user.phonenumber = userDetails.phonenumber;
            user.password = userDetails.password;
            repository.save(user);
            obscurePassword(user);
            return user
        }
        throw UsernameUnavailableException("The username ${userDetails.username} unavailable")
    }

    override fun listUsers(currentUser: User): List<User> {
        return repository.findAll().mapTo(ArrayList(), { it }).filter { it != currentUser }
    }

    @Throws(InvalidUserIdException::class)
    override fun retrieveUserData(id: Long): User? {
        val retUser = repository.findById(id)
        if(retUser.isPresent){
            val user = retUser.get()
            obscurePassword(user)
            return user
        }
        throw InvalidUserIdException("A user with a Id '$id' does not exist")
    }

    override fun retrieveUserData(username: String): User? {
        val user = repository.findByUsername(username)
        obscurePassword(user)
        return user
    }

    override fun isExist(username: String): Boolean {
        return repository.findByUsername(username) != null
    }

    private fun obscurePassword(user:User?){
        user?.password = "XXXXXX"
    }


}