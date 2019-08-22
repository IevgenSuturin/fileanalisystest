package ua.ysuturin.task1;

import java.io.IOException;
import java.io.InputStream;
import java.sql.*;
import java.util.Properties;

public class H2DataBaseOperator {
    private Connection connection;
    private PreparedStatement insertStatement;

    public void ConnectToDatabase() throws ClassNotFoundException, SQLException {
        try {
            Class.forName("org.h2.Driver");
            connection = DriverManager.getConnection(
                    "jdbc:h2:mem:testdb",
                    "sa",
                    ""
            );
        }catch (SQLException e){
            connection.close();
            throw e;
        }
    }

    public void CreateResultTable() throws SQLException{
        try {
            Statement statement = connection.createStatement();
            String query = "CREATE TABLE RESULTS " +
                    "(ID INTEGER NOT NULL, " +
                    " MINNUM INTEGER, " +
                    " MAXNUM INTEGER, " +
                    " PRIMARY KEY (ID))";
            statement.execute(query);
        }catch (SQLException e){
            connection.close();
            throw e;
        }
    }

    public void InitInsertStatement()throws SQLException{
        try {
            String query = "INSERT INTO RESULTS (ID, MINNUM, MAXNUM) VALUES(?, ?, ?)";
            insertStatement = connection.prepareStatement(query);
        }catch (SQLException e){
            connection.close();
            throw e;
        }
    }

    public void AppendReCord(int id, int min, int max) throws SQLException{
        try {
            insertStatement.setInt(1, id);
            insertStatement.setInt(2, min);
            insertStatement.setInt(3, max);
            insertStatement.execute();
        }catch (SQLException e){
            connection.close();
            throw e;
        }
    }

    public void SelectRecords() throws SQLException{
        Statement statement = connection.createStatement();
        String query = "SELECT * FROM RESULTS";
        ResultSet resultSet = statement.executeQuery(query);
        while (resultSet.next()){
            System.out.println("Id "+resultSet.getInt(1)+" MINNUM "+resultSet.getInt(2)+" MAXNUM "+resultSet.getInt(3));
        }
    }
}
