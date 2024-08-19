package org.timemates.rsp.codegen.generators.types

import com.squareup.wire.schema.Field
import com.squareup.wire.schema.ProtoType

internal object TypeDefaultValueGenerator {
    fun generateTypeDefault(field: Field): String {
        val type = field.type!!

        if(field.isRepeated)
            return "emptyList()"

        return when (type) {
            ProtoType.INT32,
            ProtoType.INT64,
            ProtoType.DURATION,
            ProtoType.FIXED32,
            ProtoType.FIXED64,
            ProtoType.SFIXED32,
            ProtoType.SFIXED64,
            ProtoType.SINT32,
            ProtoType.SINT64 -> "0"

            ProtoType.UINT32, ProtoType.UINT64 -> "0u"
            ProtoType.STRING -> "\"\""
            ProtoType.BOOL -> "false"
            ProtoType.BYTES -> "byteArrayOf()"
            ProtoType.DOUBLE -> "0.0"
            ProtoType.FLOAT -> "0.0f"
            else -> "null"
        }
    }

}