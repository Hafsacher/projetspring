package univ.fac.master.entities;

import com.fasterxml.jackson.annotation.JsonFormat;

@JsonFormat(shape = JsonFormat.Shape.STRING)
public enum TeamStatus {
    COMPLETED("Complété"), 

    PENDING("En attente"),
    
    ACCEPTED("Accepté"),

    REJECTED("Refusé");

    private final String label;

    TeamStatus(String label) {
        this.label = label;
    }

    public String getLabel() {
        return label;
    }

    public static TeamStatus fromLabel(String label) {
        for (TeamStatus status : TeamStatus.values()) {
            if (status.label.equals(label)) {
                return status;
            }
        }
        return null;
    }

    @Override
    public String toString() {
        return label;
    }
}
