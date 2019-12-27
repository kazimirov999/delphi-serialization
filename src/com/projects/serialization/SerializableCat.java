package com.projects.serialization;

import com.projects.parameters.Color;

import java.io.*;
import java.util.Objects;

public class SerializableCat implements Serializable {

    private static final long serialVersionUID = 1L;

    private String name;
    private int age;
    private Color color;
    private transient int damage;

    public SerializableCat(String name, int age, Color color, int damage) {
        this.name = name;
        this.age = age;
        this.color = color;
        this.damage = damage;
    }

    private void writeObject(ObjectOutputStream stream) throws IOException {
        stream.writeUTF(name);
        stream.writeInt(age);
        stream.writeUTF(color.toString());
    }

    private void readObject(ObjectInputStream stream) throws ClassNotFoundException, IOException {
        name = stream.readUTF();
        age = stream.readInt();
        color = Color.valueOf(stream.readUTF().toUpperCase());
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!getClass().equals(obj.getClass())) {
            return false;
        }

        SerializableCat cat = (SerializableCat) obj;
        return  age == cat.age &&
                name.equals(cat.name) &&
                color == cat.color;
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, age, color);
    }

    @Override
    public String toString() {
        return  name + ", " + age + ", " + color + ", " + damage + ";";
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    public int getDamage() {
        return damage;
    }

    public void setDamage(int damage) {
        this.damage = damage;
    }
}
