package utils;

import dataBean.*;
import javafx.application.Platform;

import java.io.File;
import java.io.FileNotFoundException;
import java.sql.*;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

public class SQLUtil {

    static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
    static final String URL = "jdbc:mysql://wzx1141207643.mysql.rds.aliyuncs.com:3306/onlineplatform?characterEncoding=utf-8&useSSL=false";
    static final String USER = "java";
    static final String PASSWORD = "Wangzixiao621";


    static {
        try {
            Class.forName(JDBC_DRIVER);
            Connection conn = getConnection();
            conn.close();
        } catch (ClassNotFoundException | SQLException e) {
            DialogBox.error(e);
        }
    }
    // 获取数据库连接
    public static Connection getConnection() {
        Connection conn = null;
        try {
            conn = DriverManager.getConnection(URL,USER,PASSWORD);
        } catch (Exception e) {
            Platform.runLater(() -> DialogBox.error(e));
        }
        return conn;
    }

    // 用户登录 by Id & password
    public static UserBean findUserForLogIn(String userId, String password) {
        // 获取 用户 如果用户不在线
        String findUser = "select * from users where userId = ? limit 1";
        // 使用户上线
        String onLine = "update Users set orLog = 1 where userId = ? and password = ? and orLog = 0 limit 1";
        try (Connection conn = SQLUtil.getConnection()) {
            PreparedStatement st = conn.prepareStatement(onLine);
            st.setString(1,userId);
            st.setString(2,password);
            int count = st.executeUpdate();
            if (count > 0) {
                st = conn.prepareStatement(findUser);
                st.setString(1,userId);
                try (ResultSet rs = st.executeQuery()) {
                    if (rs.next()) {    // 如果用户不在线且账号密码正确 则将用户上线
                        return UserUtil.ResultToUser(rs);
                    }
                } catch (Exception inEx) {
                    DialogBox.error(inEx);
                }
            }
        } catch (SQLException e) {
            DialogBox.error(e);
        }
        return null;
    }
    // 添加用户信息
    public static boolean addUser(UserBean user) {
        String add = "insert into users (userId, password, userName, userSex, userEmail, userPhone, userBirthday, registerTime) values(?, ?, ?, ?, ?, ?, ?, ?)";
        String has = "select userId from Users where userId = ?";
        try (Connection conn = SQLUtil.getConnection()) {
            PreparedStatement st = conn.prepareStatement(has);
            st.setString(1, user.getUserId());
            ResultSet rs = st.executeQuery();
            if (rs.next()) {    // 当 数据库 存在用户则导入失败
                return false;
            } else {    // 当数据库无数据则写入
                st = conn.prepareStatement(add);
                UserUtil.UserAddToSQL(st, user);   // 将用户数据输入SQL语句
                st.executeUpdate();
            }
        } catch (SQLException e) {
            DialogBox.error(e);
            return false;
        }
        return true;
    }
    // 用户是否存在
    public static boolean hasUser(String userId) {
        String sql = "select userId from Users where userId = ? limit 1";
        try (Connection conn = SQLUtil.getConnection()) {
            PreparedStatement st = conn.prepareStatement(sql);
            st.setString(1,userId);
            try (ResultSet rs = st.executeQuery()) {
                if (!rs.next())
                    return false;
            } catch (Exception inEx) {
                DialogBox.error(inEx);
            }
        } catch (SQLException e) {
            DialogBox.error(e);
        }
        return true;
    }
    // 查询用户在线状态
    public static boolean hasUserOnLine(String userId) {
        String sql = "select userId from users where userId = ? and orLog = 1 limit 1";
        try (Connection conn = SQLUtil.getConnection()) {
            PreparedStatement st = conn.prepareStatement(sql);
            st.setString(1,userId);
            try (ResultSet rs = st.executeQuery()) {
                if (rs.next())
                    return true;
            } catch (Exception inEx) {
                DialogBox.error(inEx);
                return false;
            }
        } catch (SQLException e) {
            DialogBox.error(e);
            return false;
        }
        return false;
    }
    // 查询用户信息 by Id
    public static UserBean findUser(String userId) {
        String sql = "select * from users where userId = ?";
        try (Connection conn = SQLUtil.getConnection()) {
            PreparedStatement st = conn.prepareStatement(sql);
            st.setString(1,userId);
            try (ResultSet rs = st.executeQuery()) {
                if (rs.next())
                    return UserUtil.ResultToUser(rs);
            } catch (Exception inEx) {
                inEx.printStackTrace();
                return null;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
        return null;
    }
    //用户 设置 下线
    public static void UserOutLine(String userId) {
        String sql = "update Users set orLog = 0 where userId = ?";
        try (Connection conn = SQLUtil.getConnection()) {
            PreparedStatement st = conn.prepareStatement(sql);
            st.setString(1,userId);
            st.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    // 充值
    public static boolean updateMoney(UserBean user, Double money) {
        String sql = "update users set money = money + ? where userId = ?";
        try (Connection conn = SQLUtil.getConnection()) {
            PreparedStatement st = conn.prepareStatement(sql);
            st.setDouble(1, money);
            st.setString(2, user.getUserId());
            st.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }
    // 更新用户信息
    public static boolean updateUser(UserBean user) {
        String sql = "update users set userId = ?, password = ?, userName = ?, userSex = ?, userEmail = ?, userPhone = ?, userBirthday = ?, registerTime = ?" +
                " where userId = ?";
        try (Connection conn = SQLUtil.getConnection()) {
            PreparedStatement st = conn.prepareStatement(sql);
            UserUtil.UserAddToSQL(st, user);
            st.setString(9, user.getUserId());
            st.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    // 从数据库随机获取商品
    public static List<GoodsBean> getRandomGoods(int length) {
        List<GoodsBean> goodsList = new LinkedList<>();
        String sql = "select goodsId, registerTime, userId, goodsName, Introduction, goodsPrice, saleOut, outTime, " +
                "purchaserId, imageName, keyWords, orCheck, failReason from goods where saleOut = 0 and orCheck = 1 order by rand() limit ?";
        try (Connection conn = SQLUtil.getConnection()) {
            PreparedStatement st = conn.prepareStatement(sql);
            st.setInt(1, length);
            try (ResultSet rs = st.executeQuery()) {
                while (rs.next()) {
                    goodsList.add(GoodsUtil.ResultToGoods(rs));
                }
            } catch (Exception inEx) {
                DialogBox.error(inEx);
            }
        } catch (SQLException e) {
            DialogBox.error(e);
        }
        return goodsList;
    }
    // 从数据库匹配 keyWords use for discovery
    public static List<GoodsBean> findGoodsByKeyWords(String[] userKeys) {
        String sql = "select goodsId, registerTime, userId, goodsName, Introduction, goodsPrice, saleOut, outTime, " +
                "purchaserId, imageName, keyWords, orCheck, failReason from goods where orCheck = 1 order by rand() limit 1000";
        List<GoodsBean> goodsList = new LinkedList<GoodsBean>();
        try (Connection conn = SQLUtil.getConnection()) {
            PreparedStatement st = conn.prepareStatement(sql);
            try (ResultSet rs = st.executeQuery()) {
                while (rs.next()) {
                    GoodsBean goods = GoodsUtil.findGoodsByKey(rs, userKeys);
                    if (goods != null) {
                        goodsList.add(goods);
                    }
                }
            } catch (Exception inEx) {
                DialogBox.error(inEx);
            }
        } catch (SQLException e) {
            DialogBox.error(e);
        }
        return goodsList;
    }
    // 下载商品图片
    public static File getGoodsPhoto(GoodsBean goods) {
        File goodsImage = new File("goodsPhoto/" + goods.getGoodsId() + "/" + goods.getImageName());
        if (goodsImage.exists()) {
            return goodsImage;
        }
        String sql = "select goodsImage from goods where goodsId = ?";
        try (Connection conn = SQLUtil.getConnection()) {
            PreparedStatement st = conn.prepareStatement(sql);
            st.setString(1, goods.getGoodsId());
            try (ResultSet rs = st.executeQuery()) {
                if (rs.next()) {
                    return GoodsUtil.ResultToGoodsPhoto(rs, goods);
                }
            } catch (Exception inEx) {
                DialogBox.error(inEx);
            }
        } catch (SQLException e) {
            DialogBox.error(e);
        }
        return null;
    }
    // 购买物品
    public static boolean buyGoods(String buyerId, String sellerId, GoodsBean goods) {
        String buyerMoney = "update users set money = money - ? where userId = ? and money > ? limit 1";
        String sellerMoney = "update users set money = money + ? where userId = ? and money > ? limit 1;";
        String updateGoods = "update goods set saleOut = 1, outTime = ?, purchaserId = ?, receiveAddress = ? where goodsId = ?;";
        try (Connection conn = SQLUtil.getConnection()) {
            PreparedStatement st = conn.prepareStatement(buyerMoney);
            st.setDouble(1, goods.getGoodsPrice());
            st.setString(2, buyerId);
            st.setDouble(3, goods.getGoodsPrice());
            if (st.executeUpdate() > 0) {
                st = conn.prepareStatement(sellerMoney);
                st.setDouble(1, goods.getGoodsPrice());
                st.setString(2, sellerId);
                st.setDouble(3, goods.getGoodsPrice());
                st.executeUpdate();
                st = conn.prepareStatement(updateGoods);
                st.setTimestamp(1, new Timestamp(goods.getOutTime().getTime()));
                st.setString(2, goods.getPurchaserId());
                st.setString(3, goods.getReceiveAddress());
                st.setString(4, goods.getGoodsId());
                st.executeUpdate();
                return true;
            }
        } catch (SQLException e) {
            DialogBox.error(e);
            return false;
        }
        return true;
    }
    // 删除商品
    public synchronized static void deleteGoodsById(String goodsId) {
        String sql = "delete from goods where goodsId = ?";
        try (Connection conn = SQLUtil.getConnection()) {
            PreparedStatement st = conn.prepareStatement(sql);
            st.setString(1, goodsId);
            st.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    // 查询购买
    public static List<GoodsBean> getGoodsByPurchaserId(String purchaserId) {
        String sql = "select goodsId, registerTime, userId, goodsName, Introduction, goodsPrice, saleOut, outTime, " +
                "purchaserId, imageName, keyWords, orCheck, failReason from goods where purchaserId = ?";
        List<GoodsBean> goodsList = new LinkedList<GoodsBean>();
        try (Connection conn = SQLUtil.getConnection()) {
            PreparedStatement st = conn.prepareStatement(sql);
            st.setString(1, purchaserId);
            try (ResultSet rs = st.executeQuery()) {
                while (rs.next()) {
                    goodsList.add(GoodsUtil.ResultToGoods(rs));
                }
            } catch (Exception inEx) {
                inEx.printStackTrace();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return goodsList;
    }
    // 商品编号是否存在
    public static boolean hasGoods(String goodsId) {
        String sql = "select goodsId from Goods where goodsId = ?";
        try (Connection conn = SQLUtil.getConnection()) {
            PreparedStatement st = conn.prepareStatement(sql);
            st.setString(1,goodsId);
            try (ResultSet rs = st.executeQuery()) {
                if (rs.next())
                    return true;
            } catch (Exception inEx) {
                inEx.printStackTrace();
                return true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return true;
        }
        return false;
    }
    // 从数据库中通过 userId 寻找商品
    public static List<GoodsBean> getGoodsByUserId(String userId) {
        String sql = "select goodsId, registerTime, userId, goodsName, Introduction, goodsPrice, saleOut, outTime, " +
                "purchaserId, imageName, keyWords, orCheck, failReason from goods where userId = ?";
        List<GoodsBean> goodsList = new LinkedList<GoodsBean>();
        try (Connection conn = SQLUtil.getConnection()) {
            PreparedStatement st = conn.prepareStatement(sql);
            st.setString(1, userId);
            try (ResultSet rs = st.executeQuery()) {
                while (rs.next()) {
                    goodsList.add(GoodsUtil.ResultToGoods(rs));
                }
            } catch (Exception inEx) {
                inEx.printStackTrace();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return goodsList;
    }
    // 在购买物品中搜索
    public static List<GoodsBean> getGoodsByKeyWordsAndPurchaserId(String[] userKeys, String purchaserId) {
        String sql = "select goodsId, registerTime, userId, goodsName, Introduction, goodsPrice, saleOut, outTime, " +
                "purchaserId, imageName, keyWords, orCheck, failReason from goods where purchaserId = ?";
        List<GoodsBean> goodsList = new LinkedList<GoodsBean>();
        try (Connection conn = SQLUtil.getConnection()) {
            PreparedStatement st = conn.prepareStatement(sql);
            st.setString(1, purchaserId);
            try (ResultSet rs = st.executeQuery()) {
                while (rs.next()) {
                    GoodsBean goods = GoodsUtil.findGoodsByKey(rs, userKeys);
                    if (goods != null) {
                        goodsList.add(goods);
                    }
                }
            } catch (Exception inEx) {
                inEx.printStackTrace();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return goodsList;
    }
    // 在购买物品中搜索
    public static List<GoodsBean> getGoodsByKeyWordsAndUserId(String[] userKeys, String userId) {
        String sql = "select goodsId, registerTime, userId, goodsName, Introduction, goodsPrice, saleOut, outTime, " +
                "purchaserId, imageName, keyWords from goods where userId = ?";
        List<GoodsBean> goodsList = new LinkedList<GoodsBean>();
        try (Connection conn = SQLUtil.getConnection()) {
            PreparedStatement st = conn.prepareStatement(sql);
            st.setString(1, userId);
            try (ResultSet rs = st.executeQuery()) {
                while (rs.next()) {
                    GoodsBean goods = GoodsUtil.findGoodsByKey(rs, userKeys);
                    if (goods != null) {
                        goodsList.add(goods);
                    }
                }
            } catch (Exception inEx) {
                inEx.printStackTrace();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return goodsList;
    }
    // 更新商品信息
    public static void updateGoods(GoodsBean goods) {
        String sql = "update goods set goodsId = ?, registerTime = ?, userId = ?, goodsName = ?, introduction = ?, " +
                "goodsPrice = ?, saleOut = ?, outTime = ?, purchaserId = ?, imageName = ?, goodsImage = ?, KeyWords = ?, orCheck = 0 where goodsId = ?";
        try (Connection conn = SQLUtil.getConnection()) {
            PreparedStatement st = conn.prepareStatement(sql);
            GoodsUtil.GoodsAddToSQL(st, goods);
            st.setString(13, goods.getGoodsId());
            st.executeUpdate();
        } catch (SQLException | FileNotFoundException e) {
            e.printStackTrace();
        }
    }
    // 添加商品
    public synchronized static void addGoods(GoodsBean goods) {
        String sql = "insert into goods (goodsId, registerTime, userId, goodsName, Introduction, goodsPrice, saleOut," +
                "outTime, purchaserId, imageName, goodsImage, keyWords, orCheck) values(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, 0)";
        try (Connection conn = SQLUtil.getConnection()) {
            PreparedStatement st = conn.prepareStatement(sql);
            GoodsUtil.GoodsAddToSQL(st, goods);   // 将用户数据输入SQL语句
            st.executeUpdate();
        } catch (SQLException | FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    // 添加信息
    public static void addMessage(MessageBean message) {
        String sql = "insert into message (sendUserId, receiveUserId, goodsId, sendTime, orRead, message) " +
                "values(?, ?, ?, ?, ?, ?)";
        try (Connection conn = SQLUtil.getConnection()) {
            PreparedStatement st = conn.prepareStatement(sql);
            MessageUtil.MessageAddToSQL(st, message);   // 将用户数据输入SQL语句
            st.executeUpdate();
        } catch (SQLException e) {
            DialogBox.error(e);
        }
    }
    // 修改信息属性 已读
    public static void messageToOverRead(String sendUserId, String receiveUserId) {
        String sql = "update message set orRead = 1 where sendUserId = ? and receiveUserId = ?";
        try (Connection conn = SQLUtil.getConnection()) {
            PreparedStatement st = conn.prepareStatement(sql);
            st.setString(1, sendUserId);
            st.setString(2, receiveUserId);
            st.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    // 获取消息预览
    public static HashMap<String, Integer> getMessagePreviewByUserId(String userId) {
        String receive = "select sendUserId,orRead from message where receiveUserId = ? ";
        String send = "select distinct receiveUserId from message where sendUserId = ? ";
        HashMap<String, Integer> preview = new HashMap<>();
        try (Connection conn = SQLUtil.getConnection()) {
            PreparedStatement st = conn.prepareStatement(receive);
            st.setString(1, userId);
            try (ResultSet rs = st.executeQuery()) {
                while (rs.next()) {
                    String sendUserId = rs.getString("sendUserId");
                    if (!preview.containsKey(sendUserId)) {
                        preview.put(sendUserId, 0);
                    } else if (rs.getInt("orRead") == 0) {
                        preview.put(sendUserId, preview.get(sendUserId) + 1);
                    }
                }
            } catch (Exception inEx) {
                inEx.printStackTrace();
            }
            st = conn.prepareStatement(send);
            st.setString(1,userId);
            try (ResultSet rs = st.executeQuery()) {
                while (rs.next()) {
                    String receiveUserId = rs.getString("receiveUserId");
                    if (!preview.containsKey(receiveUserId)) {
                        preview.put(receiveUserId, 0);
                    }
                }
            } catch (Exception inEx) {
                inEx.printStackTrace();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return preview;
    }
    // 获取消息信息
    public static List<MessageBean> getAllMessageByBothId(String userId, String chatUserId) {
        String get = "select * from message where (sendUserId = ? and receiveUserId = ?) or (sendUserId = ? and receiveUserId = ?)";
        String overRead = "update message set orRead = 1 where ((sendUserId = ? and receiveUserId = ?) or (sendUserId = ? and receiveUserId = ?)) and orRead = 0";
        List<MessageBean> messageList = new LinkedList<MessageBean>();
        try (Connection conn = SQLUtil.getConnection()) {
            PreparedStatement st = conn.prepareStatement(get);
            st.setString(1, userId);
            st.setString(2, chatUserId);
            st.setString(3, chatUserId);
            st.setString(4, userId);
            try (ResultSet rs = st.executeQuery()) {
                while (rs.next()) {
                    messageList.add(MessageUtil.ResultToMessage(rs));
                }
            } catch (Exception inEx) {
                inEx.printStackTrace();
            }
            st = conn.prepareStatement(overRead);
            st.setString(1, userId);
            st.setString(2, chatUserId);
            st.setString(3, chatUserId);
            st.setString(4, userId);
            st.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        Collections.sort(messageList);
        return messageList;
    }
    // 是否有新消息
    public static boolean hasNewMessageByBothId(String userId, String chatUserId) {
        String sql = "select sendUserId from message where sendUserId = ? and receiveUserId = ? and orRead = 0 limit 1";
        try (Connection conn = SQLUtil.getConnection()) {
            PreparedStatement st = conn.prepareStatement(sql);
            st.setString(1, chatUserId);
            st.setString(2, userId);
            try (ResultSet rs = st.executeQuery()) {
                if (rs.next()) {
                    return true;
                }
            } catch (Exception inEx) {
                inEx.printStackTrace();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
    // 获取新消息
    public static List<MessageBean> getNewMessageByBothId(String userId, String chatUserId) {
        String sql = "select * from message where ((sendUserId = ? and receiveUserId = ?) or (sendUserId = ? and receiveUserId = ?)) and orRead = 0 ";
        String overRead = "update message set orRead = 1 where ((sendUserId = ? and receiveUserId = ?) or (sendUserId = ? and receiveUserId = ?)) and orRead = 0";
        List<MessageBean> messageList = new LinkedList<MessageBean>();
        try (Connection conn = SQLUtil.getConnection()) {
            PreparedStatement st = conn.prepareStatement(sql);
            st.setString(1, userId);
            st.setString(2, chatUserId);
            st.setString(3, chatUserId);
            st.setString(4, userId);
            try (ResultSet rs = st.executeQuery()) {
                while (rs.next()) {
                    messageList.add(MessageUtil.ResultToMessage(rs));
                }
            } catch (Exception inEx) {
                inEx.printStackTrace();
            }
            st = conn.prepareStatement(overRead);
            st.setString(1, userId);
            st.setString(2, chatUserId);
            st.setString(3, chatUserId);
            st.setString(4, userId);
            st.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        Collections.sort(messageList);
        return messageList;
    }


    // 获取 客服通信 信息
    public static List<ServiceBean> getAllServiceByUserId(String userId) {
        String getService = "select * from service where userId = ?";
        String overRead = "update service set orRead = 1 where userId = ?";
        List<ServiceBean> serviceList = new LinkedList<ServiceBean>();
        try (Connection conn = SQLUtil.getConnection()) {
            PreparedStatement st = conn.prepareStatement(getService);
            st.setString(1, userId);
            try (ResultSet rs = st.executeQuery()) {
                while (rs.next()) {
                    serviceList.add(ServiceUtil.ResultToService(rs));
                }
            } catch (Exception inEx) {
                inEx.printStackTrace();
            }
            st = conn.prepareStatement(overRead);
            st.setString(1, userId);
            st.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        Collections.sort(serviceList);
        return serviceList;
    }
    // 新增 客服通信 信息
    public static void addService(ServiceBean service) {
        String sql = "insert into service (userId, orSend, sendTime, orRead, message, goodsId) " +
                "values(?, ?, ?, ?, ?, ?)";
        try (Connection conn = SQLUtil.getConnection()) {
            PreparedStatement st = conn.prepareStatement(sql);
            ServiceUtil.ServiceAddToSQL(st, service);   // 将用户数据输入SQL语句
            st.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    // 是否有新消息
    public static boolean hasNewServiceByUserId(String userId) {
        String sql = "select userId from service where userId = ? and orRead = 0 and orSend = 1";
        try (Connection conn = SQLUtil.getConnection()) {
            PreparedStatement st = conn.prepareStatement(sql);
            st.setString(1, userId);
            try (ResultSet rs = st.executeQuery()) {
                if  (rs.next()) {
                    return true;
                }
            } catch (Exception inEx) {
                inEx.printStackTrace();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
    // 获取新增信息
    public static List<ServiceBean> getNewServiceByUserId(String userId) {
        String getService = "select * from service where userId = ? and orSend = 1 and orRead = 0";
        String overRead = "update service set orRead = 1 where userId = ?";
        List<ServiceBean> serviceList = new LinkedList<ServiceBean>();
        try (Connection conn = SQLUtil.getConnection()) {
            PreparedStatement st = conn.prepareStatement(getService);
            st.setString(1, userId);
            try (ResultSet rs = st.executeQuery()) {
                while (rs.next()) {
                    serviceList.add(ServiceUtil.ResultToService(rs));
                }
            } catch (Exception inEx) {
                inEx.printStackTrace();
            }
            st = conn.prepareStatement(overRead);
            st.setString(1, userId);
            st.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        Collections.sort(serviceList);
        return serviceList;
    }

}
