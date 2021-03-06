package com.antin.mr.demo.nginx;

import java.io.BufferedReader;
import java.io.FileReader;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.Locale;

/**
 * Created by Administrator on 2017/4/28.
 */
public class LogParser {

    private static final String invalid = "-invalid-";//异常数据

    public static void main(String[] args) {

        try {
            FileReader fileReader = new FileReader("F:\\CommonDevelop\\nginx_log\\test.log");
            BufferedReader br = new BufferedReader(fileReader);
            String line = null;
            while ((line = br.readLine()) != null) {
                NginxLogModel nginxLogModel = parseLine(line);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public static NginxLogModel parseLine(String line) {
        NginxLogModel nginxLogModel = new NginxLogModel();
        try {
            String[] columns = line.split(" ");
            int length = columns.length;

            nginxLogModel.setIpAddr(validItem(columns, 0));//ip
            nginxLogModel.setAccessTime(formatDate(validItem(columns, 3, 1)));//时间
            nginxLogModel.setRequestMethod(validItem(columns, 5, 1));//请求类型GET/POST
            nginxLogModel.setUrl(validItem(columns, 6));
            nginxLogModel.setProtocol(validItem(columns, 7, 0, 1));//http协义 eg:HTTP/1.1
            nginxLogModel.setStatus(validItem(columns, 8));//请求状态码 eg:200
            nginxLogModel.setSize(validItem(columns, 9));
            nginxLogModel.setReference(validItem(columns, 10));

            StringBuffer browserInfo = new StringBuffer();//浏览器信息
            for (int i = 11; i < length - 1; i++)
                browserInfo.append(validItem(columns, i));
            nginxLogModel.setBrowserInfo(browserInfo.toString());

            nginxLogModel.setProxyIp(validItem(columns, length - 1));

        } catch (Exception e) {
            e.printStackTrace();
            nginxLogModel.setValid(false);
        }
        return nginxLogModel;
    }

    public static String validItem(String[] arr, int index) {
        return validItem(arr, index, 0, 0);
    }

    public static String validItem(String[] arr, int index, int start) {
        return validItem(arr, index, start, 0);
    }

    public static String validItem(String[] arr, int index, int start, int end) {
        String validString;
        try {
            validString = arr[index].substring(start, arr[index].length() - end);
        } catch (Exception e) {
            validString = invalid;
            e.printStackTrace();

        }
        return validString;
    }


    /**
     * 日期格式转换
     *
     * @param time
     * @return
     */
    public static SimpleDateFormat df1 = new SimpleDateFormat("dd/MMM/yyyy:HH:mm:ss", Locale.US);
    public static SimpleDateFormat df2 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.US);

    public static String formatDate(String time) {
        try {
            return df2.format(df1.parse(time));
        } catch (ParseException e) {
            return invalid;
        }
    }
}
