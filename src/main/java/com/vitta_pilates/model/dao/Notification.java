package com.vitta_pilates.model.dao;

import com.vitta_pilates.model.enumeration.NotificationType;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.Set;

@Entity
public class Notification {

    @Id
    @GeneratedValue
    private long id;

    private Date occurence;
    private NotificationType type;

    @ManyToMany(mappedBy = "notifications")
    private Set<UserAccount> userAccounts;

    public long getId() {
        return id;
    }

    public Date getOccurence() {
        return occurence;
    }

    public void setOccurence(Date occurence) {
        this.occurence = occurence;
    }

    public NotificationType getType() {
        return type;
    }

    public void setType(NotificationType type) {
        this.type = type;
    }

    public Set<UserAccount> getUserAccounts() {
        return userAccounts;
    }

    public void setUserAccounts(Set<UserAccount> userAccounts) {
        this.userAccounts = userAccounts;
    }
}
