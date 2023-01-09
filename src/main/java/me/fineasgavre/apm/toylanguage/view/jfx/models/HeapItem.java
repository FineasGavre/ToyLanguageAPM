package me.fineasgavre.apm.toylanguage.view.jfx.models;

public class HeapItem {
    private final String address;
    private final String value;
    private final String type;

    public HeapItem(String address, String value, String type) {
        this.address = address;
        this.value = value;
        this.type = type;
    }

    public String getAddress() {
        return address;
    }

    public String getValue() {
        return value;
    }

    public String getType() {
        return type;
    }
}
