package org.example.minesweeper.dto.converter;

import jakarta.persistence.AttributeConverter;

import java.util.Arrays;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class CustomConverter implements AttributeConverter<Character[][], String> {

    @Override
    public String convertToDatabaseColumn(Character[][] chars) {
    return Arrays.stream(chars)
            .map(charArray -> new String(Arrays.stream(charArray)
                                    .map(String::valueOf)
                                    .collect(Collectors.joining())))
            .collect(Collectors.joining(","));
    }

    @Override
    public Character[][] convertToEntityAttribute(String s){
        return  Arrays.stream(s.split(","))
                .map(x->x.chars().mapToObj(c->(char)c).toArray(Character[]::new))
                .toArray(Character[][]::new);
    }
}
