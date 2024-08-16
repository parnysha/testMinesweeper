package org.example.minesweeper.dto.converter;

import jakarta.persistence.AttributeConverter;

import java.util.stream.IntStream;

public class CustomConverter implements AttributeConverter<Character[][], String> {

    @Override
    public String convertToDatabaseColumn(Character[][] chars) {
        StringBuilder sb = new StringBuilder();
        for (int i=0; i<chars.length; i++) {
            for (int j=0; j<chars[i].length; j++) {
                sb.append(chars[i][j]);
            }
            sb.append(",");
        }
        return sb.toString();
    }

    @Override
    public Character[][] convertToEntityAttribute(String s) {
        int length=s.indexOf(",")+1;
        String[] sChars = s.split(",");
        char[][] chars = new char[sChars.length][];
        for (int i=0; i<sChars.length; i++) {
            chars[i] = sChars[i].toCharArray();
        }
        Character [][] charsRez = new Character[chars.length][];
        for (int i=0; i<chars.length; i++) {
            char[] testCh = chars[i];
            Character[] newArray = IntStream.range(0, testCh.length)
                    .mapToObj(j -> testCh[j])
                    .toArray(Character[]::new);
            charsRez[i] = newArray;
        }
        return charsRez;
    }
}
