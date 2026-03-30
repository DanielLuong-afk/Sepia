package com.example.sepia.data

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

//Convert json response from https://ocr.space/ocrapi to kotlin
@JsonClass(generateAdapter = true)
data class OcrResponse(
    @Json(name = "ParsedResults")
    val parsedResults: List<ParsedResult>?,

    @Json(name = "OCRExitCode")
    val exitCode: Int,

    @Json(name = "IsErroredOnProcessing")
    val isErrored: Boolean,

    @Json(name = "ErrorMessage")
    val errorMessage: String?
)

@JsonClass(generateAdapter = true)
data class ParsedResult(
    @Json(name = "ParsedText")
    val parsedText: String?,

    @Json(name = "ErrorMessage")
    val errorMessage: String?
)