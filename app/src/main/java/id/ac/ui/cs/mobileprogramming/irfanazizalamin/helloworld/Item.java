package id.ac.ui.cs.mobileprogramming.irfanazizalamin.helloworld;

public class Item {
    private String day, task, details;

    public Item(String day, String task, String details) {
        this.day = day;
        this.task = task;
        this.details = details;
    }

    public String getDay() {
        return day;
    }

    public String getTask() {
        return task;
    }

    public String getDetails() {
        return details;
    }
}
