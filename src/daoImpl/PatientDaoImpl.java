package daoImpl;

import dao.GenericDao;
import dao.PatientDao;
import dataBase.DataBase;
import models.Hospital;
import models.Patient;

import java.util.*;

public class PatientDaoImpl implements PatientDao , GenericDao<Patient> {
    @Override
    public String add(Long hospitalId, Patient patient) {
        Hospital hospital = new HospitalDaoImpl().findHospitalById(hospitalId);
        if (hospital != null) {
            boolean exists = hospital.getPatients().stream()
                    .anyMatch(p -> p.getId().equals(patient.getId()));
            if (exists) {
                return "Patient with this ID already exists";
            }
            hospital.getPatients().add(patient);
            return "Successfully added";
        }
        return "Hospital not found. Try again";
    }

    @Override
    public void removeById(Long id) {
        for (Hospital hospital: DataBase.hospitals){
            for (Patient patient:hospital.getPatients()){
                if (patient.getId().equals(id)){
                    hospital.getPatients().remove(patient);
                    System.out.println("Successfully deleted");
                    return;
                }
            }
        }System.out.println("Try again");
    }

    @Override
    public String updateById(Long id, Patient patient) {
        for (Hospital hospital:DataBase.hospitals){
            for (Patient patient1:hospital.getPatients()){
                if (patient1.getId().equals(id)){
                    patient1.setFirstName(patient.getFirstName());
                    patient1.setFirstName(patient.getFirstName());
                    patient1.setAge(patient.getAge());
                    patient1.setGender(patient.getGender());
                    System.out.println("Successfully updated");
                }
            }
        }return "Try again";
    }
    @Override
    public String addPatientsToHospital(Long id, List<Patient> patients) {
        Hospital hospital = new HospitalDaoImpl().findHospitalById(id);
        if (hospital!=null){
            hospital.getPatients().addAll(patients);
            return "Successfully added";
        }
        return "Try again";
    }

    @Override
    public Patient getPatientById(Long id) {
        for (Hospital hospital:DataBase.hospitals){
            for (Patient patient:hospital.getPatients()){
                if (patient.getId().equals(id)){
                    return patient;
                }
            }
        }return null;
    }


    @Override
    public Map<Integer, Patient> getPatientByAge() {
        Map<Integer,Patient> patientMap = new HashMap<>();
        for (Hospital hospital:DataBase.hospitals){
            for (Patient patient:hospital.getPatients()){
                patientMap.put(patient.getAge(),patient);
            }
        }return patientMap;
    }

    @Override
    public List<Patient> sortPatientsByAge(String ascOrDesc) {
        List<Patient> patients = new ArrayList<>();
        for (Hospital hospital:DataBase.hospitals){
            patients.addAll(hospital.getPatients());
        }
        if ("asc".equalsIgnoreCase(ascOrDesc)){
            patients.sort(Comparator.comparingInt(Patient::getAge));
        }else if ("desc".equalsIgnoreCase(ascOrDesc)){
            patients.sort(Comparator.comparingInt(Patient::getAge).reversed());
        }else {
            System.out.println("Some problem try again");
        }return patients;
    }
}
