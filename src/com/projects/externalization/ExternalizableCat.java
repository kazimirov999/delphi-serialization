package com.projects.externalization;

import com.projects.parameters.Color;

import java.io.*;
import java.util.Objects;

public class ExternalizableCat implements Externalizable {

    private String name;
    private int age;
    private Color color;
    private int damage;

    public ExternalizableCat() {
    }

    public ExternalizableCat(String name, int age, Color color, int damage) {
        this.name = name;
        this.age = age;
        this.color = color;
        this.damage = damage;
    }

    @Override
    public void writeExternal(ObjectOutput out) throws IOException {
        out.writeUTF(name);
        out.writeInt(age);
        out.writeUTF(color.toString());
    }

    @Override
    public void readExternal(ObjectInput in) throws IOException {
        name = in.readUTF();
        age = in.readInt();
        color = Color.valueOf(in.readUTF().toUpperCase());
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!getClass().equals(obj.getClass())) {
            return false;
        }

        ExternalizableCat cat = (ExternalizableCat) obj;
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
