package io.musician101.mcdndsimple.common.character.player;

import java.util.ArrayList;
import java.util.List;

public class MCDNDItem {

    private boolean carried = true;
    private List<String> description = new ArrayList<>();
    private String name = "";
    private int quantity = 1;
    private double weight = 0.0;

    public List<String> getDescription() {
        return description;
    }

    public void setDescription(List<String> description) {
        this.description = description;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public double getWeight() {
        return weight;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }

    public boolean isCarried() {
        return carried;
    }

    public void setIsCarried(boolean carried) {
        this.carried = carried;
    }
}
