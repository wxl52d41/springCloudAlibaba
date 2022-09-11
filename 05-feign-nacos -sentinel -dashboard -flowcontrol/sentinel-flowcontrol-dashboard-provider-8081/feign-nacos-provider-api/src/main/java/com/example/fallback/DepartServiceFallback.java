package com.example.fallback;

import com.example.providerapi.DepartService;
import com.example.vo.DepartVO;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

// 降级类：实现了Feign接口
@Component
//@RequestMapping("/fallback/provider/depart")  // 其开头必须是/fallback
public class DepartServiceFallback implements DepartService {
    @Override
    public boolean saveDepart(DepartVO depart) {
        System.out.println("执行saveDepart()的服务降级处理方法");
        return false;
    }

    @Override
    public boolean removeDepartById(int id) {
        System.out.println("执行removeDepartById()的服务降级处理方法");
        return false;
    }

    @Override
    public boolean modifyDepart(DepartVO depart) {
        System.out.println("执行modifyDepart()的服务降级处理方法");
        return false;
    }

    @Override
    public DepartVO getDepartById(int id) {
        System.out.println("执行getDepartById()的服务降级处理方法");
        DepartVO depart = new DepartVO();
        depart.setId(id);
        depart.setName("degrade-feign");
        return depart;
    }

    @Override
    public List<DepartVO> listAllDeparts() {
        System.out.println("执行listAllDeparts()的服务降级处理方法");
        return null;
    }
}
