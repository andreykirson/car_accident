package model;

import java.util.Objects;

/**
 * @author Andrey
 * @version 1
 * @since 26/1/21
 */

public class AccidentType {

    private int typeId;
    private String typeName;

    public static AccidentType of(int id, String name) {
        AccidentType type = new AccidentType();
        type.typeId = id;
        type.typeName = name;
        return type;
    }

    public int getTypeId() {
        return typeId;
    }

    public void setTypeId(int typeId) {
        this.typeId = typeId;
    }

    public String getTypeName() {
        return typeName;
    }

    public void setTypeName(String typeName) {
        this.typeName = typeName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        AccidentType that = (AccidentType) o;
        return typeId == that.typeId;
    }

    @Override
    public int hashCode() {
        return Objects.hash(typeId);
    }

    @Override
    public String toString() {
        return "AccidentType{"
                + "typeId=" + typeId
                + ", typeName='" + typeName + '\''
                + '}';
    }
}
