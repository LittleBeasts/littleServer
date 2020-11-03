package server;

import java.util.UUID;

public class LandingRoom extends Room {
    LandingRoom(String roomName) {
        super(roomName);
        this.setUuid("62eaf943-8637-4dab-932c-cdb46128fa4d");
    }

    @Override
    public void run() {
        // TODO: Lässt nur raumverwaltung zu
    }
}
