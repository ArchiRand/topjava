
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Meal Form</title>
</head>
<body>
    <h2 align="center" ${currentMeal == null ? "Adding user meal" : "Editing user meal"}></h2>
    <br>
    <form method="post" action="meals">
        <input type="hidden" value="${currentMeal.id}" name="id"/>
        <dl>
            <dt>Date and time</dt>
            <dd><input type="datetime-local" name="date" value="${currentMeal.dateTime}"></dd>
        </dl>
        <dl>
            <dt>Description</dt>
            <dd><input type="text" name="description" value="${currentMeal.description}"></dd>
        </dl>
        <dl>
            <dt>Calories</dt>
            <dd><input type="number" name="calories" value="${currentMeal.calories}"></dd>
        </dl>
        <button type="submit">${currentMeal.id == null ? "Save" : "Edit"}</button>
    </form>
</body>
</html>
