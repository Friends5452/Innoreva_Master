package com.example.innorevamaster.currentEvent;

public class CurrentEvent {
    private String eventImageUrl;
    private String eventDescriptionString;

    public CurrentEvent()
    {

    }
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
