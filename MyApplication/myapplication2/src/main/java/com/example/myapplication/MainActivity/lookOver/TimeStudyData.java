package com.example.myapplication.MainActivity.lookOver;

import com.example.myapplication.Bean.Answer;
import com.example.myapplication.Bean.DayStudyInfo;
import com.example.myapplication.Bean.Issue;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by 小妖王 on 2017/3/19.
 */

//用来处理按时间查看的的数据
public class TimeStudyData {
    private static ArrayList<DayStudyInfo> allDaysData = new ArrayList<>();
    private static ArrayList<List<CourseLearning>> daysCourseLearning = new ArrayList<>();
    private static ArrayList<List<Issue>> daysIssue = new ArrayList<>();
    private static ArrayList<List<Answer>> daysAnswer = new ArrayList<>();
    private static ArrayList<List<MessageRecord>> daysMessage = new ArrayList<>();

    public static ArrayList<DayStudyInfo> getAllDaysData() {
        return allDaysData;
    }

    public static void setAllDaysData(ArrayList<DayStudyInfo> allDaysData) {
        TimeStudyData.allDaysData = allDaysData;
    }

    public static ArrayList<List<CourseLearning>> getDaysCourseLearning() {
        return daysCourseLearning;
    }

    public static void setDaysCourseLearning(ArrayList<List<CourseLearning>> daysCourseLearning) {
        TimeStudyData.daysCourseLearning = daysCourseLearning;
    }

    public static ArrayList<List<Issue>> getDaysIssue() {
        return daysIssue;
    }

    public static void setDaysIssue(ArrayList<List<Issue>> everyDayIssue) {
        TimeStudyData.daysIssue = everyDayIssue;
    }

    public static ArrayList<List<Answer>> getDaysAnswer() {
        return daysAnswer;
    }

    public static void setDaysAnswer(ArrayList<List<Answer>> daysAnswer) {
        TimeStudyData.daysAnswer = daysAnswer;
    }

    public static ArrayList<List<MessageRecord>> getDaysMessage() {
        return daysMessage;
    }

    public static void setDaysMessage(ArrayList<List<MessageRecord>> daysMessage) {
        TimeStudyData.daysMessage = daysMessage;
    }

    public static void preDaysCourseLearning(){
        daysCourseLearning.clear();
        for(DayStudyInfo dayStudyInfo:getAllDaysData()){
            daysCourseLearning.add(dayStudyInfo.getLearnings());
        }
    }
    public static void preDaysIssue(){
        daysIssue.clear();
        for(DayStudyInfo dayStudyInfo:getAllDaysData()){
            daysIssue.add(dayStudyInfo.getIssues());
        }
    }
    public static void preDaysAnswer(){
        daysAnswer.clear();
        for(DayStudyInfo dayStudyInfo:getAllDaysData()){
            daysAnswer.add(dayStudyInfo.getAnswers());
        }
    }
    public static void preDaysMessage(){
        daysMessage.clear();
        for(DayStudyInfo dayStudyInfo:getAllDaysData()){
            daysMessage.add(dayStudyInfo.getMessages());
        }
    }


}
