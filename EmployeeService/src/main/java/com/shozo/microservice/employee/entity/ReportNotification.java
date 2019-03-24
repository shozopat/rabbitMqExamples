package com.shozo.microservice.employee.entity;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import static com.shozo.microservice.employee.entity.ReportNotificationType.NOTIFICATION_PROPERTY_NAME;

public class ReportNotification {

    private ReportNotificationType eventName;
    private String id;  // this field refers to a report ID

    @JsonCreator
    public ReportNotification(@JsonProperty(NOTIFICATION_PROPERTY_NAME) final ReportNotificationType eventName,
                              @JsonProperty("id") final String reportId) {
        this.eventName = eventName;
        this.id = reportId;
    }

    public ReportNotificationType getEventName() {
        return eventName;
    }

    public String getId() {
        return id;
    }

	@Override
	public String toString() {
		return "ReportNotification [eventName=" + eventName + ", id=" + id + "]";
	}
    
   
}
