package com.example.consumer8080.util;

import com.example.vo.DepartVO;

import java.util.ArrayList;
import java.util.List;

/**
 * @author xlwang55
 * @date 2022/9/6 16:57
 */
public class DepartServiceFallback {

    public static boolean saveFallback(DepartVO depart, Throwable e) {
        System.out.println("getHandle()执行异常 " + depart.toString());
        return false;
    }

    public static List<DepartVO> listFallback() {
        System.out.println("listHandle()执行异常 ");
        DepartVO departVO = new DepartVO();
        List<DepartVO> arrayList = new ArrayList<>();
        departVO.setId(1);
        departVO.setName("no any depart");
        arrayList.add(departVO);
        return arrayList;
    }

}
