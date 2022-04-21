package com.github.andreysum.lighting.service.impl

import com.github.andreysum.lighting.db.dao.TaskRepo
import com.github.andreysum.lighting.db.schema.TaskEntity
import com.github.andreysum.lighting.service.TaskAdvisor
import org.springframework.stereotype.Service

@Service
class TaskAdvisorImpl(private val taskRepo: TaskRepo) : TaskAdvisor {
    override fun list(needle: String): List<TaskEntity> {
        return taskRepo.findAllByTitleContainingIgnoreCase(needle)
    }

    override fun listAll(): List<TaskEntity> {
        return taskRepo.findAll()
    }

    override fun createTask(title: String) {
        val task = TaskEntity(title)
        taskRepo.save(task)
    }

    override fun startTask(title: String?) {
        TODO("Not yet implemented")
    }
}