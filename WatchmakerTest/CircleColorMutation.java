package WatchmakerTest;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.uncommons.maths.random.MersenneTwisterRNG;
import org.uncommons.watchmaker.framework.EvolutionaryOperator;

public class CircleColorMutation implements EvolutionaryOperator<List<Circle>>{

	@Override
	public List<List<Circle>> apply(List<List<Circle>> candidates, Random rng) {		
        List<List<Circle>> mutatedCandidates = new ArrayList<List<Circle>>(candidates.size());
		List<Circle> newCircles = new ArrayList<Circle>(candidates.size());
		
		if (rng.nextDouble()<0.01){ //1% probability	
			for (Circle circle : candidates.get(0)){
        		Color newColor = mutateColor(circle.getColor());
        		circle.setColor(newColor);
	        	newCircles.add(circle);	
	        }
	        mutatedCandidates.add(newCircles);
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
    	Double newRed = (new MersenneTwisterRNG().nextDouble() * 20.0);
    	if ((new MersenneTwisterRNG().nextDouble() < 0.5) || (color.getRed() == 255)){ //50% chance to either add or subtract
    		newRed *= -1;
    	}
    	newRed = (double)color.getRed() + newRed; //apply the mutation to the color
    	if (newRed < 0){ //if the value has become out of range (<0) leave it at 0
    		newRed = 0.0; 
    	}
    	if (newRed > 255){ //if the value has become out of range (>255) leave it at 255
    		newRed = 255.0; 
    	}
    	
    	//create the number by which we will mutate the color component (max is 20)
    	Double newGreen = (new MersenneTwisterRNG().nextDouble() * 20.0);
    	if ((new MersenneTwisterRNG().nextDouble() < 0.5) || (color.getGreen() == 255)){ //50% chance to either add or subtract
    		newGreen *= -1;
    	}
    	newGreen = (double)color.getGreen() + newGreen; //apply the mutation to the color
    	if (newGreen < 0){ //if the value has become out of range (<0) leave it at 0
    		newGreen = 0.0; 
    	}
    	if (newGreen > 255){ //if the value has become out of range (>255) leave it at 255
    		newGreen = 255.0; 
    	}
    	
    	//create the number by which we will mutate the color component (max is 50)
    	Double newBlue = (new MersenneTwisterRNG().nextDouble() * 20.0);
    	if ((new MersenneTwisterRNG().nextDouble() < 0.5) || (color.getBlue() == 255)){ //50% chance to either add or subtract
    		newBlue *= -1;
    	}
    	newBlue = (double)color.getBlue() + newBlue; //apply the mutation to the color
    	if (newBlue < 0){ //if the value has become out of range (<0) leave it at 0
    		newBlue = 0.0; 
    	}
    	if (newBlue > 255){ //if the value has become out of range (>255) leave it at 255
    		newBlue = 255.0; 
    	}
    	
    	//create the number by which we will mutate the color component (max is 50)
    	Double newAlpha = (new MersenneTwisterRNG().nextDouble() * 20.0);
    	if ((new MersenneTwisterRNG().nextDouble() < 0.5) || (color.getAlpha() > 127)){ //50% chance to either add or subtract
    		newAlpha *= -1;
    	}
    	newAlpha = (double)color.getAlpha() + newAlpha; //apply the mutation to the color
    	if (newAlpha < 10){ //if the value has become out of range (<10) leave it at 10 (less than 10 is almost invisible)
    		newAlpha = 10.0; 
    	}
    	if (newAlpha > 127){ //if the value has become out of range (>127) leave it at 127
    		newAlpha = 127.0; 
    	}


    	mutatedColor = new Color( newRed.intValue(), newGreen.intValue(),
    							  newBlue.intValue(),newAlpha.intValue());
    	return mutatedColor;
    }

}
