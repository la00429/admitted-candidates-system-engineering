package Repository;

import model.Candidate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class InMemoryCandidateRepository implements CandidateRepository {

    private final List<Candidate> candidates = new ArrayList<>();

    @Override
    public void addCandidate(Candidate candidate) {
        if (candidate != null) {
            candidates.add(candidate);
        }
    }

    @Override
    public List<Candidate> getAllCandidates() {
        return Collections.unmodifiableList(candidates);
    }
}
