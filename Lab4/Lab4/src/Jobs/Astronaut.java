package Jobs;

import Enums.JobType;
import Exceptions.InvalidAdRating;
import Stuff.Ad;
import Stuff.Job;

public class Astronaut extends Job {

    public Astronaut (String name) {
        setName(name);
        setJob(JobType.ASTRONAUT);
        try {
            setAd(new Ad("None", 0));
        } catch (InvalidAdRating ignored) {

        }
    }

}
