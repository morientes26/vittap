package com.innovatrics.commons.vittap.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

/** defaults for event notifications */

@Entity
public class NotificationSettings {

    @Id
    @GeneratedValue
    private long id;

    private NotificationType type;

    public NotificationSettings(NotificationType type){
        this.type = type;
    }

    public long getId() {
        return id;
    }

    public NotificationType getType() {
        return type;
    }

    public void setType(NotificationType type) {
        this.type = type;
    }
}
