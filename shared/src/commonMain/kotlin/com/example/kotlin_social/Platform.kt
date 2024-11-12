package com.example.kotlin_social

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform