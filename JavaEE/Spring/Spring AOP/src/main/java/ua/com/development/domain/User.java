package ua.com.development.domain;

import org.springframework.stereotype.Component;

@Component
public class User {

    private String firstName;
    private String secondName;

    public String getFirstName() {
        return this.firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getSecondName() {
        return this.secondName;
    }

    public void setSecondName(String secondName) {
        this.secondName = secondName;
    }

    @PrePersist
    public void prePersist() {
    }
}
