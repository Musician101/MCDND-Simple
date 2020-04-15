package io.musician101.mcdndsimple.forge.network;

import java.util.ArrayList;
import java.util.List;

public class NonPlayerListPacket {

    private final List<String> list = new ArrayList<>();

    public NonPlayerListPacket(List<String> list) {
        this.list.addAll(list);
    }

    public List<String> getList() {
        return list;
    }
}
