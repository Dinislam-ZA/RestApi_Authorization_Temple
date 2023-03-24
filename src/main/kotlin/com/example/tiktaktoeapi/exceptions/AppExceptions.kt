package com.example.tiktaktoeapi.exceptions


class UsernameUnavailableException(override val message:String): RuntimeException()

class InvalidUserIdException(override val message:String): RuntimeException()