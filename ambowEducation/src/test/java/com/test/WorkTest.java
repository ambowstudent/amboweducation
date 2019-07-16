package com.test;

import com.ambowEducation.dao.WorkMapper;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Map;

public class WorkTest extends BaseTest {

    @Autowired
    private WorkMapper workMapper;

    @Test
    public void selectEveryTypeCountByTechId(){
        List<Map<String, Object>> maps = workMapper.selectEveryTypeCountByTechId(4);
        for (Map<String, Object> map:
             maps) {
            System.out.println(map.get("num"));
        }
    }
}
