package com.github.andreysum.lighting.service.impl

import com.github.andreysum.lighting.db.schema.TaskEntity
import com.github.andreysum.lighting.service.TaskAdvisor
import com.github.andreysum.lighting.service.TaskListComboBoxInitializer
import javafx.beans.value.ChangeListener
import javafx.collections.FXCollections
import javafx.scene.control.ComboBox
import org.springframework.stereotype.Service

@Service
class TaskListComboBoxInitializer(
    private val taskAdvisor: TaskAdvisor,
    private val taskListEventListener: TaskListEventListener
) : TaskListComboBoxInitializer {

    override fun init(taskList: ComboBox<TaskEntity>) {
        taskList.isEditable = true
        taskListEventListener.control = taskList
        taskList.onKeyReleased = taskListEventListener
        taskList.selectionModelProperty().addListener({ observable, oldValue, newValue -> newValue.selectedItem})
        populate(taskList)
    }

    private fun populate(taskList: ComboBox<TaskEntity>) {
        val tasks = FXCollections.observableArrayList(taskAdvisor.listAll())
        taskList.items.addAll(tasks)
    }
}
