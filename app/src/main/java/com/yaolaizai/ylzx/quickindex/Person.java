package com.yaolaizai.ylzx.quickindex;

/**
 * Created by ylzx on 2017/6/21.
 */
public class Person implements  Comparable<Person>{

    private String name;
    private String pinYin;

    public Person() {
    }

    public Person(String name) {
        this.name = name;
        this.pinYin = PinYinUtils.getPinYin(name);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPinYin() {
        return pinYin;
    }

    public void setPinYin(String pinYin) {
        this.pinYin = pinYin;
    }

    @Override
    public String toString() {
        return "Person{" +
                "name='" + name + '\'' +
                ", pinYin='" + pinYin + '\'' +
                '}';
    }

    @Override
    public int compareTo(Person another) {
        return this.pinYin.compareTo(another.getPinYin());
    }
}
