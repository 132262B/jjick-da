package app.jjickda.global.aspect;

import app.jjickda.domain.role.Role;
import app.jjickda.domain.user.dto.response.User;
import app.jjickda.global.annotation.LoginCheck;
import app.jjickda.global.config.exception.CustomException;
import app.jjickda.global.config.exception.ErrorCode;
import app.jjickda.global.config.exception.PagePermissionException;
import app.jjickda.global.config.exception.Type;
import app.jjickda.global.utils.SessionUtil;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;

@Aspect
@Component
@Slf4j
public class LoginCheckAspect {

    @Around("@annotation(app.jjickda.global.annotation.LoginCheck)")
    public Object loginChecks(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
        MethodSignature methodSignature = (MethodSignature) proceedingJoinPoint.getSignature();
        LoginCheck loginCheck = methodSignature.getMethod().getAnnotation(LoginCheck.class);

        User user = (User) SessionUtil.getAttribute("user");
        Type type = loginCheck.type();

        if (user != null) {

            int roleIdx = user.getRoleIdx();
            Role role = loginCheck.auth();

            if (Role.ADMIN.getRoleIdx() == role.getRoleIdx()) {
                checkAdmin(roleIdx, type);
            }

            if (Role.USER.getRoleIdx() == role.getRoleIdx()) {
                checkUser(roleIdx, type);
            }
        } else {
            throwNewException("세션이 없는 사용자가 임의로 페이지의 접근 하였습니다.", type);
        }

        return proceedingJoinPoint.proceed();
    }

    private void checkUser(int roleIdx, Type type) {
        if (roleIdx != Role.USER.getRoleIdx()) {
            if (roleIdx == Role.ADMIN.getRoleIdx()) {
                log.debug("admin은 user권한도 볼 수 있습니다.");
            } else {
                throwNewException("로그인 후 방문 가능한 페이지에 접속.", type);
            }
        }
    }

    private void checkAdmin(int roleIdx, Type type) {
        if (roleIdx != Role.ADMIN.getRoleIdx()) {
            throwNewException("어드민만 방문 가능한 페이지에 접속", type);
        }
    }

    private void throwNewException(String logMessage, Type type) {

        if (type == Type.API) {
            throw new CustomException(logMessage, ErrorCode.HANDLE_ACCESS_DENIED);
        } else {
            throw new PagePermissionException(logMessage, ErrorCode.HANDLE_ACCESS_DENIED);
        }

    }
}