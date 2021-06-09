package com.mazy.spider.utils;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * @className: DownloadUtils
 * @description:
 * @auther: Mazy
 * @create: 2021-05-30 04:26
 */
public class DownloadUtils {
    private static int BUFFERSIZE = 10 * 1024 * 1024;
    private static String defaultPath = "E:\\image\\spider";
    private static int connectTimeout = 10 * 1000;

    public static void download(int index, String[] objUrls, String fileName, String savePath) throws Exception {
        String realUrl;
        if (objUrls[index] != null) {
            if (index < objUrls.length) {
                realUrl = objUrls[index];
                URL url = new URL(realUrl);
                HttpURLConnection con = (HttpURLConnection) url.openConnection();
                if (con.getResponseCode() != 200 || con.getContentLength() < 1000) {
                    download(++index, objUrls, fileName, savePath);
                } else {
                    con.setConnectTimeout(connectTimeout);
                    String extension = getExtensionByContentType(con.getContentType());
                    BufferedInputStream bis = null;
                    BufferedOutputStream bos = null;
                    String path;
                    if (savePath != null) {
                        path = savePath + "\\" + fileName + extension;
                    } else path = defaultPath + "\\" + fileName + extension;
                    try {
                        bis = new BufferedInputStream(con.getInputStream());
                        bos = new BufferedOutputStream(new FileOutputStream(path));
                        byte[] temp = new byte[BUFFERSIZE];
                        int count;
                        while ((count = bis.read(temp)) != -1) {
                            bos.write(temp, 0, count);
                            bos.flush();
                        }
                    } catch (IOException e) {
                        System.out.println("errorRealUrl:" + realUrl);
                        errorFileDel(path);
                        download(++index, objUrls, fileName, savePath);
                    } finally {
                        if (null != bos) {
                            bos.close();
                        }
                        if (null != bis) {
                            bis.close();
                        }
                        if (null != con) {
                            con.disconnect();
                        }
                    }
                }
            }
        } else {
            if (index < objUrls.length - 1) {
                download(++index, objUrls, fileName, savePath);
            } else throw new RuntimeException();
        }


    }

    public static void errorFileDel(String outputFile) {
        File errorFile = new File(outputFile);
        if (errorFile.exists()) {
            errorFile.delete();
        }
    }

    public static String getExtensionByContentType(String contentType) {
        switch (contentType) {
            case "image/jpeg":
                return ".jpg";
            case "image/png":
                return ".png";
            case "image/gif":
                return ".gif";
            default:
                return ".jpg";
        }
    }

    public static String getFilePath(String savePath, String keyWord) {
        String path;
        if (savePath != null) {
            path = savePath + "\\" + keyWord;
        } else path = defaultPath + "\\" + keyWord;
        new File(path).mkdirs();
        return path;
    }
}
