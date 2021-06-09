package com.mazy.spider;

import org.springframework.boot.test.context.SpringBootTest;

/**
 * @ClassName: Test
 * @Description:
 * @Auther: Mazy
 * @create: 2021-05-21 01:32
 */
@SpringBootTest
public class Test {

    @org.junit.jupiter.api.Test
    public void test() throws Exception {
        /*SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyyMMddHHmm");
        System.out.println(simpleDateFormat.format(new Date()));*/
        /*String encodeStr = URLEncoder.encode("美女","utf-8");
        System.out.println("处理后:" + encodeStr);
        String encodeStr2 = URLEncoder.encode("美女","GB2312");
        System.out.println("处理后2:" + encodeStr2);*/
    }

    @org.junit.jupiter.api.Test
    public void test1() throws Exception{
        String realUrl=null;
        try {
            realUrl = "E:\\image\\spider\\"+"飞龙";
           // String realUrl=  "E:\\image\\spider\\美女\\123.txt";
            /*File file = new File(realUrl);
            if(!file.exists()){
                file.createNewFile();
                System.out.println(realUrl+"创建成功");
            }else System.out.println(realUrl+"已存在");*/
        }catch (Exception e){
            e.printStackTrace();
            System.out.println(realUrl);
        }

    }
    @org.junit.jupiter.api.Test
    public void test2() throws Exception{
    }
}
