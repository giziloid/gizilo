package com.aplikasi.gizilo.data.response

import com.google.gson.annotations.SerializedName

data class GradeResponse(

	@field:SerializedName("result")
	val result: String? = null,

	@field:SerializedName("name")
	val name: String? = null,

	@field:SerializedName("id")
	val id: Int? = null
)
