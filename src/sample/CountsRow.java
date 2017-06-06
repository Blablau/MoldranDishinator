package sample;

import javafx.beans.property.SimpleStringProperty;

/**
 * Created by JessePinkman on 06.06.2017.
 */
public class CountsRow {
    private SimpleStringProperty timeStamp;
    private SimpleStringProperty maxDishCount;
    private SimpleStringProperty maxWOWCount;
    private SimpleStringProperty maxNOCount;
    private SimpleStringProperty maxKappaCount;
    private SimpleStringProperty maxSubCount;
    private SimpleStringProperty maxCalcCount;
    private SimpleStringProperty maxDWGCount;
    private SimpleStringProperty maxAllinCount;
    private SimpleStringProperty maxRubCount;

    public CountsRow(String timeStamp, String maxDishCount, String maxWOWCount, String maxNOCount, String maxKappaCount, String maxSubCount, String maxCalcCount, String maxDWGCount, String maxAllinCount, String maxRubCount) {
        this.timeStamp = new SimpleStringProperty(timeStamp);
        this.maxDishCount = new SimpleStringProperty(maxDishCount);
        this.maxWOWCount = new SimpleStringProperty(maxWOWCount);
        this.maxNOCount = new SimpleStringProperty(maxNOCount);
        this.maxKappaCount = new SimpleStringProperty(maxKappaCount);
        this.maxSubCount = new SimpleStringProperty(maxSubCount);
        this.maxCalcCount = new SimpleStringProperty(maxCalcCount);
        this.maxDWGCount = new SimpleStringProperty(maxDWGCount);
        this.maxAllinCount = new SimpleStringProperty(maxAllinCount);
        this.maxRubCount = new SimpleStringProperty(maxRubCount);
    }

    public String getTimeStamp() {
        return timeStamp.get();
    }

    public void setTimeStamp(String timeStamp) {
        this.timeStamp.set(timeStamp);
    }

    public SimpleStringProperty timeStampProperty() {
        return timeStamp;
    }

    public String getMaxDishCount() {
        return maxDishCount.get();
    }

    public void setMaxDishCount(String maxDishCount) {
        this.maxDishCount.set(maxDishCount);
    }

    public SimpleStringProperty maxDishCountProperty() {
        return maxDishCount;
    }

    public String getMaxWOWCount() {
        return maxWOWCount.get();
    }

    public void setMaxWOWCount(String maxWOWCount) {
        this.maxWOWCount.set(maxWOWCount);
    }

    public SimpleStringProperty maxWOWCountProperty() {
        return maxWOWCount;
    }

    public String getMaxNOCount() {
        return maxNOCount.get();
    }

    public void setMaxNOCount(String maxNOCount) {
        this.maxNOCount.set(maxNOCount);
    }

    public SimpleStringProperty maxNOCountProperty() {
        return maxNOCount;
    }

    public String getMaxKappaCount() {
        return maxKappaCount.get();
    }

    public void setMaxKappaCount(String maxKappaCount) {
        this.maxKappaCount.set(maxKappaCount);
    }

    public SimpleStringProperty maxKappaCountProperty() {
        return maxKappaCount;
    }

    public String getMaxSubCount() {
        return maxSubCount.get();
    }

    public void setMaxSubCount(String maxSubCount) {
        this.maxSubCount.set(maxSubCount);
    }

    public SimpleStringProperty maxSubCountProperty() {
        return maxSubCount;
    }

    public String getMaxCalcCount() {
        return maxCalcCount.get();
    }

    public void setMaxCalcCount(String maxCalcCount) {
        this.maxCalcCount.set(maxCalcCount);
    }

    public SimpleStringProperty maxCalcCountProperty() {
        return maxCalcCount;
    }

    public String getMaxDWGCount() {
        return maxDWGCount.get();
    }

    public void setMaxDWGCount(String maxDWGCount) {
        this.maxDWGCount.set(maxDWGCount);
    }

    public SimpleStringProperty maxDWGCountProperty() {
        return maxDWGCount;
    }

    public String getMaxAllinCount() {
        return maxAllinCount.get();
    }

    public void setMaxAllinCount(String maxAllinCount) {
        this.maxAllinCount.set(maxAllinCount);
    }

    public SimpleStringProperty maxAllinCountProperty() {
        return maxAllinCount;
    }

    public String getMaxRubCount() {
        return maxRubCount.get();
    }

    public void setMaxRubCount(String maxRubCount) {
        this.maxRubCount.set(maxRubCount);
    }

    public SimpleStringProperty maxRubCountProperty() {
        return maxRubCount;
    }

    @Override
    public String toString() {
        return "CountsRow{" +
                "timeStamp=" + timeStamp +
                ", maxDishCount=" + maxDishCount +
                ", maxWOWCount=" + maxWOWCount +
                ", maxNOCount=" + maxNOCount +
                ", maxKappaCount=" + maxKappaCount +
                ", maxSubCount=" + maxSubCount +
                ", maxCalcCount=" + maxCalcCount +
                ", maxDWGCount=" + maxDWGCount +
                ", maxAllinCount=" + maxAllinCount +
                ", maxRubCount=" + maxRubCount +
                '}';
    }
}
