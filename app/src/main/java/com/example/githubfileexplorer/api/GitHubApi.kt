package com.example.githubfileexplorer.api

import retrofit2.http.*
import com.example.githubfileexplorer.model.User
import com.example.githubfileexplorer.model.Repository
import com.example.githubfileexplorer.model.ContentItem
import com.example.githubfileexplorer.model.CommitBody

interface GitHubApi {

    @GET("user")
    suspend fun getUser(@Header("Authorization") auth: String): User

    @GET("user/repos")
    suspend fun listRepos(
        @Header("Authorization") auth: String,
        @Query("per_page") perPage: Int = 100,
        @Query("page") page: Int = 1
    ): List<Repository>

    // 修改 1：将 {*path} 改为 {path}，并设置 encoded = true
    @GET("repos/{owner}/{repo}/contents/{path}")
    suspend fun getContents(
        @Header("Authorization") auth: String,
        @Path("owner") owner: String,
        @Path("repo") repo: String,
        @Path(value = "path", encoded = true) path: String
    ): List<ContentItem>

    // 修改 2：获取单个文件内容同样修正
    @GET("repos/{owner}/{repo}/contents/{path}")
    suspend fun getFileContent(
        @Header("Authorization") auth: String,
        @Path("owner") owner: String,
        @Path("repo") repo: String,
        @Path(value = "path", encoded = true) path: String
    ): ContentItem

    // 修改 3：创建/更新文件
    @PUT("repos/{owner}/{repo}/contents/{path}")
    suspend fun createOrUpdateFile(
        @Header("Authorization") auth: String,
        @Path("owner") owner: String,
        @Path("repo") repo: String,
        @Path(value = "path", encoded = true) path: String,
        @Body body: CommitBody
    ): Any

    // 修改 4：删除文件
    @DELETE("repos/{owner}/{repo}/contents/{path}")
    suspend fun deleteFile(
        @Header("Authorization") auth: String,
        @Path("owner") owner: String,
        @Path("repo") repo: String,
        @Path(value = "path", encoded = true) path: String,
        @Query("message") message: String,
        @Query("sha") sha: String
    ): Any
}
