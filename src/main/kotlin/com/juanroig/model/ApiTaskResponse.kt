package com.juanroig.model

import kotlinx.serialization.Serializable

@Serializable
data class ApiTaskResponse<T>(
    val success: Boolean,
    val message: String? = null,
    val data: T?,
    val lastUpdated: Long? = null
)