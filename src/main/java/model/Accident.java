package model;

import java.util.List;
import java.util.Objects;


public class Accident {

    private int accidentId;

    private String accidentName;

    private String accidentText;

    private String accidentAddress;

    private AccidentType accidentType;

    private List<Rule> rules;

    public List<Rule> getRules() {
        return rules;
    }

    public void setRules(List<Rule> rules) {
        this.rules = rules;
    }

    public AccidentType getAccidentType() {
        return accidentType;
    }

    public void setAccidentType(AccidentType accidentType) {
        this.accidentType = accidentType;
    }

    public int getAccidentId() {
        return accidentId;
    }

    public void setAccidentId(int accidentId) {
        this.accidentId = accidentId;
    }

    public String getAccidentName() {
        return accidentName;
    }

    public void setAccidentName(String accidentName) {
        this.accidentName = accidentName;
    }

    public String getAccidentText() {
        return accidentText;
    }

    public void setAccidentText(String accidentText) {
        this.accidentText = accidentText;
    }

    public String getAccidentAddress() {
        return accidentAddress;
    }

    public void setAccidentAddress(String accidentAddress) {
        this.accidentAddress = accidentAddress;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Accident accident = (Accident) o;
        return accidentId == accident.accidentId;
    }

    @Override
    public int hashCode() {
        return Objects.hash(accidentId);
    }

}
