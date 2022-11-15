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
            //throw new PagePermissionException("로그인 되기전 페이지에 방문하였습니다.", ErrorCode.HANDLE_ACCESS_DENIED);
            throwNewException("로그인 되기전 페이지에 방문하였습니다.", type);

        }

        Object proceedReturnValue = proceedingJoinPoint.proceed();
        log.info("After invoke getSomeValue()");
        return proceedReturnValue;
    }

    private void checkUser(int roleIdx, Type type) {
        if (roleIdx != Role.USER.getRoleIdx()) {
            if (roleIdx == Role.ADMIN.getRoleIdx()) {
                log.debug("admin은 user권한도 볼 수 있습니다.");
            } else {
                //throw new CustomException("로그인한 사람만 방문할 수 있음.", ErrorCode.HANDLE_ACCESS_DENIED);
                throwNewException("로그인한 사람만 방문할 수 있음", type);
            }
        }
    }

    private void checkAdmin(int roleIdx, Type type) {
        if (roleIdx != Role.ADMIN.getRoleIdx()) {
            //throw new CustomException("어드민만 접속 가능", ErrorCode.HANDLE_ACCESS_DENIED);
            throwNewException("어드민만 접속 가능", type);
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