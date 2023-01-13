package Jobs;

import Exceptions.InvalidAdRating;
import Stuff.Ad;
import Enums.JobType;
import Stuff.Job;

public class TestJob extends Job {

    public TestJob (String name) throws InvalidAdRating {
        setName(name);
        setJob(JobType.OTHER);
        setAd(new Ad("None", 0));
    }
}
