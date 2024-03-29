package com.example.tiktaktoeapi.filters

import com.example.tiktaktoeapi.services.TokenAuthenticationService
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.web.filter.GenericFilterBean
import java.io.IOException
import javax.servlet.FilterChain
import javax.servlet.ServletException
import javax.servlet.ServletRequest
import javax.servlet.ServletResponse
import javax.servlet.http.HttpServletRequest

class JWTAuthenticationFilter: GenericFilterBean() {

    @Throws(IOException::class, ServletException::class)
    override fun doFilter(req: ServletRequest, res: ServletResponse, fChain: FilterChain) {
        val authentication = TokenAuthenticationService.getAuthentication(req as HttpServletRequest)
        SecurityContextHolder.getContext().authentication = authentication
        fChain.doFilter(req, res)

    }

}