package com.example.examples.sep4app.project;

import android.media.Image;

import java.util.List;

/**
 * Created by MrWhitemount on 08-Dec-17.
 */

public class Project {

    private String id, creatorID, name, reqExp, otherReqs, duration, summary, creatorEmail;
    private List<String> reqSkills;
    //private Image projectImage

    public Project(){

    }

    public Project(String id, String creatorID, String creatorEmail, String name, List<String> reqSkills, String reqExp, String otherReqs, String duration, String summary) {
        this.id = id;
        this.creatorID = creatorID;
        this.name = name;
        this.reqSkills = reqSkills;
        this.reqExp = reqExp;
        this.otherReqs = otherReqs;
        this.duration = duration;
        this.summary = summary;
        this.creatorEmail = creatorEmail;
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

    public List<String> getReqSkills() {
        return reqSkills;
    }

    public void setReqSkills(List<String> reqSkills) {
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

    public String getCreatorID() {
        return creatorID;
    }

    public void setCreatorID(String creatorID) {
        this.creatorID = creatorID;
    }

    public String getCreatorEmail() {
        return creatorEmail;
    }

    public void setCreatorEmail(String creatorEmail) {
        this.creatorEmail = creatorEmail;
    }
}
