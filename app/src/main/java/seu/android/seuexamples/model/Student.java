package seu.android.seuexamples.model;

/**
 * Created by Lenovo on 8/25/2017.
 */

public class Student {
    private String userName;
    private String password;
    private Float cgpa;
    private String phoneNo;

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Float getCgpa() {
        return cgpa;
    }

    public void setCgpa(Float cgpa) {
        this.cgpa = cgpa;
    }

    public String getPhoneNo() {
        return phoneNo;
    }

    public void setPhoneNo(String phoneNo) {
        this.phoneNo = phoneNo;
    }

    @Override
    public String toString() {
        return "UserName: "+ userName+"\n"+
                "PhoneNo: "+phoneNo;
    }

    public String toStringForDialog(){
        return "UserName: "+ userName+"\n"+
                "Password: "+ password+"\n"+
                "PhoneNo: "+phoneNo+"\n"+
                "Cgpa: "+ cgpa;
    }
}
