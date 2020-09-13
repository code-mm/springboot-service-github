package com.ms.service.component;


import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.web.servlet.error.DefaultErrorAttributes;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.WebRequest;

import java.util.Map;
import java.util.Set;

/**
 * 默认错误返回数据处理
 */

@Component
@Slf4j
public class AppErrorAttributes extends DefaultErrorAttributes {
    @Override
    public Map<String, Object> getErrorAttributes(WebRequest webRequest, boolean includeStackTrace) {
        Map<String, Object> errorAttributes = super.getErrorAttributes(webRequest, includeStackTrace);
        errorAttributes.remove("timestamp");
        errorAttributes.remove("path");
        errorAttributes.remove("timestamp");

        Set<String> strings = errorAttributes.keySet();
        for (String it : strings) {
            Object o = errorAttributes.get(it);
            log.info("key : " + it, "value : " + o);
        }
        return errorAttributes;
    }
}