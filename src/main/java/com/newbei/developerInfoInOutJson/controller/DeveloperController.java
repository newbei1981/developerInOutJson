package com.newbei.developerInfoInOutJson.controller;

import com.newbei.developerInfoInOutJson.model.Developer;
import com.newbei.developerInfoInOutJson.repository.JsonDeveloperRepositoryImpl;
import static com.newbei.developerInfoInOutJson.view.DeveloperView.*;

public class DeveloperController {

    JsonDeveloperRepositoryImpl developerRepository = new JsonDeveloperRepositoryImpl();

    public Developer developerAuthentication(){
        String login, password = "";
        do {
            login = saveOrUpdateRecord("Login");
            password = saveOrUpdateRecord("Password");
            if (developerRepository.authentication(login, password) == -1) {
                printAuthenticationError();
            }
        } while (developerRepository.authentication(login, password) == -1) ;
        return  developerRepository.getById(developerRepository.authentication(login,password));
    }

    public Developer saveNewDeveloper(Developer developer){
        developer.setName(saveOrUpdateRecord("Name"));
        return developerRepository.save(developer);
    }

    public Developer updateName(Developer developer){
        developer.setName(saveOrUpdateRecord("Name", developer.getName()));
        return developerRepository.update(developer);
    }

    public Developer deleteAccountAndSkill(Developer developer){
        if (deleteChoice()) {
            developerRepository.deleteById(developer.getId());
        }
        return developerRepository.getById(developer.getId());
    }
}
