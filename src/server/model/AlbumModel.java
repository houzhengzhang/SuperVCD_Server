package server.model;

import server.dao.DbUtil;
import server.entity.AlbumInfo;
import server.utils.ResultSetHandler;

import java.sql.Connection;
import java.sql.ResultSet;
import java.util.List;

/**
 * @Auther: Administrator
 * @Date: 2018/10/2 09:39
 * @Description:
 */
public class AlbumModel {

    public static List<AlbumInfo> hazilyQueryByName(String albumName) throws Exception {
        Connection conn = DbUtil.getCon();
        String sql = "select * from album_info where album_name like CONCAT('%',?,'%')";
        String[] param = {albumName};
        ResultSet resultSet = DbUtil.executeQuery(conn, sql, param);
        List<AlbumInfo> albumInfoList = ResultSetHandler.doHandler(resultSet, AlbumInfo.class);
        // 关闭数据库连接
        conn.close();
        return albumInfoList;
    }

    public static int getSingerId(int albumId) throws Exception {
        Connection conn = DbUtil.getCon();
        String sql = "select singer_id from album_info where id=?";
        String[] param = {albumId + ""};
        ResultSet resultSet = DbUtil.executeQuery(conn, sql, param);
        List<AlbumInfo> albumInfoList = ResultSetHandler.doHandler(resultSet, AlbumInfo.class);
        // 关闭数据库连接
        conn.close();
        return albumInfoList.get(0).getSingerId();
    }

    public static List<AlbumInfo> queryByType(String typeName) throws Exception {
        Connection conn = DbUtil.getCon();
        String sql = "select * from album_info where type_id=(select id from music_type where type_name=?)";
        String[] param = {typeName + ""};
        ResultSet resultSet = DbUtil.executeQuery(conn, sql, param);
        List<AlbumInfo> albumInfoList = ResultSetHandler.doHandler(resultSet, AlbumInfo.class);
        // 关闭数据库连接
        conn.close();
        return albumInfoList;
    }

    /**
     * 查询多条专辑信息
     * @param albumIdArr
     * @return
     * @throws Exception
     */
    public static List<AlbumInfo> queryByIdArr(int[] albumIdArr) throws Exception {
        Connection conn = DbUtil.getCon();
        String sql = "select * from album_info where id in ";
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append("(");
        for (int i = 0; i < albumIdArr.length; i++) {
            stringBuffer.append(albumIdArr[i] + ",");
        }
        String arrayStr = stringBuffer.toString().substring(0, stringBuffer.length()-1)+")";
        String[] param = {};
        ResultSet resultSet = DbUtil.executeQuery(conn, sql + arrayStr, param);
        List<AlbumInfo> albumInfoList = ResultSetHandler.doHandler(resultSet, AlbumInfo.class);
        // 关闭数据库连接
        conn.close();
        return albumInfoList;
    }
}
