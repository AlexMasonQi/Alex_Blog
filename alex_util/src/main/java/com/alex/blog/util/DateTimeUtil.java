package com.alex.blog.util;

import com.google.common.collect.Lists;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.time.DateFormatUtils;
import org.apache.commons.lang3.time.DateUtils;

import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * 日期和时间处理方法
 * Created by ibm on 2017/7/28.
 */
public class DateTimeUtil
{

    /**
     * 返回房间小时的列表
     *
     * @return
     */
    public static List<String> getClassHoursList()
    {
        Date nDate = null;
        SimpleDateFormat sdf = new SimpleDateFormat("HH:mm");
        List<String> list = new ArrayList<>();
        Date initDate = getInitDate(nDate);
        initDate.setMinutes(initDate.getMinutes());
        list.add(sdf.format(initDate));
        for (int i = 0; i <= 94; i++)
        {
            initDate.setMinutes(initDate.getMinutes() + 15);
            list.add(sdf.format(initDate));
        }

        return list;
    }

    public static String getUnixStrByYmd2(String str)
    {
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        Date date = null;
        try
        {
            date = df.parse(str);
        }
        catch (ParseException e)
        {
            e.printStackTrace();
        }
        Long unixTime = date.getTime() / 1000;
        return unixTime.toString();
    }

    /**
     * 获得当前日期
     *
     * @return
     */
    public static String getCurrentDate()
    {
        return DateFormatUtils.ISO_DATE_FORMAT.format(System.currentTimeMillis());
    }

    public static String getCurrentDate(Long time)
    {
        return DateFormatUtils.ISO_DATE_FORMAT.format(time);
    }

    /**
     * 获得当前日期和时间
     *
     * @return
     */
    public static String getCurrentDateTime()
    {
        return DateFormatUtils.format(System.currentTimeMillis(), "yyyy-MM-dd HH:mm:ss");
    }

    /**
     * 获得小时分钟
     *
     * @param time
     * @return
     */
    public static String getHourMis(Long time)
    {
        return DateFormatUtils.format(time, "HH:mm");
    }

    /**
     * 获得当前日期的前一天
     *
     * @return
     */
    public static String getDateBeforeCurrentDate()
    {
        Date now = new Date(System.currentTimeMillis());
        Date beforeDay = DateUtils.addDays(now, -1);

        return DateFormatUtils.ISO_DATE_FORMAT.format(beforeDay);
    }

    /**
     * 获得当前日期的前N天
     *
     * @return
     */
    public static String getDateBeforeDaysCurrentDate(int days)
    {
        Date now = new Date(System.currentTimeMillis());
        Date beforeDay = DateUtils.addDays(now, -days);

        return DateFormatUtils.ISO_DATE_FORMAT.format(beforeDay);
    }


    /**
     * 获得当前日期的后一天
     *
     * @return
     */
    public static String getDateAfterCurrentDate()
    {
        Date now = new Date(System.currentTimeMillis());
        Date afterDay = DateUtils.addDays(now, 1);

        return DateFormatUtils.ISO_DATE_FORMAT.format(afterDay);
    }

    /**
     * 根据时间差，获得分钟数
     *
     * @param diff 时间差：毫秒
     * @return
     */
    public static String getDatePoor(long diff)
    {
        StringBuilder sb = new StringBuilder();
        long nd = 1000 * 24 * 60 * 60;
        long nh = 1000 * 60 * 60;
        long nm = 1000 * 60;
        long ns = 1000;

        // 计算差多少小时
        long hour = diff % nd / nh;

        if (0 != hour)
        {
            ;
            sb.append(StringUtils.leftPad("" + hour, 2, '0') + ":");
        }

        // 计算差多少分钟
        long min = diff % nd % nh / nm;

        sb.append(StringUtils.leftPad("" + min, 2, '0') + ":");

        long ss = diff % nd % nh % nm / ns;

        sb.append(StringUtils.leftPad("" + ss, 2, '0'));

        // 计算差多少秒//输出结果
        return sb.toString();
    }

    /**
     * 计算两个日期之间的差
     *
     * @param endDate   结束日期
     * @param startDate 开始日期
     * @return
     */
    public static String getDatePoor(Date endDate, Date startDate)
    {

        // 获得两个时间的毫秒时间差异
        long diff = endDate.getTime() - startDate.getTime();

        return getDatePoor(diff);
    }

