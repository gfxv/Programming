package Jobs;

import Enums.JobType;
import Stuff.Ad;
import Stuff.Job;

public class Doctor extends Job {
    public Doctor (String name, Ad ad) {
        setName(name);
        setJob(JobType.DOCTOR);
        setAd(ad);
    }

    public Doctor (String name) {
        setName(name);
        setJob(JobType.DOCTOR);
    }

}