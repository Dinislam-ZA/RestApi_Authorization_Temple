package com.example.tiktaktoeapi.services



import com.example.tiktaktoeapi.repositories.UserRepository
import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.userdetails.User
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.core.userdetails.UsernameNotFoundException
import org.springframework.stereotype.Component
import kotlin.jvm.Throws

@Component
class AppUserDetailsService(val userRep : UserRepository): UserDetailsService {

    @Throws(UsernameNotFoundException::class)
    override fun loadUserByUsername(username: String): UserDetails {
        val user = userRep.findByUsername(username) ?:
        throw UsernameNotFoundException("Not exist")
        return User(user.username, user.password, ArrayList<GrantedAuthority>())
    }
}