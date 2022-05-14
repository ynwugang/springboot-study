package com.wugang.dao;

import com.wugang.pojo.Department;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

/**
 * 部门dao
 */
@Repository
public class DepartmentDao {
    //模拟数据库中的数据
    private static Map<Integer, Department> departments = null;
    static {
        //创建一个部门表
        departments = new HashMap<>();
        departments.put(101, new Department(101, "教学部"));
        departments.put(102, new Department(102, "市场部"));
        departments.put(103, new Department(103, "教研部"));
        departments.put(104, new Department(104, "运营部"));
        departments.put(105, new Department(105, "后勤部"));
    }

    /**
     * 获得所有部门信息
     * @return
     */
    public Collection<Department> getDepartments(){
        return departments.values();
    }

    /**
     * 获得指定id的部门信息
     * @param id
     * @return
     */
    public Department getDepartmentById(Integer id){
        return departments.get(id);
    }
}
