package views;

import dataBean.CacheDataBean;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Rectangle2D;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.stage.Modality;
import javafx.stage.Screen;
import javafx.stage.Stage;
import utils.DialogBox;

public class loginView extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {
        Class.forName("utils.SQLUtil");

        CacheDataBean.setMyStage(primaryStage);     // 保存 程序主窗口 对象

        FXMLLoader loader = new FXMLLoader(getClass().getResource("../resource/fxml/login.fxml"));  // 加载 login 页面
        Pane root = loader.load();

        primaryStage.setTitle("LOAP 二手交易平台 登录页面");  // 设置标题
        primaryStage.setScene(new Scene(root, 800, 500));   // 加载画布
        Rectangle2D screenRectangle = Screen.getPrimary().getBounds();   // 获取屏幕大小
        primaryStage.setX((screenRectangle.getWidth() - 800) / 2.0);
        primaryStage.setY((screenRectangle.getHeight()- 500) / 2.0 - 100);  // 使得左右居中, 上下定位
        primaryStage.setResizable(false);   // 窗口大小不可调整
        primaryStage.show();    // 显示窗口

        // 窗口推出事件 用户登出, 退出子线程
        primaryStage.setOnCloseRequest(event -> {
            CacheDataBean.logOut();
            System.exit(0);
        });
    }


    public static void main(String[] args) {
        launch(args);
    }
}

