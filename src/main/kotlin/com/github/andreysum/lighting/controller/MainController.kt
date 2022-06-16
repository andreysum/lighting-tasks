package com.github.andreysum.lighting.controller

import com.github.andreysum.lighting.db.schema.TaskEntity
import com.github.andreysum.lighting.service.TaskListComboBoxInitializer
import javafx.fxml.FXML
import javafx.scene.control.ComboBox
import net.rgielen.fxweaver.core.FxmlView
import org.springframework.stereotype.Component


/**
 * Handles task input.
 *
 * @author andreysum
 */
@Component
@FxmlView("/javafx/main.fxml")
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