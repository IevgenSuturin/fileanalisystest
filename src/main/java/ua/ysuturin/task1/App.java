package ua.ysuturin.task1;

import java.io.File;
import java.io.IOException;
import java.sql.SQLException;
import java.util.*;

import static jdk.nashorn.internal.objects.NativeMath.max;

public class App
{
    public static void main( String[] args )
    {
        try {
            String userDir= new File("resources/file.txt").getAbsolutePath();
            List<String> lines = new FileLinesReader(userDir).GetFileLines();
            H2DataBaseOperator dataBaseOperator = new H2DataBaseOperator();
            dataBaseOperator.InitDatabase();
            for (String line:lines) {
                LineStatistics lineStatistics = new LineStatistics(line);
                dataBaseOperator.AppendReCord(lineStatistics);
            }
            for (ResultEntity entity:dataBaseOperator.SelectRecords()) {
                System.out.println(entity.toString());
            };
            dataBaseOperator.FinalizeDatabase();
        }catch ( ClassNotFoundException | SQLException | IOException e){
            System.out.println(e.getMessage());
        }
    }
}
