package ua.ysuturin.task1;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class FileLinesReader {
    private String fileName;

    public FileLinesReader(String fileName) {
        this.fileName = fileName;
    }

    public List<String> GetFileLines() throws FileNotFoundException {
        List<String> lines = new ArrayList<>();

        try  (Scanner scanner = new Scanner(new File(fileName)))
        {
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                if (!line.isEmpty()) {
                    lines.add(line);
                }
            }
        }
        return lines;
    }
}
