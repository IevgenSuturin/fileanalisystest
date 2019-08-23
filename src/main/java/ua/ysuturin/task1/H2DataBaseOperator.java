package ua.ysuturin.task1;

import java.io.IOException;
import java.io.InputStream;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

public class H2DataBaseOperator {
    private Connection connection;
    private PreparedStatement insertStatement;
    private boolean IsDatabaseInitialized = false;

    public void InitDatabase() throws ClassNotFoundException, SQLException {
        try {
            Class.forName("org.h2.Driver");
            connection = DriverManager.getConnection(
                    "jdbc:h2:mem:testdb",
                    "sa",
                    ""
            );

            Statement statement = connection.createStatement();
            String query = "CREATE TABLE RESULTS " +
                    "(ID INTEGER NOT NULL, " +
                    " MINNUM INTEGER, " +
                    " MAXNUM INTEGER, " +
                    " PRIMARY KEY (ID))";
            statement.execute(query);

            query = "INSERT INTO RESULTS (ID, MINNUM, MAXNUM) VALUES(?, ?, ?)";
            insertStatement = connection.prepareStatement(query);

            IsDatabaseInitialized = true;
        }catch (SQLException e){
            connection.close();
            throw e;
        }
    }

    public void AppendReCord(int id, int min, int max) throws SQLException{
        if(IsDatabaseInitialized)
        {
            try {
                insertStatement.setInt(1, id);
                insertStatement.setInt(2, min);
                insertStatement.setInt(3, max);
                insertStatement.execute();
            }catch (SQLException e){
                connection.close();
                IsDatabaseInitialized=false;
                throw e;
            }
        }

    }

    public List<ResultEntity> SelectRecords() throws SQLException{
        List<ResultEntity> result = new ArrayList<>();
        if(IsDatabaseInitialized) {
            try {
                Statement statement = connection.createStatement();
                String query = "SELECT * FROM RESULTS";
                ResultSet resultSet = statement.executeQuery(query);
                while (resultSet.next()) {
                    System.out.println("Id " + resultSet.getInt(1) +
                            " MINNUM " + resultSet.getInt(2) +
                            " MAXNUM " + resultSet.getInt(3));
                    result.add(new ResultEntity(resultSet.getInt(1),
                                                resultSet.getInt(2),
                                                resultSet.getInt(3)));
                }
            }catch (SQLException e){
                connection.close();
                IsDatabaseInitialized=false;
                throw e;
            }
        }
        return result;
    }
}
