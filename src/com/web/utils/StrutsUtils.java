package com.web.utils;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

/**
 * Struts2工具类
 * @author yyq
 */
public class StrutsUtils {
    /**
     * 解析请求的Json数据
     * @param request
     * @return
     * @throws IOException
     */
    public String getRequestData(HttpServletRequest request) throws IOException {
        int contentLength = request.getContentLength();
        if(contentLength<0){
            return null;
        }
        byte buffer[] = new byte[contentLength];
        for (int i = 0; i < contentLength;) {
            int len = request.getInputStream().read(buffer, i, contentLength - i);
            if (len == -1) {
                break;
            }
            i += len;
        }
        return new String(buffer, "utf-8");
    }
}
