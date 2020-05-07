package com.github.andreysum.lighting.controller

import com.github.andreysum.lighting.db.dao.TaskRepo
import com.github.andreysum.lighting.db.schema.TaskEntity
import javafx.collections.FXCollections
import javafx.collections.ObservableList
import javafx.event.EventHandler
import javafx.scene.control.ComboBox
import javafx.scene.input.KeyEvent

class AutoCompleteComboBoxListener(
    private val comboBox: ComboBox<TaskEntity>,
    private val taskRepo: TaskRepo
) : EventHandler<KeyEvent?> {
    private val data: ObservableList<TaskEntity> = comboBox.items
    override fun handle(event: KeyEvent?) {
        if (event == null) return
        val tasks : ObservableList<TaskEntity> =
            FXCollections.observableArrayList(taskRepo.findAllByTitleContainingIgnoreCase(comboBox.editor.text))
        data.clear()
        data.addAll(tasks)
        if (!data.isEmpty()) {
            comboBox.show()
        } else {
            comboBox.hide()
        }
    }

    init {
        comboBox.isEditable = true
        comboBox.onKeyPressed = EventHandler { comboBox.hide() }
        comboBox.onKeyReleased = this@AutoCompleteComboBoxListener
    }
}
