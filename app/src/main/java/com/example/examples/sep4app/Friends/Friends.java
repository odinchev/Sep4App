package com.example.examples.sep4app.Friends;

/**
 * Created by PC on 12.12.2017 г..
 */

public class Friends
{
    public int projectID;
    public int user1ID;
    public int user2ID;
    public Friends()
    {

    }
    public Friends(int projectID,int user1ID,int user2ID)
    {
        this.projectID=projectID;
        this.user1ID=user1ID;
        this.user2ID=user2ID;
    }
    public void setProjectID(int id)
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
    public int getProjectID()
    {
        return projectID;
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
