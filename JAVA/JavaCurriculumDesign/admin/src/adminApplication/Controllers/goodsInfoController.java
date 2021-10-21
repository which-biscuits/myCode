package adminApplication.Controllers;

import adminApplication.dataBean.CacheDataBean;
import adminApplication.dataBean.Goods;
import adminApplication.dataBean.GoodsBean;
import adminApplication.dataBean.StatusBean;
import adminApplication.utils.DialogBox;
import adminApplication.utils.SQLUtil;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.Objects;
import java.util.ResourceBundle;

public class goodsInfoController implements Initializable {

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        goodsId.setCellValueFactory(new PropertyValueFactory<>("goodsId"));
        goodsName.setCellValueFactory(new PropertyValueFactory<>("goodsName"));
        introduction.setCellValueFactory(new PropertyValueFactory<>("introduction"));
        goodsPrice.setCellValueFactory(new PropertyValueFactory<>("goodsPrice"));
        keyWords.setCellValueFactory(new PropertyValueFactory<>("keyWords"));
        orCheck.setCellValueFactory(new PropertyValueFactory<>("orCheck"));
        failReason.setCellValueFactory(new PropertyValueFactory<>("failReason"));
        saleOut.setCellValueFactory(new PropertyValueFactory<>("saleOut"));
        outTime.setCellValueFactory(new PropertyValueFactory<>("outTime"));
        purchaserId.setCellValueFactory(new PropertyValueFactory<>("purchaserId"));
        receiveAddress.setCellValueFactory(new PropertyValueFactory<>("receiveAddress"));
        goodsInfo.setItems(data);
        new Thread(() -> {
            for (GoodsBean goods : Objects.requireNonNull(SQLUtil.getAllGoods())) {
                data.add(new Goods(goods));
            }
        }).start();
    }

    ObservableList<Goods> data = FXCollections.observableArrayList();

    @FXML
    private TableView<Goods> goodsInfo;

    @FXML
    private TableColumn<Goods, String> goodsId;

    @FXML
    private TableColumn<Goods, String> goodsName;

    @FXML
    private TableColumn<Goods, String> introduction;

    @FXML
    private TableColumn<Goods, Double> goodsPrice;

    @FXML
    private TableColumn<Goods, String> keyWords;

    @FXML
    private TableColumn<Goods, String> orCheck;

    @FXML
    private TableColumn<Goods, String> failReason;

    @FXML
    private TableColumn<Goods, String> saleOut;

    @FXML
    private TableColumn<Goods, String> outTime;

    @FXML
    private TableColumn<Goods, String> purchaserId;

    @FXML
    private TableColumn<Goods, String> receiveAddress;

    @FXML
    private Button deleteGoods;

    @FXML
    private TextField goodsIdTextIn;

    @FXML
    private Button auditGoods;

    @FXML
    void auditGoodsOnClick(ActionEvent event) {
        if (goodsIdTextIn.getText() == null || goodsIdTextIn.getText().equals("")) {
            DialogBox.warning("请输入商品ID");
        } else if (!SQLUtil.hasGoods(goodsIdTextIn.getText())) {
            DialogBox.warning("商品信息不存在!");
        } else {
            GoodsBean goods = SQLUtil.getGoodsByGoodsId(goodsIdTextIn.getText());
            CacheDataBean.setStatus(StatusBean.FromAuditGoods);
            assert goods != null;
            CacheDataBean.setLookingGoodsId(goods.getGoodsId());
            CacheDataBean.getWorkingPane().getChildren().clear();
            FXMLLoader loader = new FXMLLoader();	// 创建对象
            InputStream in = getClass().getResourceAsStream("/adminApplication/resource/fxml/introduction.fxml");
            AnchorPane page = null; // 对象方法的参数是InputStream，返回值是Object
            try {
                page = loader.load(in);
            } catch (IOException e) {
                e.printStackTrace();
            }
            CacheDataBean.getWorkingPane().getChildren().add(page);
        }
    }

    @FXML
    void deleteGoodsOnClick(ActionEvent event) {
        if (goodsIdTextIn.getText() == null || goodsIdTextIn.getText().equals("")) {
            DialogBox.warning("请输入商品ID");
        } else if (DialogBox.confirm("确定删除该商品吗?")) {
            if (SQLUtil.deleteGoodsById(goodsIdTextIn.getText())) {
                data.clear();
                new Thread(() -> {
                    for (GoodsBean goods : Objects.requireNonNull(SQLUtil.getAllGoods())) {
                        data.add(new Goods(goods));
                    }
                }).start();
            } else {
                DialogBox.warning("删除失败!");
            }
        }
    }
}
