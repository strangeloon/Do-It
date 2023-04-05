package com.example.anew;
import java.util.ArrayList;

public class TodoList {

    private ArrayList<String> tasks = new ArrayList<String>();

    public void addTask(String task) {
        tasks.add(task);
    }

    public void removeTask(int index) {
        tasks.remove(index);
    }

    public ArrayList<String> getTasks() {
        return tasks;
    }
}