    /**
     * 两个日期字符串之间的时间差
     *
     * @param endDate   结束日期
     * @param startDate 开始日期
     * @return
     */
    public static String getDatePoor(String endDate, String startDate)
    {
        String parsePattern = "yyyy-MM-dd HH:mm:ss";
        Date dEndDate = new Date();
        Date dStartDate = dEndDate;

        try
        {
            dEndDate = DateUtils.parseDate(endDate, parsePattern);
            dStartDate = DateUtils.parseDate(startDate, parsePattern);
        }
        catch (ParseException e)
        {
            e.printStackTrace();
        }

        return getDatePoor(dEndDate, dStartDate);
    }

    /**
     * Method description
     *
     * @param endDate
     * @param startDate
     * @return
     */
    public static Integer getDatePoorDiff(String endDate, String startDate)
    {
        String parsePattern = "yyyy-MM-dd HH:mm:ss";
        Date dEndDate = new Date();
        Date dStartDate = dEndDate;

        try
        {
            dEndDate = DateUtils.parseDate(endDate, parsePattern);
            dStartDate = DateUtils.parseDate(startDate, parsePattern);
        }
        catch (ParseException e)
        {
            e.printStackTrace();
        }

        long diff = dEndDate.getTime() - dStartDate.getTime();

        return Long.valueOf(diff).intValue();
    }

    /**
     * 将毫秒转换为日期时间格式
     *
     * @param mills 毫秒
     * @return 日期格式
     */
    public static String getDateTimeByMillis(Long mills)
    {
        return DateFormatUtils.format(mills, "yyyy-MM-dd HH:mm:ss");
    }

    /**
     * 初始化房间小时
     *
     * @param nDate
     * @return
     */
    public static Date getInitDate(Date nDate)
    {
        if (nDate == null)
        {
            nDate = new Date();
            nDate.setHours(0);
            nDate.setMinutes(0);
        }

        return nDate;
    }

    /*
     * 将时间转换为时间戳
     */
    public static String dateToStamp(String s) throws ParseException
    {
        String res;
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date = simpleDateFormat.parse(s);
        long ts = date.getTime();
        res = String.valueOf(ts);
        return res;
    }

    /**
     * 将当前日期yyyy-MM-dd转换成秒 Integer 除以1000
     * 一般用于开始时间
     */
    public static Integer dateToStampdivide(String s)
    {
//        String res;
//        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//        Date date = simpleDateFormat.parse(s);
//        long ts = date.getTime()/1000;
//        res = String.valueOf(ts);
//        return res;
        int result = 0;
        try
        {
            Date dstartDate = DateUtils.parseDate(s, "yyyy-MM-dd HH:mm:ss");
            result = Integer.parseInt(String.valueOf(dstartDate.getTime() / 1000));
        }
        catch (Exception ee)
        {
            throw new UnsupportedOperationException(ee);
        }

        return result;

    }

    public static Integer dateToUnix(String s)
    {
        int result = 0;
        try
        {
            Date dstartDate = DateUtils.parseDate(s, "yyyy-MM-dd");
            result = Integer.parseInt(String.valueOf(dstartDate.getTime() / 1000));
        }
        catch (Exception ee)
        {
            throw new UnsupportedOperationException(ee);
        }
        return result;
    }

    public static Integer dateAfterToUnix(String s)
    {
        int result = 0;
        try
        {
            Date dstartDate = DateUtils.parseDate(s, "yyyy-MM-dd");
            dstartDate = DateUtils.addDays(dstartDate, 1);
            result = Integer.parseInt(String.valueOf(dstartDate.getTime() / 1000));
        }
        catch (Exception ee)
        {
            throw new UnsupportedOperationException(ee);
        }
        return result;
    }

    /*
     * 将时间戳转换为时间
     */
    public static String stampToDate(String s)
    {
        String res;
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        long lt = new Long(s);
        Date date = new Date(lt);
        res = simpleDateFormat.format(date);
        return res;
    }

