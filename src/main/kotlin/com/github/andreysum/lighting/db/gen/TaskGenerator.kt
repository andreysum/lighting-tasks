package com.github.andreysum.lighting.db.gen

import com.github.andreysum.lighting.db.dao.TaskRepo
import com.github.andreysum.lighting.db.schema.TaskEntity
import org.springframework.stereotype.Service

@Service
class TaskGenerator (private val repo: TaskRepo): Generating {
    override fun generate() {
        if (repo.count() == 0L) {
            repo.save(TaskEntity("Управление"))
            repo.save(TaskEntity("Помощь аналитикам"))
        }
    }
}