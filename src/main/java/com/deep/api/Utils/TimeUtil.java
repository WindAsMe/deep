package com.deep.api.Utils;

import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class TimeUtil {
    // 获取下一天
    public static Date getNextDay(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.DAY_OF_MONTH, 1);
        date = calendar.getTime();
        return date;
    }

    // 类型转换
    public static Date Translate(Timestamp timestamp) {
        // 将TimeStamp转化为字符串
        String tsStr = "";
        DateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");
        try {
            tsStr = sdf.format(timestamp);
            System.out.println("tsStr = " + tsStr);
        } catch (Exception e) {
            e.printStackTrace();
        }
        // 将字符串转化成为Date类型
        Date date = null;
        try {
            date = sdf.parse(tsStr);
            System.out.println("date = " + date.toString());
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return date;
    }
}