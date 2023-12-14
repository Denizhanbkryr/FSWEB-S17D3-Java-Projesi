package com.workintech.S17D3.validation;

import com.workintech.S17D3.entity.Koala;
import com.workintech.S17D3.exceptions.ZooException;
import org.springframework.http.HttpStatus;

import java.util.Map;

public class ZooKoalaValidation {
    public static void isIdValid(Integer id) {
        if (id == null || id < 0) {
            throw new ZooException("Id is not valid: " + id, HttpStatus.BAD_REQUEST);
        }
    }

    public static void checkKoalaExistence(Map<Integer, Koala> koalas, int id, boolean shouldBeExist) {
        if (shouldBeExist) {
            if (!koalas.containsKey(id)) {
                throw new ZooException("Id is not exist: " + id, HttpStatus.NOT_FOUND);
            }
        } else {
            if (koalas.containsKey(id)) {
                throw new ZooException("Id already exist: " + id, HttpStatus.BAD_REQUEST);
            }
        }
    }

    public static void checkKoalaWeight(Double weight) {
        if (weight == null || weight <= 0) {
            throw new ZooException("Weight shouldn't be null or less than zero: " + weight, HttpStatus.BAD_REQUEST);
        }
    }
}
