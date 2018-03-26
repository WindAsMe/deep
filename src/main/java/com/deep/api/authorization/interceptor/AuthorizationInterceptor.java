package com.deep.api.authorization.interceptor;

import com.deep.api.authorization.token.TokenManagerRealization;
import com.deep.api.authorization.token.TokenModel;
import com.deep.api.authorization.tools.Constants;
import com.deep.domain.service.ServiceConfiguration;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;
import redis.clients.jedis.Jedis;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.lang.reflect.Method;

/**
 * 登录识别拦截器, 判断用户的登录信息
 */
@Component
public class AuthorizationInterceptor extends HandlerInterceptorAdapter {

    @Resource
    private TokenManagerRealization tokenManagerRealization;

    /**
     * 预处理拦截器
     * @param request
     * @param response
     * @param handler
     * @return
     * @throws Exception
     */
    public boolean preHandle(HttpServletRequest request,
                             HttpServletResponse response, Object handler) throws Exception {

        // 加相关的回应头

        response.setHeader("Access-Control-Allow-Origin", "http://localhost:8080");
        response.setHeader("Access-Control-Allow-Headers", "Content-Type, Authorization");
        response.setHeader("Access-Control-Allow-Methods", "OPTIONS, GET, PUT, POST, DELETE");
        response.setHeader("Access-Control-Expose-Headers", "Authorization");

        // 如果不是映射到方法可以直接通过
        if (!(handler instanceof HandlerMethod)) {
            return true;
        }
        System.out.println(request.getRequestURI());
        if (request.getRequestURI().equals("/login") || request.getRequestURI().equals("/register") ||
                request.getRequestURI().equals("/allfunction") ||request.getRequestURI().equals("/loginresult")||
                request.getRequestURI().equals("/userAdd") ||request.getRequestURI().equals("/ensurequestion")||
                request.getRequestURI().equals("/phonefind") ||request.getRequestURI().equals("/questionfind")||
                request.getRequestURI().equals("/ensureverify") ||request.getRequestURI().equals("/findpassword")||
                request.getRequestURI().equals("/phonefind")||request.getRequestURI().equals("/ensurequestion") ||
                request.getRequestURI().equals("/error")
                ) {
            return true;
        }
        HandlerMethod handlerMethod = (HandlerMethod)handler;
        Method method = handlerMethod.getMethod();
        // 从header中获取token
        String authorization = request.getHeader(Constants.AUTHORIZATION);

        // 从authorization中获取用户名以及token
        TokenModel model = tokenManagerRealization.getToken(authorization);

        //　TODO 需要采用zr的方法进行验证
        Jedis jedis = new Jedis(ServiceConfiguration.redisServer);
        if(jedis.get(String.valueOf(model.getUserId())) == null){
            // Redis数据库中没有相关的信息, 返回false
            return false;
        } else if (jedis.get(String.valueOf(model.getUserId())) != model.getToken()){
            // 登录成功
            return false;
        }

        return true;
    }
}
