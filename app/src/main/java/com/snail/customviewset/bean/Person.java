package com.snail.customviewset.bean;

/**
 * @author yongjie on 2018/6/26.
 */
public class Person implements Comparable<Person> {

    private String name;
    private String pinyin;

    public Person(String name) {
        this.name = name;
    }

    @Override
    public int compareTo(Person o) {
        return pinyin.compareTo(o.pinyin);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPinyin() {
        return pinyin;
    }

    public void setPinyin(String pinyin) {
        this.pinyin = pinyin;
    }

    @Override
    public String toString() {
        return "Person{" +
                "name='" + name + '\'' +
                ", pinyin='" + pinyin + '\'' +
                '}';
    }
}
