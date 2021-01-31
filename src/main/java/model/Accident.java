package model;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Table(name = "accident")
@Entity
public class Accident {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int accidentId;

    private String accidentName;

    private String accidentText;

    private String accidentAddress;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinTable(
            name = "accident_type",
            joinColumns = @JoinColumn(name = "accident_id"),
            inverseJoinColumns = @JoinColumn(name = "type_id")
    )
    private AccidentType accidentType;

    @ManyToMany(cascade = {CascadeType.PERSIST})
    @JoinTable(
            name = "accident_rule",
            joinColumns = @JoinColumn(name = "accident_id"),
            inverseJoinColumns = @JoinColumn(name = "rule_id")
    )
    private List<Rule> rules = new ArrayList<>();

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

    @Override
    public String toString() {
        return "Accident{"
                + "accidentId=" + accidentId
                + ", accidentName='" + accidentName + '\''
                + ", accidentText='" + accidentText + '\''
                + ", accidentAddress='" + accidentAddress + '\''
                + ", accidentType=" + accidentType
                + ", rules=" + rules
                + '}';
    }
}
