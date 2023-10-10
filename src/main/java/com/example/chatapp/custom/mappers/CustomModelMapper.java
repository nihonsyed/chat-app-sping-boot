package com.example.chatapp.custom.mappers;

import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;

import java.lang.reflect.Field;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
public class CustomModelMapper extends ModelMapper {


    public CustomModelMapper() {
        super();
    }
    //todo:handle exceptions for it
    public static <S, D> void mapUsingParentClassProperties(S source, D destination) throws IllegalAccessException {

        Class<?> sourceClass = source.getClass();
        Field[] sourceFields = sourceClass.getDeclaredFields();
        for (Field field : sourceFields) {
            try {
                field.setAccessible(true);
                Object fieldValue = field.get(source);
                if (fieldValue != null) {
                    Field destinationField;
                    try {
                        destinationField = destination.getClass().getDeclaredField(field.getName());
                        destinationField.setAccessible(true);
                        destinationField.set(destination, fieldValue);
                    } catch (NoSuchFieldException e) {
                    }
                }
            } catch (IllegalAccessException e) {
            }
        }
    }

    public <D, S> List<D> mapList(List<S> sourceList, Class<D> destinationType) {
        return sourceList.stream().map(source -> map(source, destinationType)).collect(Collectors.toList());
    }

    public <S> void mapToUpdate(S source, S destination) throws IllegalAccessException {
        Class<?> sourceClass = source.getClass();
        Field[] sourceFields = sourceClass.getDeclaredFields();
        for (Field field : sourceFields) {

            field.setAccessible(true);
            Object fieldValue = field.get(source);
            if (fieldValue != null) {
                field.set(destination, fieldValue);
            }

        }
    }


}
