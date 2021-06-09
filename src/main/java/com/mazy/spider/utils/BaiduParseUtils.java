package com.mazy.spider.utils;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;

import java.net.URLDecoder;

/**
 * @className: BaiduParseUtils
 * @description:
 * @auther: Mazy
 * @create: 2021-05-28 03:51
 */

public class BaiduParseUtils {

    /**
     * 解析百度图片网址
     *"ippr":"http" =>ippr
     * "_z2C$q":":"
     * "AzdH3F":"/"
     * "_z&e3B":"."
     * @param url 传入的图片加密网址，参数以ippr开头，以_z&e3B3r2结尾
     * @return 返回真实的网络图片地址
     */
    public static String decode(String url) {
        String myUrl = "";
        //myUrl = url.replace("ippr", "http");
        myUrl = url.replace("_z2C$q", ":");
        myUrl = myUrl.replace("AzdH3F", "/");
        myUrl = myUrl.replace("_z&e3B", ".");
       // myUrl = myUrl.toLowerCase();
        char[] arr = myUrl.toCharArray();
        myUrl = "";
        for (char c : arr) {
            switch (c) {
                case 'w':
                    myUrl += "a";
                    break;
                case 'k':
                    myUrl += "b";
                    break;
                case 'v':
                    myUrl += "c";
                    break;
                case '1':
                    myUrl += "d";
                    break;
                case 'j':
                    myUrl += "e";
                    break;
                case 'u':
                    myUrl += "f";
                    break;
                case '2':
                    myUrl += "g";
                    break;
                case 'i':
                    myUrl += "h";
                    break;
                case 't':
                    myUrl += "i";
                    break;
                case '3':
                    myUrl += "j";
                    break;
                case 'h':
                    myUrl += "k";
                    break;
                case 's':
                    myUrl += "l";
                    break;
                case '4':
                    myUrl += "m";
                    break;
                case 'g':
                    myUrl += "n";
                    break;
                case '5':
                    myUrl += "o";
                    break;
                case 'r':
                    myUrl += "p";
                    break;
                case 'q':
                    myUrl += "q";
                    break;
                case '6':
                    myUrl += "r";
                    break;
                case 'f':
                    myUrl += "s";
                    break;
                case 'p':
                    myUrl += "t";
                    break;
                case '7':
                    myUrl += "u";
                    break;
                case 'e':
                    myUrl += "v";
                    break;
                case 'o':
                    myUrl += "w";
                    break;
                case '8':
                    myUrl += "1";
                    break;
                case 'd':
                    myUrl += "2";
                    break;
                case 'n':
                    myUrl += "3";
                    break;
                case '9':
                    myUrl += "4";
                    break;
                case 'c':
                    myUrl += "5";
                    break;
                case 'm':
                    myUrl += "6";
                    break;
                case '0':
                    myUrl += "7";
                    break;
                case 'b':
                    myUrl += "8";
                    break;
                case 'l':
                    myUrl += "9";
                    break;
                case 'a':
                    myUrl += "0";
                    break;
                default:
                    myUrl += c;
                    break;
            }
        }
        myUrl = myUrl.replace("%30", "%3A");
        myUrl = myUrl.replace("%2s", "%2F");
//        myUrl = myUrl.replace("%nA", ":");
//        myUrl = myUrl.replace("%dF", "/");
        return myUrl;
    }

    public static HttpEntity getHeaders(){
        HttpHeaders headers = new HttpHeaders();
        headers.set("Accept","text/plain, */*; q=0.01");
        //  headers.set("Accept-Encoding","gzip, deflate, br");
        headers.set("Accept-Language","zh-CN,zh;q=0.9");
        headers.set("Host","image.baidu.com");
        headers.set("User-Agent","Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/89.0.4389.90 Safari/537.36");
        headers.set("X-Requested-With","XMLHttpRequest");
        headers.set("Connection","keep-alive");
        return new HttpEntity<>(headers);
    }
    public static String getRquestUrl(Integer pageNumber, Integer pageSize, Integer width, Integer height, String keyWord){
        StringBuffer sb = new StringBuffer("https://image.baidu.com/search/acjson?tn=resultjson_com&ipn=rj&ct=201326592&is=&fp=result&cl=2&lm=-1&ie=utf-8&oe=utf-8&st=-1&z=&ic=0&face=0&istype=2&qc=&nc=1");
        sb.append("&pn="+pageNumber);
        sb.append("&rn="+pageSize);
        sb.append("&word="+keyWord);
        sb.append("&pn="+pageNumber);
        if(width!=null&&width>0){
            sb.append(width);
        }
        if(height!=null&&height>0){
            sb.append(height);
        }
        return sb.toString();
    }
    public static String[] decodeObjectUrl(String ObjectUrl,String hoverURL){
        String url1=null;
        String url2 = decode(ObjectUrl);
        String[] urls={url1,url2,hoverURL};
        try {
            urls[0] = URLDecoder.decode(url2.substring(url2.indexOf("src=") + 4, url2.lastIndexOf("&refer")), "utf-8");
        }catch (Exception e){

        }
        return urls;
    }
}
