package com.example.examples.sep4app.Friends;

import com.example.examples.sep4app.DeveloperProfile.Developer;

import java.util.ArrayList;

/**
 * Created by PC on 12.12.2017 г..
 */

public class Friends
{
    public String projectID;
    public int user1ID;
    public int user2ID;
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
    public void setUser1ID(int id)
    {
        this.user1ID=id;
    }
    public void setUser2ID(int id)
    {
        this.user2ID=id;
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
    public int getUser1ID()
    {
        return user1ID;
    }
    public int getUser2ID()
    {
        return user2ID;
    }

}
