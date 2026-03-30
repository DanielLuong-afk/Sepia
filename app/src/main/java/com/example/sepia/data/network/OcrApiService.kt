package com.example.sepia.data.network

import com.example.sepia.data.OcrResponse
import okhttp3.MultipartBody
import okhttp3.RequestBody
import retrofit2.http.Multipart
import retrofit2.http.POST
import retrofit2.http.Part

interface OcrApiService {
    @Multipart
    @POST("parse/image")
    suspend fun extractText(
        @Part("apikey") apiKey: RequestBody,
        @Part("language") language: RequestBody,
        @Part image: MultipartBody.Part
    ): OcrResponse
}