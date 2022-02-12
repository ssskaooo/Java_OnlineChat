package ru.biriukov.clientserver.commands;

import java.io.Serializable;

public class UpdateUsernameCommandData implements Serializable {

    private final String username;

    public UpdateUsernameCommandData(String username) {
        this.username = username;
    }

    public String getUsername() {
        return username;
    }
}
