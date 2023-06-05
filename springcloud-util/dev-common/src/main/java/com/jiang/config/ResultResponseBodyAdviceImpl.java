//package com.jiang.config;
//
//import com.jiang.constant.Result;
//import com.jiang.constant.ResultAnnotation;
//import com.jiang.util.ResultUtil;
//import com.sun.istack.internal.NotNull;
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.core.MethodParameter;
//import org.springframework.http.MediaType;
//import org.springframework.http.converter.HttpMessageConverter;
//import org.springframework.http.server.ServerHttpRequest;
//import org.springframework.http.server.ServerHttpResponse;
//import org.springframework.web.bind.annotation.ControllerAdvice;
//import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;
//
///**
// * @author jiangsj
// * @create 2023/6/2
// * @desc
// */
//@Slf4j
//@ControllerAdvice
//public class ResultResponseBodyAdviceImpl implements ResponseBodyAdvice<Object> {
//
//    @Override
//    public boolean supports(@NotNull MethodParameter returnType, @NotNull Class<? extends HttpMessageConverter<?>> converterType) {
//
//        /*支持所有方法*/
//        ResultAnnotation methodAnnotation = returnType.getMethodAnnotation(ResultAnnotation.class);
//        if (methodAnnotation!=null){
//            return methodAnnotation.required();
//        }
//        return false;
//    }
//
//    @Override
//    public Object beforeBodyWrite(Object body,
//                                  @NotNull MethodParameter returnType,
//                                  @NotNull MediaType selectedContentType,
//                                  @NotNull Class<? extends HttpMessageConverter<?>> selectedConverterType,
//                                  @NotNull ServerHttpRequest request,
//                                  @NotNull ServerHttpResponse response) {
//
//        if (log.isDebugEnabled()) {
//            log.debug("ResultResponseBodyAdviceImpl.beforeBodyWrite:{}",body);
//        }
//        if (body instanceof Result) {
//            return body;
//        }
//        return ResultUtil.successData(body);
//    }
//}
