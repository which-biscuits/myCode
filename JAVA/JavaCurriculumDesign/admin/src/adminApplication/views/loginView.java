package adminApplication.views;

import adminApplication.dataBean.CacheDataBean;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Rectangle2D;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Screen;
import javafx.stage.Stage;

public class loginView extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {
        CacheDataBean.setMyStage(primaryStage);
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/adminApplication/resource/fxml/login.fxml"));
        Pane root = loader.load();
        primaryStage.setTitle("LOAP 二手交易平台 管理员");
        primaryStage.setScene(new Scene(root, 800, 500));
        Rectangle2D screenRectangle = Screen.getPrimary().getBounds();
        primaryStage.setX((screenRectangle.getWidth() - 800) / 2.0);
        primaryStage.setY((screenRectangle.getHeight()- 500) / 2.0 - 100);
        primaryStage.setResizable(false);   // 窗口大小不可调整
        primaryStage.show();

        primaryStage.setOnCloseRequest(event -> {
            System.exit(0);
        });
    }


    public static void main(String[] args) {
        launch(args);
    }
}

