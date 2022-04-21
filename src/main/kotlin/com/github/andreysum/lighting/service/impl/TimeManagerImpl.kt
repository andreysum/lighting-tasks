package com.github.andreysum.lighting.service.impl

import com.github.andreysum.lighting.db.schema.TaskEntity
import com.github.andreysum.lighting.service.TimeManager
import org.springframework.stereotype.Service

@Service
class TimeManagerImpl : TimeManager {
    override fun startTask(task: TaskEntity) {
        println("Time tracking started for task '${task.title}'.")
    }
}