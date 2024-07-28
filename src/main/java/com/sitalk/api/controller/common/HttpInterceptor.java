package com.sitalk.api.controller.common;

//import io.jsonwebtoken.Claims;
//import io.jsonwebtoken.Jws;
//import io.jsonwebtoken.Jwts;
//import io.jsonwebtoken.security.Keys;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.Nullable;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;
import com.sitalk.api.config.ConfigBean;

import javax.crypto.SecretKey;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.nio.charset.StandardCharsets;
import java.util.Date;

/**
 * packageName      : com.teamsoft.api.controller.common
 * fileName         : HttpInterceptor
 * description      :
 * ======================================================
 * DATE                     AUTHOR                  NOTE
 * ======================================================
 * 2023-07-05               최재혁 (9876006)         최초생성
 */

@Slf4j
public class HttpInterceptor implements HandlerInterceptor {

    @Autowired
    private ConfigBean configBean;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
            throws Exception {

        log.info("***************************** START *****************************");
        log.info("======================== preHandler START =======================");

        if (request.getMethod().equals(("OPTIONS"))) {
            return true;
        }

//        String jwtToken = request.getHeader("Authorization");
//        log.info("jwtToken = {}", jwtToken);

//        if (jwtToken == null || jwtToken.isEmpty() || jwtToken.equals("Bearer undefined") || jwtToken.equals("Bearer")) {
//            log.info("토큰 없음!");
//            response.setStatus(401);
//            return false;
//        }

//        String pureToken = jwtToken.substring(7);
//        log.info("토큰값 체크! = {}", pureToken);

        try {
//            byte[] keyBytes = configBean.getJwtSecret().getBytes(StandardCharsets.UTF_8);
//            SecretKey key = Keys.hmacShaKeyFor(keyBytes);
//
//
//            Jws<Claims> claimsJws = Jwts.parserBuilder()
//                    .setSigningKey(key)
//                    .build()
//                    .parseClaimsJws(pureToken);
//
//            Claims claims = claimsJws.getBody();
//            String username = claims.getSubject();
//            Date expirationDate = claims.getExpiration();
//
//            Date now = new Date();
//
//            log.info("check token value = {}", claims);
//            log.info("uesrname = {}", username);
//            log.info("expirationDate = {}", expirationDate);


            /*
            MemberDto memberDto = MemberDto.builder()
                    .username(username)
                    .email((String) claims.get("email"))
                    .loginType((String) claims.get("loginType"))
                    .build();

            requestScopeBean.setData(memberDto);


            log.info("요청객체 상태확인 = {}", requestScopeBean.getData());
             */


//            if (expirationDate.before(new Date())) {
//                log.info("토큰 유효날짜가 지났습니다. 다시 로그인을 요청하도록 하세요");
//                response.setStatus(401);
//                return false;
//            } else {
//                log.info("통과");
//            }

        } catch(Exception e) {
            e.printStackTrace();
            response.setStatus(401);
            return false;
        }



        //세션이 있는지 처리
        /*
        if (sessionUserInfo == null || !StringUtils.hasText(sessionUserInfo.getEmp_no())) {
            throw new ApiException(ErrorCodeEnum.NOT_FOUND_SESSION);
        }
        * */
        
        //메뉴에 대한 URL 요청에 대한 로그적재처리
        /*
        Optional<MenuEnum> option = Arrays.stream(MenuEnum.values())
                .filter(menu -> menu.getUrl().equals(request.getRequestURI()))
                .findFirst();

        log.debug("option = {}", option);

        if (option.isPresent()) {
            log.debug("option.get() = {}, CommUtils.getUserIp(request) = {}", option.get(), CommUtils.getUserIp(request));
            logService.insertLog(option.get(), CommUtils.getUserIp(request));
        }*/
        
        log.info("Request URL: : {}", request.getRequestURI());
        log.info("======================== preHandler END =========================");
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
                            @Nullable ModelAndView modelAndView) throws Exception {
        //log.info("======================== postHandle START =======================");
        log.info("***************************** END *****************************");
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler,
                                 @Nullable Exception ex) throws Exception {
        //log.info("======================== afterCompletion START =======================");
    }

}
