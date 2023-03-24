package com.example.tiktaktoeapi.models

import com.example.tiktaktoeapi.listeners.UserListener
import org.springframework.format.annotation.DateTimeFormat
import java.time.Instant
import java.util.*
import javax.persistence.*
import javax.validation.constraints.Pattern
import javax.validation.constraints.Size


@Entity
@Table(name = "users")
@EntityListeners(UserListener::class)
class User (
    @Column(unique = true)
    @Size(min=2)
    var username: String = "",
    @Size(min = 11)
    @Pattern(regexp = "^\\(?(\\d{3})\\)?[- ]?(\\d{3}) [- ]?(\\d{4})$")
    var phonenumber: String = "",
    @Size(min = 6, max = 60)
    var password: String = "",
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    var id:Long = 0,
    @DateTimeFormat
    var createdAt: Date = Date.from(Instant.now())
)