package com.example.providerapi;


import com.example.fallback.DepartServiceFallback;
import com.example.vo.DepartVO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.util.List;


// 注意，接口名与方法名可以随意
// 参数指定了要访问的提供者微服务名称
//@FeignClient(url ="http://127.0.0.1:8081", value="abcmsc-provider-depart", path = "/provider/depart")
//@FeignClient(url ="${feign.client.url}", value="abcmsc-provider-depart", path = "/provider/depart")
@FeignClient( value="feign-nacos-provider-modules", path = "/provider/depart",fallback = DepartServiceFallback.class)
public interface DepartService {
    @PostMapping("/save")
    boolean saveDepart(@RequestBody DepartVO depart);

    @DeleteMapping("/del/{id}")
    boolean removeDepartById(@PathVariable("id") int id);

    @PutMapping("/update")
    boolean modifyDepart(@RequestBody DepartVO depart);

    @GetMapping("/get/{id}")
    DepartVO getDepartById(@PathVariable("id") int id);

    @GetMapping("/list")
    List<DepartVO> listAllDeparts();
}
