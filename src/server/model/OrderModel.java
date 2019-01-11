package server.model;

import server.dao.DbUtil;
import server.entity.OrderInfo;
import server.utils.ResultSetHandler;

import java.sql.Connection;
import java.sql.ResultSet;
import java.util.List;

/**
 * @Auther: Administrator
 * @Date: 2018/10/12 14:19
 * @Description:
 */
public class OrderModel {

    public static int insert(int userId, int[] albumIdArr) {
        int num = 0;
        try {
            Connection conn = DbUtil.getCon();
            String sql = "insert into order_info(user_id, album_id) values(?,?)";
            for (int i = 0; i < albumIdArr.length; i++) {
                String[] param = {String.valueOf(userId), String.valueOf(albumIdArr[i])};
                num += DbUtil.executeUpdate(conn, sql, param);
            }
            // 关闭数据库连接
            conn.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return num;
    }

    public static List<OrderInfo> queryByUserId(int userId) {
        List<OrderInfo> orderInfoList = null;
        try {
            Connection conn = DbUtil.getCon();
            String sql = "select * from order_info where user_id=?";
            String[] param = {String.valueOf(userId)};
            ResultSet resultSet = DbUtil.executeQuery(conn, sql, param);

            orderInfoList = ResultSetHandler.doHandler(resultSet, OrderInfo.class);

            // 关闭数据库连接
            conn.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return orderInfoList;
    }
}
