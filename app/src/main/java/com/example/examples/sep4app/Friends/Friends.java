package com.example.examples.sep4app.Friends;

import com.example.examples.sep4app.DeveloperProfile.Developer;

import java.util.ArrayList;

/**
 * Created by PC on 12.12.2017 г..
 */
// this is for the interested in system in the projects
public class Friends
{
    public String projectID;

    ArrayList<Developer> FriendsList=new ArrayList<>();
    public Friends()
    {

    }
    public Friends(String projectID)
    {
        this.projectID=projectID;

    }
    public void setProjectID(String id)
    {
        this.projectID=id;
    }

    public void AddtoList(Developer developer)
    {
        FriendsList.add(developer);
    }



    public String getProjectID()
    {
        return projectID;
    }

    public ArrayList getList()
    {
        return FriendsList;
    }


}
