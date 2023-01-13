package Exceptions;

public class HotelOverflow extends RuntimeException {
    public HotelOverflow() {
        super("Отель переполнен");
    }
}
