package org.example.expert.aspect;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;
import org.springframework.validation.BindingResult;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Aspect
@Slf4j
@Component
public class UserRoleAspect {

    private final ObjectMapper objectMapper = new ObjectMapper();

    @Pointcut("@annotation(org.example.expert.aspect.AdminLogging)")
    private void adminAPI() {
    }

    @Around("adminAPI()")
    public Object doAdminLog(ProceedingJoinPoint joinPoint) throws Throwable {

        //1. Interceptior 사용하여 요청 정보 사전 처리
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.currentRequestAttributes()).getRequest();

        //2. 로깅에 포함되어야 할 Attribute 꺼내기
        Long userId = (Long) request.getAttribute("userId");
        String userRole = (String) request.getAttribute("userRole");
        String requestTime = LocalDateTime.now().format(DateTimeFormatter.ISO_DATE_TIME);
        String requestURL = String.valueOf(request.getRequestURL());
        String requestBody = getRequestBody(joinPoint);

        log.info("[Admin Authentication START] userId = '{}', time = '{}', requestURL = '{}', reqeustBody = '{}'",
                userId, requestTime, requestURL, requestBody);

        //3. 실제 로직 수행
        Object result = joinPoint.proceed();

        //4. 응답 JSON 직렬화
        String responseBody = objectMapper.writeValueAsString(result);

        log.info("[Admin Authentication DONE] userId = '{}', userRole = '{}', responseBody = '{}', ",
                userId, userRole, responseBody);

        //5. 결과 다시 반환하기
        return result;
    }

    private String getRequestBody(JoinPoint joinPoint) {

        try {
            for (Object args : joinPoint.getArgs()) {
                if (isSerializable(args)) {
                    return objectMapper.writeValueAsString(args);
                }
            }
        } catch (JsonProcessingException ex) {
            log.warn("request 직렬화 실패", ex);
        }
        return "{}";
    }

    private boolean isSerializable(Object args) {
        return !(args instanceof HttpServletRequest ||
                args instanceof HttpServletResponse ||
                args instanceof BindingResult);
    }
}
