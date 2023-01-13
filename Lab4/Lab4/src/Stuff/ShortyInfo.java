package Stuff;

import Citizens.LoonarCitizen;
import Jobs.HotelOwner;

public class ShortyInfo {

    public static class HotelInfo {
        private static Hotel hotel;
        private static double monthPrice;

        public static void setInfo(Hotel _hotel, double _monthPrice) {
            hotel = _hotel;
            monthPrice = _monthPrice;
        }

        public static void setHotel(Hotel _hotel) {
            hotel = _hotel;
        }

        public static void setMonthPrice(double _monthPrice) {
            monthPrice = _monthPrice;
        }

        public static String getGeneralInfo() {
            return "Коротышка живёт в отеле " + hotel.getName() + "\n" +
                    "Владелец отеля: " + hotel.getOwner().getName() + "\n" +
                    "В месяц он платит: " + monthPrice;
        }

        public static String getHotelName() {
            return hotel.getName();
        }

        public static HotelOwner getHotelOwner() {
            return hotel.getOwner();
        }

        public static double getMonthPrice() {
            return monthPrice;
        }
    }

    public static class LfastVisitedProgramme {
        private static String programmeName;
        private static LoonarCitizen presenter;
        private static double fee;

        public static void setInfo(String _programmeName, LoonarCitizen _presenter, double _fee) {
            programmeName = _programmeName;
            presenter = _presenter;
            fee = _fee;
        }

        public static void setProgrammeName(String _programmeName) {
            programmeName = _programmeName;
        }

        public static void setPresenter(LoonarCitizen _presenter) {
            presenter = _presenter;
        }

        public static void setFee(double _fee) {
            fee = _fee;
        }

        public static String getGeneralInfo() {
            return "Коротышка посетил прогармму: " + programmeName + "\n" +
                    "Ведущий прогаммы был: " + presenter.getName() + "\n" +
                    "За это программу Коротышке заплатили: " + fee;
        }

        public static String getProgrammeName() {
            return programmeName;
        }

        public static LoonarCitizen getPresenter() {
            return presenter;
        }

        public static double getFee() {
            return fee;
        }
    }
}
