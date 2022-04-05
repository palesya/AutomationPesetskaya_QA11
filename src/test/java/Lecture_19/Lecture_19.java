package Lecture_19;

import MySql.DBConnector;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import static MySql.SelectHelper.getSelect;

public class Lecture_19 {
    DBConnector dbConnector;

   /* @BeforeTest
    public void preconditions() {
       dbConnector.

    }*

    @Test(priority = 1)
    public void select_test() {


    }

    @Test(priority = 2)
    public void update_test() throws Exception {
        System.out.println(update("update user set age = 100 where id = 1"));
        System.out.println(select("select * from user where id = 1"));
        System.out.println(update("update user set age = 20 where id = 1"));
    }

    @Test(priority = 3)
    public void insert_test() throws Exception {
        System.out.println(insert("insert into pet (id, name, type) values (2, 'Jack', 'Street')"));
        System.out.println(select("select * from pet"));
    }

    @Test(priority = 4)
    public void delete_test() throws Exception {
        System.out.println(delete("delete from pet where id = 2"));
        System.out.println(select("select * from pet"));
    }

    @AfterTest(alwaysRun = true)
    public void closeConnect() {
        dbConnector.closeConnect();
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

    public ResultSet delete(String query) {
        return dbConnector.executeQuery(query);
    }
*/

}
