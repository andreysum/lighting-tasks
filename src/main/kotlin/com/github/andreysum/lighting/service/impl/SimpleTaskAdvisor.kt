package com.github.andreysum.lighting.service.impl

import com.github.andreysum.lighting.db.dao.TaskRepo
import com.github.andreysum.lighting.db.schema.TaskEntity
import com.github.andreysum.lighting.service.TaskAdvisor
import org.springframework.stereotype.Service

@Service
class SimpleTaskAdvisor(var taskRepo: TaskRepo) : TaskAdvisor {
    override fun list(): MutableList<TaskEntity> {
        return taskRepo.findAll()
    }
}