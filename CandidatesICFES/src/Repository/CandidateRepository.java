package Repository;

import model.Candidate;
import java.util.List;

public interface CandidateRepository {

    void addCandidate(Candidate candidate);

    List<Candidate> getAllCandidates();
}
