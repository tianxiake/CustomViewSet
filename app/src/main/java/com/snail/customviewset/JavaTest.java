package com.snail.customviewset;

import com.snail.customviewset.bean.Person;
import com.snail.customviewset.util.PinyinUtil;

import net.sourceforge.pinyin4j.PinyinHelper;
import net.sourceforge.pinyin4j.format.HanyuPinyinCaseType;
import net.sourceforge.pinyin4j.format.HanyuPinyinOutputFormat;
import net.sourceforge.pinyin4j.format.HanyuPinyinToneType;
import net.sourceforge.pinyin4j.format.exception.BadHanyuPinyinOutputFormatCombination;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class JavaTest {
    public static void main(String[] args) {
        Person person = new Person("12");
        Person person2 = new Person("liu");
        List<Person> list = new ArrayList<>();
        list.add(person);
        list.add(person2);

        Collections.sort(list);

        System.out.println(list.toString());
    }
}
