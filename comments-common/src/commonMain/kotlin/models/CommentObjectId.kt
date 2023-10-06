package com.crowdproj.comments.common.models

import kotlin.jvm.JvmInline

@JvmInline
value class CommentObjectId(val id: String) {
    fun asString() = id

    companion object {
        val NONE = CommentObjectId("")
    }
}