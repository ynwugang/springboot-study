package com.wugang.dao;

import com.wugang.pojo.Department;
import com.wugang.pojo.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

/**
 * 员工dao
 */
@Repository
public class EmployeeDao {
    //模拟数据库中的数据
    private static Map<Integer, Employee> employees = null;
    //员工有所属的部门
    @Autowired
    private DepartmentDao departmentDao;

    static {
        //创建一个部门表
        employees = new HashMap<>();
        employees.put(1001, new Employee(1001, "张三", "111@qq.com", 1, new Department(101, "教学部")));
        employees.put(1002, new Employee(1002, "李四", "222@qq.com", 0, new Department(102, "市场部")));
        employees.put(1003, new Employee(1003, "王五", "333@qq.com", 1, new Department(103, "教研部")));
        employees.put(1004, new Employee(1004, "小明", "444@qq.com", 1, new Department(104, "运营部")));
        employees.put(1005, new Employee(1005, "小红", "555@qq.com", 0, new Department(105, "后勤部")));
    }

    //主键自增！
    private static Integer initId = 10086;

    /**
     * 保存员工信息
     *
     * @param employee
     * @return
     */
    public void saveEmployee(Employee employee) {
        if (employee.getId() == null) {
            employee.setId(initId++);
        }
        employee.setDepartment(departmentDao.getDepartmentById(employee.getDepartment().getId()));
        employees.put(employee.getId(), employee);
    }

    /**
     * 查询全部员工信息
     *
     * @return
     */
    public Collection<Employee> getAllEmployee() {
        return employees.values();
    }

    /**
     * 通过id查询员工
     *
     * @param id
     * @return
     */
    public Employee getEmployeeById(Integer id) {
        return employees.get(id);
    }

    /**
     * 通过id删除员工
     *
     * @param id
     */
    public void deleteEmployeeById(Integer id) {
        employees.remove(id);
    }
}
