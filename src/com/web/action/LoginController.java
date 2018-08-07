package com.web.action;

import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.TypeReference;
import com.opensymphony.xwork2.ActionSupport;
import com.web.service.LoginService;
import com.web.service.LoginServiceImpl;
import com.web.utils.StrutsUtils;
import com.web.vo.User;
import org.apache.struts2.interceptor.ServletRequestAware;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * 管理登录页面，验证登录信息
 * @author yyq
 */
public class LoginController extends ActionSupport implements ServletRequestAware {
    //用于接收前端数据
    private HttpServletRequest request;
    //用于与前端交互
    private Map msg=null;

    //定义工具类
    StrutsUtils strutsUtils = new StrutsUtils();
    User user = new User();
    //用于存值
    Map<String, Object> map = new HashMap<>();
    //调用接口方法
    LoginService loginService = new LoginServiceImpl();

    /**
     * 登录页面，验证登录信息
     * @return "success"
     * @throws IOException
     */
    public String create() throws IOException {
        //获取前端数据
        Map json = (Map) JSONObject.parse(strutsUtils.getRequestData(request));
        JSONObject jsonObjec = (JSONObject) json.get("data");
        //转为User对象
        user = JSONObject.parseObject(jsonObjec.toString(),new TypeReference<User>() {});
        //根据user从数据库查到的用户信息
        User userMeg = loginService.login(user);
        //如果为查到的数据不为空且与输入的密码一致，向前端发送200，否则发0
        if(userMeg != null && userMeg.getPassword().equals(user.getPassword())){
            map.put("code",200);
            map.put("flag",userMeg.getFlag());
        }else {
            map.put("code",0);
        }
        this.setMsg(map);
        return "success";
    }

    public Map getMsg() {
        return msg;
    }

    public void setMsg(Map msg) {
        this.msg = msg;
    }

    @Override
    public void setServletRequest(HttpServletRequest request) {
        this.request = request;
    }

}
