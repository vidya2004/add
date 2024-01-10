package com.example.performit.Model;

public class TodoModel {
    private String taskTile,Task,tasdate,tasprior,tastat;
    public String gettastat(){return tastat;}
    public void setTastat(String tastat){this.tastat=tastat;}

    public String getTaskTile() {
        return taskTile;
    }

    public void setTaskTile(String taskTile) {
        this.taskTile = taskTile;
    }
    public String getTask() {
        return Task;
    }

    public void setTask(String Task) {
        this.Task = Task;
    }
    public String getTasdate() {
        return tasdate;
    }

    public void setTasdate(String tasdate) {
        this.tasdate= tasdate;
    }
    public String gettasprior() {
        return tasprior;
    }

    public void settasprior(String tasprior) {
        this.tasprior = tasprior;
    }


    }

