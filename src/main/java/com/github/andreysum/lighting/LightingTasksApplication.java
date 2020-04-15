package com.github.andreysum.lighting;

import com.melloware.jintellitype.JIntellitype;
import javafx.application.Application;
import javafx.stage.Stage;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Component;

/**
 * Главный класс JavaFX приложения.
 *
 * @author Andrey Sumtsov
 */
@Component
public class LightingTasksApplication extends Application {
    private static ClassPathXmlApplicationContext ctx;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void init() {
        ctx = new ClassPathXmlApplicationContext("spring/application-context.xml");
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        SpringStageLoader.loadMain().show();
    }

    @Override
    public void stop() {
        JIntellitype.getInstance().cleanUp();
        ctx.close();
    }
}
