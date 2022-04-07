package Homework_19;

public class EmployeesTable {
    String companyID;
    String PersonID;
    String FirstName;
    String LastName;
    String Job;

    public static EmployeesTable getEmployeesData() {
        return new EmployeesTable();
    }

    public String employeesTable(String companyID, String personID, String firstName, String lastName, String job) {
        this.companyID = companyID;
        PersonID = personID;
        FirstName = firstName;
        LastName = lastName;
        Job = job;
        return "'"+companyID + "','" + personID + "','" + firstName + "','" + lastName + "','" + job+"'";
    }

    public String employeesColumns() {
        return "CompanyID, PersonID, FirstName, LastName, Job";
    }
}
