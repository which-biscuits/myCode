package userApplication.Controllers;

import userApplication.dataBean.*;
import userApplication.utils.*;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.FlowPane;
import javafx.stage.FileChooser;
import java.io.File;
import java.net.URL;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.regex.Pattern;

public class distributionController implements Initializable {

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        CacheDataBean.setStatus(StatusBean.OnDistribution);
        CacheDataBean.setGoodsPhoto(null);
        goodsPhoto.setImage(new Image(String.valueOf(getClass().getResource("/userApplication/resource/images/choiceImage.png"))));
    }

    @FXML
    void addKeyWordsOnClick(ActionEvent event) {
        TextField textField = new TextField();
        textField.setMaxWidth(100);
        textField.setMaxHeight(35);
        Keywords.getChildren().remove(addKeyWords);
        Keywords.getChildren().add(textField);
        Keywords.getChildren().add(addKeyWords);
    }

    @FXML
    void releaseOnClick(ActionEvent event) {
        if (CacheDataBean.getGoodsPhoto() == null) {
            DialogBox.warning("请选择商品照片");return;
        } else if (CacheDataBean.getGoodsPhoto().length() > 16777216) {
            DialogBox.warning("图片文件过大!");return;
        }
        if (goodsName.getText() == null || goodsName.getText().equals("")) {
            DialogBox.warning("请输入商品名称");return;
        }
        if (!Pattern.matches("^-?([1-9]\\d*\\.\\d*|0\\.\\d*[1-9]\\d*|0?\\.0+|0)$", goodsPrice.getText())) {
            DialogBox.warning("请正确输入商品价格");return;
        }
        if (introduction.getText() == null || introduction.getText().equals("")) {
            DialogBox.warning("请输入商品介绍");return;
        }
        ObservableList<Node> nodeList = Keywords.getChildren();
        List<String> keys = new LinkedList<String>();
        for (int i = 0; i < nodeList.size()-1; i++) {
            if (((TextField) nodeList.get(i)).getText() != null || !((TextField) nodeList.get(i)).getText().equals("")) {
                keys.add(((TextField) nodeList.get(i)).getText());
            }
        }
        if (keys.size() == 0) {
            DialogBox.warning("请填入一个商品标签");return;
        }
        GoodsBean goods = new GoodsBean(
                GoodsUtil.GenerateGoodsId(), new Date(System.currentTimeMillis()),
                CacheDataBean.getNowUser().getUserId(), goodsName.getText(), introduction.getText(), Double.parseDouble(goodsPrice.getText()),
                GoodsUtil.generateImageName(CacheDataBean.getGoodsPhoto().getName()), CacheDataBean.getGoodsPhoto(), keys.toArray(new String[] {}));
        SQLUtil.addGoods(goods);
        refreshOnClick(null);
    }

    @FXML
    void uploadPhotoOnClick(ActionEvent event) {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Open Resource Photo");
        fileChooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("All Images", "*.*"),
                new FileChooser.ExtensionFilter("JPG", "*.jpg"),
                new FileChooser.ExtensionFilter("PNG", "*.png"),
                new FileChooser.ExtensionFilter("JPEG", "*.jpeg")
        );
        File photo = fileChooser.showOpenDialog(CacheDataBean.getMyStage());
        if (photo == null) {
            return;
        }
        if (photo.length() <= 16777216) {
            CacheDataBean.setGoodsPhoto(photo);
            Image image = new Image("file:" + CacheDataBean.getGoodsPhoto().getAbsolutePath());
            goodsPhoto.setImage(image);
        } else if (photo.length() > 16777216) {
            DialogBox.warning("图片大于 16M");
        }
    }

    @FXML
    void refreshOnClick(ActionEvent event) {
        CacheDataBean.setGoodsPhoto(null);
        goodsPhoto.setImage(new Image(String.valueOf(getClass().getResource("/userApplication/resource/images/choiceImage.png"))));
        goodsName.setText("");
        goodsPrice.setText("");
        introduction.setText("");
        Keywords.getChildren().clear();
        TextField textField = new TextField();
        textField.setMaxWidth(100);
        textField.setMaxHeight(35);
        Keywords.getChildren().add(textField);
        Keywords.getChildren().add(addKeyWords);
    }

    @FXML
    private Button refresh;

    @FXML
    private Button uploadPhoto;

    @FXML
    private ImageView goodsPhoto;

    @FXML
    private TextField goodsName;

    @FXML
    private TextField goodsPrice;

    @FXML
    private TextArea introduction;

    @FXML
    private FlowPane Keywords;

    @FXML
    private Button addKeyWords;

    @FXML
    private Button release;
}
