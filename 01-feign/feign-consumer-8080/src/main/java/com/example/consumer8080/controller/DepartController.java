package com.example.consumer8080.controller;


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

    @GetMapping("/get/{id}")
    public DepartVO getHandle(@PathVariable("id") int id) {
        return departService.getDepartById(id);
    }

    @GetMapping("/list")
    public List<DepartVO> listHandle() {
        return departService.listAllDeparts();
    }
}
