public class Job {

    private String name;
    private JobType citizenJob;
    private Ad ad;

    public JobType getJob() {
        return this.citizenJob;
    }

    public String getName() {
        return this.name;
    }

    public void setJob(JobType job) {
        this.citizenJob = job;
    }

    public void setName(String name) { this.name = name; }

    public double getAdRating() {
        return this.ad.getRating();
    }

    public void setAd(Ad ad) {
        this.ad = ad;
    }

    public String toString() {
        return this.name + "(" + this.citizenJob + ")";
    }

    public boolean equals(Job job) {
        if (this == job) return true;
        if (job == null || getClass() != job.getClass()) return false;
        return this.name.equals(job.getName()) && this.citizenJob == job.getJob() && this.ad.getRating() == (job.getAdRating());
    }

    public int hashCode() {
        return ((this.name == null ? 0 :this.name.hashCode()) * 3 +
                (this.citizenJob == null ? 0 : this.citizenJob.hashCode()) * 5 -
                (this.ad == null ? 0 : this.ad.hashCode()));
    }
}
