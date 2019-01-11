package server.utils;


import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

/**
 * @Auther: Administrator
 * @Date: 2018/9/30 21:06
 * @Description:
 */
public enum StatusMsg {
    LOGIN_SUCCESS_HINT(1000, "登录成功！"),
    REGISTERED_SUCCESS_HINT(1001, "注册成功！"),
    PASSWORD_ERROR_HINT(1002, "密码错误！请重新输入"),
    UNREGISTERED_HINT(1003, "该用户未注册，请先注册！"),
    REGISTERED_ERROR_HINT(1004, "注册失败！该用户已注册，请登录！"),

    SUBMITORDER_SUCCESS_HINT(1005,"提交订单成功！"),
    SUBMITORDER_ERROR_HINT(1006,"专辑已购买成功，请到用户界面查看！"),

    SERVER_DISCONNECT_ERROR(1010,"服务器断开连接，请重试！");

    private int code;
    private String msg;

    public int getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }

    public static StatusMsg getStateByCode(int code){
        for(StatusMsg s : StatusMsg.values()){
            if(s.getCode()==code){
                return s;
            }
        }
        return null;
    }

    @Override
    public String toString(){
        JSONObject json = new JSONObject();
        json.put("code", code);
        json.put("msg", msg);

        return json.toString();
    }

    public Map<String, Object> toMapDict(){
        Map<String, Object> map = new HashMap<>();
        map.put("code", code);
        map.put("msg", msg);
        return map;
    }
    private StatusMsg(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }
}
