package com.example.baidumap.util;

/**
 * LocationUtils:根据经纬度计算两点之间的距离
 *
 * @author zhangxiaoxiang
 * @date: 2019/07/16
 */
public class LocationUtils {
    /**
     * 极半径
     * 从地心到北极或南极的距离，大约3950英里(6356.9088千米)（两极的差极小，可以忽略）。
     * 赤道半径
     * 是从地心到赤道的距离，大约3963英里(6377.830千米)。
     * 平均半径
     * 大约3959英里(6371.393千米) 。这个数字是地心到地球表面所有各点距离的平均值。
     * 可以这样求：平均半径=(赤道半径×2+极半径)/3
     * 地球半径有时被使用作为距离单位, 特别是在天文学和地质学中常用。它通常用RE表示。
     * 地球大概半径6370.856千米。
     *
     * 这里取的是地球半径 6378.137
     */
    private static double EARTH_RADIUS = 6378.137;

    private static double rad(double d) {
        return d * Math.PI / 180.0;
    }

    /**
     * 通过经纬度获取距离(单位：米)
     *
     * 因为很多地图的提供商都是提供10位小数的金纬度值,float是单精度浮点数有效数字8位,在7位就开始四舍五入了,并且写法还得带f结尾或者强转,只是CPU更喜欢单精度
     * 双精度浮点数有效数字16位,写法简单粗暴实用
     *
     * @param lat1 经度
     * @param lng1 纬度
     * @param lat2 经度2
     * @param lng2 纬度2
     * @return 距离(米)
     */
    public static double getDistanceM(double lat1, double lng1, double lat2,
                                     double lng2) {
        double radLat1 = rad(lat1);
        double radLat2 = rad(lat2);
        double a = radLat1 - radLat2;
        double b = rad(lng1) - rad(lng2);
        double s = 2 * Math.asin(Math.sqrt(Math.pow(Math.sin(a / 2), 2)
                + Math.cos(radLat1) * Math.cos(radLat2)
                * Math.pow(Math.sin(b / 2), 2)));
        s = s * EARTH_RADIUS;
        s = Math.round(s * 10000d) / 10000d;
        s = s * 1000;
        return s;
    }

    /**
     * 通过经纬度获取距离(单位：千米)
     * @param lat1 经度
     * @param lng1 纬度
     * @param lat2 经度2
     * @param lng2 纬度2
     * @return 距离(公里)Km
     */
    public static double getDistanceKm(double lat1, double lng1, double lat2,
                                     double lng2) {
        double distanceM = getDistanceM(lat1, lng1, lat2, lng2);
        double distanceKm =distanceM/1000;
        return distanceKm;
    }

    public static void main(String[] args) {
        double distance = getDistanceKm(29.57, 106.55,
                31.22, 107.50);
        System.out.println("距离" + distance + "公里");
    }

}
