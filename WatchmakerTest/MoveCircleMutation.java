package WatchmakerTest;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.uncommons.maths.random.MersenneTwisterRNG;
import org.uncommons.watchmaker.framework.EvolutionaryOperator;

public class MoveCircleMutation implements EvolutionaryOperator<List<Circle>>{

	@Override
	public List<List<Circle>> apply(List<List<Circle>> candidates, Random rng) {
		List<List<Circle>> mutatedCandidates = new ArrayList<List<Circle>>(candidates.size());
		List<Circle> newCircles = new ArrayList<Circle>(candidates.size());
		
		if (rng.nextDouble() < 0.03){ // 3% probability	
			for (Circle circle : candidates.get(0)){
        		Circle mutatedCircle = moveCircle(circle);
	        	newCircles.add(mutatedCircle);	
	        }
	        mutatedCandidates.add(newCircles);
	        return mutatedCandidates;
		} else return candidates;
	}
	
	public Circle moveCircle(Circle circle){
		//mutation will only be up to 10pixels
		int newX = (int)(new MersenneTwisterRNG().nextDouble()*10);
		int newY = (int)(new MersenneTwisterRNG().nextDouble()*10);
		
		//make it equally possible to add than to subtract
		if (new MersenneTwisterRNG().nextBoolean()){
			newX = newX*-1; 
			newY = newY*-1;
		}
		
		circle.setX(circle.getX()+newX); //add mutation to circles coordinates
		circle.setY(circle.getY()+newY);
		
		if (circle.getX()<0) { circle.setX(0); } //dont allow the circle to be outside the canvas
		if (circle.getY()<0) { circle.setY(0); }
		
		//if the circle is outside the boundaries of the canvas, drag it inside the canvas
		if (circle.getX()>UserInterface.targetImage.getWidth()){
			circle.setX(UserInterface.targetImage.getWidth());
		} 
		if (circle.getY()>UserInterface.targetImage.getHeight()){
			circle.setY(UserInterface.targetImage.getHeight());
		}
		
		return circle;
	}

}
