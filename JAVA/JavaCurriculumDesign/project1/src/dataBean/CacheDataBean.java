package dataBean;

import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import utils.MessageUtil;
import utils.SQLUtil;

import java.io.File;
import java.util.HashMap;
import java.util.List;

public class CacheDataBean {

    private static Stage myStage = null;   // 存储当前舞台

    private static UserBean nowUser = null;   // 存储当前用户
    private static File goodsPhoto = null;
    private static String chatUserId = null;
    private static Stage stage = null;
    private static Pane workingPane = null;
    private static StatusBean status = null;
    private static GoodsBean LookingGoods;
    private static List<MessageBean> messagePreview;

    private CacheDataBean() {}  // 禁止实例化

    public static void logOut() {
        if (nowUser != null)
            SQLUtil.UserOutLine(nowUser.getUserId());
        nowUser = null;goodsPhoto = null;chatUserId = null;
        stage = null;LookingGoods = null;workingPane = null;
        status = null;
    }


    public static UserBean getNowUser() {
        return nowUser;
    }

    public static void setNowUser(UserBean nowUser) {
        CacheDataBean.nowUser = nowUser;
    }

    public static Stage getMyStage() {
        return myStage;
    }

    public static void setMyStage(Stage myStage) {
        CacheDataBean.myStage = myStage;
    }

    public static File getGoodsPhoto() {
        return goodsPhoto;
    }

    public static void setGoodsPhoto(File goodsPhoto) {
        CacheDataBean.goodsPhoto = goodsPhoto;
    }

    public static String getChatUserId() {
        return chatUserId;
    }

    public static void setChatUserId(String chatUserId) {
        CacheDataBean.chatUserId = chatUserId;
    }

    public static Stage getStage() {
        return stage;
    }

    public static void setStage(Stage stage) {
        CacheDataBean.stage = stage;
    }

    public static Pane getWorkingPane() {
        return workingPane;
    }

    public static void setWorkingPane(Pane workingPane) {
        CacheDataBean.workingPane = workingPane;
    }

    public static StatusBean getStatus() {
        return status;
    }

    public static void setStatus(StatusBean status) {
        CacheDataBean.status = status;
    }

    public static GoodsBean getLookingGoods() {
        return LookingGoods;
    }

    public static void setLookingGoods(GoodsBean lookingGoods) {
        LookingGoods = lookingGoods;
    }

    public static List<MessageBean> getMessagePreview() {
        return messagePreview;
    }

    public static void setMessagePreview(List<MessageBean> messagePreview) {
        CacheDataBean.messagePreview = messagePreview;
    }
}
