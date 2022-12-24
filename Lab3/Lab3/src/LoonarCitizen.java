public abstract class LoonarCitizen implements CitizenAction, FoodAction, ThingAction, JobAction{

    private String name;
    private CitizenType citizenType;
    private boolean canVisitHotel;

    private Job citizenJob;

    public String getName() {
        return this.name;
    }

    public CitizenType getType() {
        return this.citizenType;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setCitizenType(CitizenType citizenType) {
        this.citizenType = citizenType;
    }

    public boolean getVisitInfo () {
        return this.canVisitHotel;
    }

    public void setVisitInfo(boolean canVisitHotel) {
        this.canVisitHotel = canVisitHotel;
    }

    public Job getJob () {
        return this.citizenJob;
    }

    public void setJob (Job job) {
        this.citizenJob = job;
    }


    public abstract boolean equals(LoonarCitizen citizen);
    public abstract String toString();
    public abstract int hashCode();

}
