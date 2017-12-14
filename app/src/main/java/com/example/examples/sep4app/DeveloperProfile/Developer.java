package com.example.examples.sep4app.DeveloperProfile;

import java.util.Comparator;
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


    public Developer(String id, String Setname, String SetlastName, String Setcertifications, String SetYearsofExperience, String Setdescription, List<String> skills, String SetPreferredIDE, String ProfilePucture)
    {
        // fetch name from database
        this.id=id;
        this.name=Setname;
        this.lastName=SetlastName;
        this.certifications=Setcertifications;
        this.yearsofExperience=SetYearsofExperience;
        this.description=Setdescription;
        this.skills=skills;
        this.preferredIDE=SetPreferredIDE;
        this.picture=ProfilePucture;
    }


/**
    public Developer(String firstName, String lastName, String tagsToString, String key, String yearsOfExp,String interests, String certificates, String email, String linkedIn) {
        this.name = firstName + " " + lastName;
        this.tagsToString = tagsToString;
        this.yearsOfExp = yearsOfExp;
        this.key = key;
        this.yearsOfExp = yearsOfExp;
        this.interests = interests;
        this.certificates = certificates;
        this.email = email;
        this.linkedIn = linkedIn;
    }

    public Developer(String firstName, String lastName, String tagsToString, String yearsOfExp,String interests, String certificates, String email, String linkedIn) {
        this.name = firstName + " " + lastName;
        this.tagsToString = tagsToString;
        this.yearsOfExp = yearsOfExp;
        this.key = key;
        this.yearsOfExp = yearsOfExp;
        this.interests = interests;
        this.certificates = certificates;
        this.email = email;
        this.linkedIn = linkedIn;
    }
    @Override
    public String toString() {
        return "DevModel{" +
                "name='" + name + '\'' +
                ", tagsToString='" + tagsToString + '\'' +
                ", key='" + key + '\'' +
                ", yearsOfExp='" + yearsOfExp + '\'' +
                ", interests='" + interests + '\'' +
                ", certificates='" + certificates + '\'' +
                ", email='" + email + '\'' +
                ", linkedIn='" + linkedIn + '\'' +
                '}';
    }

    public Map<String, Object> toMap(){
        HashMap<String, Object> result = new HashMap<>();
        result.put("name", name);
        result.put("skills", tags);
        result.put("experience", yearsofExperience);
        result.put("key", id);
        result.put("interests", description);
        result.put("certs", certificates);
       // result.put("email", email);
        //result.put("linkedIn", linkedIn);
        return result;
    }
*/

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
