package com.snail.customviewset.util;

import android.support.annotation.NonNull;

import net.sourceforge.pinyin4j.PinyinHelper;
import net.sourceforge.pinyin4j.format.HanyuPinyinCaseType;
import net.sourceforge.pinyin4j.format.HanyuPinyinOutputFormat;
import net.sourceforge.pinyin4j.format.HanyuPinyinToneType;
import net.sourceforge.pinyin4j.format.exception.BadHanyuPinyinOutputFormatCombination;

/**
 * @author yongjie on 2018/6/26.
 */
public class PinyinUtil {

    @NonNull
    public static String getPinYin(@NonNull String ch) {
        char[] chars = ch.toCharArray();
        HanyuPinyinOutputFormat format = new HanyuPinyinOutputFormat();
        format.setToneType(HanyuPinyinToneType.WITHOUT_TONE);
        format.setCaseType(HanyuPinyinCaseType.LOWERCASE);
        StringBuilder builder = new StringBuilder();
        try {
            for (int i = 0; i < chars.length; i++) {
                String[] strings = PinyinHelper.toHanyuPinyinStringArray(chars[i], format);
                if (strings == null) {
                    builder.append(chars[i]);
                } else {
                    builder.append(strings[0]);
                }
            }
        } catch (BadHanyuPinyinOutputFormatCombination badHanyuPinyinOutputFormatCombination) {
            badHanyuPinyinOutputFormatCombination.printStackTrace();
        }
        return builder.toString();
    }
}
