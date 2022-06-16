package com.github.andreysum.lighting.service.impl

import com.github.andreysum.lighting.db.schema.TaskEntity
import com.github.andreysum.lighting.service.TaskAdvisor
import com.github.andreysum.lighting.service.TimeManager
import com.melloware.jintellitype.JIntellitype
import javafx.collections.FXCollections
import javafx.collections.ObservableList
import javafx.event.EventHandler
import javafx.scene.control.ComboBox
import javafx.scene.input.KeyCode
import javafx.scene.input.KeyCodeCombination
import javafx.scene.input.KeyCombination
import javafx.scene.input.KeyEvent
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import kotlin.system.exitProcess

@Service
class TaskListEventListener(private val timeManager: TimeManager) : EventHandler<KeyEvent?> {
    @Autowired
    lateinit var taskAdvisor: TaskAdvisor
    lateinit var control: ComboBox<TaskEntity>
    val combo = KeyCodeCombination(KeyCode.F4, KeyCombination.CONTROL_ANY)

    override fun handle(event: KeyEvent?) {
        if (event == null) {
            return
        }
        if (performSpecialKeyActions(event)) {
            return
        }
        if (isNeedSkip(event)) {
            println("key skipped")
            return
        }
        val list = taskAdvisor.list(control.editor.text)
        val tasks: ObservableList<TaskEntity> = FXCollections.observableArrayList(list)
        control.items = tasks
        if (list.isNotEmpty()) {
            control.show()
        } else {
            control.hide()
        }
    }

    private fun isNeedSkip(event: KeyEvent): Boolean {
        return !event.code.isLetterKey || event.code.isModifierKey || event.code == KeyCode.DOWN
    }

    private fun performSpecialKeyActions(event: KeyEvent): Boolean {
//        if (event.code == KeyCode.ENTER) {
//            val title = control.editor.text
//            println(control.selectionModel.isEmpty)
//            if (control.selectionModel.isEmpty) {
//                taskAdvisor.createTask(title)
//            } else {
//                val starting = control.selectionModel.selectedItem
//                taskAdvisor.
//                timeManager.startTask(starting)
//            }
//            return true
//        }
        if (combo.match(event)) {
            JIntellitype.getInstance().unregisterHotKey(1)
            JIntellitype.getInstance().cleanUp()
            exitProcess(0)
        }
        return false
    }
}
