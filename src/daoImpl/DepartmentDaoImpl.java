package daoImpl;

import dao.DepartmentDao;
import dao.GenericDao;
import dataBase.DataBase;
import models.Department;
import models.Hospital;

import java.util.List;

import static dataBase.DataBase.hospitals;

public class DepartmentDaoImpl implements DepartmentDao, GenericDao<Department> {
    @Override
    public List<Department> getAllDepartmentByHospital(Long id) {
        Hospital hospital = new HospitalDaoImpl().findHospitalById(id);
        if (hospital != null) {
            return hospital.getDepartments();
        }
        return null;
    }

    @Override
    public Department findDepartmentByName(String name) {
        for (Hospital hospital : hospitals) {
            List<Department> departments = hospital.getDepartments();
            for (Department department : departments) {
                if (department.getDepartmentName().equalsIgnoreCase(name)) {
                    return department;
                }
            }
        }
        return null;
    }

    @Override
    public String add(Long hospitalId, Department department) {
        Hospital hospital = new HospitalDaoImpl().findHospitalById(hospitalId);
        if (hospital != null) {
            hospital.getDepartments().add(department);
            return "Successfully added";
        }
        return "Try again";
    }
    @Override
    public void removeById(Long id) {
        boolean isRemoved = false;
        for (Hospital hospital : hospitals) {
            isRemoved = hospital.getDepartments().removeIf(department -> department.getId().equals(id));
            if (isRemoved) {
                System.out.println("Successfully deleted");
                return;
            }
        }
        System.out.println("Department not found. Try again.");
    }


    @Override
    public String updateById(Long id, Department department) {
        for (Hospital hospital : DataBase.hospitals) {
            for (Department existingDepartment : hospital.getDepartments()) {
                if (existingDepartment.getId().equals(id)) {
                    existingDepartment.setDepartmentName(department.getDepartmentName());
                    return "Successfully updated";
                }
            }
        }
        return "Try again.";
    }
}
