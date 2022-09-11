package com.example.consumer8080.controller;


import com.example.vo.DepartVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.List;

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
