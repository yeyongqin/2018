package com.web.action;

import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.TypeReference;
import com.web.service.IndexService;
import com.web.utils.StrutsUtils;
import com.web.vo.Index;
import com.opensymphony.xwork2.ActionSupport;
import com.web.service.IndexServiceImpl;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;
import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.rest.DefaultHttpHeaders;
import org.apache.struts2.rest.HttpHeaders;

import javax.servlet.http.HttpServletRequest;
import javax.websocket.*;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CopyOnWriteArraySet;

/**
 *指标信息管理
 * @author yyq
 */
//定义返回 success 时重定向到 index Action
@Results(@Result(name="success"
        , type="redirectAction"
        , params = {"actionName" , "index"}))
//设置websocket的路径，运用该技术进行只对规定用户页面进行时时刷新
@ServerEndpoint(value = "/websocket")
public class IndexController extends ActionSupport implements ServletRequestAware {
    //concurrent包的线程安全Set，用来存放每个客户端对应的MyWebSocket对象。
    private static CopyOnWriteArraySet<IndexController> webSocketSet = new CopyOnWriteArraySet <IndexController>();
    //这个session不是Httpsession，相当于用户的唯一标识，用它进行与指定用户通讯
    private  javax.websocket.Session session=null;

    //用于存放Index对象类型的list容器
    private List<Index> list;
    //用于接收前端数据
    private HttpServletRequest request;
    //用于与前端交互
    private Map msg=null;

    //定义工具类
    StrutsUtils strutsUtils = new StrutsUtils();
    Index index = new Index();
    Map<String, Object> map = new HashMap<>();
    //调用接口方法
    IndexService indexService = new IndexServiceImpl();

    /**
     * 指标主页,显示所有指标数据
     * @return "index"
     * @throws IOException
     */
    public HttpHeaders index() throws IOException {
        //前端访问显示所有指标信息，后端访问返回状态码200
        if(request.getParameter("page") !=null ){
            //获取前端传来的页数值
            int page = Integer.parseInt(request.getParameter("page"));
            list = indexService.getAllIndexMessage(page);
            //将数据库查询到的list数组存入msg
            map.put("list",list);
            this.setMsg(map);
        }else {
            //向前端发送信息
            sendMessage("update");
            map.put("code",200);
            this.setMsg(map);
        }
        return new DefaultHttpHeaders("index")
                .disableCaching();
    }

    /**
     * 修改指标
     * @return "success"
     * @throws IOException
     */
    public String update() throws IOException {
        //获取前端传来的数据
        Map json = (Map) JSONObject.parse(strutsUtils.getRequestData(request));
        JSONObject jsonObjec = (JSONObject) json.get("data");
        //将JSONObject转为javabean对象
        index = JSONObject.parseObject(jsonObjec.toString(),new TypeReference<Index>() {});
        //调用端口方法更新数据库
        indexService.updateIndexMessage(index);
        addActionMessage("编辑指标成功");
        return "success";
    }

    /**
     * 保存新指标
     * @return "success"
     * @throws IOException
     */
    public String create() throws IOException {
        //获取前端传来的数据
        Map json = (Map) JSONObject.parse(strutsUtils.getRequestData(request));
        JSONObject jsonObjec = (JSONObject) json.get("data");
        //将JSONObject转为javabean对象
        index = JSONObject.parseObject(jsonObjec.toString(),new TypeReference<Index>() {});
        //调用接口方法向数据库存新数据
        indexService.addIndexMessage(index);
        //向前端发送信息
        sendMessage("update");
        //数据存储成功后向前端发送200
        map.put("code",200);
        this.setMsg(map);
        addActionMessage("添加指标成功");
        return "success";
    }

    /**
     * 删除指标
     * @return "success"
     * @throws IOException
     */
    public String destroy() throws IOException {
        //获取前端传来的数据
        Map json = JSONObject.parseObject(strutsUtils.getRequestData(request));
       //将Object对象转为JSONArray
        net.sf.json.JSONArray jsonArray = net.sf.json.JSONArray.fromObject(json.get("id"));
        //调用接口方法删除数据库信息
        indexService.deleteIndexMessage(jsonArray);
        addActionMessage("删除成功");
        return "success";
    }

    /**
     * 显示指定指标，暂时没用到
     * @return "show"
     */
    public HttpHeaders show()
    {
        return new DefaultHttpHeaders("show");
    }

    /**
     * 接收到客户端消息时使用
     * @param message
     * @param session
     */
    @OnMessage
    public void onMessage(String message, Session session){
        System.out.println("Message from " + session.getId() + ": " + message);
        try {
            this.session = session;
            //如果前端用户为管理员则加入set，可以实现实时刷新。（flag=1：管理员   flag=0：用户）
            if(message.equals("1")){
                webSocketSet.add(this);
                session.getBasicRemote().sendText("Connection....");
            }else if(message.equals("webscoketClose")) {
                this.onClose(session);
            }else {
                session.getBasicRemote().sendText("Don't need connection");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 建立websocket连接时调用
     * @param session
     */
    @OnOpen
    public void onOpen(Session session) {
        //websocket连接成功后输出
        System.out.println("Session " + session.getId() + " has opened a connection");
    }

    /**
     * 关闭连接时调用
     * @param session
     */
    @OnClose
    public void onClose(Session session) {
        this.session = session;
        webSocketSet.remove(this);  //从set中删除
        System.out.println("Session " +session.getId()+" has closed!");
    }

    /**
     * 连接发生错误时调用
     * @param session
     * @param t
     */
    @OnError
    public void onError(Session session, Throwable t) {
        t.printStackTrace();
        System.out.println("Session" +session.getId()+" has error");
    }

    /**
     * 发送自定义信号message，告诉前台数据库发生改变了，需要刷新
     * @throws IOException
     */
    public void sendMessage(String message) throws IOException {
        //对存入的对象发送相应的信息
        for (IndexController item : webSocketSet) {
            try {
                item.session.getBasicRemote().sendText(message);
            } catch (IOException e) {
                e.printStackTrace();
                continue;
            }
        }
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
