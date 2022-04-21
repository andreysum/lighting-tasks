package com.github.andreysum.lighting;

import com.melloware.jintellitype.JIntellitype;
import com.melloware.jintellitype.JIntellitypeConstants;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextClosedEvent;
import org.springframework.stereotype.Component;

import java.io.IOException;

/**
 * Определяет порядок загрузки FX-компонентов.
 */
@Component
public class SpringStageLoader implements ApplicationContextAware, ApplicationListener<ContextClosedEvent> {
    private static final String FXML_DIR = "/javafx/";
    private static final String MAIN_STAGE = "main";
    private static final char SPACE_KEYCODE = ' ';
    private static ApplicationContext staticContext;
    private static String staticTitle;
    @Value("${applicationTitle}")
    private String title;

    /**
     * Загрузка корневого узла и его дочерних элементов из fxml шаблона.
     *
     * @return объект типа Parent
     * @throws IOException бросает исключение ввода-вывода
     */
    private static Parent load() throws IOException {
        FXMLLoader loader = new FXMLLoader();
        final String fxmlPath = FXML_DIR + MAIN_STAGE + ".fxml";
        loader.setLocation(SpringStageLoader.class.getResource(fxmlPath));
        // setLocation необходим для корректной того чтобы loader видел наши кастомные котнролы
        loader.setClassLoader(SpringStageLoader.class.getClassLoader());
        loader.setControllerFactory(staticContext::getBean);
        return loader.load(SpringStageLoader.class.getResourceAsStream(fxmlPath));
    }

    private static void registerHotKeys(Stage stage) {
        // global key to show window
        JIntellitype.getInstance().registerHotKey(1, JIntellitypeConstants.MOD_CONTROL, SPACE_KEYCODE);
        JIntellitype.getInstance().addHotKeyListener(identifier -> {
            if (identifier == 1)
                Platform.runLater(stage::show);
        });
        // local key to hide window
//        stage.getScene().setOnKeyPressed(e -> {
//            KeyCode keyCode = e.getCode();
//            if (Objects.equals(keyCode, KeyCode.ESCAPE)) {
//                Platform.runLater(() -> hideStage(stage));
//            }
//        });
    }

    private static void hideStage(Stage stage) {
        Platform.runLater(stage::hide);
    }

    /**
     * Реализуем загрузку главной сцены. На закрытие сцены стоит обработчик, которых выходит из приложения
     *
     * @return главную сцену
     * @throws IOException бросает исключение ввода-вывода
     */
    static Stage loadMain() throws IOException {
        Platform.setImplicitExit(false);
        Stage stage = new Stage();
        configure(stage);
        return stage;
    }

    private static void configure(Stage stage) throws IOException {
        final Scene scene = new Scene(load());
        stage.setTitle(staticTitle);
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
                        Platform.runLater(stage::hide);
                        filter.consume();
                    }
                });
        stage.initStyle(StageStyle.UNDECORATED);
        registerHotKeys(stage);
    }

    /**
     * Передаем данные в статические поля в реализации метода интерфейса ApplicationContextAware, т.к. методы их использующие тоже статические
     */
    @Override
    public void setApplicationContext(ApplicationContext context) throws BeansException {
        SpringStageLoader.staticContext = context;
        SpringStageLoader.staticTitle = title;
    }

    @Override
    public void onApplicationEvent(ContextClosedEvent event) {
        JIntellitype.getInstance().cleanUp();
    }
}
