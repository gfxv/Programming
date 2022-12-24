
public class TestCitizen extends LoonarCitizen {

    public TestCitizen (String name, CitizenType cType, Job job) {
        setName(name);
        setVisitInfo(true);
        setCitizenType(cType);
        setJob(job);
    }

    @Override
    public void readNews() {
        System.out.println("ЧИТАЮ");
    }

    @Override
    public void visitHotel() {
        System.out.println("ИДУ");
    }

    @Override
    public void wannaLiveInHotel() {
        System.out.println("ХОЧУ ТАМ ЖИТЬ");
    }

    @Override
    public void eatFood(Food food) {
        if (isFoodOK(food)) {
            System.out.println("Ем что-то");
        }
    }

    @Override
    public boolean isFoodOK(Food food) {
        return true;
    }

    @Override
    public void visitJOB(LoonarCitizen citizen) {
        if (isJobOK(citizen.getJob())) {
            System.out.println("Иду к: " + citizen.getName());
        }
    }

    @Override
    public boolean isJobOK(Job job) {
        return true;
    }

    @Override
    public void buyThing(Thing thing) {
        if (isThingOK(thing)) {
            System.out.println("Иду покупать: " + thing.getName());
        }
    }

    @Override
    public boolean isThingOK(Thing thing) {
        return true;
    }

    @Override
    public boolean equals(LoonarCitizen citizen) {
        if (this == citizen) return true;
        if (citizen == null || getClass() != citizen.getClass()) return false;
        return (this.getName().equals(citizen.getName()) && this.getJob().equals(citizen.getJob()) && this.getType().name().equals(citizen.getType().name()));
    }

    @Override
    public String toString() {
        return null;
    }

    @Override
    public int hashCode() {
        return ((this.getName() == null ? 0 : this.getName().hashCode()) * 7 +
                (this.getType() == null ? 0 : this.getType().hashCode()) -
                (this.getJob() == null ? 0 : this.getJob().hashCode()) * 3);

    }
}
