
class Doctor extends Job{
    public Doctor (String name, Ad ad) {
        setName(name);
        setJob(JobType.DOCTOR);
        setAd(ad);
    }

    public Doctor (String name) {
        setName(name);
        setJob(JobType.DOCTOR);
        setAd(new Ad("None", 0));
    }

}