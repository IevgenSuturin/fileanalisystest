package ua.ysuturin.task1;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

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
                    " MINWORDLENGTH INTEGER, " +
                    " MAXWORDLENGTH INTEGER, " +
                    " LINELENGTH INTEGER,  " +
                    " AVGLENGTH DOUBLE, "+
                    " PRIMARY KEY (ID))";
            statement.execute(query);

            query="CREATE SEQUENCE id_sequence";
            statement.execute(query);

            query = "INSERT INTO RESULTS (ID, MINWORDLENGTH, MAXWORDLENGTH, LINELENGTH, AVGLENGTH) " +
                    " VALUES(id_sequence.NEXTVAL, ?, ?, ?, ?)";
            insertStatement = connection.prepareStatement(query);

            IsDatabaseInitialized = true;
        }catch (SQLException e){
            FinalizeDatabase();
            throw e;
        }
    }

    public void AppendReCord(LineStatistics lineStatistics) throws SQLException{
        if(IsDatabaseInitialized)
        {
            try {
                insertStatement.setInt(1, lineStatistics.getMinWordLength());
                insertStatement.setInt(2, lineStatistics.getMaxWordLength());
                insertStatement.setInt(3, lineStatistics.getLineLength());
                insertStatement.setDouble(4, lineStatistics.getAverageWordLength());
                insertStatement.execute();
            }catch (SQLException e){
                FinalizeDatabase();
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
                    result.add(new ResultEntity(resultSet.getInt(1),
                                                resultSet.getInt(2),
                                                resultSet.getInt(3),
                                                resultSet.getInt(4),
                                                resultSet.getDouble(5))
                    );
                }
            }catch (SQLException e){
                FinalizeDatabase();
                throw e;
            }
        }
        return result;
    }

    public void FinalizeDatabase() throws SQLException{
        if(IsDatabaseInitialized){
            IsDatabaseInitialized=false;
            connection.close();
        }
    }
}
