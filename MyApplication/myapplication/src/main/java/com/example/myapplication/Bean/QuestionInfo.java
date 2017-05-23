package com.example.myapplication.Bean;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by 60440 on 2017/2/8.
 */

public class QuestionInfo implements Serializable{
    public String id;
    public String title;
    public String date;
    public String content;
    public String foucusnumber;
    public String questionerName;
    private int answerNumber;
    private boolean anonymous;
    private User user;

    public boolean isAnonymous() {
        return anonymous;
    }

    public void setAnonymous(boolean anonymous) {
        this.anonymous = anonymous;
    }

    public int getAnswerNumber() {
        return answerNumber;
    }

    public void setAnswerNumber(int answerNumber) {
        this.answerNumber = answerNumber;
    }

    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    private Date time;




    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }





    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String questionerImage;

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    @Override
    public String toString() {
        return "QuestionInfo{" +
                "content='" + content + '\'' +
                ", id='" + id + '\'' +
                ", title='" + title + '\'' +
                ", date='" + date + '\'' +
                ", foucusnumber='" + foucusnumber + '\'' +
                ", questionerName='" + questionerName + '\'' +
                ", questionerImage='" + questionerImage + '\'' +
                '}';
    }
}
