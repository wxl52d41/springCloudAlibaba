@[TOC](RestTemplate使用)

# 一、RestTemplate 概述

在实际开发中需要调用第三方接口数据，或者是调用另外一个服务接口时，我们可以使用`spring` 框架提供的 `RestTemplate` 类可用于在应用中调用 rest 服务，它简化了与 `http` 服务的通信方式，统一了 `restful` 的标准，封装了 `http` 链接， 我们只需要传入 url 及返回值类型即可。

[项目中源码](https://github.com/wxl52d41/springCloudAlibaba/tree/main/00-feign-resttemplate)

# 二、使用步骤

## 2.1 添加依赖

```xml
     <dependency>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter-web</artifactId>
        <version>2.2.9.RELEASE</version>
      </dependency>
```

## 2.2 添加配置类
 将RestTemplate 交给spring容器管理，方便直接使用
```java
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
public class RestTemplateConfig {
    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }
}
```

## 2.3 使用案例

```java
@RestController
@RequestMapping("/consumer/depart")
public class DepartController {
    @Autowired
    private RestTemplate restTemplate;

    private static final String SERVICE_PROVIDER = "http://localhost:8081";

    @PostMapping("/save")
    public boolean saveHandle(@RequestBody DepartVO depart) {
        String url = SERVICE_PROVIDER + "/provider/depart/save";
        return restTemplate.postForObject(url, depart, Boolean.class);
    }

    @DeleteMapping("/del/{id}")
    public void deleteHandle(@PathVariable("id") int id) {
        String url = SERVICE_PROVIDER + "/provider/depart/del/" + id;
        restTemplate.delete(url);
    }

    @PutMapping("/update")
    public void updateHandle(@RequestBody DepartVO depart) {
        String url = SERVICE_PROVIDER + "/provider/depart/update";
        restTemplate.put(url, depart, Boolean.class);
    }

    @GetMapping("/get/{id}")
    public DepartVO getHandle(@PathVariable("id") int id) {
        String url = SERVICE_PROVIDER + "/provider/depart/get/" + id;
        return restTemplate.getForObject(url, DepartVO.class);
    }

    @GetMapping("/list")
    public List<DepartVO> listHandle() {
        String url = SERVICE_PROVIDER + "/provider/depart/list/";
        return restTemplate.getForObject(url, List.class);
    }
}
```

## 2.4 测试
消费者调用http服务接口
![在这里插入图片描述](https://img-blog.csdnimg.cn/0d7b02ea0d304bac99b74aef85495783.png)
服务端接收到请求
![在这里插入图片描述](https://img-blog.csdnimg.cn/84ae5690a4564f6589c5fa4a43fd37ae.png)

## 本文只是简单记录一下，无过多深入拓展

[一文吃透接口调用神器RestTemplate](https://jishuin.proginn.com/p/763bfbd6b359)
[RestTemplate全网最强总结(永久更新)](https://learnku.com/articles/53555)


## 三 实际项目中使用

###  3.1 入参单参数
普通表单默认为 application/x-www-form-urlencoded 类型的请求。

#### 接口文档
入参要求：
请求类型: POST
参数名：parm
参数值为json字符串，内部参数如下

```json
{
    "requestBody": {
        "condition": {
            "currentPageNo": "1",
            "pageSize": "1000"
        }
    }
}
```
![在这里插入图片描述](https://img-blog.csdnimg.cn/943a091d18a041aabaa0d318f2a5f62e.png)

####  java代码发送请求

```java
  private List<SysUnitRelation> queryUnitRelation() throws JsonProcessingException {
        List<SysUnitRelation> datalist = new ArrayList<>();
        Map<String, Object> param = new HashMap<>(2);
        Map<String, Object> requestBody = new HashMap<>(2);
        Map<String, Object> condition = new HashMap<>(4);
        condition.put("currentPageNo", 1);
        condition.put("pageSize", 1000);
        requestBody.put("condition", condition);
        param.put("requestBody", requestBody);
        String body = objectMapper.writeValueAsString(param);
       //①：表单信息，需要放在MultiValueMap中，MultiValueMap相当于Map<String,List<String>>
        MultiValueMap<String, String> postParameters = new LinkedMultiValueMap<>();
        //调用add方法填充表单数据(表单名称:值)
        postParameters.add("parm", body);

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
        HttpEntity<MultiValueMap<String, String>> httpEntity = new HttpEntity<>(postParameters, headers);

        QxbResponse unitRelationResult = restTemplate.postForObject(unitRelationUrl, httpEntity, QxbResponse.class);
        log.info("查询中台经营组织、业务单元、产出线映射关系：" + unitRelationUrl + "结束\n返回结果：" + unitRelationResult);
        String jsonResult = objectMapper.writeValueAsString(unitRelationResult.getContent());
        if (unitRelationResult.isResult() && ObjectUtils.isNotEmpty(unitRelationResult.getContent())) {
            datalist = objectMapper.readValue(jsonResult, new TypeReference<List<SysUnitRelation>>() {
            });
        }
        return datalist;
    }
```

