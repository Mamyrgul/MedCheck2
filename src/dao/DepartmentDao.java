package dao;

import models.Department;

import java.util.List;

public interface DepartmentDao {
    List<Department> getAllDepartmentByHospital(Long id);
    Department findDepartmentByName(String name);

    String add(Long hospitalId, Department department);

    void removeById(Long id);

    String updateById(Long id,Department department);
}
