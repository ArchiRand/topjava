package ru.javawebinar.topjava.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.repository.UserMealRepository;

import java.util.Collection;

@Service
public class MealServiceImpl implements MealService {

    @Autowired
    private UserMealRepository repository;

    @Override
    public Meal save(Meal meal, int userId) {
        return repository.save(meal, userId);
    }

    @Override
    public boolean delete(int id, int userId) {
        return repository.delete(id, userId);
    }

    @Override
    public Meal get(int id, int userId) {
        return repository.get(id, userId);
    }

    @Override
    public Collection<Meal> getAll(int userId) {
        return repository.getAll(userId);
    }

    @Override
    public void deleteAll(int userId) {
//        repository.deleteAll(userId);
    }

    @Override
    public void update(Meal meal, int userId) {
        //repository.update(meal, userId);
    }
}