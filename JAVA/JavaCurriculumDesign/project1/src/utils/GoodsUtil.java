package utils;

import dataBean.GoodsBean;

import java.awt.image.BufferedImage;
import java.io.*;
import java.math.BigDecimal;
import java.sql.*;
import java.util.Objects;
import java.util.Random;
import net.coobird.thumbnailator.Thumbnails;

import javax.imageio.ImageIO;

public class GoodsUtil {

    public static void GoodsAddToSQL(PreparedStatement st, GoodsBean goods) throws SQLException, FileNotFoundException {
        st.setString(1, goods.getGoodsId());
        st.setTimestamp(2, new Timestamp(goods.getRegisterTime().getTime()));
        st.setString(3, goods.getUserId());
        st.setString(4, goods.getGoodsName());
        st.setString(5, goods.getIntroduction());
        st.setDouble(6, goods.getGoodsPrice());
        st.setInt(7, goods.getSaleOut());
        st.setTimestamp(8, goods.getOutTime() == null ? null:new Timestamp(goods.getOutTime().getTime()));
        st.setString(9, goods.getPurchaserId());
        st.setString(10, goods.getImageName());
        if (!new File("goodsPhoto/" + goods.getGoodsId()).exists()) {
            new File("goodsPhoto/" + goods.getGoodsId()).mkdirs();
        }
        FileInputStream inputStream = new FileInputStream(Objects.requireNonNull(compressPicture(goods.getImage(),
                new File("goodsPhoto/" + goods.getGoodsId() + "/" + goods.getImageName()))));
        st.setBlob(11, inputStream, goods.getImage().length());
        st.setString(12, String.join("," , goods.getKeyWords()));
    }

    public static GoodsBean ResultToGoods(ResultSet rs) throws SQLException, IOException {
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
        goods.setOrCheck(rs.getInt("orCheck"));
        goods.setFailReason(rs.getString("failReason"));
        return goods;
    }

    public static String GenerateGoodsId() {
        Random random = new Random();
        StringBuilder buffer = new StringBuilder();
        do {
            buffer.setLength(0);
            for(int i = 0; i < 20; i++){
                int number = random.nextInt(3);
                long result=0;
                switch(number){
                    case 0:
                        result=Math.round(Math.random()*25+65);
                        buffer.append((char) result);
                        break;
                    case 1:
                        result=Math.round(Math.random()*25+97);
                        buffer.append((char) result);
                        break;
                    case 2:
                        buffer.append(new Random().nextInt(10));
                        break;
                }
            }
        } while (SQLUtil.hasGoods(buffer.toString()));
        return buffer.toString();
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

    public static String generateImageName(String imageName) {
        return GenerateGoodsId() + imageName.substring(imageName.lastIndexOf("."));
    }

    private static File compressPicture(File srcFile, File desFile) {
        try {
            // 按指定像素比例压缩图片
            Thumbnails.of(srcFile.getAbsolutePath()).size(1380, 900).toFile(desFile.getAbsolutePath());
        } catch (Exception e) {
            DialogBox.error(e);
            return null;
        }
        return desFile;
    }

    public static void main(String[] args) {
        long a = System.currentTimeMillis();
        compressPicture(new File("C:\\Users\\WZX\\Desktop\\JavaCurriculumDesign\\project1\\goodsPhoto\\JEGEsj9147oRNIX99pM3\\IMG20190811155718.jpg"),
                new File("C:\\Users\\WZX\\Desktop\\JavaCurriculumDesign\\project1\\goodsPhoto\\JEGEsj9147oRNIX99pM3\\123.jpg"));
        System.out.println(System.currentTimeMillis() - a);
    }
}
