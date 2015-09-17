/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package librarysystem;

/**
 *
 * @author Shabnil
 */
public class Student {
    private String StudentID;
    private String StudentName;
    private String StudentAddress;
    private String StudentPhoneNumber;
    private String StudentProgram;
    private String StudentStatus;
    private String StudentMajors;
    private String Password;
    
    
    public String getPassword(){
        return Password;
    }
    
    public void setPassword(String s){
        this.Password=s;
    }
    
    public Student (){        
        StudentID = "";
        StudentName = "";
        StudentAddress = "";
        StudentPhoneNumber = "";
        StudentProgram = "";
        StudentStatus = "";
        StudentMajors = "";
    }
    
    public Student(String dat1, String dat2, String dat3, String dat4,String dat5, String dat6, String dat7){
        StudentID = dat1;
        StudentName = dat2;
        StudentAddress = dat3;
        StudentPhoneNumber = dat4;
        StudentProgram = dat5;
        StudentStatus = dat6;
        StudentMajors = dat7;
    }
    
    
    public String getStudentID(){
        return StudentID ;
    }
    public String getStudentName(){
        return StudentName ;
    }
    public String getStudentAddress(){
        return StudentAddress ;
    }
    public String getStudentPhoneNumber(){
        return StudentPhoneNumber ;
    }
    public String getStudentProgram(){
        return StudentProgram ;
    }
    public String getStudentStatus(){
        return StudentStatus ;
    }
    public String getStudentMajors(){
        return StudentMajors ;
    }
    
    public void setStudentID(String dat){
        StudentID = dat;
    }
    
    public void setStudentName(String dat){
        StudentName = dat;
    }
    
    public void setStudentAddress(String dat){
        StudentAddress = dat;
    }
    
    public void setStudentPhoneNumber(String dat){
        StudentPhoneNumber = dat;
    }
    
    public void setStudentProgram(String dat){
        StudentProgram = dat;
    }
    
    public void setStudentStatus(String dat){
        StudentStatus = dat;
    }
    
    public void setStudentMajors(String dat){
        StudentMajors = dat;
    }
    
}
