package com.clevertec.receipt.parser;

import com.clevertec.receipt.models.entities.Employee;
import com.clevertec.receipt.models.entities.EmployeeCard;
import com.clevertec.receipt.utils.Position;
import lombok.Data;

import java.lang.reflect.Array;
import java.lang.reflect.Field;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

@Data
public class JsonParser {
    private final Object object;
    private final int indent;
    private String json;

    public JsonParser(Object object, int indent) throws IllegalAccessException {
        this.object = object;
        this.indent = indent;
        this.json = serialize(this.object, this.indent);
        System.out.println(this.json);
    }

    public String getJson() {
        if (this.json != null)
            return this.json;
        else
            throw new NullPointerException("No JSON Object Found");
    }

    private static String serialize(Object object, int indent) throws IllegalAccessException {
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
            stringBuilder.append(spacing(indent + 1)).append(formatString(field.getName())).append(": ");

            if (field.getType().isPrimitive()) {
                stringBuilder.append(formatString(field.get(object).toString()));
            } else if (field.getType().equals(String.class)) {
                stringBuilder.append(formatString(field.get(object).toString()));
            } else if (field.getType().isArray()) {
                stringBuilder.append(jsonArray(field.get(object), indent));
            } else if (field.getType().isEnum()) {
                stringBuilder.append(formatString(field.get(object).toString()));
            } else if (field.getType().equals(Collection.class)) {
                stringBuilder.append(jsonArray(field.get(object), indent));
            } else {
                stringBuilder.append(serialize(field.get(object), indent));
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
                stringBuilder.append(serialize(item, indent + 1));
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

    public static Employee deserializeEmployee(String jsonString) {

        Employee employee1 = new Employee();

        try {
            Field[] fields = Employee.class.getDeclaredFields();
            Map<String, Field> fieldMap = new HashMap<>();
            for (Field field : fields) {
                field.setAccessible(true);
                fieldMap.put(field.getName(), field);
            }
            StringBuilder sb = new StringBuilder();

            boolean isName = true;
            boolean isString = false;
            for (char c : jsonString.toCharArray()) {
                if (c == '"') {
                    isString = !isString;
                    continue;
                }
                if (c == ':' && !isString) {
                    isName = false;
                    continue;
                }
                if (c == ',' && !isString) {
                    isName = true;
                    continue;
                }
                if ((c == '{' || c == '}') && !isString) {
                    continue;
                }
                sb.append(c);
                if (c == ' ' && !isString) {
                    continue;
                }

                if ((c == ':' || c == ',' || c == '}') && !isString) {
                    String current = sb.toString();
                    sb.setLength(0);

                    if (isName) {
                        Field field = fieldMap.get(current);
                        sb.append(field.getName());
                    } else {
                        String key = sb.toString();
                        sb.setLength(0);
                        Field field = fieldMap.get(key);

                        Class<?> type = field.getType();
                        Object value;

                        if (type.equals(String.class)) {
                            value = current;

                        } else if (type.equals(int.class)) {
                            value = Integer.parseInt(current);

                        } else if (type.equals(boolean.class)) {
                            value = Boolean.parseBoolean(current);
                        } else if (type.isEnum()) {
                            value = current;
                        } else if (type.equals(char.class)) {
                            value = current.charAt(0);

                        } else {
                            throw new IllegalArgumentException("Unsupported type: " + type);
                        }
                        field.set(employee1, value);
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return employee1;
    }

    public static void main(String[] args) throws Exception {
        EmployeeCard employeeCard = new EmployeeCard(12345L, "0001");
        Employee employee = new Employee("Pavel", "Lukyanovich", 32, true, employeeCard, 'A', Position.DRIVER);
        JsonParser jsonParser = new JsonParser(employee, 2);

        Employee deserializedEmployee = deserializeEmployee(jsonParser.getJson());
        System.out.println(deserializedEmployee.getFirstName());
    }
}
