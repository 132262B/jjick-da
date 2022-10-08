package com.jjickda.global.utils;

import lombok.experimental.UtilityClass;
import lombok.extern.slf4j.Slf4j;

import java.time.LocalDate;
import java.time.ZoneId;

/**
 * 날짜 처리와 관련된 Utility Class
 */
@Slf4j
@UtilityClass
public class DateUtil {

//    @UtilityClass 로 아래 로직 추가 안해도 되게 수정.
//    private DateUtil() throws InstantiationException {
//        throw new InstantiationException();
//    }

    /**
     * 오늘 날짜를 YYYY-MM-DD 형태로 return.
     * @return 오늘 날짜(YYYY-MM-DD)
     */
    public String getDate_YYYY_MM_DD() {
        LocalDate now = LocalDate.now();
        return now.toString();
    }

    /**
     * 오늘 날짜를 YYYYMMDD 형태로 return.
     * @return 오늘 날짜(YYYYMMDD)
     */
    public String getDate_YYYYMMDD() {
        LocalDate now = LocalDate.now(ZoneId.of("YYYYmmDD"));
        return now.toString().replaceAll("-","");
    }

}
