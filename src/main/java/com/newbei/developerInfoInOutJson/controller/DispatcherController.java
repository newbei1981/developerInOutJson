package com.newbei.developerInfoInOutJson.controller;

import com.newbei.developerInfoInOutJson.model.Account;
import com.newbei.developerInfoInOutJson.model.Developer;
import com.newbei.developerInfoInOutJson.model.Skill;

import java.util.HashSet;
import java.util.Set;

import static com.newbei.developerInfoInOutJson.view.StaticUtility.*;

public class DispatcherController {
    private AccountController accountController = new AccountController();
    private SkillController skillsController = new SkillController();
    private DeveloperController developerController = new DeveloperController();

    Developer developer = new Developer();
    Account account = new Account();

    public void menuFlow() {
        byte choice = selectMenuItems(MAIN_MENU);
        if ((choice > 0) && (choice < MAIN_MENU.size())) {
            if (choice == 1) {
                logInAndUpdate();
            } else if (choice == 2) {
                registration();
            }
        }
    }

    public void registration() {
        developer.setAccount(accountController.saveAccount());
        developer.setSkills(skillsController.createSkills(new HashSet<>()));
        developerController.saveNewDeveloper(developer);
    }

    public void logInAndUpdate() {
        developer = developerController.developerAuthentication();
        account = developer.getAccount();
        System.out.println("Select the field which you want to change?");
        byte choice = selectMenuItems(DEVELOPER_CARD_FIELDS);
        switch (choice) {
            case 1: {
                developerController.updateName(developer);
                break;
            }
            case 2: {
                accountController.updateAccountLogin(account);
                break;
            }
            case 3: {
                accountController.updateAccountPassword(account);
                break;
            }
            case 4: {
                Set<Skill> skillSet = developer.getSkills();
                developer.setSkills(skillsController.createSkills(skillSet));
                break;
            }
            case 5: {
                developerController.deleteAccountAndSkill(developer);
                break;
            }
        }
    }
}
