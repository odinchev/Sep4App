package com.example.examples.sep4app.DeveloperProfile;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by MrWhitemount on 01-Dec-17.
 */

public class Developer
{
    // developer class which holds all of the methods that we would use for making the objects we save to the firebase database
   // String name, tagsToString, key;
   public String yearsOfExp;
    String interests;
    public String certificates;

    String linkedIn;
    public String id;
    public String name;
  public  String lastName;
   public  String certifications;
   public String yearsofExperience;
   public String description;
   public List<String> skills;
    public String preferredIDE;
    String picture;

    private String email;

    public Developer(){

    }


    public Developer(String id, String email, String Setname, String SetlastName, String Setcertifications, String SetYearsofExperience, String Setdescription, List<String> skills, String SetPreferredIDE, String ProfilePucture)
    {
        // fetch name from database
        this.id=id;
        this.email=email;
        this.name=Setname;
        this.lastName=SetlastName;
        this.certifications=Setcertifications;
        this.yearsofExperience=SetYearsofExperience;
        this.description=Setdescription;
        this.skills=skills;
        this.preferredIDE=SetPreferredIDE;
        this.picture=ProfilePucture;
    }



    public void  Setname(String Name)
    {
        this.name=Name;
    }
    public void SetlastName(String LastName)
    {
        this.lastName=LastName;
    }
    public void Setcertifications(String SetCertifications)
    {
        this.certifications=SetCertifications;
    }
    public void SetyearsOfExperience(String SetyearsofExperience)
    {
        this.yearsofExperience=SetyearsofExperience;
    }
    public void Setdesctription(String SetDescription)
    {
        this.description=SetDescription;
    }
    public void SetSkills(List<String> Tags)
    {
        this.skills=Tags;
    }
    public void Setide(String Setpreferredide)
    {
        this.preferredIDE=Setpreferredide;
    }
    public void SetProfilePicture(String picture)
    {
        this.picture=picture;
    }
    public String getName()
    {
        return name;
    }
    public String getLastName()
    {
        return lastName;
    }
    public String getCertifications()
    {
        return certifications;
    }
    public String getYearsofExperience()
    {
        return yearsofExperience;
    }
    public String getDescription()
    {
        return description;
    }
    public String getide()
    {
        return preferredIDE;
    }
    public List<String> getSkills()
    {
        return skills;
    }
    public String getPicture()
    {
        return picture;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
