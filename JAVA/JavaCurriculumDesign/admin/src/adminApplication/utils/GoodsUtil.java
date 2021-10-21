package adminApplication.utils;

import adminApplication.dataBean.GoodsBean;

import java.io.*;
import java.sql.ResultSet;
import java.sql.SQLException;

public class GoodsUtil {

    public static GoodsBean ResultToGoods(ResultSet rs) throws SQLException {
        GoodsBean goods = new GoodsBean();
        goods.setGoodsId(rs.getString("goodsId"));
        goods.setRegisterTime(rs.getTimestamp("registerTime"));
        goods.setUserId(rs.getString("userId"));
        goods.setGoodsName(rs.getString("goodsName"));
        goods.setIntroduction(rs.getString("introduction"));
        goods.setGoodsPrice(rs.getDouble("goodsPrice"));
        goods.setSaleOut(rs.getInt("saleOut"));
        goods.setOutTime(rs.getTimestamp("outTime"));
        goods.setPurchaserId(rs.getString("purchaserId"));
        goods.setImageName(rs.getString("imageName"));
        goods.setKeyWords(rs.getString("keyWords").split(","));
        return goods;
    }

    public static File ResultToGoodsPhoto(ResultSet rs, GoodsBean goods) throws SQLException, IOException {
        File goodsImage = null;
        if (rs.getBlob("goodsImage") != null) {
            if (!new File("goodsPhoto/" + goods.getGoodsId()).exists()) {
                new File("goodsPhoto/" + goods.getGoodsId()).mkdirs();
            }
            goodsImage = new File("goodsPhoto/" + goods.getGoodsId() + "/" + goods.getImageName());
            InputStream in = rs.getBlob("goodsImage").getBinaryStream();
            FileOutputStream out = new FileOutputStream(goodsImage);
            byte[] b = new byte[40960];
            while (in.read(b) != -1) {
                out.write(b);
            }
            in.close();
            out.close();
        }
        goods.setImage(goodsImage);
        return goodsImage;
    }

    public static GoodsBean findGoodsByKey(ResultSet rs, String[] userKeys) throws SQLException, IOException {
        GoodsBean goods = ResultToGoods(rs);
        String goodsString = goods.toString();
        for (String user : userKeys) {
            if (goodsString.contains(user)) {
                return goods;
            }
        }
        return null;
    }
}
