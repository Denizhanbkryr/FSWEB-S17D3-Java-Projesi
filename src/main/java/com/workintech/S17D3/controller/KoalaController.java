package com.workintech.S17D3.controller;

import com.workintech.S17D3.entity.Kangaroo;
import com.workintech.S17D3.entity.Koala;
import com.workintech.S17D3.validation.ZooKangarooValidation;
import com.workintech.S17D3.validation.ZooKoalaValidation;
import jakarta.annotation.PostConstruct;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import java.util.Map;
@RestController
@RequestMapping("/koalas")
public class KoalaController {

    private Map<Integer, Koala> koalas;

    @PostConstruct
    public void init() {
        koalas = new HashMap<>();
    }
    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<Koala> findAll(){
        return this.koalas.values().stream().toList();
    }
    @GetMapping("/{id}")
    public Koala find(@PathVariable Integer id){

        ZooKoalaValidation.isIdValid(id);
        ZooKoalaValidation.checkKoalaExistence(koalas,id,true);
        return koalas.get(id);
    }

    @PostMapping
    public Koala add(@RequestBody Koala koala){
        ZooKoalaValidation.checkKoalaExistence(koalas,koala.getId(),false);
        ZooKoalaValidation.checkKoalaWeight(koala.getWeight());
        koalas.put(koala.getId(),koala);
        return koalas.get(koala.getId());
    }

    @PutMapping("/{id}")
    public Koala update(Integer id,Koala koala){
        ZooKoalaValidation.isIdValid(id);
        ZooKoalaValidation.checkKoalaWeight(koala.getWeight());
        koala.setId(id);
        if (koalas.containsKey(id)) {
            koalas.put(id,koala);
            return koalas.get(koala.getId());
        } else {
            return add(koala);
        }
    }

    @DeleteMapping("/{id}")
    public Koala delete(@PathVariable("id") Integer id){

        ZooKoalaValidation.isIdValid(id);
        ZooKoalaValidation.checkKoalaExistence(koalas,id,true);
        return koalas.remove(id);
    }


}
