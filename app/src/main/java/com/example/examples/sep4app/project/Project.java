package com.example.examples.sep4app.project;

import android.media.Image;

/**
 * Created by MrWhitemount on 08-Dec-17.
 */

public class Project {

    private String id, name, reqSkills, reqExp, otherReqs, duration, summary;
    //private Image projectImage

    public Project(){

    }

    public Project(String id, String name, String reqSkills, String reqExp, String otherReqs, String duration, String summary) {
        this.id = id;
        this.name = name;
        this.reqSkills = reqSkills;
        this.reqExp = reqExp;
        this.otherReqs = otherReqs;
        this.duration = duration;
        this.summary = summary;
    }

    public String getId() {
        return id;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getReqSkills() {
        return reqSkills;
    }

    public void setReqSkills(String reqSkills) {
        this.reqSkills = reqSkills;
    }

    public String getReqExp() {
        return reqExp;
    }

    public void setReqExp(String reqExp) {
        this.reqExp = reqExp;
    }

    public String getOtherReqs() {
        return otherReqs;
    }

    public void setOtherReqs(String otherReqs) {
        this.otherReqs = otherReqs;
    }

    public String getDuration() {
        return duration;
    }

    public void setDuration(String duration) {
        this.duration = duration;
    }

    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }
}
