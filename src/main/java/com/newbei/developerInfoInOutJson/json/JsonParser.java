package com.newbei.developerInfoInOutJson.json;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.newbei.developerInfoInOutJson.model.Account;
import com.newbei.developerInfoInOutJson.model.Developer;
import com.newbei.developerInfoInOutJson.model.Skill;

import java.io.*;
import java.lang.reflect.Type;
import java.util.*;
import java.util.stream.Collectors;

import static com.newbei.developerInfoInOutJson.json.JsonFilePath.*;

public class JsonParser {

    public static void writeJsonFile(List listEntities, String filePath){
        try (FileWriter file = new FileWriter(filePath)) {
            String fileGSON = new Gson().toJson(listEntities);
            file.write(fileGSON);
            file.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static List<DeveloperOnlyIDs> readJsonDeveloperIdFile(){
        try {
            Gson gson = new Gson();
            BufferedReader br = new BufferedReader(new FileReader(DEVELOPER_JSON_PATH));
            DeveloperOnlyIDs[] developersId = gson.fromJson(br, DeveloperOnlyIDs[].class);
            return Arrays.stream(developersId).collect(Collectors.toList());
        }
        catch (FileNotFoundException ex){System.out.println(" Where is my file ? ");}
        return null;
    }

    public static List<Developer> developerReader(){
        List<Developer> tempListDeveloper = new ArrayList<>();
        for(DeveloperOnlyIDs devId:readJsonDeveloperIdFile()){
            Set<Skill> skillSet = new HashSet<>();
            for (Long skillId:devId.getSkillId()){
                for (Skill skill:readJsonSkillFile()){
                    if (skill.getId() == skillId) skillSet.add(skill);
                }
            }
            tempListDeveloper.add(new Developer(devId.getId(),devId.getName(),new Account(devId.getAccountId()),skillSet));
        }
        return  tempListDeveloper;
    }

    public static void developerWriter(List<Developer> listDevIdRec){
        List<DeveloperOnlyIDs> tempListDeveloperId = new ArrayList<>();
        for(Developer developer:listDevIdRec){
            Set<Long> skillSetId = new HashSet<>();
            for (Skill skill:developer.getSkills()){
                skillSetId.add(skill.getId());
            }
            tempListDeveloperId.add(new DeveloperOnlyIDs(developer.getId(),developer.getName(),developer.getAccount().getId(),skillSetId));
        }
        writeJsonFile(tempListDeveloperId,DEVELOPER_JSON_PATH);
    }

    public static List<Account> readJsonAccountFile(){
        try {
            Gson gson = new Gson();
            BufferedReader br = new BufferedReader(new FileReader(ACCOUNT_JSON_PATH));
            Account[] accounts = gson.fromJson(br, Account[].class);
            return Arrays.stream(accounts).collect(Collectors.toList());
        }
        catch (FileNotFoundException ex){System.out.println(" Where is my file ? ");}
        return null;
    }

    public static List<Skill> readJsonSkillFile() {
        List<Skill> models = null;
        try{
            Gson gson = new Gson();
            BufferedReader br = new BufferedReader(new FileReader(SKILL_JSON_PATH));
            Type type = new TypeToken<List<Skill>>(){}.getType();
            models = gson.fromJson(br, type);
            return models;
        }
        catch (FileNotFoundException ex){System.out.println(" Where is my files ? ");}
        return models;
    }

}
