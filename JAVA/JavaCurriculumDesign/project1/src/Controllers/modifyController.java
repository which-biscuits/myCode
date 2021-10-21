package Controllers;

import dataBean.CacheDataBean;
import dataBean.GoodsBean;
import javafx.application.Platform;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.FlowPane;
import javafx.stage.FileChooser;
import utils.DialogBox;
import utils.GoodsUtil;
import utils.SQLUtil;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.LinkedList;
import java.util.List;
import java.util.Objects;
import java.util.ResourceBundle;
import java.util.regex.Pattern;

public class modifyController implements Initializable {

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        new Thread(new setView(CacheDataBean.getLookingGoods())).start();
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
    void submitOnClick(ActionEvent event) throws IOException {
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
        GoodsBean goods = CacheDataBean.getLookingGoods();
        assert goods != null;
        goods.setGoodsName(goodsName.getText());
        goods.setIntroduction(introduction.getText());
        goods.setGoodsPrice(Double.parseDouble(goodsPrice.getText()));
        goods.setImageName(GoodsUtil.generateImageName(CacheDataBean.getGoodsPhoto().getName()));
        goods.setImage(CacheDataBean.getGoodsPhoto());
        goods.setKeyWords(keys.toArray(new String[] {}));
        SQLUtil.updateGoods(goods);
        cancelOnClick(null);
    }

    @FXML
    void cancelOnClick(ActionEvent event) throws IOException {
        CacheDataBean.getWorkingPane().getChildren().clear();
        FXMLLoader loader = new FXMLLoader();	// 创建对象
        InputStream in = getClass().getResourceAsStream("../resource/fxml/myDistribution.fxml");
        AnchorPane page = loader.load(in); // 对象方法的参数是InputStream，返回值是Object
        CacheDataBean.getWorkingPane().getChildren().add(page);
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
        } else if (photo.length() <= 16777216) {
            CacheDataBean.setGoodsPhoto(photo);
            Image image = new Image("file:" + CacheDataBean.getGoodsPhoto().getAbsolutePath());
            goodsPhoto.setImage(image);
        } else if (photo.length() > 16777216) {
            DialogBox.warning("图片大于 4M");
        }
    }

    class setView implements Runnable {
        GoodsBean goods;

        public setView(GoodsBean goods) {
            this.goods = goods;
        }

        @Override
        public void run() {
            Platform.runLater(()->{
                if (goods.getOrCheck() != -1) {
                    failReason.setVisible(false);
                } else {
                    failReason.setText(goods.getFailReason());
                }
                CacheDataBean.setGoodsPhoto(goods.getImage());
                goodsName.setText(goods.getGoodsName());
                goodsPrice.setText(String.valueOf(goods.getGoodsPrice()));
                introduction.setText(goods.getIntroduction());
                Keywords.getChildren().remove(addKeyWords);
                for (String key : goods.getKeyWords()) {
                    TextField textField = new TextField();
                    textField.setMaxWidth(100);textField.setMaxHeight(35);
                    textField.setText(key);
                    Keywords.getChildren().add(textField);
                }
                Keywords.getChildren().add(addKeyWords);
                File photo = SQLUtil.getGoodsPhoto(goods);
                assert photo != null;
                goodsPhoto.setImage(new Image("file:" + Objects.requireNonNull(photo.getPath())));
                CacheDataBean.setGoodsPhoto(photo);
            });
        }
    }

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
    private Button submit;

    @FXML
    private Button cancel;

    @FXML
    private TextField failReason;
}
