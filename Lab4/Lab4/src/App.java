import Actions.Executor;
import Citizens.LoonarShorty;
import Citizens.TestCitizen;
import Enums.CitizenType;
import Enums.FoodType;
import Exceptions.InvalidAdRating;
import Jobs.Doctor;
import Jobs.HotelOwner;
import Jobs.TestJob;
import Stuff.*;

public class App {

    public static void main(String[] args) {

        Executor executor = new Executor() {
            @Override
            public void execute() {
                System.out.println();

                LoonarShorty shorty = new LoonarShorty("Nez");
                HotelOwner hOwner = new HotelOwner("Haps");
                TestCitizen TVPresenter = null;
                try {
                    TVPresenter = new TestCitizen("Jimmy F.", CitizenType.DAVILION, new TestJob("Ведущий"));
                } catch (InvalidAdRating e) {
                    System.out.println(e.getMessage());
                }

                TestCitizen goodDoc = null;
                try {
                    goodDoc = new TestCitizen("gDOC", CitizenType.DAVILION, new Doctor("Хирург", new Ad("ЛУЧШИЙ ВРАЧ!", 10)));
                } catch (InvalidAdRating e) {
                    System.out.println(e.getMessage());
                }

                TestCitizen badDoc = null;
                try {
                    badDoc = new TestCitizen("bDOC", CitizenType.VISITOR, new Doctor("Ветеринар", new Ad("РЕЖУ ЛЮДЕЙ", 1)));
                } catch (InvalidAdRating e) {
                    System.out.println(e.getMessage());
                }

                Hotel emerald = new Hotel("Изумруд", hOwner, 1000);

                Factory goodFactory = null;
                try {
                    goodFactory = new Factory("GoodFactory", new Ad("У НАС ВКУСНО!!!", 9));
                } catch (InvalidAdRating e) {
                    System.out.println(e.getMessage());
                }
                Factory badFactory = null;
                try {
                    badFactory = new Factory("BadFactory", new Ad("У НАС (не) ВКУСНО!!!", 3));
                } catch (InvalidAdRating e) {
                    System.out.println(e.getMessage());
                }

                Food sausage = new Food(FoodType.SAUSAGE, "Сосиски", goodFactory);
                Food bread = new Food(FoodType.BREAD, "Хлеб", badFactory);
                Food burger = new Food(FoodType.OTHER, "Бургеры", goodFactory);
                Food pizza = new Food(FoodType.OTHER, "Пиццу", badFactory);

                Thing ball = null;
                try {
                    ball = new Thing("ШАР", new Ad("ЛУЧШИЕ ШАРЫ ТОЛЬКО У НАС", 9));
                } catch (InvalidAdRating e) {
                    System.out.println(e.getMessage());
                }
                Thing gaymingMouse = null;
                try {
                    gaymingMouse = new Thing("Геймерские мыши", new Ad("ну у нас есть мыши", 5));
                } catch (InvalidAdRating e) {
                    System.out.println(e.getMessage());
                }

                // Еда
                shorty.eatFood(sausage);
                shorty.eatFood(bread);
                shorty.eatFood(burger);
                shorty.eatFood(pizza);

                System.out.println();

                // Вещи
                shorty.buyThing(ball);
                shorty.buyThing(gaymingMouse);

                System.out.println();

                // Врачи
                shorty.visitJOB(goodDoc);
                shorty.visitJOB(badDoc);

                System.out.println();

                // Газета
                Newspaper davilion = new Newspaper("Вечерний Давилион", "03.09.2043");
                Newspaper.News news1 = davilion.new News("НОВОСТИ ПРО ПРИШЕЛЬЦА", "Невероятные новости!!! Пришелец рекламирует конфетную фабрику \"Заря\"!!!");

                Newspaper OMGNEWS = new Newspaper("ОГО НОВОСТИ", "01.09.2043");
                Newspaper.News news2 = OMGNEWS.new News("ЛУЧШИЙ ВРАЧ В ДАВИЛИОНЕ???", "Пришельца осматривал некий доктор Шприц, проживающий по адреcу...");

                System.out.println(news1);
                System.out.println(news2);

                System.out.println();

                // ТВ Программа
                ShortyInfo.LastVisitedProgramme.setInfo("Интервью и тд...", TVPresenter, 20000);

                System.out.println(ShortyInfo.LastVisitedProgramme.getGeneralInfo());

                System.out.println();

                // Отель
                System.out.println("GetIncome 1");
                System.out.println(emerald.getMonthIncome());

                emerald.addGuest(goodDoc);
                System.out.println("GetIncome 2");
                System.out.println(emerald.getMonthIncome());

                emerald.addGuest(badDoc);
                System.out.println("GetIncome 3");
                System.out.println(emerald.getMonthIncome());

                emerald.addGuest(shorty);

                System.out.println("GetIncome 4");
                System.out.println(emerald.getMonthIncome());

                System.out.println();

                ShortyInfo.HotelInfo.setInfo(emerald, 0);

                System.out.println(ShortyInfo.HotelInfo.getGeneralInfo());
            }
        };

        executor.execute();

    }
}
