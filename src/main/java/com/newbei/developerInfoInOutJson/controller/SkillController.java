package com.newbei.developerInfoInOutJson.controller;

import com.newbei.developerInfoInOutJson.model.AccountStatus;
import com.newbei.developerInfoInOutJson.model.Skill;
import com.newbei.developerInfoInOutJson.repository.JsonSkillRepositoryImpl;
import com.newbei.developerInfoInOutJson.repository.SkillRepository;

import java.util.Set;

import static com.newbei.developerInfoInOutJson.view.SkillView.*;
import static com.newbei.developerInfoInOutJson.view.StaticUtility.*;

public class SkillController {
    private SkillRepository skillRepository = new JsonSkillRepositoryImpl();
    public Set<Skill> createSkills(Set<Skill> skillSet){
        byte input;
        do {
            mainHeader(skillRepository.getAll().toString());
          //  showMenuItems(SKILLS_MENU);
            input = selectMenuItems(SKILLS_MENU);
            switch (input) {
                case 1:{ saveAbsentSkills(skillSet);
                    break;}
                case 2:{ updateUserSkills(skillSet);
                    break;}
                case 3:{ deleteUserSkill(skillSet);
                    break;}
            }
        } while (input != 0);
        return skillSet;
    }

    public Set<Skill> saveAbsentSkills(Set<Skill> userSkillSet){
        String input;
        do {
            absentHeader(skillRepository.getAll().toString());
            input = userInput.get();
            if (input.charAt(0) != '0') {
                userSkillSet.add(skillRepository.save(new Skill(input)));
            }
        }
        while (input.charAt(0) != '0');
        return userSkillSet;
    }

    public Set<Skill> deleteUserSkill(Set<Skill> userSkillSet){
        deleteHeader();
        if (userInput.get().toUpperCase().charAt(0) == 'Y') {
            try{
                deleteYesView();
                Skill skill = skillRepository.getById(Long.parseLong(userInput.get()));
                userSkillSet.remove(skill);
            } catch (Exception exception) {
                System.out.println("You have entered incorrect data. Please repeat!");
            }
        }
        return userSkillSet;
    }


    public Set<Skill> updateUserSkills(Set<Skill> userSkillSet){
        updateHeader(skillRepository.getAll().toString());
        String input;
        do {
            input = userInput.get();
            if (input.charAt(0) != '0') {
                userSkillSet.add(skillRepository.getById(Long.parseLong(input)));
            }
        }
        while (input.charAt(0) != '0');
        return userSkillSet;
    }

    //only for administrator
    public Skill deleteSkillByIdFromRepo(AccountStatus accountStatus, Long sId){
        if (accountStatus == AccountStatus.BANNED){
            System.out.println("You now wat u do!!!");
            return skillRepository.deleteById(sId);
        }
        return null;
    }
}
