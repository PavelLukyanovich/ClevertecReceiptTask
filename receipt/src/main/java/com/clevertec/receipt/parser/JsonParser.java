package com.clevertec.receipt.parser;

import java.lang.reflect.Array;
import java.lang.reflect.Field;

public class JsonParser {
    private final Object object;
    private final int indent;
    private String json;

    public JsonParser(Object object, int indent) throws IllegalAccessException {
        this.object = object;
        this.indent = indent;
        this.json = createJson(this.object, this.indent);
        System.out.println(this.json);
    }

    public String getJson() {
        if (this.json != null)
            return this.json;
        else
            throw new NullPointerException("No JSON Object Found");
    }

    private static String createJson(Object object, int indent) throws IllegalAccessException {
        Field[] fields = object.getClass().getDeclaredFields();
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(spacing(indent));
        stringBuilder.append("{");
        stringBuilder.append("\n");

        int length = Array.getLength(fields);

        for (int i = 0; i < length; i++) {
            Field field = fields[i];
            field.setAccessible(true);

            if (field.isSynthetic()) {
                continue;
            }
            stringBuilder.append(spacing(indent + 1) + formatString(field.getName()) + ": ");

            if (field.getType().isPrimitive()) {
                stringBuilder.append(formatString(field.get(object).toString()));
            } else if (field.getType().equals(String.class)) {
                stringBuilder.append(formatString(field.get(object).toString()));
            } else if (field.getType().isArray()) {
                stringBuilder.append(jsonArray(field.get(object), indent));
            } else {
                stringBuilder.append(createJson(field.get(object), indent));
            }
            if (i != length - 1) {
                stringBuilder.append(",");
            }
            stringBuilder.append("\n");
        }

        stringBuilder.append(spacing(indent));
        stringBuilder.append("}");
        return stringBuilder.toString();
    }

    private static String jsonArray(Object arrObject, int indent) throws IllegalAccessException {
        Class<?> arrType = arrObject.getClass().getComponentType();
        int size = Array.getLength(arrObject);
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(spacing(indent + 1));
        stringBuilder.append("[\n");

        for (int i = 0; i < size; i++) {
            Object item = Array.get(arrObject, i);
            if (arrType.isPrimitive()) {
                stringBuilder.append(spacing(indent + 1));
                stringBuilder.append(formatString(item.toString()));
            } else if (arrType.equals(String.class)) {
                stringBuilder.append(spacing(indent + 1));
                stringBuilder.append(formatString(item.toString()));
            } else {
                stringBuilder.append(createJson(item, indent + 1));
            }
            if (i != size - 1) {
                stringBuilder.append(",");
            }
            stringBuilder.append("\n");
        }

        stringBuilder.append(spacing(indent));
        stringBuilder.append("]");
        return stringBuilder.toString();
    }

    private static String formatString(String value) {
        return String.format("\"%s\"", value);
    }

    private static String spacing(int indent) {
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < indent; i++) {
            stringBuilder.append("\t");
        }
        return stringBuilder.toString();
    }
}
