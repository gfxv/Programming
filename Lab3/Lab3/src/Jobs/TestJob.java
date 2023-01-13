package Jobs;

import Stuff.Ad;
import Enums.JobType;
import Stuff.Job;

public class TestJob extends Job {

    public TestJob (String name) {
        setName(name);
        setJob(JobType.OTHER);
        setAd(new Ad("None", 0));
    }
}
