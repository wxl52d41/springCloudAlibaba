package com.example.providermodules.controller;


import com.example.vo.DepartVO;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;


@RequestMapping("/provider/depart")
@RestController
public class DepartController {


    @PostMapping("/save")
    public boolean saveHandle(@RequestBody DepartVO DepartVO) {
        System.out.println("DepartVO = " + DepartVO);
        return true;
    }

    @DeleteMapping("/del/{id}")
    public boolean deleteHandle(@PathVariable("id") int id) {
        System.out.println("id = " + id);
        return true;
    }

    @PutMapping("/update")
    public boolean updateHandle(@RequestBody DepartVO DepartVO) {
        System.out.println("DepartVO = " + DepartVO);
        return true;
    }

    @GetMapping("/get/{id}")
    public DepartVO getHandle(@PathVariable("id") int id) throws InterruptedException {
        TimeUnit.MILLISECONDS.sleep(500);
        DepartVO departVO = new DepartVO();
        departVO.setId(1);
        departVO.setName("liming");
        return departVO;
    }

    @GetMapping("/list")
    public List<DepartVO> listHandle() {
        return new ArrayList<>();
    }


}
