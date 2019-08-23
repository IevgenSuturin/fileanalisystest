package ua.ysuturin.task1;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class LineStatistics {
    private final String line;
    private Integer Id;
    private Integer maxWordLength;
    private Integer minWordLength;
    private Integer lineLength;
    private Double averageWordLength;

    public LineStatistics(String line){
        this.line=line;
        CalculateStatistics();
    }

    public void CalculateStatistics(){
        List<String> words = new ArrayList<String>( Arrays.asList(line.split(" ")))
                .stream().filter(((Predicate<String>) String::isEmpty).negate())
                .collect(Collectors.toList());
        maxWordLength = words.stream().mapToInt(String::length).max().orElse(0);
        minWordLength = words.stream().mapToInt(String::length).min().orElse(0);
        averageWordLength = words.stream().mapToInt(String::length).average().getAsDouble();
        lineLength = line.length();
    }

    public Integer getMaxWordLength() {
        return maxWordLength;
    }

    public Integer getMinWordLength() {
        return minWordLength;
    }

    public Integer getLineLength() {
        return lineLength;
    }

    public Double getAverageWordLength() {
        return averageWordLength;
    }
}
