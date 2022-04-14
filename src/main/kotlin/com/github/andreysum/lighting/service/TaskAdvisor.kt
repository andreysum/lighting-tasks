package com.github.andreysum.lighting.service

import com.github.andreysum.lighting.db.schema.TaskEntity

interface TaskAdvisor {
    fun list(): MutableList<TaskEntity>
}