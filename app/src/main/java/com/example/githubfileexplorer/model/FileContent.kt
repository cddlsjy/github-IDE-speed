package com.example.githubfileexplorer.model

data class FileContent(
    val content: String,   // base64 encoded
    val encoding: String = "base64",
    val sha: String? = null
)