package com.example.hongpark.service;

import com.example.hongpark.entity.Pizza;
import com.example.hongpark.repository.PizzaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PizzaService {
    @Autowired
    private PizzaRepository pizzaRepository;

    public Pizza createPizza(Pizza pizza){
        return pizzaRepository.save(pizza);
    }

    public List<Pizza> getAllPizzas(){
        return pizzaRepository.findAll();
    }

    public Optional<Pizza> getPizzaById(Long id){
        return pizzaRepository.findById(id);
    }

    public Pizza updatePizza(Long id, Pizza newPizza){
        Optional<Pizza> optionalPizza = pizzaRepository.findById(id);
        if(optionalPizza.isPresent()){
            Pizza existingPizza = optionalPizza.get();
            existingPizza.setName(newPizza.getName());
            existingPizza.setPrice(newPizza.getPrice());
            return pizzaRepository.save(existingPizza);
        } else{
            return null;
        }
    }
    public void deletePizza(Long id){
        pizzaRepository.deleteById(id);
    }
}
