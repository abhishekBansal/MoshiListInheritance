package dev.abhishekbansal.moshilistinheritance

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

interface OptionV2 {
    val id: String
}

@JsonClass(generateAdapter = true)
open class SelectionOption(
    override val id: String,
    val value: String
) : OptionV2

@JsonClass(generateAdapter = true)
class ImageSelectionOption(
    override val id: String,

    value: String,

    @Json(name = "active_image")
    val image: String?,
): SelectionOption(id, value)