package Homework_19;


import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class Homework_19 {

    Connection connect;
    Statement statement;

    @BeforeTest
    public void preconditions() {
        try {
            connect = DriverManager.getConnection("jdbc:mysql://db4free.net/testqa11?user=testqa11&password=testqa11");
            statement = connect.createStatement();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Test(priority = 1)
    public void selectAllCompanies_test() throws Exception {
        System.out.println("Select all companies:");
        System.out.println(select("select * from AlesyaCompany"));
    }

    @Test(priority = 2)
    public void selectAllEmployees_test() throws Exception {
        System.out.println("Select all employees:");
        System.out.println(select("select * from AlesyaEmployees"));
    }

    @Test(priority = 3)
    public void selectDataFromBothTables_test() throws Exception {
        System.out.println("Select data from both tables:");
        System.out.println(select("select CompanyName, PersonID, FirstName, LastName, Job from AlesyaCompany inner join AlesyaEmployees on AlesyaCompany.CompanyID=AlesyaEmployees.CompanyID"));
    }

    @Test(priority = 4)
    public void countNumberOfEmployees_test() throws Exception {
        System.out.println("Number of employees in each company:");
        System.out.println(select("select CompanyName, Count(PersonID) as NumberOfEmployees from AlesyaCompany inner join AlesyaEmployees on AlesyaCompany.CompanyID=AlesyaEmployees.CompanyID group by CompanyName order by Count(PersonID) desc;"));
    }

    @Test(priority = 5)
    public void updateEmployees_test() throws Exception {
        System.out.println(update("update AlesyaEmployees set Job = 'AutomationQA' where Job = 'QA'"));
        System.out.println(select("select * from AlesyaEmployees"));
    }

    @Test(priority = 6)
    public void insertIntoCompany_test() throws Exception {
        System.out.println(insert("INSERT INTO AlesyaCompany (CompanyID, CompanyName, Country, City, PostalCode) VALUES (1,'DBC','Georgia','Batumi','23456')"));
        System.out.println(select("select * from AlesyaCompany"));
    }

    @Test(priority = 7)
    public void insertIntoCompanyNullStatement_test() throws Exception {
        System.out.println(insert("INSERT INTO AlesyaCompany (CompanyID, CompanyName, Country, City, PostalCode) VALUES (3,'Company','Country','City', NULL)"));
        System.out.println(select("select * from AlesyaCompany"));
    }

    @Test(priority = 8)
    public void insertIntoCompanyABCZipCode_test() throws Exception {
        System.out.println(insert("INSERT INTO AlesyaCompany (CompanyID, CompanyName, Country, City, PostalCode) VALUES (11,'Extra','Bali','Borisov', 'fgdhtg')"));
        System.out.println(select("select * from AlesyaCompany"));
    }

    @Test(priority = 9)
    public void insertIntoEmployees_test() throws Exception {
        System.out.println(insert("INSERT INTO AlesyaEmployees (`CompanyID`,`PersonID`,`FirstName`,`LastName`,`Job`)VALUES(2, 6, 'Melaniya','Pretty','CEO')"));
        System.out.println(select("select * from AlesyaEmployees"));
    }

    @Test(priority = 10)
    public void insertIntoEmployeesExternalData_test() throws Exception {
        System.out.println(insert("INSERT INTO AlesyaEmployees (`CompanyID`,`PersonID`,`FirstName`,`LastName`,`Job`)VALUES(2, 600, 'Xaab','Empty','Cleaner')"));
        System.out.println(select("select * from AlesyaEmployees"));
    }

    @Test(priority = 11)
    public void deleteDataWithEmptyPostalCode_test() throws Exception {
        System.out.println(delete("delete from AlesyaCompany where PostalCode is NULL"));
        System.out.println(select("select * from AlesyaCompany"));
    }

    @Test(priority = 12)
    public void updateCompany_test() throws Exception {
        System.out.println(update("update AlesyaCompany set PostalCode = '123' where PostalCode like '[a-z]'"));
        System.out.println(select("select * from AlesyaCompany"));
    }

    @Test(priority = 13)
    public void deleteDataFromEmployees_test() throws Exception {
        System.out.println(delete("delete from AlesyaEmployees where FirstName LIKE'X%' AND PersonID LIKE'__0'"));
        System.out.println(select("select * from AlesyaEmployees"));
    }

    @AfterTest(alwaysRun = true)
    public void postconditions() {
        try {
            connect.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public int update(String query) {
        try {
            return statement.executeUpdate(query);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }

    public int insert(String query) {
        try {
            return statement.executeUpdate(query);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }

    public int delete(String query) {
        try {
            return statement.executeUpdate(query);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }

    public List<List<String>> select(String query) throws Exception {
        ResultSet resultSet = statement.executeQuery(query);
        List<List<String>> data = new ArrayList<>();
        while (resultSet.next()) {
            List<String> row = new ArrayList<>();
            for (int index = 1; index <= resultSet.getMetaData().getColumnCount(); index++) {
                row.add(resultSet.getString(index));
            }
            data.add(row);
        }
        return data;
    }
}
