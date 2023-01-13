package Jobs;

import Enums.JobType;
import Stuff.Ad;
import Stuff.Job;

public class Astronaut extends Job {

    public Astronaut (String name) {
        setName(name);
        setJob(JobType.ASTRONAUT);
        setAd(new Ad("None", 0));
    }

}
