package com.example.providermodules.controller;


import com.example.vo.DepartVO;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;


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
    public DepartVO getHandle(@PathVariable("id") int id) {
        return new DepartVO();
    }

    @GetMapping("/list")
    public List<DepartVO> listHandle() {
        return new ArrayList<>();
    }


}
