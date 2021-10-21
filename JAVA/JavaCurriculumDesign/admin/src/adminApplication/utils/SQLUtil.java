package adminApplication.utils;

import adminApplication.dataBean.AdminBean;
import adminApplication.dataBean.GoodsBean;
import adminApplication.dataBean.ServiceBean;
import adminApplication.dataBean.UserBean;

import java.io.File;
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
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
    // 获取数据库连接
    public static Connection getConnection() {
        Connection conn = null;
        try {
            conn = DriverManager.getConnection(URL,USER,PASSWORD);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return conn;
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
    // 获取所有用户
    public static List<UserBean> getAllUser() {
        String sql = "select * from users";
        List<UserBean> userList = new LinkedList<>();
        try (Connection conn = SQLUtil.getConnection()) {
            PreparedStatement st = conn.prepareStatement(sql);
            try (ResultSet rs = st.executeQuery()) {
                while (rs.next())
                    userList.add(UserUtil.ResultToUser(rs));
            } catch (Exception inEx) {
                inEx.printStackTrace();
                return null;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
        return userList;
    }
    //用户 设置 下线
    public static boolean UserOutLine(String userId) {
        String sql = "update Users set orLog = 0 where userId = ?";
        try (Connection conn = SQLUtil.getConnection()) {
            PreparedStatement st = conn.prepareStatement(sql);
            st.setString(1,userId);
            if (st.executeUpdate() > 0) {
                return true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
    // 充值
    public static boolean updateMoney(String userId, Double money) {
        String sql = "update users set money = money + ? where userId = ?";
        try (Connection conn = SQLUtil.getConnection()) {
            PreparedStatement st = conn.prepareStatement(sql);
            st.setDouble(1, money);
            st.setString(2, userId);
            st.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }


    // 获取购买数量
    public static int getPurchaseNumbersByUserId(String userId) {
        String sql = "select userId from goods where purchaserId = ?";
        int count = 0;
        try (Connection conn = SQLUtil.getConnection()) {
            PreparedStatement st = conn.prepareStatement(sql);
            st.setString(1, userId);
            try (ResultSet rs = st.executeQuery()) {
                while (rs.next())
                    count++;
            } catch (Exception inEx) {
                inEx.printStackTrace();
                return 0;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return 0;
        }
        return count;
    }
    // 获取商品数量
    public static int getSaleNumbers(String userId) {
        String sql = "select userId from goods where userId = ?";
        int count = 0;
        try (Connection conn = SQLUtil.getConnection()) {
            PreparedStatement st = conn.prepareStatement(sql);
            st.setString(1, userId);
            try (ResultSet rs = st.executeQuery()) {
                while (rs.next())
                    count++;
            } catch (Exception inEx) {
                inEx.printStackTrace();
                return 0;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return 0;
        }
        return count;
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
    // 从数据库中获取商品 by id
    public static GoodsBean getGoodsByGoodsId(String goodsId) {
        String sql = "select goodsId, registerTime, userId, goodsName, Introduction, goodsPrice, saleOut, outTime, " +
                "purchaserId, imageName, keyWords from goods where goodsId = ?";
        try (Connection conn = SQLUtil.getConnection()) {
            PreparedStatement st = conn.prepareStatement(sql);
            st.setString(1, goodsId);
            try (ResultSet rs = st.executeQuery()) {
                if (rs.next()) {
                    return GoodsUtil.ResultToGoods(rs);
                }
            } catch (Exception inEx) {
                inEx.printStackTrace();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
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
                inEx.printStackTrace();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
    // 获取待检商品
    public static List<GoodsBean> getNeedCheckGoods(int length) {
        List<GoodsBean> goodsList = new LinkedList<>();
        String sql = "select goodsId, registerTime, userId, goodsName, Introduction, goodsPrice, saleOut, outTime, " +
                "purchaserId, imageName, keyWords from goods where orCheck = 0 order by rand() limit ?";
        try (Connection conn = SQLUtil.getConnection()) {
            PreparedStatement st = conn.prepareStatement(sql);
            st.setInt(1, length);
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
    // 通过检验
    public static void passAudit(String goodsId) {
        String sql = "update goods set orCheck = 1 where goodsId = ?";
        try (Connection conn = SQLUtil.getConnection()) {
            PreparedStatement st = conn.prepareStatement(sql);
            st.setString(1, goodsId);
            st.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    // 未通过检验
    public static void noPassAudit(String goodsId, String failReason) {
        String sql = "update goods set orCheck = -1,failReason = ? where goodsId = ?";
        try (Connection conn = SQLUtil.getConnection()) {
            PreparedStatement st = conn.prepareStatement(sql);
            st.setString(1, failReason);
            st.setString(2, goodsId);
            st.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    // 从数据库匹配 keyWords use for discovery
    public static List<GoodsBean> findGoodsByKeyWords(String[] userKeys) {
        String sql = "select goodsId, registerTime, userId, goodsName, Introduction, goodsPrice, saleOut, outTime, " +
                "purchaserId, imageName, keyWords, orCheck, failReason from goods where orCheck = 0 order by rand() limit 1000";
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
    // 获取所有商品信息
    public static List<GoodsBean> getAllGoods() {
        String sql = "select goodsId, registerTime, userId, goodsName, Introduction, goodsPrice, saleOut, outTime, " +
                "purchaserId, imageName, keyWords, orCheck, receiveAddress, failReason from goods";
        List<GoodsBean> goodsList = new LinkedList<>();
        try (Connection conn = SQLUtil.getConnection()) {
            PreparedStatement st = conn.prepareStatement(sql);
            try (ResultSet rs = st.executeQuery()) {
                while (rs.next()) {
                    GoodsBean goods = GoodsUtil.ResultToGoods(rs);
                        goods.setOrCheck(rs.getInt("orCheck"));
                        goods.setReceiveAddress(rs.getString("receiveAddress"));
                        goods.setFailReason(rs.getString("failReason"));
                        goodsList.add(goods);
                }
            } catch (Exception inEx) {
                inEx.printStackTrace();
                return null;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
        return goodsList;
    }
    // 删除商品
    public synchronized static boolean deleteGoodsById(String goodsId) {
        String sql = "delete from goods where goodsId = ?";
        try (Connection conn = SQLUtil.getConnection()) {
            PreparedStatement st = conn.prepareStatement(sql);
            st.setString(1, goodsId);
            st.executeUpdate();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    // 新增客服通信
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
    // 获取消息预览
    public static HashMap<String, Integer> getServicePreview() {
        String receive = "select userId,orRead from service where orSend = 0";
        String send = "select distinct userId from service where orSend = 1 ";
        HashMap<String, Integer> preview = new HashMap<>();
        try (Connection conn = SQLUtil.getConnection()) {
            PreparedStatement st = conn.prepareStatement(receive);
            try (ResultSet rs = st.executeQuery()) {
                while (rs.next()) {
                    String sendUserId = rs.getString("userId");
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
            try (ResultSet rs = st.executeQuery()) {
                while (rs.next()) {
                    String receiveUserId = rs.getString("userId");
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
    // 获取用户消息
    public static List<ServiceBean> getAllServiceByUserId(String userId) {
        String get = "select * from service where userId = ?";
        String overRead = "update service set orRead = 1 where userId = ? and orRead = 0";
        List<ServiceBean> serviceList = new LinkedList<>();
        try (Connection conn = SQLUtil.getConnection()) {
            PreparedStatement st = conn.prepareStatement(get);
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
    // 是否需要更新userVIew
    public static boolean hasNewService() {
        String sql = "select userId from service where orRead = 0 limit 1";
        try (Connection conn = SQLUtil.getConnection()) {
            PreparedStatement st = conn.prepareStatement(sql);
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
    // 是否有新消息
    public static boolean hasNewServiceByUserId(String userId) {
        String sql = "select userId from service where userId = ? and orRead = 0 limit 1";
        try (Connection conn = SQLUtil.getConnection()) {
            PreparedStatement st = conn.prepareStatement(sql);
            st.setString(1, userId);
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
    public static List<ServiceBean> getNewServiceByUserId(String userId) {
        String sql = "select * from service where userId = ? and orRead = 0 ";
        String overRead = "update service set orRead = 1 where userId = ? and orRead = 0";
        List<ServiceBean> serviceList = new LinkedList<>();
        try (Connection conn = SQLUtil.getConnection()) {
            PreparedStatement st = conn.prepareStatement(sql);
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


    // 管理员登录 by Id & password
    public static AdminBean findAdminForLogIn(String adminId, String password) {
        String sql = "select * from admins where adminId = ? and password = ? limit 1";
        try (Connection conn = SQLUtil.getConnection()) {
            PreparedStatement st = conn.prepareStatement(sql);
            st.setString(1,adminId);
            st.setString(2,password);
            try (ResultSet rs = st.executeQuery()) {
                if (rs.next()) {
                    return AdminUtil.ResultToAdmin(rs);
                }
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
    // 管理员登录 by Id & password
    public static AdminBean findAdminById(String adminId) {
        String sql = "select * from admins where adminId = ? limit 1";
        try (Connection conn = SQLUtil.getConnection()) {
            PreparedStatement st = conn.prepareStatement(sql);
            st.setString(1,adminId);
            try (ResultSet rs = st.executeQuery()) {
                if (rs.next()) {
                    return AdminUtil.ResultToAdmin(rs);
                }
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
    // 获取所有用户
    public static List<AdminBean> getAllAdmin() {
        String sql = "select * from admins";
        List<AdminBean> adminList = new LinkedList<>();
        try (Connection conn = SQLUtil.getConnection()) {
            PreparedStatement st = conn.prepareStatement(sql);
            try (ResultSet rs = st.executeQuery()) {
                while (rs.next()) {
                    adminList.add(AdminUtil.ResultToAdmin(rs));
                }
            } catch (Exception inEx) {
                inEx.printStackTrace();
                return null;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
        return adminList;
    }
    // 删除管理员账号
    public static boolean deleteAdminById(String adminId) {
        String sql = "delete from admins where adminId = ?";
        try (Connection conn = SQLUtil.getConnection()) {
            PreparedStatement st = conn.prepareStatement(sql);
            st.setString(1, adminId);
            st.executeUpdate();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
    // 判断管理员信息存在
    public static boolean hasAdmin(String adminId) {
        String sql = "select adminId from admins where adminId = ?";
        try (Connection conn = SQLUtil.getConnection()) {
            PreparedStatement st = conn.prepareStatement(sql);
            st.setString(1, adminId);
            try (ResultSet rs = st.executeQuery()){
                if (!rs.next()) {
                    return false;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return true;
    }
    // 添加用户信息
    public static boolean addAdmin(AdminBean admin) {
        String add = "insert into admins (adminId, password, adminName, adminSex, adminEmail, adminPhone, registerTime) values(?, ?, ?, ?, ?, ?, ?)";
        try (Connection conn = SQLUtil.getConnection()) {
            PreparedStatement st = conn.prepareStatement(add);
            AdminUtil.AdminAddToSQL(st, admin);
            st.executeUpdate();
            return true;
        } catch (SQLException e) {
            DialogBox.error(e);
            return false;
        }
    }
    // 更新用户信息
    public static boolean updateAdmin(AdminBean admin) {
        String sql = "update admins set adminId = ?, password = ?, adminName = ?, adminSex = ?, adminEmail = ?, adminPhone = ?, registerTime = ? where adminId = ?";
        try (Connection conn = SQLUtil.getConnection()) {
            PreparedStatement st = conn.prepareStatement(sql);
            AdminUtil.AdminAddToSQL(st, admin);
            st.setString(8, admin.getAdminId());
            st.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

}
