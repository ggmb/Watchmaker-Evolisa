package WatchmakerTest;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.uncommons.maths.random.MersenneTwisterRNG;
import org.uncommons.watchmaker.framework.EvolutionaryOperator;

public class CircleColorMutation implements EvolutionaryOperator<List<Circle>>{

	@Override
	public synchronized List<List<Circle>> apply(List<List<Circle>> candidates, Random rng) {		
        List<List<Circle>> mutatedCandidates = new ArrayList<List<Circle>>(candidates.size());
		
		if (rng.nextDouble()<0.01){ //1% probability	
			for (List<Circle> list : candidates){
				List<Circle> newCircles = new ArrayList<Circle>(list.size());
				for (Circle circle : list){
	        		Color newColor = mutateColor(circle.getColor());
	        		Circle newCircle = new Circle(circle.getX(), circle.getY(), 
	        										circle.getWidth(), circle.getHeight(), newColor); 
		        	newCircles.add(newCircle);	
		        }
		        mutatedCandidates.add(newCircles);
			}
			return mutatedCandidates;
		}
		else return candidates;
	}
	
	/**
     * Mutate the specified colour.
     * @param colour The colour to mutate.
     * @return return the mutated colour.
     */
    public static Color mutateColor(Color color){
    	Color mutatedColor;
    	
    	//create the number by which we will mutate the color component (max is 20)
    	int newRed = (new MersenneTwisterRNG().nextInt(20) );
    	if ((new MersenneTwisterRNG().nextDouble() < 0.5) || (color.getRed() == 255)){ //50% chance to either add or subtract
    		newRed *= -1;
    	}
    	newRed = color.getRed() + newRed; //apply the mutation to the color
    	if (newRed < 0){ //if the value has become out of range (<0) leave it at 0
    		newRed = 0; 
    	}
    	if (newRed > 255){ //if the value has become out of range (>255) leave it at 255
    		newRed = 255; 
    	}
    	
    	//create the number by which we will mutate the color component (max is 20)
    	int newGreen = new MersenneTwisterRNG().nextInt(20) ;
    	if ((new MersenneTwisterRNG().nextDouble() < 0.5) || (color.getGreen() == 255)){ //50% chance to either add or subtract
    		newGreen *= -1;
    	}
    	newGreen = color.getGreen() + newGreen; //apply the mutation to the color
    	if (newGreen < 0){ //if the value has become out of range (<0) leave it at 0
    		newGreen = 0; 
    	}
    	if (newGreen > 255){ //if the value has become out of range (>255) leave it at 255
    		newGreen = 255; 
    	}
    	
    	//create the number by which we will mutate the color component (max is 50)
    	int newBlue = new MersenneTwisterRNG().nextInt(20) ;
    	if ((new MersenneTwisterRNG().nextDouble() < 0.5) || (color.getBlue() == 255)){ //50% chance to either add or subtract
    		newBlue *= -1;
    	}
    	newBlue = color.getBlue() + newBlue; //apply the mutation to the color
    	if (newBlue < 0){ //if the value has become out of range (<0) leave it at 0
    		newBlue = 0; 
    	}
    	if (newBlue > 255){ //if the value has become out of range (>255) leave it at 255
    		newBlue = 255; 
    	}
    	
    	//create the number by which we will mutate the color component (max is 50)
    	int newAlpha = new MersenneTwisterRNG().nextInt(20) ;
    	if ((new MersenneTwisterRNG().nextDouble() < 0.5) || (color.getAlpha() > 127)){ //50% chance to either add or subtract
    		newAlpha *= -1;
    	}
    	newAlpha = color.getAlpha() + newAlpha; //apply the mutation to the color
    	if (newAlpha < 10){ //if the value has become out of range (<10) leave it at 10 (less than 10 is almost invisible)
    		newAlpha = 10; 
    	}
    	if (newAlpha > 127){ //if the value has become out of range (>127) leave it at 127
    		newAlpha = 127; 
    	}


    	mutatedColor = new Color( newRed, newGreen,
    							  newBlue,newAlpha);
    	return mutatedColor;
    }

}