    /**
     * 获取自2017-07-01后的所有月份
     *
     * @return
     */
    public static List<HashMap<String, String>> getYearMoth()
    {
        ArrayList<HashMap<String, String>> listMap = new ArrayList<>();
        try
        {
            Date endDate = new Date(System.currentTimeMillis());
            Date startDate = DateUtils.parseDate("2017-08-01", "yyyy-MM-dd");
            while (startDate.getTime() < endDate.getTime())
            {
                HashMap<String, String> mapYearMoth = new HashMap<>();
                String yearDate = DateFormatUtils.format(endDate, "yyyyMM");
                mapYearMoth.put("index", yearDate);
                mapYearMoth.put("value", yearDate);
                endDate = DateUtils.addMonths(endDate, -1);
                listMap.add(mapYearMoth);
            }
        }
        catch (ParseException e)
        {
            e.printStackTrace();
        }
        return listMap;

    }

    /**
     * 获取unix十位时间戳
     *
     * @return
     */
    public static Integer getUnixTime()
    {
        Long l = System.currentTimeMillis() / 1000;
        return l.intValue();
    }

    /**
     * @param
     * @return
     * @description 毫秒转换unix十位时间戳
     * @author liyingnan
     * @date 2018/3/15 15:21
     */
    public static Integer millisTimeToUnix(String millisTime)
    {
        return Integer.valueOf(millisTime.substring(0, 10));
    }

    //根据unix获取年月日
    public static String getDateTimeYMD(Integer unix)
    {
        Long millions = (long) unix * 1000;
        return DateFormatUtils.format(millions, "yyyy-MM-dd");
    }

    /*
     * 获取当前时间对应的数据库时间戳
     * */
    public static Integer getTimeAsDb()
    {
        Date date = new Date();
        long ts = date.getTime();
        String inputStr = String.valueOf(ts);
        String inputStr2 = inputStr.substring(0, inputStr.length() - 3);
        return Integer.valueOf(inputStr2);
    }

