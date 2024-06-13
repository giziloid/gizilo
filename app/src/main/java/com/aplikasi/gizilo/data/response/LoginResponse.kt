package com.aplikasi.gizilo.data.response

import com.google.gson.annotations.SerializedName

data class LoginResponse(

	@field:SerializedName("error")
	val error: String? = null
)
