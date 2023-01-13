package Enums;

public enum FoodType {
    CANDY("Конфета"),
    GINGERBREAD("Коврижка"),
    BREAD("Хлеб"),
    SAUSAGE("Колбаса"),
    ICECREAM("Мороженное"),
    OTHER("Другое");

    private String name;

    FoodType (String name) {
        this.name = name;
    }

    public String getName () {
        return this.name;
    }
}
