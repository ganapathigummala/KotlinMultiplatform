package com.example.multimodule.coreNetwork.model

import kotlinx.serialization.Serializable

@Serializable
data class ApodResponse(
    val copyright: String? = null,
    val date: String,
    val explanation: String,
    val hdurl: String? = null,
    val media_type: String,
    val service_version: String,
    val title: String,
    val url: String,
    val thumbnail_url: String? = null
)