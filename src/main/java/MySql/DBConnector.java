package MySql;

import Properties.PropertyReader;
import lombok.extern.log4j.Log4j;

import java.sql.*;

@Log4j
public class DBConnector <T>{

    private static ThreadLocal <Connection> connection=new ThreadLocal<>();

    Connection connect;

    private Connection getConnection() {
        try {
            if (connection.get() == null) {
                new PropertyReader(System.getProperty("config"));
                connection.set(DriverManager.getConnection(PropertyReader.getProperties().getProperty("connection")));
            }
            return connection.get();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return connection.get();
    }

    private Statement getStatement() {
        Statement statement=null;
        try {
            statement = getConnection().createStatement();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return statement;
    }

    protected ResultSet executeQuery(String query){
        ResultSet resultSet = null;
        try {
            resultSet = getStatement().executeQuery(query);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return resultSet;
    }

    public int executeUpdate(String query){
        int count=0;
        try {
            count=getStatement().executeUpdate(query);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return count;
    }

    public void closeConnect() {
        try {
            connect.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
