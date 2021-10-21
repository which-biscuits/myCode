package adminApplication.dataBean;

import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class CacheDataBean {

    private static AdminBean nowAdmin = null;
    private static Stage MyStage = null;
    private static Pane workingPane = null;
    private static String LookingGoodsId;
    private static StatusBean status;
    private static String chatUserId;
    private static Stage stage;
    private static String workingAdminId;

    private CacheDataBean() {}  // 禁止实例化

    public static void logOut() {
        nowAdmin = null; workingPane = null; LookingGoodsId = null;
        status = null; chatUserId = null; stage = null; workingAdminId = null;
    }

    public static AdminBean getNowAdmin() {
        return nowAdmin;
    }

    public static void setNowAdmin(AdminBean nowAdmin) {
        CacheDataBean.nowAdmin = nowAdmin;
    }

    public static Stage getMyStage() {
        return MyStage;
    }

    public static void setMyStage(Stage myStage) {
        MyStage = myStage;
    }

    public static Pane getWorkingPane() {
        return workingPane;
    }

    public static void setWorkingPane(Pane workingPane) {
        CacheDataBean.workingPane = workingPane;
    }

    public static String getLookingGoodsId() {
        return LookingGoodsId;
    }

    public static void setLookingGoodsId(String lookingGoodsId) {
        LookingGoodsId = lookingGoodsId;
    }

    public static StatusBean getStatus() {
        return status;
    }

    public static void setStatus(StatusBean status) {
        CacheDataBean.status = status;
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

    public static String getWorkingAdminId() {
        return workingAdminId;
    }

    public static void setWorkingAdminId(String workingAdminId) {
        CacheDataBean.workingAdminId = workingAdminId;
    }
}
