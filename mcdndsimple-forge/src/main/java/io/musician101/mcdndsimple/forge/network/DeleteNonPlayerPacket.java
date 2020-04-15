package io.musician101.mcdndsimple.forge.network;

public class DeleteNonPlayerPacket {

    private final String name;

    public DeleteNonPlayerPacket(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