    /**
     * @description 将Unix十位时间戳转换为日期(日期格式 : yyyy - MM - dd HH : mm : ss)
     */
    public static String unixTimeToDate(Integer unixTimeMills)
    {
        Long temp = (long) unixTimeMills * 1000;
        Timestamp ts = new Timestamp(temp);
        String tsStr = "";
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        try
        {
            tsStr = dateFormat.format(ts);
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        return tsStr;
    }

    /**
     * @description 将Unix十位时间戳转换为日期(日期格式 : yyyy - MM - dd)
     */
    public static String unixTimeToDateStr(Integer unixTimeMills)
    {
        Long temp = (long) unixTimeMills * 1000;
        Timestamp ts = new Timestamp(temp);
        String tsStr = "";
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        try
        {
            tsStr = dateFormat.format(ts);
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        return tsStr;
    }


    /**
     * 将当前日期yyyy-MM-dd转换成秒 Integer
     * 一般用于开始时间
     */
    public static Integer getIStartDate(String startDate) throws ParseException
    {
        if (startDate == null)
        {
            return null;
        }
        Date tempDate = null;
        tempDate = DateUtils.parseDate(startDate, "yyyy-MM-dd");
        return Integer.valueOf(String.valueOf(tempDate.getTime() / 1000));
    }

    /**
     * 将当前日期yyyy-MM-dd 加一天转换成秒 Integer
     * 一般用于结束时间
     */
    public static Integer getIEndDate(String endDate) throws ParseException
    {
        if (endDate == null)
        {
            return null;
        }
        Date tempDate = null;
        tempDate = DateUtils.parseDate(endDate, "yyyy-MM-dd");
        tempDate = DateUtils.addDays(tempDate, 1);
        return Integer.valueOf(String.valueOf(tempDate.getTime() / 1000));
    }

    public static String secToTime(int time)
    {
        String timeStr = null;
        int hour = 0;
        int minute = 0;
        int second = 0;
        if (time <= 0)
        {
            return "00:00";
        }
        else
        {
            minute = time / 60;
            if (minute < 60)
            {
                second = time % 60;
                timeStr = unitFormat(minute) + ":" + unitFormat(second);
            }
            else
            {
                hour = minute / 60;
                if (hour > 99)
                {
                    return "99:59:59";
                }
                minute = minute % 60;
                second = time - hour * 3600 - minute * 60;
                timeStr = unitFormat(hour) + ":" + unitFormat(minute) + ":" + unitFormat(second);
            }
        }
        return timeStr;
    }

    public static String unitFormat(int i)
    {
        String retStr = null;
        if (i >= 0 && i < 10)
        {
            retStr = "0" + Integer.toString(i);
        }
        else
        {
            retStr = "" + i;
        }
        return retStr;
    }

    /**
     * 获得当前日期的前N天
     *
     * @return
     */
    public static String getDateBeforeCurrentDate(int days)
    {
        Date now = new Date(System.currentTimeMillis());
        Date beforeDay = DateUtils.addDays(now, -days);
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return simpleDateFormat.format(beforeDay);
    }

    /**
     * 获取当前日期至当前年份最后一天的所有日期集合
     *
     * @return : java.util.List<java.lang.String>
     * @author:hushihai
     * @date:11:10 2018/5/25
     * @params:[date]
     */
    public static List<String> getToYearEndDays(String date)
    {
        Calendar currCal = Calendar.getInstance();
        Calendar calendar = Calendar.getInstance();
        calendar.clear();
        calendar.set(Calendar.YEAR, currCal.get(Calendar.YEAR));
        calendar.roll(Calendar.DAY_OF_YEAR, -1);
        Date currYearLast = calendar.getTime();
        String lastDay = new SimpleDateFormat("yyyy-MM-dd").format(currYearLast);
        return getDateList(date, lastDay);
    }


    /**
     * 获取开始日期 --> 结束日期之间的所有日期
     *
     * @return : java.util.List<java.lang.String>
     * @author:hushihai
     * @date:11:23 2018/5/25
     * @params:[startDay, lastDay]
     */
    public static List<String> getDateList(String startDay, String lastDay)
    {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        List<String> result = Lists.newArrayList();
        try
        {
            Date start = format.parse(startDay);
            Date end = format.parse(lastDay);
            Calendar tempStart = Calendar.getInstance();
            tempStart.setTime(start);
            Calendar tempEnd = Calendar.getInstance();
            tempEnd.setTime(end);
            while (tempStart.compareTo(tempEnd) <= 0)
            {
                result.add(format.format(tempStart.getTime()));
                tempStart.add(Calendar.DAY_OF_YEAR, 1);
            }
        }
        catch (ParseException e)
        {
            e.printStackTrace();
        }
        return result;
    }

    /**
     * 获取开始日期 --> 获取月日列表
     *
     * @return : java.util.List<java.lang.String>
     * @author:hushihai
     * @date:11:23 2018/5/25
     * @params:[startDay, lastDay]
     */
    public static List<String> getMDList(String startDay, String lastDay)
    {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        SimpleDateFormat format2 = new SimpleDateFormat("MM-dd");
        List<String> result = Lists.newArrayList();
        try
        {
            Date start = format.parse(startDay);
            Date end = format.parse(lastDay);
            Calendar tempStart = Calendar.getInstance();
            tempStart.setTime(start);
            Calendar tempEnd = Calendar.getInstance();
            tempEnd.setTime(end);
            while (tempStart.compareTo(tempEnd) <= 0)
            {
                result.add(format2.format(tempStart.getTime()));
                tempStart.add(Calendar.DAY_OF_YEAR, 1);
            }
        }
        catch (ParseException e)
        {
            e.printStackTrace();
        }
        return result;
    }

    public static List<String> getAllNextYearDays()
    {
        Calendar calendar = Calendar.getInstance();
        Date date = new Date(System.currentTimeMillis());
        calendar.setTime(date);
        calendar.add(Calendar.YEAR, 1);
        date = calendar.getTime();
        calendar.setTime(date);
        int year = calendar.get(Calendar.YEAR);
        calendar.add(Calendar.YEAR, 1);
        int year2 = calendar.get(Calendar.YEAR);
        calendar.clear();
        calendar.set(Calendar.YEAR, year);
        Date firstDay = calendar.getTime();
        calendar.clear();
        calendar.set(Calendar.YEAR, year2);
        calendar.add(Calendar.DATE, -1);
        Date lastDay = calendar.getTime();
        SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd");
        return getDateList(sf.format(firstDay), sf.format(lastDay));
    }

}