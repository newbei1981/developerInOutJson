package com.newbei.developerInfoInOutJson.repository;

import com.newbei.developerInfoInOutJson.model.Developer;
import com.newbei.developerInfoInOutJson.model.Skill;

import java.util.HashSet;
import java.util.List;

import static com.newbei.developerInfoInOutJson.json.JsonParser.developerReader;
import static com.newbei.developerInfoInOutJson.json.JsonParser.developerWriter;

public class JsonDeveloperRepositoryImpl implements DeveloperRepository{

    private List<Developer> developers;
    private AccountRepository accountRepository = new JsonAccountRepositoryImpl();

    private long generateId(){
        developers = developerReader();
        long id;
        if (developers.size() == 0) {id = 1;}
        else id = (long)(developers.get(developers.size()-1).getId()+1);
        return id;
    }

    @Override
    public Developer save(Developer developer){
        developer.setId(generateId());
        developers = getAll();
        developer.setAccount(developer.getAccount());
        developers.add(developer);
        developerWriter(developers);
        return developer;
    }

    @Override
    public Developer update(Developer developer) {
        developers = getAll();
        Developer updetedDeveloper;
        for (Developer dev: developers) {
            if (dev.getId() == developer.getId()) {
                updetedDeveloper = dev;
                updetedDeveloper.setAccount(accountRepository.getById(dev.getAccount().getId()));
                dev.setAccount(developer.getAccount());
                dev.setName(developer.getName());
                dev.setSkills(developer.getSkills());
                developerWriter(developers);
                return updetedDeveloper;
            }
        }
        return null;
    }

    @Override
    public List<Developer> getAll() {
        return developerReader();
    }

    @Override
    public Developer getById(Long aLong) {
        developers = getAll();
        for (Developer dev:developers){
            if (dev.getId() == aLong) {
                dev.setAccount(accountRepository.getById(dev.getAccount().getId()));
                return dev;
            }
        }
        return null;
    }

    @Override
    public Developer deleteById(Long aLong) {
        Developer developer = getById(aLong);
        Developer deletedDeveloper = developer;
        accountRepository.deleteById(developer.getAccount().getId());
        developer.setSkills(new HashSet<Skill>());
        update(developer);
        return deletedDeveloper;
    }

    public long authentication(String login, String password){
        long accountId = accountRepository.authentication(login,password);
        developers = getAll();
        if (accountId != -1) {
            for (Developer developer:developers){
                if (developer.getAccount().getId() == accountId) return developer.getId();
            }
        }
        return -1;
    }
}
