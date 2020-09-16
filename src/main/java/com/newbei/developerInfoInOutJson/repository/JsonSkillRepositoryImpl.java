package com.newbei.developerInfoInOutJson.repository;

import com.newbei.developerInfoInOutJson.model.Skill;

import java.util.List;

import static com.newbei.developerInfoInOutJson.json.JsonParser.*;
import static com.newbei.developerInfoInOutJson.json.JsonFilePath.SKILL_JSON_PATH;

public class JsonSkillRepositoryImpl implements SkillRepository{

        private List<Skill> skills;

        private long generateId(){
            skills = readJsonSkillFile();
            return (long)(skills.get(skills.size()-1).getId()+1);
        }

        @Override
        public Skill save(Skill skill){
            skill.setId(generateId());
            skills = getAll();
            skills.add(skill);
            writeJsonFile(skills, SKILL_JSON_PATH);
            return skill;
        }

        @Override
        public Skill update(Skill skill) {
            skills = getAll();
            int index;
            Skill updetedSkill = new Skill();
            for (Skill skil:skills) {
                if (skil.getId() == skill.getId()) {
                    updetedSkill = skill;
                    index = skills.indexOf(skil);
                    skills.set(index, skil);
                    writeJsonFile(skills,SKILL_JSON_PATH);
                    return updetedSkill;
                }
            }
            return null;
        }

        @Override
        public List<Skill> getAll() {
            return readJsonSkillFile();
        }

        @Override
        public Skill getById(Long aLong) {
            skills = getAll();
            for (Skill skill:skills){
                if (skill.getId()==aLong) return skill;
            }
            return null;
        }

        @Override
        public Skill deleteById(Long aLong) {
            skills = getAll();
            for(Skill skill:skills){
                if (skill.getId()==aLong){
                    Skill tempSkill = skill;
                    skills.remove(skill);
                    return tempSkill;
                }
            }
            return null;
        }

}
