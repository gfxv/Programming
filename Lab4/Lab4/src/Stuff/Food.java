package Stuff;

import Enums.FoodType;

public class Food {

    private String foodName;

    private FoodType food;
    private Factory factory;

    public Food (FoodType food, String name, Factory factory) {
        this.foodName = name;
        this.food = food;
        this.factory = factory;
    }

    public String getFoodName() {
        return this.foodName;
    }
    public FoodType getFoodType() {
        return this.food;
    }
    public Factory getFactory() {
        return this.factory;
    }

    public String toString() {
        return this.food.getName() + " - " + this.factory.getName();
    }

    public boolean equals(Food othertFood) {
        if (this == othertFood) return true;
        if (othertFood == null || getClass() != othertFood.getClass()) return false;
        return this.food.equals(othertFood.getFoodType()) && this.factory.equals(othertFood.getFactory());
    }

    public int hashCode() {
        return ((this.food == null ? 0 : this.food.hashCode()) + (this.factory == null ? 0 : this.factory.hashCode()));
    }
}
