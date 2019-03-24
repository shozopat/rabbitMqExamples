package com.shozo.microservice.employee.entity;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

public enum ReportNotificationType {
    IN_PROGRESS(Values.IN_PROGRESS), DONE(Values.DONE), EDITED(Values.EDITED);

    public static final String NOTIFICATION_PROPERTY_NAME = "eventName";

    private final String name;

    ReportNotificationType(final String name) {
        this.name = name;
    }

    @JsonCreator
    public static ReportNotificationType forValue(final String value) {
        switch (value) {
            case Values.IN_PROGRESS:
                return IN_PROGRESS;
            case Values.DONE:
                return DONE;
            case Values.EDITED:
                return EDITED;
            default:
                throw new IllegalArgumentException("Invalid value for report notification type");
        }
    }

    @JsonValue
    public String getName() {
        return name;
    }

    private static class Values {
        private Values() {
        }
        private static final String IN_PROGRESS = "inProgress";
        private static final String DONE = "done";
        private static final String EDITED = "edited";
    }
}
