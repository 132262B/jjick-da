package app.jjickda.global.utils;

import app.jjickda.domain.user.dto.response.User;
import lombok.experimental.UtilityClass;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@UtilityClass
public class SessionUtil {

    private static final String SESSION_USER = "user";

    /**
     * 세션에 저장한 정보를 return 한다.
     * @param key 세션에 저장한 key
     * @return Object
     */
    public static Object getAttribute(String key) {
        return RequestContextHolder.getRequestAttributes().getAttribute(key, RequestAttributes.SCOPE_SESSION);
    }

    /**
     * 세션에 담긴 User 정보를 return 한다.
     * @return User
     */
    public static User getUserAttribute() {
        return (User) RequestContextHolder.getRequestAttributes().getAttribute(SESSION_USER, RequestAttributes.SCOPE_SESSION);
    }

    /**
     * 세션에 key로 value를 저장한다.
     * @param key 세션에 저장한 key
     * @param object 세션에 담을 value
     */
    public static void setAttribute(String key, Object object) {
        RequestContextHolder.getRequestAttributes().setAttribute(key, object, RequestAttributes.SCOPE_SESSION);
    }

    /**
     * User 데이터를 받아, 세션에 저장한다.
     * @param user User 정보
     */
    public static void setUserAttribute(User user) {
        RequestContextHolder.getRequestAttributes().setAttribute(SESSION_USER, user, RequestAttributes.SCOPE_SESSION);
    }

    /**
     * 세션에 key 로 저장한 정보를 삭제한다.
     * @param key 세션에 저장한 key
     */
    public static void removeAttribute(String key) {
        RequestContextHolder.getRequestAttributes().removeAttribute(key, RequestAttributes.SCOPE_SESSION);
    }

    /**
     * 세션에 모든 데이터를 삭제한다.
     */
    public static void invalidate() {
        HttpServletRequest req = ((ServletRequestAttributes)RequestContextHolder.getRequestAttributes()).getRequest();
        HttpSession session = req.getSession();
        session.invalidate();
    }

    /**
     * 세션ID를 return 한다.
     * @return SessionID
     */
    public static String getSessionId() {
        return RequestContextHolder.getRequestAttributes().getSessionId();
    }
}