package ru.javawebinar.topjava.dao;

import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.model.MealWithExceed;
import ru.javawebinar.topjava.util.MealsUtil;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

public class MealDaoImpl implements MealDao {

    private static final Map<Integer, Meal> USER_MEAL = new ConcurrentHashMap<>();
    private static final AtomicInteger ID = new AtomicInteger(0);

    @Override
    public void addMeal(Meal meal) {
        int x = ID.incrementAndGet();
        meal.setId(x);
        USER_MEAL.put(x, meal);
    }

    @Override
    public Meal getMeal(int id) {
        return USER_MEAL.get(id);
    }

    @Override
    public List<MealWithExceed> getUserMeal() {
        if (USER_MEAL.isEmpty()) {
            for (Meal y : MealsUtil.MEALS_LIST) {
                int x = ID.incrementAndGet();
                y.setId(x);
                USER_MEAL.put(x, y);
            }
        }
        List<Meal> list = new ArrayList<>();
        list.addAll(USER_MEAL.values());
        return MealsUtil.getFilteredWithExceeded(list, LocalTime.MIN, LocalTime.MAX, 2000);
    }

    @Override
    public void deleteMeal(int id) {
        USER_MEAL.remove(id);
    }

    @Override
    public void updateMeal(Meal meal) {
        USER_MEAL.put(meal.getId(), meal);
    }
}
