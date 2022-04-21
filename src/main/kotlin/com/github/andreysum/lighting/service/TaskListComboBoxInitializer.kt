package com.github.andreysum.lighting.service

import com.github.andreysum.lighting.db.schema.TaskEntity
import javafx.scene.control.ComboBox

interface TaskListComboBoxInitializer {
    fun init(taskList: ComboBox<TaskEntity>)
}