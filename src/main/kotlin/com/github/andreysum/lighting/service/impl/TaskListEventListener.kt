package com.github.andreysum.lighting.service.impl

import com.github.andreysum.lighting.db.schema.TaskEntity
import com.github.andreysum.lighting.service.TaskAdvisor
import com.github.andreysum.lighting.service.TimeManager
import javafx.collections.FXCollections
import javafx.collections.ObservableList
import javafx.event.EventHandler
import javafx.scene.control.ComboBox
import javafx.scene.input.KeyCode
import javafx.scene.input.KeyEvent
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class TaskListEventListener(private val timeManager: TimeManager) : EventHandler<KeyEvent?> {
    @Autowired
    lateinit var taskAdvisor: TaskAdvisor
    lateinit var control: ComboBox<TaskEntity>

    override fun handle(event: KeyEvent?) {
        if (event == null) {
            return
        }
        if (isNotPrintable(event)) {
            return
        }
        val keyCode = event.code
        if (performSpecialKeyActions(keyCode)) {
            return
        }
        val list = taskAdvisor.list(control.editor.text)
        val tasks : ObservableList<TaskEntity> = FXCollections.observableArrayList(list)
        control.items = tasks
        if (list.isNotEmpty()) {
            control.show()
        } else {
            control.hide()
        }
    }

    private fun isNotPrintable(event: KeyEvent): Boolean {
        return !event.code.isLetterKey && event.code.isModifierKey
    }

    private fun performSpecialKeyActions(code: KeyCode): Boolean {
        if (code == KeyCode.ENTER) {
            val title = control.editor.text
            if (control.selectionModel.isEmpty) {
                taskAdvisor.createTask(title)
                return true
            }
        }
        return false
    }
}
