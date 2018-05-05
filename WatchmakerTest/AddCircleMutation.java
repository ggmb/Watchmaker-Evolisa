package WatchmakerTest;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import org.uncommons.watchmaker.framework.EvolutionaryOperator;

public class AddCircleMutation implements EvolutionaryOperator<List<Circle>>{

	@Override
	public List<List<Circle>> apply(List<List<Circle>> candidates, Random rng) {
		List<List<Circle>> mutatedCandidates = new ArrayList<List<Circle>>(candidates.size());
        for (List<Circle> candidate : candidates)
        {
            // A single circle is added with the configured probability, unless
            // we already have the maximum permitted number of circles.
        	//probability of adding one is 2%
            if (candidate.size() < 50 && (rng.nextDouble()<0.03)){
            	List<Circle> newCircles = new ArrayList<Circle>(candidate);
                newCircles.add(rng.nextInt(newCircles.size() + 1),
                                CircleFactory.createRandomCircle(rng));
                mutatedCandidates.add(newCircles);
            }
            else { // Nothing changed.
                mutatedCandidates.add(candidate);
            }
        }
        return mutatedCandidates;
	}

}
