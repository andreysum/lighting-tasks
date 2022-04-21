package com.github.andreysum.lighting.controller

import com.github.andreysum.lighting.db.schema.TaskEntity
import com.github.andreysum.lighting.service.TaskListComboBoxInitializer
import javafx.fxml.FXML
import javafx.scene.control.ComboBox
import org.springframework.stereotype.Component


/**
 * Handles task input.
 *
 * @author andreysum
 */
@Component
class MainController(
    private val initializer: TaskListComboBoxInitializer
) {
    @FXML
    lateinit var taskList: ComboBox<TaskEntity>

    @FXML
    fun initialize() {
        initializer.init(taskList)
    }
}