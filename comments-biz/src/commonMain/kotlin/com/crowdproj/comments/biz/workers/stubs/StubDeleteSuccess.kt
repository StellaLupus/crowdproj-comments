package com.crowdproj.comments.biz.workers.stubs

import com.crowdproj.comments.common.CommentContext
import com.crowdproj.comments.common.models.CommentId
import com.crowdproj.comments.common.models.CommentState
import com.crowdproj.comments.common.stubs.CommentStubs
import com.crowdproj.comments.stubs.CommentsStub
import com.crowdproj.kotlin.cor.handlers.CorChainDsl
import com.crowdproj.kotlin.cor.handlers.worker

fun CorChainDsl<CommentContext>.stubDeleteSuccess(title: String) = worker {
    this.title = title
    on { state == CommentState.RUNNING && stubCase == CommentStubs.SUCCESS }
    handle {
        val stub = CommentsStub.prepareResult {
            state = CommentState.FINISHING
            commentRequest.id.takeIf { it != CommentId.NONE }?.also { this.id = it }
        }
        commentResponse = stub
    }
}