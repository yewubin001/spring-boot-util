//package com.example.springboot.idempotent;
//
//imports org.springframework.beans.factory.annotation.Autowired;
//imports org.springframework.web.method.HandlerMethod;
//imports org.springframework.web.servlet.HandlerInterceptor;
//imports org.springframework.web.servlet.ModelAndView;
//
//imports javax.servlet.http.HttpServletRequest;
//imports javax.servlet.http.HttpServletResponse;
//imports java.lang.reflect.Method;
//
///**
// * 接口幂等性拦截器
// */
//public class ApiIdempotentInterceptor implements HandlerInterceptor {
//
//    @Autowired
//    private TokenService tokenService;
//
//    @Override
//    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {
//        if (!(handler instanceof HandlerMethod)) {
//            return true;
//        }
//
//        HandlerMethod handlerMethod = (HandlerMethod) handler;
//        Method method = handlerMethod.getMethod();
//
//        ApiIdempotent methodAnnotation = method.getAnnotation(ApiIdempotent.class);
//        if (methodAnnotation != null) {
//            check(request);// 幂等性校验, 校验通过则放行, 校验失败则抛出异常, 并通过统一异常处理返回友好提示
//        }
//
//        return true;
//    }
//
//    private void check(HttpServletRequest request) {
//        tokenService.checkToken(request);
//    }
//
//    @Override
//    public void postHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, ModelAndView modelAndView) throws Exception {
//    }
//
//    @Override
//    public void afterCompletion(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, Exception e) throws Exception {
//    }
//}