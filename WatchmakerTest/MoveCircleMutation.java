package WatchmakerTest;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.uncommons.maths.random.MersenneTwisterRNG;
import org.uncommons.watchmaker.framework.EvolutionaryOperator;

public class MoveCircleMutation implements EvolutionaryOperator<List<Circle>>{

	@Override
	public synchronized List<List<Circle>> apply(List<List<Circle>> candidates, Random rng) {
		List<List<Circle>> mutatedCandidates = new ArrayList<List<Circle>>(candidates.size());
		
		if (rng.nextDouble() < 0.03){ // 3% probability	

			for (List<Circle> list : candidates){ //for every candidate, create a mutation
				List<Circle> newCircles = new ArrayList<Circle>(list.size());
				for (Circle circle : list){

					//duplicating the original circle
					Circle mutatedCircle = new Circle(circle.getX(), circle.getY(), 
													circle.getWidth(), circle.getHeight(), circle.getColor());
					//applying the mutation to the duplicate
	        		mutatedCircle = moveCircle(circle);
		        	newCircles.add(mutatedCircle);	
		        }
		        mutatedCandidates.add(newCircles);
			}
			return mutatedCandidates;
		} 
		return candidates;
	}
	
	public Circle moveCircle(Circle circle){
		//mutation will only be up to 10pixels
		int newX = new MersenneTwisterRNG().nextInt(10);
		int newY = new MersenneTwisterRNG().nextInt(10);
		
		//make it equally possible to add than to subtract
		if (new MersenneTwisterRNG().nextBoolean()){
			newX = newX*-1; 
			newY = newY*-1;
		}
		
		circle.setX(circle.getX()+newX); //add mutation to circles coordinates
		circle.setY(circle.getY()+newY);
		
		if (circle.getX()<0) { circle.setX(0); } //dont allow the circle to be outside the canvas (too far left)
		if (circle.getY()<0) { circle.setY(0); } //dont allow the circle to be outside the canvas (too far up)
		
		//if the circle is outside the boundaries of the canvas, drag it inside the canvas
		if (circle.getX()> (UserInterface.targetImage.getWidth()-circle.getWidth())){
			circle.setX(UserInterface.targetImage.getWidth());
		} 
		if (circle.getY()>(UserInterface.targetImage.getHeight()-circle.getHeight())){
			circle.setY(UserInterface.targetImage.getHeight());
		}
		
		return circle;
	}

}
