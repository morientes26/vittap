package com.innovatrics.commons.vittap.entity;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Set;

@Entity
public class Notification {

    @Id
    @GeneratedValue
    private long id;

    private LocalDateTime occurence;
    private NotificationType type;

    @ManyToMany(mappedBy = "notifications")
    private Set<User> users;

    public long getId() {
        return id;
    }

    public LocalDateTime getOccurence() {
        return occurence;
    }

    public void setOccurence(LocalDateTime occurence) {
        this.occurence = occurence;
    }

    public NotificationType getType() {
        return type;
    }

    public void setType(NotificationType type) {
        this.type = type;
    }

    public Set<User> getUsers() {
        return users;
    }

    public void setUsers(Set<User> users) {
        this.users = users;
    }
}
