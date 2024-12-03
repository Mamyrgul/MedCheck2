package dataBase;

public class Counter {

    public static Long departmentId=0L;
    public static Long counterDepartmentId(){
        return ++departmentId;
    }
    public static Long doctorId=0L;
    public static Long counterDoctorId(){
        return ++doctorId;
    }
    public static Long hospitalId=0L;
    public static Long counterHospitalId(){
        return ++hospitalId;
    }
    public static Long patientId=0L;
    public static Long counterPatientId(){
        return ++patientId;
    }
}
