package server.model;


import org.json.JSONArray;
import server.dao.DbUtil;
import server.entity.MusicInfo;
import server.entity.SingerInfo;
import server.utils.ResultSetHandler;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.util.ArrayList;
import java.util.List;

/**
 * @Auther: Administrator
 * @Date: 2018/9/29 21:29
 * @Description:
 */
public class MusicModel {

    public static List<MusicInfo> queryByType(String typeName) throws Exception {
        // TODO 异常处理的位置
        Connection conn = DbUtil.getCon();
        String sql = "select * from music_info where type_id=(select id from music_type where type_name=?)";
        String[] param = {typeName};
        ResultSet resultSet = DbUtil.executeQuery(conn, sql, param);
        List<MusicInfo> musicInfoList = ResultSetHandler.doHandler(resultSet, MusicInfo.class);
        // 关闭数据库连接
        conn.close();
        // TODO 判断结果为空
        return musicInfoList;
    }

    public static List<MusicInfo> queryByAlbumId(int albumId) throws Exception {
        // TODO 异常处理的位置
        Connection conn = DbUtil.getCon();

        String sql = "select * from music_info where album_id=?";
        String[] param = {albumId + ""};
        ResultSet resultSet = DbUtil.executeQuery(conn, sql, param);
        List<MusicInfo> musicInfoList = ResultSetHandler.doHandler(resultSet, MusicInfo.class);
        // 关闭数据库连接
        conn.close();
        // TODO 判断结果为空
        return musicInfoList;
    }

}
