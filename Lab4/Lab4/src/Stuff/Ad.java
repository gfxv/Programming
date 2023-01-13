package Stuff;

import Exceptions.InvalidAdRating;

import java.lang.Math;

public class Ad {

    private String name;
    private double rating;

    public Ad(String name) {
        this.name = name;
        this.rating = generateRating();
    }

    public Ad(String name, double rating) throws InvalidAdRating {
        this.name = name;
        if (rating < 0) {
            throw new InvalidAdRating("Рейтинг рекламы не может быть меньше нуля");
        }
        this.rating = rating;
    }

    private double generateRating() {
        return Math.random() * 10;
    }

    public double getRating() {
        return this.rating;
    }

    public String getName() {
        return this.name;
    }

    public String toString() {
        return "Stuff.Ad: " + this.name + " Rating: " + this.rating;
    }

    public boolean equals(Ad ad) {
        if (this == ad) return true;
        if (ad == null || getClass() != ad.getClass()) return false;
        return this.name.equals(ad.getName()) && this.rating == ad.getRating();
    }

    public int hashCode() {
        return (this.name == null ? 0 : this.name.hashCode() - (int) this.rating * 5);
    }

}
