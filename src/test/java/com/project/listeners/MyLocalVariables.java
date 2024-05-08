package com.project.listeners;
import java.util.ArrayList;

public class MyLocalVariables { private static ArrayList<String> testTagList=new ArrayList<>();
    private static int activeTestNumber;
    public static void addTestTagToList(String tag)
    {
        testTagList.add(tag);
    }
    public static String getTestTagFromList(int index)
    {
        return testTagList.get(index);
    }

    public static int getActiveTestNumber() {
        return activeTestNumber;
    }

    public static void setActiveTestNumber(int activeTestNumber) {
        activeTestNumber = activeTestNumber;
    }

}