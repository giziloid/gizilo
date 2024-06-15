package com.aplikasi.gizilo.data.response

import com.google.gson.annotations.SerializedName

data class PostProductResponse(
	@field:SerializedName("error")
	val error: String? = null
)
