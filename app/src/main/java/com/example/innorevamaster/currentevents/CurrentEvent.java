package com.example.innorevamaster.currentevents;

public class CurrentEvent {
    String eventImageUrl;
    String eventDescriptionString;

    public CurrentEvent(String eventImageUrl, String eventDescriptionString) {
        this.eventImageUrl = eventImageUrl;
        this.eventDescriptionString = eventDescriptionString;
    }

    public String getEventImageUrl() {
        return eventImageUrl;
    }

    public void setEventImageUrl(String eventImageUrl) {
        this.eventImageUrl = eventImageUrl;
    }

    public String getEventDescriptionString() {
        return eventDescriptionString;
    }

    public void setEventDescriptionString(String eventDescriptionString) {
        this.eventDescriptionString = eventDescriptionString;
    }
}
