package com.example.assignment.utils;

public enum ChatType {
    MULTI_PROCESS("multi"),
    SINGLE_PROCESS("single");

    private final String type;

    ChatType(String type) {
        this.type = type;
    }

    public String type() {
        return type;
    }

    public static ChatType byValue(String type) {
        if (type.equals(MULTI_PROCESS.type()))
            return MULTI_PROCESS;
        else if (type.equals(SINGLE_PROCESS.type()))
            return SINGLE_PROCESS;
        throw new IllegalArgumentException("No enum found for: " + type);
    }
}
