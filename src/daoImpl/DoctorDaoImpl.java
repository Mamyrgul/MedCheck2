package daoImpl;

import dao.DoctorDao;
import dao.GenericDao;
import dataBase.DataBase;
import models.Department;
import models.Doctor;
import models.Hospital;

import java.util.ArrayList;
import java.util.List;

public class DoctorDaoImpl implements DoctorDao , GenericDao <Doctor> {

    @Override
    public String add(Long hospitalId, Doctor doctor) {
        Hospital hospital = new HospitalDaoImpl().findHospitalById(hospitalId);
        if (hospital!=null){
            hospital.getDoctors().add(doctor);
            return "Successfully added";
        }
        return "Try again";
    }

    @Override
    public void removeById(Long id) {
      for (Hospital hospital:DataBase.hospitals){
          for (Doctor doctor:hospital.getDoctors()){
              if (doctor.getId().equals(id)){
                  hospital.getDoctors().remove(doctor);
                  System.out.println("Successfully deleted");
                  return;
              }
          }
      }  System.out.println("Doctor not found. Try again.");
    }

    @Override
    public String updateById(Long id, Doctor doctor) {
        for (Hospital hospital:DataBase.hospitals){
            for (Doctor doctor1:hospital.getDoctors()){
                if (doctor1.getId().equals(id)){
                    doctor1.setFirstName(doctor.getFirstName());
                    return "Successfully updated";
                }
            }
        }return "Try again";

    }

    @Override
    public Doctor findDoctorById(Long id) {
      for (Hospital hospital:DataBase.hospitals){
          for (Doctor doctor:hospital.getDoctors()){
              if (doctor.getId().equals(id)){
                  return doctor;
              }
          }
      }return null;
    }

    @Override
    public String assignDoctorToDepartment(Long departmentId, List<Long> doctorsId) {
        for (Hospital hospital:DataBase.hospitals){
            for (Department department:hospital.getDepartments()){
                if (department.getId().equals(departmentId)){
                    List<Doctor> doctors = new ArrayList<>();
                    for (Long doctorId:doctorsId){
                        Doctor doctor = findDoctorById(doctorId);
                            if (doctor!=null){
                                doctors.add(doctor);
                        }
                    }department.getDoctors().addAll(doctors);
                    return "Successfully assigned";
                }
            }
        }return "Try again";
    }

    @Override
    public List<Doctor> getAllDoctorsByHospitalId(Long id) {
        Hospital hospital = new HospitalDaoImpl().findHospitalById(id);
        if (hospital!=null){
            return hospital.getDoctors();
        }
      return null;
    }

    @Override
    public List<Doctor> getAllDoctorsByDepartmentId(Long id) {
        for (Hospital hospital:DataBase.hospitals){
            for (Department department:hospital.getDepartments()){
                if (department.getId().equals(id)){
                    return department.getDoctors();
                }
            }
        }return null;
    }
}
