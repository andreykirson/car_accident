package model;

import java.util.Objects;

/**
 * @author Andrey
 * @version 1
 * @since 23/1/2021
 */

public class Rule {

    private int ruleId;
    private String ruleName;

    public Rule() {

    }

    public Rule(int id, String name) {
        ruleId = id;
        ruleName = name;
    }

    public int getRuleId() {
        return ruleId;
    }

    public void setRuleId(int ruleId) {
        this.ruleId = ruleId;
    }

    public String getRuleName() {
        return ruleName;
    }

    public void setRuleName(String ruleName) {
        this.ruleName = ruleName;
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

    @Override
    public String toString() {
        return "Rule{"
                + "ruleId=" + ruleId
                + ", ruleName='" + ruleName + '\''
                + '}';
    }
}
