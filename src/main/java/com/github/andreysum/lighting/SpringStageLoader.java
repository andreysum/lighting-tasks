package com.github.andreysum.lighting;

import com.github.andreysum.lighting.controller.MainController;
import com.melloware.jintellitype.JIntellitype;
import com.melloware.jintellitype.JIntellitypeConstants;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import net.rgielen.fxweaver.core.FxWeaver;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ApplicationListener;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.event.ContextClosedEvent;
import org.springframework.stereotype.Component;

/**
 * Определяет порядок загрузки FX-компонентов.
 */
@Component
public class SpringStageLoader extends Application implements ApplicationListener<ContextClosedEvent> {
    private static final char SPACE_KEYCODE = ' ';
    private ConfigurableApplicationContext applicationContext;

    /**
     * Загрузка корневого узла и его дочерних элементов из fxml шаблона.
     *
     * @return объект типа Parent.
     */
    private Parent load() {
        FxWeaver fxWeaver = applicationContext.getBean(FxWeaver.class);
        return fxWeaver.loadView(MainController.class);
    }

    private void registerHotKeys(Stage stage) {
        // global key to show window
        JIntellitype.getInstance().registerHotKey(1, JIntellitypeConstants.MOD_CONTROL, SPACE_KEYCODE);
        JIntellitype.getInstance().addHotKeyListener(identifier -> {
            if (identifier == 1)
                Platform.runLater(stage::show);
        });
    }

    private void hideStage(Stage stage) {
        Platform.runLater(stage::hide);
    }

    /**
     * Реализуем загрузку главной сцены. На закрытие сцены стоит обработчик, которых выходит из приложения
     */
    private void loadMain() {
        Platform.setImplicitExit(false);
        Stage stage = new Stage();
        configure(stage);
    }

    private void configure(Stage stage) {
        final Scene scene = new Scene(load());
        stage.setTitle("Lighting Tasks");
        stage.setScene(scene);
        // TODO asum commented due to need to clear logs of idea
//        stage.focusedProperty().addListener((observable, wasFocused, isNowFocused) -> {
//            if (!isNowFocused) {
//                Platform.runLater(() -> hideStage(stage));
//            }
//        });
        stage.addEventFilter(KeyEvent.KEY_RELEASED, filter -> {
                    if (filter == null) {
                        return;
                    }
                    if (filter.getCode() == KeyCode.ESCAPE) {
                        Platform.runLater(() -> hideStage(stage));
                        filter.consume();
                    }
                });
        stage.initStyle(StageStyle.UNDECORATED);
        registerHotKeys(stage);
    }

    @Override
    public void onApplicationEvent(ContextClosedEvent event) {
        JIntellitype.getInstance().cleanUp();
    }

    @Override
    public void start(Stage primaryStage) {
        loadMain();
    }

    @Override
    public void stop() {
        JIntellitype.getInstance().cleanUp();
        applicationContext.close();
    }
    @Override
    public void init() {
        String[] args = getParameters().getRaw().toArray(new String[0]);

        this.applicationContext = new SpringApplicationBuilder()
                .sources(LightingTasksApplication.class)
                .run(args);
    }
}
