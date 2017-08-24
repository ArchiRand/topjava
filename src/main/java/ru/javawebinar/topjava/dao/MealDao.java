package ru.javawebinar.topjava.dao;

import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.to.MealWithExceed;

import java.util.List;

public interface MealDao {
    void addMeal(Meal meal);
    Meal getMeal(int id);
    List<MealWithExceed> getUserMeal();
    void deleteMeal(int id);
    void updateMeal(Meal meal);
}
