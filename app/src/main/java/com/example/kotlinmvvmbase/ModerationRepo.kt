package com.example.kotlinmvvmbase

import com.example.kotlinmvvmbase.core.base.repository.BaseRepository
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Job

class ModerationRepo(vmJob: Job, dispatcher: CoroutineDispatcher) : BaseRepository(vmJob, dispatcher) {
}