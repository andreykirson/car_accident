package model;

import java.util.Objects;

/**
 * @author Andrey
 * @version 1
 * @since 23/1/2021
 */

public class Rule {

    private int ruleId;
    private String name;

    public static Rule of(int id, String name) {
        Rule rule = new Rule();
        rule.ruleId = id;
        rule.name = name;
        return rule;
    }

    public int getRuleId() {
        return ruleId;
    }

    public void setRuleId(int ruleId) {
        this.ruleId = ruleId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Rule rule = (Rule) o;
        return ruleId == rule.ruleId;
    }

    @Override
    public int hashCode() {
        return Objects.hash(ruleId);
    }
}
