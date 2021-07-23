package dev.abhishekbansal.moshilistinheritance

import com.squareup.moshi.*
import org.junit.Test
import java.lang.reflect.Type

class MoshiTest {
    @Test
    fun testGenerics() {
        val childOptions = listOf(ImageSelectionOption(value = "dummy", id = "dummy", image = "dummy"))
        val childResponse = QuestionResponse<List<OptionV2>>(answer = childOptions, id = "child_qid")

        val parentOptions = listOf(childResponse)
        val parentResponse = QuestionResponse<Any>(answer = parentOptions, id = "parent_qid")

        val moshi = Moshi.Builder().add(OptionV2MoshiAdapter.OptionAdapterFactory).build()
        val listType = Types.newParameterizedType(List::class.java, OptionV2::class.java)
        val type = Types.newParameterizedType(QuestionResponse::class.java, listType)
        moshi.adapter<QuestionResponse<Any>>(type).toJson(parentResponse)
    }
}

class OptionV2MoshiAdapter<T>(val elementAdapter: JsonAdapter<T>) : JsonAdapter<T>() {

    override fun fromJson(reader: JsonReader): T? {
        return elementAdapter.fromJson(reader)
    }

    override fun toJson(writer: JsonWriter, value: T?) {
        elementAdapter.toJson(writer, value)
    }

    object OptionAdapterFactory : Factory {

        override fun create(type: Type, annotations: MutableSet<out Annotation>, moshi: Moshi): JsonAdapter<*>? {
            val rawType = Types.getRawType(type)
            if (rawType != OptionV2::class.java) {
                return null
            }

            val elementType = Types.supertypeOf(rawType).upperBounds[0]
            // element type is java.lang.Object in above statement but this is somehow working.
            // not sure how
            val elementAdapter: JsonAdapter<Any> = moshi.adapter(elementType)

            return OptionV2MoshiAdapter(elementAdapter).nullSafe()
        }

    }
}