package com.example.examples.sep4app.profile;

/**
 * Created by PC on 2.12.2017 г..
 */

public class User
{
    public User()
    {

    }
    String id;
    String name;
    String certifications;
    String yearsofExperience;
    String description;
    String preferredIDE;


    public User(String id,String Setname,String Setcertifications,String SetYearsofExperience,String Setdescription,String SetPreferredIDE)
    {
        this.id=id;
        this.name=Setname;
        this.certifications=Setcertifications;
        this.yearsofExperience=SetYearsofExperience;
        this.description=Setdescription;
        this.preferredIDE=SetPreferredIDE;
    }

    public void  Setname(String Name)
    {
        this.name=Name;
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
    public void Setide(String Setpreferredide)
    {
        this.preferredIDE=Setpreferredide;
    }
    public String getName()
    {
        return name;
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


}

