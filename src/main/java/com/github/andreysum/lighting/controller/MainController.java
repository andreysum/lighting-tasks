package com.github.andreysum.lighting.controller;

import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import org.springframework.stereotype.Component;

/**
 * Handles saving category table controller.
 *
 * @author andreysum
 */
@Component
public class MainController {
    @FXML
    private ComboBox<String> input;

    @FXML
    public void initialize() {
        input.getItems().addAll("Any", "Second");
    }
}
