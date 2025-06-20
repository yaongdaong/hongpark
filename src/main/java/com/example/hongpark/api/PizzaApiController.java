package com.example.hongpark.api;

import com.example.hongpark.entity.Pizza;
import com.example.hongpark.service.PizzaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/pizzas")
public class PizzaApiController {
    @Autowired
    private PizzaService pizzaService;
    // 피자 등록
    @PostMapping
    public ResponseEntity<Pizza> create(@RequestBody Pizza pizza){
        Pizza createdPizza = pizzaService.createPizza(pizza);
        return new ResponseEntity<>(createdPizza, HttpStatus.CREATED);
    }
    // 피자 목록 조회
    @GetMapping
    public ResponseEntity<List<Pizza>> getAllPizza(){
        List<Pizza> pizzas = pizzaService.getAllPizzas();
        return new ResponseEntity<>(pizzas, HttpStatus.OK);
    }

    // 피자 단건 조회
    @GetMapping("/{id}")
    public ResponseEntity<Pizza> getPizzaById(@PathVariable Long id){
        Optional<Pizza> pizza = pizzaService.getPizzaById(id);
        return pizza.map(value->new ResponseEntity<>(value, HttpStatus.OK))
                .orElseGet(()->new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    // 피자 수정
    @PatchMapping("/{id}")
    public ResponseEntity<Pizza> updatePizza(@PathVariable Long id, @RequestBody Pizza pizza){
        Pizza updatedPizza = pizzaService.updatePizza(id, pizza);
        return updatedPizza != null?
                new ResponseEntity<>(updatedPizza, HttpStatus.OK):
                new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    // 피자 삭제
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePizza(@PathVariable Long id){
        pizzaService.deletePizza(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
