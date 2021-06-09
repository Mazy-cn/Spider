package com.mazy.spider.utils;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.web.client.RestTemplate;

import java.nio.charset.StandardCharsets;

/**
 * @ClassName: RestTemplateManager
 * @Description:
 * @Auther: Mazy
 * @create: 2019-06-24 15:40
 */
@Configuration
public class RestTemplateManager {
   /* @Resource
    private LoadBalancerInterceptor loadBalancerInterceptor;*/

    /**
     * Time:millisecond
     * @param readtimout
     * @param connectionRequestTimeout
     * @param connectTimeout
     * @return HttpComponentsClientHttpRequestFactory
     */
    public HttpComponentsClientHttpRequestFactory getHttpRequestFactory(int readtimout,int connectionRequestTimeout,int connectTimeout) {
        HttpComponentsClientHttpRequestFactory factory = new HttpComponentsClientHttpRequestFactory();
        factory.setReadTimeout(readtimout);
        factory.setConnectionRequestTimeout(connectionRequestTimeout);
        factory.setConnectTimeout(connectTimeout);
        factory.setBufferRequestBody(false);
        return factory;
    }

    @Bean
    @Scope("prototype")
    public RestTemplate getConfigRestTemplate(HttpComponentsClientHttpRequestFactory factory) {
        RestTemplate restTemplate = new RestTemplate();
        //解决编码问题
        restTemplate.getMessageConverters().set(1, new StringHttpMessageConverter(StandardCharsets.UTF_8));
        restTemplate.setRequestFactory(factory);
        //add loadbalancerInterceptor,list is empty default
      //  restTemplate.getInterceptors().add(loadBalancerInterceptor);
        return restTemplate;
    }

    @Bean
    //@LoadBalanced
    public RestTemplate getRestTemplate() {
        RestTemplate restTemplate = new RestTemplate();
        //解决编码问题
        restTemplate.getMessageConverters().set(1,new StringHttpMessageConverter(StandardCharsets.UTF_8));
/*        SortedMap<String, Charset> stringCharsetSortedMap = Charset.availableCharsets();
        System.out.println(stringCharsetSortedMap.values().toString());
        StringHttpMessageConverter stringHttpMessageConverter = new StringHttpMessageConverter(StandardCharsets.UTF_8);
        stringHttpMessageConverter.setWriteAcceptCharset(true);
        List<MediaType> mediaTypeList = new ArrayList<>();
        mediaTypeList.add(MediaType.TEXT_HTML);
        mediaTypeList.add(MediaType.TEXT_PLAIN);
        stringHttpMessageConverter.setSupportedMediaTypes(mediaTypeList);
        List<HttpMessageConverter<?>> httpMessageConverters = new ArrayList<>();
        httpMessageConverters.add(stringHttpMessageConverter);
        restTemplate.setMessageConverters(httpMessageConverters);*/
/*        MappingJackson2HttpMessageConverter stringHttpMessageConverter = new MappingJackson2HttpMessageConverter();
        List<MediaType> mediaTypeList = new ArrayList<>();
        mediaTypeList.add(MediaType.TEXT_HTML);
        stringHttpMessageConverter.setDefaultCharset(StandardCharsets.UTF_8);
        stringHttpMessageConverter.setSupportedMediaTypes(mediaTypeList);
        List<HttpMessageConverter<?>> httpMessageConverters = new ArrayList<>();
        httpMessageConverters.add(stringHttpMessageConverter);
        restTemplate.setMessageConverters(httpMessageConverters);*/
        //restTemplate.getMessageConverters().add(new MappingJackson2HttpMessageConverter());
       /* for (int i = 0; i < restTemplate.getMessageConverters().size(); i++) {
            if (restTemplate.getMessageConverters().get(i) instanceof StringHttpMessageConverter) {
                restTemplate.getMessageConverters().remove(i);
                restTemplate.getMessageConverters().add(i, stringHttpMessageConverter);
            }
            if(restTemplate.getMessageConverters().get(i) instanceof MappingJackson2HttpMessageConverter){
                try{
                    ((MappingJackson2HttpMessageConverter) restTemplate.getMessageConverters().get(i)).setSupportedMediaTypes(mediaTypeList);
                }catch(Exception e){
                    e.printStackTrace();
                }
            }
        }*/
        return restTemplate;
    }
}
