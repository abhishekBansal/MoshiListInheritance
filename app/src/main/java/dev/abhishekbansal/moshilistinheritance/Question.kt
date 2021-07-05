package dev.abhishekbansal.moshilistinheritance

import com.squareup.moshi.JsonClass
import java.util.*

sealed class QuestionV2(
    val type: String,
    val id: String,
    val options: List<OptionV2>?
) {
    open fun convertToV2IfNeeded() = this
}

@JsonClass(generateAdapter = true)
open class SelectionQuestion(
    type: String,
    id: String,
    options: List<SelectionOption>?,
) : QuestionV2(type, id, options)

@JsonClass(generateAdapter = true)
class BubbleQuestion(
    type: String,
    id: String,
    options: List<SelectionOption>?,

    val select: List<String>?
) : SelectionQuestion(type, id, options)