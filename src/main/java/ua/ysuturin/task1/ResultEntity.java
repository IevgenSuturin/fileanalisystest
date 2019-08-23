package ua.ysuturin.task1;

public class ResultEntity {
    private Integer Id;
    private Integer maxNumber;
    private Integer minNumber;

    public Integer getId() {
        return Id;
    }

    public void setId(Integer id) {
        Id = id;
    }

    public Integer getMaxNumber() {
        return maxNumber;
    }

    public void setMaxNumber(Integer maxNumber) {
        this.maxNumber = maxNumber;
    }

    public Integer getMinNumber() {
        return minNumber;
    }

    public void setMinNumber(Integer minNumber) {
        this.minNumber = minNumber;
    }

    public ResultEntity(Integer id, Integer maxNumber, Integer minNumber) {
        Id = id;
        this.maxNumber = maxNumber;
        this.minNumber = minNumber;
    }
}
