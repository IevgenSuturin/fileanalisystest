package ua.ysuturin.task1;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class FileReaderLineByLine {
    private String fileName;

    public FileReaderLineByLine(String fileName) {
        this.fileName = fileName;
    }

    public List<String> GetFileLines() throws FileNotFoundException {
        Scanner scanner = new Scanner(new File(fileName));
        List<String> lines = new ArrayList<String>();

        while (scanner.hasNextLine() ){
            String line = scanner.nextLine();
            if(!line.isEmpty()) {
                lines.add(line);
            }
        }
        return lines;
    }
}
