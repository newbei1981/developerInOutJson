package com.newbei.developerInfoInOutJson.json;

import java.util.Set;

public class DeveloperOnlyIDs {
    private long id;
    private String name;
    private long accountId;
    private Set<Long> skillId;

    public DeveloperOnlyIDs(long id, String name, long accountId, Set<Long> skillId) {
        this.id = id;
        this.name = name;
        this.accountId = accountId;
        this.skillId = skillId;
    }

    public long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public long getAccountId() {
        return accountId;
    }

    public Set<Long> getSkillId() {
        return skillId;
    }

}
