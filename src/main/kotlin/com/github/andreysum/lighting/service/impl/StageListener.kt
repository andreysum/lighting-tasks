package com.github.andreysum.lighting.service.impl

import javafx.application.Platform
import javafx.event.EventHandler
import javafx.scene.input.KeyCode
import javafx.scene.input.KeyEvent
import javafx.stage.Stage

class StageListener(
    private val stage: Stage
) : EventHandler<KeyEvent?> {
    override fun handle(event: KeyEvent?) {
        if (event == null) {
            return
        }
        if (event.code == KeyCode.ESCAPE) {
            Platform.runLater { stage.hide() }
        }
    }
}
