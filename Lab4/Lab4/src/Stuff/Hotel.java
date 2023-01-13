package Stuff;

import Citizens.LoonarCitizen;
import Enums.CitizenType;
import Exceptions.HotelOverflow;
import Jobs.HotelOwner;
import Enums.JobType;

import java.lang.Math;

public class Hotel {

    private String name;
    private HotelOwner owner;
    private int monthIncome;

    private int maxCapacity;

    private LoonarCitizen[] hotelGuests = new LoonarCitizen[0];

    public Hotel (String name, HotelOwner owner, int maxCapacity) {
        this.name = name;
        this.owner = owner;
        this.monthIncome = (int) (Math.random() * 100);
        this.maxCapacity = maxCapacity;

    }

    public void addGuest (LoonarCitizen guest) {

        if (this.hotelGuests.length >= this.maxCapacity) {
            throw new HotelOverflow();
        }

        if (checkGuestWill(guest)) {

            LoonarCitizen[] newGuestList = new LoonarCitizen[this.hotelGuests.length + 1];

            for (int i = 0; i < this.hotelGuests.length; i++) {
                newGuestList[i] = this.hotelGuests[i];
            }

            newGuestList[newGuestList.length - 1] = guest;

            this.hotelGuests = newGuestList;
        }

    }

    private boolean checkGuestWill (LoonarCitizen guest) {
        if (guest.getType() == CitizenType.VISITOR && this.name.equals("Изумруд")) {
            return true;
        }

        if (guest.getType() != CitizenType.DAVILION && guest.getType() != CitizenType.VISITOR) {
            return true;
        }

        return false;

    }

    public LoonarCitizen[] getHotelGuests () {
        return this.hotelGuests;
    }

    public int getMonthIncome () {

        if (this.hotelGuests.length == 0) {
            return 0;
        }

        for (LoonarCitizen s : this.hotelGuests) {
            if (s.getJob().getJobType().equals(JobType.ASTRONAUT)) {
                return this.monthIncome * this.hotelGuests.length * 2;
            }
        }
        return this.monthIncome * this.hotelGuests.length;
    }

    public void setOwner (HotelOwner newOwner) {
        this.owner = newOwner;
    }

    public HotelOwner getOwner() {
        return this.owner;
    }

    public String getName () {
        return this.name;
    }

    public String toString() {
        return "Name: " + this.name + "; Owner: " + owner.getName();
    }

    public boolean equals(Hotel hotel) {
        if (this == hotel) return true;
        if (hotel == null || getClass() != hotel.getClass()) return false;
        return this.name.equals(hotel.getName()) && this.owner.equals(hotel.getOwner()) && this.monthIncome == hotel.getMonthIncome();
    }

    public int hashCode() {
        return ((this.name == null ? 0 : this.name.hashCode()) * 17 -
                (this.owner == null ? 0 :this.owner.getName().hashCode()) * 3 +
                this.monthIncome);
    }

}
