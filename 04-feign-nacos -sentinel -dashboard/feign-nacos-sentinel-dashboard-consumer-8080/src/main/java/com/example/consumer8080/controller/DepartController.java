package com.example.consumer8080.controller;


import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.example.consumer8080.util.DepartServiceFallback;
import com.example.providerapi.DepartService;
import com.example.vo.DepartVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/consumer/depart")
public class DepartController {
    @Autowired
    private DepartService departService;

    /**
     *@SentinelResource注解中指定降级方法和降级方法对应的类
     */
    @SentinelResource(fallback = "saveFallback", fallbackClass = DepartServiceFallback.class)
    @PostMapping("/save")
    public boolean saveHandle(@RequestBody DepartVO depart) {
        return departService.saveDepart(depart);
    }

    @DeleteMapping("/del/{id}")
    public void deleteHandle(@PathVariable("id") int id) {
        departService.removeDepartById(id);
    }

    @PutMapping("/update")
    public void updateHandle(@RequestBody DepartVO depart) {
        departService.modifyDepart(depart);
    }

    @SentinelResource(fallback = "listFallback", fallbackClass = DepartServiceFallback.class)
    @GetMapping("/list")
    public List<DepartVO> listHandle() {
        return departService.listAllDeparts();
    }

    /**
     * 定义了降级
     */
    @SentinelResource(value = "errorCountDegradeRule",fallback = "getHandleFallback")
    @GetMapping("/get/{id}")
    public DepartVO getHandle(@PathVariable("id") int id) {
        int i= 1/0;
        return departService.getDepartById(id);
    }

    public DepartVO getHandleFallback(int id, Throwable e) {
        DepartVO departVO = new DepartVO();
        departVO.setId(id);
        departVO.setName("degrade-method-" + id + "-" + e.getMessage());
        return departVO;
    }

}
