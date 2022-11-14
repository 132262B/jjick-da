package app.jjickda.global.utils;

import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public class SessionUtil {

    public static Object getAttribute(String name) {
        return RequestContextHolder.getRequestAttributes().getAttribute(name, RequestAttributes.SCOPE_SESSION);
    }


    public static void setAttribute(String name, Object object) {
        RequestContextHolder.getRequestAttributes().setAttribute(name, object, RequestAttributes.SCOPE_SESSION);
    }

    public static void removeAttribute(String name) {
        RequestContextHolder.getRequestAttributes().removeAttribute(name, RequestAttributes.SCOPE_SESSION);
    }

    public static void invalidate() {
        HttpServletRequest req = ((ServletRequestAttributes)RequestContextHolder.getRequestAttributes()).getRequest();
        HttpSession session = req.getSession();
        session.invalidate();
    }

    public static String getSessionId() {
        return RequestContextHolder.getRequestAttributes().getSessionId();
    }
}