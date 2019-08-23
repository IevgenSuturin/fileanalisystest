package ua.ysuturin.task1;

import static org.junit.Assert.assertTrue;

import org.junit.Test;
import org.junit.jupiter.api.DisplayName;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Collections;
import java.util.Random;

public class AppTest
{
    public String GenerateLine(int maxWordLength, int minWordLength, int wordsNumber){
        Random rand = new Random();
        StringBuilder stringBuilder =new StringBuilder();
        stringBuilder.append(String.join("", Collections.nCopies(maxWordLength, "A")));
        stringBuilder.append(" ");
        stringBuilder.append(String.join("", Collections.nCopies(minWordLength, "B")));
        for (int i=0; i<wordsNumber-2; i++){
            stringBuilder.append(" ");
            stringBuilder.append(String.join("",
                    Collections.nCopies(minWordLength+rand.nextInt(maxWordLength-minWordLength), "C")));
        }
        return stringBuilder.toString();
    }

    @Test
    @DisplayName("Review line analysis")
    public void lineStatisticShouldBeDefinedInAProperWay()
    {
        //Given
        final int maxWordLength = 10;
        final int minWordLength = 3;
        final int wordsNumber = 10;
        String line = GenerateLine(maxWordLength, minWordLength, wordsNumber);

        int lineLength = line.length();
        Double averageWordLength = Math.round(100.0*(lineLength-wordsNumber+1.0)/wordsNumber)/100.0;

        //When
        LineStatistics lineStatistics = new LineStatistics(line);

        //Then
        assertTrue( lineStatistics.getLineLength()==lineLength &&
                              lineStatistics.getMaxWordLength() == maxWordLength &&
                              lineStatistics.getMinWordLength() == minWordLength &&
                              Math.round(100*lineStatistics.getAverageWordLength())/100.0 == averageWordLength
        );
    }

    @Test
    public void allLinesStatisticsFileShouldBeAggregated(){
        final int numGivenLines = 18;
        int numCalculatedLines = 0;
        try {
            File fileouput = new File("resources/tmpfile.txt");
            if(!fileouput.exists()){
                fileouput.createNewFile();
            }
            PrintWriter pw = new PrintWriter(new FileWriter(fileouput));

            //Given
            final int maxWordLength = 10;
            final int minWordLength = 3;
            final int maxWordsNumber = 20;

            Random random = new Random();
            for (int i=0; i<numGivenLines; i++)
            {
                String line = GenerateLine(maxWordLength, minWordLength, random.nextInt(maxWordsNumber));
                pw.println(line);
            }
            pw.close();

            FileLinesReader fileLinesReader = new FileLinesReader(fileouput.getAbsolutePath());
            numCalculatedLines = fileLinesReader.GetFileLines().size();
            fileouput.delete();

        }catch (IOException e){
            System.out.println(e.getMessage());
        }
        //Then
        assertTrue( numGivenLines==numCalculatedLines);
    }
}
