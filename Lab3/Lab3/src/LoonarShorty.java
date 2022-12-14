
public class LoonarShorty extends LoonarCitizen{

    public LoonarShorty (String name) {
        setName(name);
        setVisitInfo(true);
        setCitizenType(CitizenType.VISITOR);
        setJob(new Astronaut("Астронавт"));
    }

    @Override
    public void readNews() {
        System.out.println("Какие интерсеные новости!");
    }

    @Override
    public void visitHotel() {
        System.out.println("Дома лучше!");
    }

    @Override
    public void wannaLiveInHotel() {
        System.out.println("Я и так тут живу");
    }

    @Override
    public void eatFood(Food food) {
        if (isFoodOK(food)) {
            System.out.println("Кушаю что-то");
        } else {
            System.out.println("Я такое есть не буду! ");
        }
    }

    @Override
    public boolean isFoodOK(Food food) {
        if (food.getFoodType().equals(FoodType.OTHER)) {
            return true;
        }

        for (FoodType foodType : FoodType.values()) {
            if (food.getFoodType().equals(foodType)) {
                if (food.getFactory().getAdInfo().getRating() > 8) {
                    return true;
                }
            }
        }
        return false;
    }

    @Override
    public void visitJOB(LoonarCitizen citizen) {
        if (isJobOK(citizen.getJob())) {
            System.out.println("Иду к: " + citizen.getJob().getName());
        } else {
            System.out.println("Не, я не пойду к: " + citizen.getJob().getName());
        }
    }

    @Override
    public boolean isJobOK(Job job) {
        if (job.getJob().equals(JobType.DOCTOR)){
            return job.getAdRating() > 8;
        }
        return false;
    }

    @Override
    public void buyThing(Thing thing) {
        if (isThingOK(thing)) {
            System.out.println("Надо бы купить: " + thing.getName());
        } else {
            System.out.println("Я не буду покупать: " + thing.getName());
        }
    }

    @Override
    public boolean isThingOK(Thing thing) {
        return thing.getAdInfo().getRating() > 8;
    }

    public String toString() {
        return this.getJob().getName() + " - " + this.getName();
    }

    public boolean equals(LoonarCitizen lc) {
        if (this == lc) return true;
        if (lc == null || getClass() != lc.getClass()) return false;
        return this.getName().equals(lc.getName()) && this.getVisitInfo() == lc.getVisitInfo() &&
                this.getType().equals(lc.getType()) && this.getJob().equals(lc.getJob());
    }

    public int hashCode() {
        return ((this.getName() == null ? 0 : this.getName().hashCode()) * 23 +
                (this.getType() == null ? 0 : this.getType().name().hashCode()) * 47 -
                (this.getJob() == null ? 0 : this.getJob().getName().hashCode()));
    }

}
