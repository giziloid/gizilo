package com.aplikasi.gizilo.data.response

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

data class GetProductResponse(

	@field:SerializedName("GetProductResponse")
	val getProductResponse: List<GetProductResponseItem?>? = null
)

@Parcelize
data class GetProductResponseItem(

	@field:SerializedName("images")
	val images: String? = null,

	@field:SerializedName("potassium")
	val potassium: Double? = null,

	@field:SerializedName("weight")
	val weight: Double? = null,

	@field:SerializedName("created_at")
	val createdAt: String? = null,

	@field:SerializedName("calories")
	val calories: Double? = null,

	@field:SerializedName("carbohydrate")
	val carbohydrate: Double? = null,

	@field:SerializedName("sodium")
	val sodium: Double? = null,

	@field:SerializedName("proteins")
	val proteins: Double? = null,

	@field:SerializedName("name")
	val name: String? = null,

	@field:SerializedName("fat")
	val fat: Double? = null,

	@field:SerializedName("id")
	val id: Int? = null,

	@field:SerializedName("sugar")
	val sugar: Double? = null,

	@field:SerializedName("last_modified")
	val lastModified: String? = null
):Parcelable
