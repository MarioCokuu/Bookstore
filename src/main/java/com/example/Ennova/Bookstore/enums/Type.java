package com.example.Ennova.Bookstore.enums;

import java.util.HashMap;
import java.util.Map;

public enum Type {

    ACTION(0),
    THRILLER(1),
    TECHNOLOGY(2),
    DRAMA(3),
    POETRY(4);

    private int value;
    private static Map map = new HashMap<>();

    private Type(int value) {
        this.value = value;
    }

    static {
        for (Type type : Type.values()) {
            map.put(type.value, type);
        }
    }

    public static Type valueOf(int type) {
        return (Type) map.get(type);
    }

    public int getValue() {
        return value;
    }
}
