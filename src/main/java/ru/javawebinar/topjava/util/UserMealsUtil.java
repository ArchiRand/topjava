package ru.javawebinar.topjava.util;

import ru.javawebinar.topjava.model.UserMeal;
import ru.javawebinar.topjava.model.UserMealWithExceed;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.Month;
import java.util.*;

/**
 * GKislin
 * 31.05.2015.
 */
public class UserMealsUtil {
    public static void main(String[] args) {
        List<UserMeal> mealList = Arrays.asList(
                new UserMeal(LocalDateTime.of(2015, Month.MAY, 30,10,0), "Завтрак", 500),
                new UserMeal(LocalDateTime.of(2015, Month.MAY, 30,13,0), "Обед", 1000),
                new UserMeal(LocalDateTime.of(2015, Month.MAY, 30,20,0), "Ужин", 500),
                new UserMeal(LocalDateTime.of(2015, Month.MAY, 31,10,0), "Завтрак", 1000),
                new UserMeal(LocalDateTime.of(2015, Month.MAY, 31,13,0), "Обед", 500),
                new UserMeal(LocalDateTime.of(2015, Month.MAY, 31,20,0), "Ужин", 510)
        );
        getFilteredWithExceeded(mealList, LocalTime.of(7, 0), LocalTime.of(12,0), 2000);
//        .toLocalDate();
//        .toLocalTime();
    }

//    public static List<UserMealWithExceed>  getFilteredWithExceeded(List<UserMeal> mealList, LocalTime startTime, LocalTime endTime, int caloriesPerDay) {
//        // TODO return filtered list with correctly exceeded field
//        Map<LocalDate, Integer> map = mealList.stream().collect(Collectors.groupingBy(meal -> meal.getDateTime().toLocalDate(),
//                Collectors.summingInt(UserMeal::getCalories)));
//
//
//        return mealList.stream().
//                filter(meal -> TimeUtil.isBetween(meal.getDateTime().toLocalTime(), startTime, endTime)).
//                map(meal -> new UserMealWithExceed(meal.getDateTime(), meal.getDescription(), meal.getCalories(),
//                        map.get(meal.getDateTime().toLocalDate()) > caloriesPerDay)).collect(Collectors.toList());
//    }

//    The same method but calculating with loops
        public static List<UserMealWithExceed>  getFilteredWithExceeded(List<UserMeal> mealList, LocalTime startTime, LocalTime endTime, int caloriesPerDay) {

            Map<LocalDate, Integer> map = new HashMap<>();
            for (UserMeal x : mealList) {
                map.merge(x.getDateTime().toLocalDate(), x.getCalories(), (a, b) -> a + b);
            }

            List<UserMealWithExceed> list = new ArrayList<>();
            for (UserMeal x : mealList) {
                if (TimeUtil.isBetween(x.getDateTime().toLocalTime(), startTime, endTime)) {
                    list.add(new UserMealWithExceed(x.getDateTime(), x.getDescription(), x.getCalories(),
                            map.get(x.getDateTime().toLocalDate()) > caloriesPerDay));
                }
            }
            return list;
        }
}
