package com.github.andreysum.lighting.service

import com.github.andreysum.lighting.db.schema.TaskEntity

interface TaskAdvisor {
    fun list(needle: String): List<TaskEntity>
    fun listAll(): List<TaskEntity>
    fun createTask(title: String)
    fun startTask(title: String?)
}