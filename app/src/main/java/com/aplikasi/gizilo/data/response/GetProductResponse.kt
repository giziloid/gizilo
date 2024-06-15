package com.aplikasi.gizilo.data.response

import com.google.gson.annotations.SerializedName

data class GetProductResponse(

	@field:SerializedName("GetProductResponse")
	val getProductResponse: List<GetProductResponseItem?>? = null
)

data class GetProductResponseItem(

	@field:SerializedName("images")
	val images: String? = null,

	@field:SerializedName("potassium")
	val potassium: Double? = null,

	@field:SerializedName("weight")
	val weight: Int? = null,

	@field:SerializedName("created_at")
	val createdAt: String? = null,

	@field:SerializedName("calories")
	val calories: Int? = null,

	@field:SerializedName("carbohydrate")
	val carbohydrate: Int? = null,

	@field:SerializedName("sodium")
	val sodium: Int? = null,

	@field:SerializedName("proteins")
	val proteins: Int? = null,

	@field:SerializedName("name")
	val name: String? = null,

	@field:SerializedName("fat")
	val fat: Int? = null,

	@field:SerializedName("id")
	val id: Int? = null,

	@field:SerializedName("sugar")
	val sugar: Int? = null,

	@field:SerializedName("last_modified")
	val lastModified: String? = null
)
