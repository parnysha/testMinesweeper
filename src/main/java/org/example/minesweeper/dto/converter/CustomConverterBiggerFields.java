package org.example.minesweeper.dto.converter;

import lombok.SneakyThrows;
import org.example.minesweeper.dto.FieldsString;

import java.lang.reflect.Method;

public class CustomConverterBiggerFields{
    @SneakyThrows
    //Конвертер из массива чаров в несколько строк
    public static FieldsString convertCharsToStrings(String game_id,Character[][] serverChars,int height,int width){
        CustomConverter conv = new CustomConverter();
        String convert = conv.convertToDatabaseColumn(serverChars);
        FieldsString fieldsString = new FieldsString();
        fieldsString.setGameId(game_id);
        for (int i = 0; i < height * width / 224 + 1; i++) {
            String s = "";
            if (i == height * width / 224) {
                s = convert.substring(i * 224, convert.length() - 1);
            } else {
                s = convert.substring(i * 224, (i + 1) * 224);
            }
            Class clazz = fieldsString.getClass();

            Method[] setFieldStringNum = clazz.getMethods();
            for (Method o : setFieldStringNum) {
                if (("setFieldString" + i).equals(o.getName())) {
                    o.setAccessible(true);
                    o.invoke(fieldsString, s);
                }
            }
        }
        return fieldsString;
    }
    //конвертер из нескольких строк в массив чаров
    public static Character[][] convertStringsToChars(FieldsString fieldsString){
        CustomConverter conv = new CustomConverter();
        String sc = "";
        if (fieldsString.getFieldString0() != null) {
            sc += fieldsString.getFieldString0();
        }
        if (fieldsString.getFieldString1() != null) {
            sc += fieldsString.getFieldString1();
        }
        if (fieldsString.getFieldString2() != null) {
            sc += fieldsString.getFieldString2();
        }
        if (fieldsString.getFieldString3() != null) {
            sc += fieldsString.getFieldString3();
        }
        if (fieldsString.getFieldString4() != null) {
            sc += fieldsString.getFieldString4();
        }
        return conv.convertToEntityAttribute(sc);
    }


}
