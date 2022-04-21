package com.github.andreysum.lighting.service

import com.github.andreysum.lighting.db.schema.TaskEntity

interface TimeManager {
    fun startTask(task: TaskEntity)
}
