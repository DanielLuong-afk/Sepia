package com.example.sepia.data

import android.content.Context
import android.net.Uri
import com.example.sepia.data.network.RetrofitInstance
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.MultipartBody
import okhttp3.RequestBody.Companion.asRequestBody
import okhttp3.RequestBody.Companion.toRequestBody
import java.io.File

class OcrRepository(private val context: Context) {

    private val api = RetrofitInstance.api

    //Don't do this in real project...
    private val API_KEY = "K88795900288957"

    suspend fun extractTextFromImage(imageUri: Uri): Result<String> {
        return try {
            val inputStream = context.contentResolver.openInputStream(imageUri)
            val tempFile = File.createTempFile("ocr_", ".jpg", context.cacheDir)
            tempFile.outputStream().use { inputStream?.copyTo(it) }

            val requestFile = tempFile.asRequestBody("image/jpeg".toMediaTypeOrNull())
            val imagePart = MultipartBody.Part.createFormData("file", tempFile.name, requestFile)
            val apiKeyBody = API_KEY.toRequestBody("text/plain".toMediaTypeOrNull())
            val languageBody = "eng".toRequestBody("text/plain".toMediaTypeOrNull())

            val response = api.extractText(apiKeyBody, languageBody, imagePart)

            if (response.isErrored) {
                Result.failure(Exception(response.errorMessage ?: "OCR failed"))
            } else {
                val text = response.parsedResults?.firstOrNull()?.parsedText ?: "No text found"
                Result.success(text)
            }
        } catch (e: Exception) {
            Result.failure(e)
        }
    }
}