package com.wugang.controller;

import com.wugang.dao.DepartmentDao;
import com.wugang.dao.EmployeeDao;
import com.wugang.pojo.Department;
import com.wugang.pojo.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.websocket.server.PathParam;
import java.util.Collection;

@Controller
public class EmployeeController {
    @Autowired
    EmployeeDao employeeDao;
    @Autowired
    DepartmentDao departmentDao;

    /**
     * 打开员工列表
     *
     * @param model
     * @return
     */
    @RequestMapping("/emps")
    public String list(Model model) {
        Collection<Employee> allEmployee = employeeDao.getAllEmployee();

        model.addAttribute("emps", allEmployee);

        return "emp/list";
    }

    /**
     * 前往新增员工页面
     *
     * @param model
     * @return
     */
    @GetMapping("/emp")
    public String toAddPage(Model model) {
        //查出所有部门信息
        Collection<Department> departments = departmentDao.getDepartments();
        model.addAttribute("departments", departments);
        return "emp/add";
    }

    /**
     * 添加员工
     *
     * @param employee
     * @return
     */
    @PostMapping("/emp")
    public String addEmp(Employee employee) {
        //保存员工信息
        employeeDao.saveEmployee(employee);
        return "redirect:/emps";
    }

    /**
     * 前往修改员工页面
     *
     * @param empID
     * @return
     */
    @GetMapping("/emp/{id}")
    public String toUpdateEmp(@PathVariable("id") Integer empID, Model model) {
        //根据员工ID查询员工信息
        Employee employee = employeeDao.getEmployeeById(empID);
        model.addAttribute("emp", employee);
        //查出所有部门信息
        Collection<Department> departments = departmentDao.getDepartments();
        model.addAttribute("departments", departments);
        return "emp/update";
    }

    /**
     * 添加员工
     *
     * @param employee
     * @return
     */
    @PostMapping("/updateEmp")
    public String updateEmp(Employee employee) {
        //保存员工信息
        employeeDao.saveEmployee(employee);
        return "redirect:/emps";
    }

    @GetMapping("/delemp/{id}")
    public String deleteEmp(@PathVariable("id") Integer empID){
        employeeDao.deleteEmployeeById(empID);
        return "redirect:/emps";
    }
}
