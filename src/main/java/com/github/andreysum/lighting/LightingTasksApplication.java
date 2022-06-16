package com.github.andreysum.lighting;

import com.melloware.jintellitype.JIntellitype;
import javafx.application.Application;
import javafx.stage.Stage;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Component;

/**
 * Главный класс JavaFX приложения.
 *
 * @author Andrey Sumtsov
 */
@SpringBootApplication
public class LightingTasksApplication {
    public static void main(String[] args) {
        Application.launch(SpringStageLoader.class, args);
    }
}
