package com.mazy.spider.utils;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @className: DateUtils
 * @description:
 * @auther: Mazy
 * @create: 2021-05-30 06:17
 */
public class DateUtils {


    public static String getDateString(){
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyyMMddHHmm");
        return simpleDateFormat.format(new Date());
    }
}
