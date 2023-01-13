package Jobs;

import Enums.JobType;
import Exceptions.InvalidAdRating;
import Stuff.Ad;
import Stuff.Job;

public class HotelOwner extends Job {

    public HotelOwner (String name) {
        setName(name);
        setJob(JobType.HOTEL_OWNER);
        try {
            setAd(new Ad("None", 0));
        } catch (InvalidAdRating ignored) {

        }
    }

}
