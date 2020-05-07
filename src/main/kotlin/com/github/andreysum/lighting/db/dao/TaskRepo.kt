package com.github.andreysum.lighting.db.dao

import com.github.andreysum.lighting.db.schema.TaskEntity
import org.springframework.stereotype.Repository

@Repository
interface TaskRepo : BaseRepo<TaskEntity> {
    fun findAllByTitleContainingIgnoreCase(title: String): List<TaskEntity>
}
