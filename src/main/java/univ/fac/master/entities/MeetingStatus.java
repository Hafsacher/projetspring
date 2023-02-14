package univ.fac.master.entities;

import com.fasterxml.jackson.annotation.JsonFormat;

@JsonFormat(shape = JsonFormat.Shape.STRING)
public enum MeetingStatus {
    ACCEPTED("Accepté"),

    PENDING("En attente"),

    REJECTED("Refusé");

    private final String label;

    MeetingStatus(String label) {
        this.label = label;
    }

    public String getLabel() {
        return label;
    }

    public static MeetingStatus fromLabel(String label) {
        for (MeetingStatus status : MeetingStatus.values()) {
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
