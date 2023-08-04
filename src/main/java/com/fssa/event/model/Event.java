package com.fssa.event.model;



import java.sql.Date;
import java.time.LocalDate;
import java.util.Objects;

/**
 * Model object for representing an event in the application.
 */
public class Event {

    // Member variables representing event properties
    private int eventId;
    private String eventName;
    private String eventLocation;
    private String organizerName;
    private String contactNumber;
    private LocalDate eventDate;
    private Date eventDateSQL; // This appears to be a SQL-specific date representation.
    private String imageUrl;
    private String aboutEvent;

public Event(){
	 
 }
    // hashCode() and equals() methods to support comparison based on eventName property
    @Override
    public int hashCode() {
        return Objects.hash(eventName);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null || getClass() != obj.getClass())
            return false;
        Event other = (Event) obj;
        return Objects.equals(eventName, other.eventName);
    }

    /**
     * Parameterized constructor to initialize an Event object with provided values.
     *
     * @param eventId        The unique identifier for the event.
     * @param eventName      The name of the event.
     * @param eventLocation  The location of the event.
     * @param organizerName  The name of the event organizer.
     * @param contactNumber  The contact number for the event.
     * @param imageUrl       The URL representing the event's image.
     * @param aboutEvent     The about the event
     */
    public Event( String eventName, String eventLocation, String organizerName,
                 String contactNumber, String imageUrl,LocalDate eventDate, String aboutEvent) {
      
        this.eventName = eventName;
        this.eventLocation = eventLocation;
        this.organizerName = organizerName;
        this.contactNumber = contactNumber;
        this.imageUrl = imageUrl;
        this.aboutEvent = aboutEvent;
        this.eventDate=eventDate;
    }

    /**
     * Default constructor for the Event class.
     * Sets the event date to the current date.
     */
   

    // Getters and setters for the member variables

    public String getAboutEvent() {
        return aboutEvent;
    }

    public void setAboutEvent(String aboutEvent) {
        this.aboutEvent = aboutEvent;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public int getEventId() {
        return eventId;
    }

    public void setEventId(int eventId) {
        this.eventId = eventId;
    }

    public String getEventName() {
        return eventName;
    }

    public void setEventName(String eventName) {
        this.eventName = eventName;
    }

    public String getEventLocation() {
        return eventLocation;
    }

    public void setEventLocation(String eventLocation) {
        this.eventLocation = eventLocation;
    }

    public String getOrganizerName() {
        return organizerName;
    }

    public void setOrganizerName(String organizerName) {
        this.organizerName = organizerName;
    }

    public String getContactNumber() {
        return contactNumber;
    }

    public void setContactNumber(String contactNumber) {
        this.contactNumber = contactNumber;
    }

    public LocalDate getEventDate() {
        return eventDate;
    }

    /**
     * Sets the event date to the current date.
     */
    public void setEventDate(LocalDate date) {
        this.eventDate = date;
    }

    /**
     * Sets the event date based on a given SQL Date representation.
     *
     * @param date The SQL Date representing the event date.
     */
    public void setEventDate(Date date) {
        this.eventDateSQL = date;
    }
}

