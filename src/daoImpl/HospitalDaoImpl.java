package daoImpl;

import dao.HospitalDao;
import dataBase.DataBase;
import models.Hospital;
import models.Patient;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class HospitalDaoImpl implements HospitalDao {
    @Override
    public String addHospital(Hospital hospital) {
        DataBase.hospitals.add(hospital);
        return "Successfully added";
    }

    @Override
    public Hospital findHospitalById(Long id) {
       return DataBase.hospitals.stream()
               .filter(hospital -> hospital
                       .getId().equals(id))
               .findFirst().orElse(null);
    }

    @Override
    public List<Hospital> getAllHospital() {
        return DataBase.hospitals;
    }

    @Override
    public List<Patient> getAllPatientFromHospital(Long id) {
        Hospital hospital = new HospitalDaoImpl().findHospitalById(id);
        return hospital!=null ? hospital.getPatients() : new ArrayList<>();
    }

    @Override
    public String deleteHospitalById(Long id) {
        Hospital hospital = new HospitalDaoImpl().findHospitalById(id);
        if (hospital != null) {
            DataBase.hospitals.remove(hospital);
            return "Successfully deleted";
        }
        return "Hospital not found. Try again.";
    }

    @Override
    public Map<String, Hospital> getAllHospitalByAddress(String address) {
        Map<String, Hospital> hospitalMap = new HashMap<>();
        for (Hospital hospital : DataBase.hospitals) {
            if (hospital.getAddress().equalsIgnoreCase(address)) {
                hospitalMap.put(hospital.getAddress(), hospital);

            }
        }
        return hospitalMap;
    }
}
