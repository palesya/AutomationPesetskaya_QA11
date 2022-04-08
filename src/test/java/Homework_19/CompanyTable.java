package Homework_19;

import MySql.InsertHelper;

public class CompanyTable {
    String companyID;
    String companyName;
    String country;
    String city;
    String postalCode;

    public static CompanyTable getCompanyData() {
        return new CompanyTable();
    }

    public String companyData(String companyID, String companyName, String country, String city, String postalCode) {
        this.companyID = companyID;
        this.companyName = companyName;
        this.country = country;
        this.city = city;
        this.postalCode = postalCode;
        return "'"+companyID + "','" + companyName + "','" + country + "','" + city + "','" + postalCode+"'";
    }

    public String companyColumns() {
        return "CompanyID,CompanyName,Country,City,PostalCode";
    }
}
