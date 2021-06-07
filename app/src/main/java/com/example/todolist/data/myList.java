package com.example.todolist.data;

public class myList {
    private  int id;
    private String taskname;
    private String Details;
    private int state;

    public myList()
    {

    }

    public myList(int id, String taskname, String details, int state) {
        this.id = id;
        this.taskname = taskname;
        Details = details;
        this.state = state;
    }

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTaskname() {
        return taskname;
    }

    public void setTaskname(String taskname) {
        this.taskname = taskname;
    }

    public String getDetails() {
        return Details;
    }

    public void setDetails(String details) {
        Details = details;
    }
}
