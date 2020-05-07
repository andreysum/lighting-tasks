package com.github.andreysum.lighting.logic

import com.github.andreysum.lighting.db.schema.TaskEntity

interface TaskAdvisor {
    fun list(): MutableList<TaskEntity>
}