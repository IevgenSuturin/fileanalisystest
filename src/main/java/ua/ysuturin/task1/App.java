package ua.ysuturin.task1;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.*;

import static jdk.nashorn.internal.objects.NativeMath.max;

public class App
{
    public static void main( String[] args )
    {
        try {
            String userDir= new File("resources/file.txt").getAbsolutePath();
            List<String> lines = new FileReaderLineByLine(userDir).GetFileLines();
            H2DataBaseOperator dataBaseOperator = new H2DataBaseOperator();
            dataBaseOperator.InitDatabase();
            int i=0;
            for (String line:lines) {
                List<String> words = new ArrayList<String>( Arrays.asList(line.split(" ")));
                OptionalInt maxLength = words.stream().mapToInt(String::length).max();
                OptionalInt minLength = words.stream().mapToInt(String::length).min();
                dataBaseOperator.AppendReCord(++i, minLength.orElse(0), maxLength.orElse(0));
            }
            dataBaseOperator.SelectRecords();
        }catch ( ClassNotFoundException | SQLException | IOException e){
            System.out.println(e.getMessage());
        }
    }
}
