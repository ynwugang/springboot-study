package com.wugang.config;

import org.springframework.util.StringUtils;
import org.springframework.web.servlet.LocaleResolver;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Locale;

public class MyLocaleContextResolver implements LocaleResolver {
    @Override
    public Locale resolveLocale(HttpServletRequest request) {
        //获取请求中的语言参数
        String language = request.getParameter("l");

        //如果没有就使用默认的
        Locale locale = Locale.getDefault();

        //如果请求的连接携带了国际化的参数
        if (StringUtils.hasLength(language)){
            //zh_CN
            String[] languages = language.split("_");
            //国家，地区
            locale = new Locale(languages[0], languages[1]);
        }

        return locale;
    }

    @Override
    public void setLocale(HttpServletRequest request, HttpServletResponse response, Locale locale) {

    }
}
