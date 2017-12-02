package com.example.examples.sep4app.profile;

/**
 * Created by PC on 2.12.2017 г..
 */

public class User
{
    public User()
    {

    }
    String name;
    String certifications;
    String YearsofExperience;
    String description;
    String PreferredIDE;


    public User(String name,String certifications,String YearsofExperience,String description,String PreferredIDE)
    {
        this.name=name;
        this.certifications=certifications;
        this.YearsofExperience=YearsofExperience;
        this.description=description;
        this.PreferredIDE=PreferredIDE;
    }
    public void  Setname(String name)
    {
        this.name=name;
    }
    public void Setcertifications(String certifications)
    {
        this.certifications=certifications;
    }
    public void SetyearsOfExperience(String yearsofExperience)
    {
        this.YearsofExperience=yearsofExperience;
    }
    public void Setdesctription(String description)
    {
        this.description=description;
    }
    public void SetpreferredIDE(String preferredIDE)
    {
        this.PreferredIDE=preferredIDE;
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
        return YearsofExperience;
    }
    public String getDescription()
    {
        return description;
    }
    public String getPreferredIDE()
    {
        return PreferredIDE;
    }


}

