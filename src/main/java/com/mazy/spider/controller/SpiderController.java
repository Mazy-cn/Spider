package com.mazy.spider.controller;

import com.mazy.spider.utils.BaiduParseUtils;
import com.mazy.spider.utils.DownloadUtils;
import com.mazy.spider.utils.RestTemplateManager;
import io.swagger.annotations.ApiOperation;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.apache.commons.lang3.StringUtils;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;

/**
 * @ClassName: SpiderController
 * @Description:
 * @Auther: Mazy
 * @create: 2021-05-16 04:44
 */
@RequestMapping("/spider")
@RestController
public class SpiderController {

    @Resource
    private RestTemplateManager restTemplateManager;

    @ApiOperation(value = "百度图片下载本地")
    @GetMapping(value = "/localDownload/baiduPicture")
    public void startSpider(@RequestParam(value = "start", defaultValue = "1") Integer start,
                            @RequestParam(value = "end", defaultValue = "60") Integer end,
                            @RequestParam(value = "width", defaultValue = "-1") Integer width,
                            @RequestParam(value = "height", defaultValue = "-1") Integer height,
                            @RequestParam(value = "path", required = false) String path,
                            @RequestParam(value = "keyWord") String... keyWord) {
        RestTemplate restTemplate = restTemplateManager.getRestTemplate();
        String keyWords = StringUtils.join(keyWord, "+");
        String filePath = DownloadUtils.getFilePath(path, keyWords);
        for (int i = getTotalPage(start, 60); i <= getTotalPage(end, 60); i++) {
            Integer sequence = (i - 1) * 60 + 1;
            try {
                System.out.println(BaiduParseUtils.getRquestUrl(i, 60, width, height, keyWords));
                ResponseEntity<String> exchange = restTemplate.exchange(BaiduParseUtils.getRquestUrl(i, 60, width, height, keyWords), HttpMethod.GET, BaiduParseUtils.getHeaders(), String.class);
                String body = exchange.getBody();
                JSONObject jsonObj = JSONObject.fromObject(body);
                JSONArray data = jsonObj.getJSONArray("data");
                for (Object v : data) {
                    if (sequence < start) {
                        sequence++;
                        continue;
                    }
                    String filName = sequence.toString();
                    System.out.println(filName);
                    try {
                        JSONObject v1 = (JSONObject) v;
                        String hoverURL = v1.getString("hoverURL");
                        String[] objUrls = BaiduParseUtils.decodeObjectUrl(v1.getString("objURL"),hoverURL);
                        DownloadUtils.download(0, objUrls, filName, filePath);
                        sequence++;
                    } catch (Exception e) {
                        e.printStackTrace();
                        System.out.println("ERRORhoverURL:" + filName);
                    }
                    if (sequence.equals(end+1)) {
                        break;
                    }
                }

            } catch (Exception e) {
                e.printStackTrace();
            }

        }
    }

    @ApiOperation(value = "returnTest", produces = MediaType.TEXT_HTML_VALUE)
    @GetMapping(value = "/returnTest")
    public String returnTest() {
        StringBuilder sb = new StringBuilder();
        sb.append("{\"queryEnc\": \"%C3%C0%C5%AE\",\n" +
                "    \"queryExt\": \"美女\",\n" +
                "    \"listNum\": 1210,}");
        return sb.toString();
    }

    private int getTotalPage(int count, int pageSize) {
        return (count + pageSize - 1) / pageSize;
    }
}
