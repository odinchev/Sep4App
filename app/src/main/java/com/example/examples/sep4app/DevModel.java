package com.example.examples.sep4app;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by MrWhitemount on 01-Dec-17.
 */

class DevModel {

    String name, tagsToString, key;
    String yearsOfExp, interests, certificates, email, linkedIn;

    public DevModel(){

    }

    public DevModel(String firstName, String lastName, String tagsToString, String key, String yearsOfExp,String interests, String certificates, String email, String linkedIn) {
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
        result.put("skills", tagsToString);
        result.put("experience", yearsOfExp);
        result.put("key", key);
        result.put("interests", interests);
        result.put("certs", certificates);
        result.put("email", email);
        result.put("linkedIn", linkedIn);
        return result;
    }
}
