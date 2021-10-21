package userApplication.dataBean;

import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import userApplication.utils.SQLUtil;

import java.io.File;
import java.util.HashMap;
import java.util.List;

public class CacheDataBean {

    private static Stage myStage = null;   // 当前舞台 / 用户登录界面和主界面的转换
    private static UserBean nowUser = null;     // 存储当前用户
    private static File goodsPhoto = null;      // 用户选择的照片 / 新增及修改时使用
    private static String chatUserId = null;    // 正在聊天的对象
    private static Stage stage = null;          // 跳出性的舞台 / 密码更改的等操作
    private static Pane workingPane = null;     // 工作区对象 / 用于页面跳转
    private static StatusBean status = null;    // 用户行为的状态变量
    private static GoodsBean LookingGoods;      // 用户正在浏览的商品信息, 减少对数据库的读取次数
    private static List<MessageBean> messagePreview;    // 存储用户的消息对象列表

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
