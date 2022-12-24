public class App {

    public static void main(String[] args) {


        LoonarShorty shorty = new LoonarShorty("Nez");
        HotelOwner hOwner = new HotelOwner("Haps");
        TestCitizen testC = new TestCitizen("ЪЫЪ", CitizenType.DAVILION, new TestJob("xdd"));
        TestCitizen goodDoc = new TestCitizen("gDOC", CitizenType.DAVILION, new Doctor("Хирург", new Ad("ЛУЧШИЙ ВРАЧ!", 10)));
        TestCitizen badDoc = new TestCitizen("bDOC", CitizenType.VISITOR, new Doctor("Ветеринар", new Ad("ХУДШИЙ ВРАЧ!", 1)));

        Hotel emerald = new Hotel("Изумруд", hOwner);

        Factory goodFactory = new Factory("GoodFactory", new Ad("У НАС ВКУСНО!!!", 9));
        Factory badFactory = new Factory("BadFactory", new Ad("У НАС (не) ВКУСНО!!!", 3));

        Food sausage = new Food(FoodType.SAUSAGE, goodFactory);
        Food bread = new Food(FoodType.BREAD, badFactory);
        Food burger = new Food(FoodType.OTHER, goodFactory);
        Food pizza = new Food(FoodType.OTHER, badFactory);

        Thing ball = new Thing("ШАР", new Ad("ЛУЧШИЕ ШАРЫ ТОЛЬКО У НАС", 9));
        Thing gaymingMouse = new Thing("Геймерские мыши", new Ad("ну у нас есть мыши", 5));

        shorty.eatFood(sausage);
        shorty.eatFood(bread);
        shorty.eatFood(burger);
        shorty.eatFood(pizza);

        System.out.println();

        shorty.buyThing(ball);
        shorty.buyThing(gaymingMouse);

        System.out.println();

        shorty.visitJOB(goodDoc);
        shorty.visitJOB(badDoc);

        System.out.println();

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



    }
}
