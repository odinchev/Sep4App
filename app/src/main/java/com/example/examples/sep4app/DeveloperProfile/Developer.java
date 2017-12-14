package com.example.examples.sep4app.DeveloperProfile;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by MrWhitemount on 01-Dec-17.
 */

public class Developer
{

   // String name, tagsToString, key;
   public String yearsOfExp;
    String interests;
    public String certificates;
    String email;
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

    public Developer(){

    }


    public Developer(String id, String  name, String  lastName, String  certifications, String yearsofExperience, String description, List<String> skills, String preferredIDE, String ProfilePucture)
    {
        // fetch name from database
        this.id=id;
        this.name= name;
        this.lastName= lastName;
        this.certifications= certifications;
        this.yearsofExperience=yearsofExperience;
        this.description= description;
        this.skills=skills;
        this.preferredIDE=preferredIDE;
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


}
