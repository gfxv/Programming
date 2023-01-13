package Actions;

import Citizens.LoonarCitizen;
import Stuff.Job;

public interface JobAction {
    void visitJOB(LoonarCitizen citizen);
    boolean isJobOK(Job job);
}
