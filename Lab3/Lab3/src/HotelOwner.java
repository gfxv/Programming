public class HotelOwner extends Job{

    public HotelOwner (String name) {
        setName(name);
        setJob(JobType.HOTEL_OWNER);
        setAd(new Ad("None", 0));
    }

}
