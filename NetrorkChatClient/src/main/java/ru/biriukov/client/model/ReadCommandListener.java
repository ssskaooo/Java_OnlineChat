package ru.biriukov.client.model;

import ru.biriukov.clientserver.Command;

public interface ReadCommandListener {

    void processReceivedCommand(Command command);

}