package ua.ysuturin.task1;

public class ResultEntity {
    private Integer Id;
    private Integer maxWordLength;
    private Integer minWordLength;
    private Integer lineLength;
    private Double averageWordLength;

    public ResultEntity(Integer id, Integer maxWordLength, Integer minWordLength, Integer lineLength, Double averageWordLength) {
        Id = id;
        this.maxWordLength = maxWordLength;
        this.minWordLength = minWordLength;
        this.lineLength = lineLength;
        this.averageWordLength = averageWordLength;
    }

    public Integer getId() {
        return Id;
    }

    public void setId(Integer id) {
        Id = id;
    }

    public Integer getMaxWordLength() {
        return maxWordLength;
    }

    public void setMaxWordLength(Integer maxWordLength) {
        this.maxWordLength = maxWordLength;
    }

    public Integer getMinWordLength() {
        return minWordLength;
    }

    public void setMinWordLength(Integer minWordLength) {
        this.minWordLength = minWordLength;
    }

    public Integer getLineLength() {
        return lineLength;
    }

    public void setLineLength(Integer lineLength) {
        this.lineLength = lineLength;
    }

    public Double getAverageWordLength() {
        return averageWordLength;
    }

    public void setAverageWordLength(Double averageWordLength) {
        this.averageWordLength = averageWordLength;
    }

    @Override
    public String toString() {
        return  "Id=" + Id +
                ", maxWordLength=" + maxWordLength +
                ", minWordLength=" + minWordLength +
                ", lineLength=" + lineLength +
                ", averageWordLength=" + averageWordLength ;
    }
}
