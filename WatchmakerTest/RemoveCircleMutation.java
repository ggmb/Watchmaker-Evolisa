package WatchmakerTest;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import org.uncommons.watchmaker.framework.EvolutionaryOperator;

public class RemoveCircleMutation implements EvolutionaryOperator<List<Circle>>{

	@Override
	public synchronized List<List<Circle>> apply(List<List<Circle>> candidates, Random rng) {
		List<List<Circle>> mutatedCandidates = new ArrayList<List<Circle>>(candidates.size());
        for (List<Circle> candidate : candidates){
            // A single circle is removed with the configured probability, unless
            // we already have the minimum permitted number of circles.
        	//probability of removing one is 2%
            if (candidate.size() > 2 && (rng.nextDouble() < 0.02)){
            	List<Circle> newCircles = new ArrayList<Circle>(candidate);
                newCircles.remove(rng.nextInt(newCircles.size()));
                mutatedCandidates.add(newCircles);
            }
            else { // Nothing changed.
                mutatedCandidates.add(candidate);
            }
        }
        return mutatedCandidates;
	}

}
