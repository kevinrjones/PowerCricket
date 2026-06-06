package com.knowledgespike.powercricket

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform