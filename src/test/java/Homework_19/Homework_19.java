package Homework_19;


import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.List;

import static Homework_19.CompanyTable.getCompanyData;
import static Homework_19.EmployeesTable.getEmployeesData;
import static MySql.DeleteHelper.getDelete;
import static MySql.InsertHelper.getInsert;
import static MySql.SelectHelper.getSelect;
import static MySql.UpdateHelper.getUpdate;
import static org.apache.log4j.spi.Configurator.NULL;

public class Homework_19 {

    @Test(priority = 1)
    public void selectCompany_test() throws Exception {
        Assert.assertTrue(getSelect().select("*").from("AlesyaCompany").execute().getListData().size() > 0);
        Assert.assertEquals(getSelect().select("*").from("AlesyaCompany").where("CompanyID=1").execute().getListData().get(0).get(4), "DBC");
    }

    @Test(priority = 2)
    public void selectEmployee_test() throws Exception {
        Assert.assertTrue(getSelect().select("*").from("AlesyaEmployees").execute().getListData().size() > 0);
        Assert.assertEquals(getSelect().select("*").from("AlesyaEmployees").where("PersonID=1").execute().getListData().get(0).get(2), "Alesya");
    }

    @Test(priority = 3)
    public void selectDataFromBothTables_test() throws Exception {
        List<List<String>> query = getSelect().select("CompanyName, PersonID, FirstName, LastName, Job").from("AlesyaCompany inner join AlesyaEmployees on AlesyaCompany.CompanyID=AlesyaEmployees.CompanyID").execute().getListData();
        Assert.assertTrue(query.size() > 0);
        Assert.assertEquals(query.get(0).get(4), "AutomationQA");
    }

    @Test(priority = 4)
    public void countNumberOfEmployees_test() throws Exception {
        List<List<String>> query = getSelect().select("CompanyName, Count(PersonID) as NumberOfEmployees").from("AlesyaCompany inner join AlesyaEmployees on AlesyaCompany.CompanyID=AlesyaEmployees.CompanyID group by CompanyName order by Count(PersonID) desc").execute().getListData();
        Assert.assertTrue(query.size() > 0);
        Assert.assertTrue(Integer.parseInt(query.get(0).get(1)) > Integer.parseInt(query.get(1).get(1)));
    }

    @Test(priority = 5)
    public void updateEmployees_test() throws Exception {
        getUpdate().update("AlesyaEmployees").set("Job = 'QA'").where("FirstName = 'Kate'").execute();
        Assert.assertEquals(getSelect().select("*").from("AlesyaEmployees").where("FirstName = 'Kate'").execute().getListData().get(0).get(4), "QA");
        getUpdate().update("AlesyaEmployees").set("Job = 'Engineer'").where("FirstName = 'Kate'").execute();
        Assert.assertEquals(getSelect().select("*").from("AlesyaEmployees").where("FirstName = 'Kate'").execute().getListData().get(0).get(4), "Engineer");
    }

    @Test(priority = 6)
    public void insertIntoCompany_test() throws Exception {
        getInsert().insert("AlesyaCompany").into(getCompanyData().companyColumns()).values(getCompanyData().companyData("12", "XWR", "Georgia", "Batumi", "123456")).execute();
        Assert.assertEquals(getSelect().select("*").from("AlesyaCompany").where("CompanyID=1").execute().getListData().get(0).get(1), "Georgia");
    }

    @Test(priority = 7)
    public void deleteItemCompany_test() throws Exception {
        getDelete().from("AlesyaCompany").where("CompanyID=12").execute();
        Assert.assertEquals(getSelect().select("*").from("AlesyaCompany").where("CompanyID=12").execute().getListData().size(), 0);
    }

    @Test(priority = 8)
    public void insertIntoCompanyNullStatement_test() throws Exception {
        getInsert().insert("AlesyaCompany").into(getCompanyData().companyColumns()).values(getCompanyData().companyData("3", "Company", "Country", "City", NULL)).execute();
        Assert.assertEquals(getSelect().select("*").from("AlesyaCompany").where("CompanyID=3").execute().getListData().get(0).get(3),NULL);
    }

    @Test(priority = 9)
    public void deleteDataWithEmptyPostalCode_test() throws Exception {
        getDelete().from("AlesyaCompany").where("PostalCode='null'").execute();
        Assert.assertEquals(getSelect().select("*").from("AlesyaCompany").where("PostalCode='null'").execute().getListData().size(), 0);
    }

    @Test(priority = 10)
    public void insertIntoEmployeesExternalData_test() throws Exception {
        getInsert().insert("AlesyaEmployees").into(getEmployeesData().employeesColumns()).values(getEmployeesData().employeesTable("2", "600", "Xaab","Empty","Cleaner")).execute();
        Assert.assertEquals(getSelect().select("*").from("AlesyaEmployees").where("PersonID=600").execute().getListData().get(0).get(2), "Xaab");

    }

    @Test(priority = 11)
    public void deleteDataFromEmployees_test() throws Exception {
        getDelete().from("AlesyaEmployees").where("FirstName LIKE 'X%' AND PersonID LIKE'__0'").execute();
        Assert.assertEquals(getSelect().select("*").from("AlesyaEmployees").where("PersonID=600").execute().getListData().size(), 0);
    }

    @Test(priority = 12)
    public void insertUpdateAndDeleteCompany_test() throws Exception {
        getInsert().insert("AlesyaCompany").into(getCompanyData().companyColumns()).values(getCompanyData().companyData("11", "Extra", "Bali", "Borisov", "fgdhtg")).execute();
        Assert.assertEquals(getSelect().select("*").from("AlesyaCompany").where("CompanyID=11").execute().getListData().get(0).get(2), "Borisov");
        getUpdate().update("AlesyaCompany").set("PostalCode = '123'").where("PostalCode LIKE 'f%'").execute();
        Assert.assertEquals(getSelect().select("*").from("AlesyaCompany").where("CompanyID=11").execute().getListData().get(0).get(3), "123");
        getDelete().from("AlesyaCompany").where("PostalCode = '123'").execute();
        Assert.assertEquals(getSelect().select("*").from("AlesyaCompany").where("CompanyID=11").execute().getListData().size(), 0);
    }

}
