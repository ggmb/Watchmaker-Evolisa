package WatchmakerTest;


import java.awt.EventQueue;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import org.uncommons.watchmaker.framework.termination.UserAbort;
import org.uncommons.maths.random.MersenneTwisterRNG;
import org.uncommons.maths.random.Probability;
import org.uncommons.swing.SwingBackgroundTask;
import org.uncommons.watchmaker.framework.CachingFitnessEvaluator;
import org.uncommons.watchmaker.framework.EvolutionEngine;
import org.uncommons.watchmaker.framework.EvolutionObserver;
import org.uncommons.watchmaker.framework.EvolutionaryOperator;
import org.uncommons.watchmaker.framework.FitnessEvaluator;
import org.uncommons.watchmaker.framework.GenerationalEvolutionEngine;
import org.uncommons.watchmaker.framework.PopulationData;
import org.uncommons.watchmaker.framework.SelectionStrategy;
import org.uncommons.watchmaker.framework.operators.EvolutionPipeline;
import org.uncommons.watchmaker.framework.selection.TournamentSelection;
import javax.swing.JButton;
import java.awt.Color;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class UserInterface {

	private JFrame frame;
	public static BufferedImage targetImage;
	public UserAbort abort = new UserAbort();
	public static Canvas canvas;
	public static int tempFitness = 99999999;
	
	public void readImage(){
		try {
			targetImage = ImageIO.read(new File("/Users/gonzalo5207/Documents/workspace/EvolutionaryArt/src/gioconda.jpg"));			
		} catch (IOException e) {
			e.printStackTrace();
			JOptionPane.showMessageDialog(null, "Couldn't load image");
			System.exit(1);
		}
	}

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					UserInterface window = new UserInterface();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public UserInterface() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 500, 500);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JPanel panel = new JPanel();
		frame.getContentPane().add(panel);
		panel.setLayout(null);
		
		readImage();
		ImageIcon imgIcon = new ImageIcon(targetImage);
		JLabel lbl = new JLabel();
		lbl.setIcon(imgIcon);
		lbl.setBounds(0, 0, 220, 282);
		panel.add(lbl);
		
		JButton btnStart = new JButton("Start");
		btnStart.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				(new Evolution()).execute();
			}
		});
		btnStart.setForeground(new Color(0, 128, 0));
		btnStart.setBounds(66, 305, 117, 29);
		panel.add(btnStart);
		
		canvas = new Canvas();
		canvas.setBackground(Color.BLACK); //used to be LIGHT_GRAY
		canvas.setBounds(221, 0, targetImage.getWidth(), targetImage.getHeight());		
		panel.add(canvas);
		
		JButton btnAbort = new JButton("Abort");
		btnAbort.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//abort = true;
				abort.abort();
			}
		});
		btnAbort.setForeground(new Color(255, 0, 0));
		btnAbort.setBounds(249, 305, 117, 29);
		panel.add(btnAbort);
		
	}
	
	private class Evolution extends SwingBackgroundTask<List<Circle>>{

		@Override
		protected List<Circle> performTask() throws Exception {
			
			//creating the circle factory
			CircleFactory factory = new CircleFactory();
			
			//creating the list of mutations
			List<EvolutionaryOperator<List<Circle>>> operators
		    	= new LinkedList<EvolutionaryOperator<List<Circle>>>();
			operators.add(new AddCircleMutation()); //so far 1 mutation
			operators.add(new CircleColorMutation()); 
			operators.add(new CircleSizeMutation());
			operators.add(new MoveCircleMutation());
			operators.add(new RemoveCircleMutation());
			
			//pipeline of all mutations
			EvolutionaryOperator<List<Circle>> pipeline
			= new EvolutionPipeline<List<Circle>>(operators);
			
			//fitness evaluator
			FitnessEvaluator<List<Circle>> evaluator
            = new CachingFitnessEvaluator<List<Circle>>(new FitnessFunction());
			
			SelectionStrategy<Object> selection = new TournamentSelection(new Probability(0.7));
			Random rng = new MersenneTwisterRNG();
			
			EvolutionEngine<List<Circle>> engine
				= new GenerationalEvolutionEngine<List<Circle>>(factory,
                                                                     pipeline,
                                                                     evaluator,
                                                                     selection,
                                                                     rng);
			
			engine.addEvolutionObserver(new EvolutionObserver<List<Circle>>()
			{
				@Override
				public void populationUpdate(PopulationData<? extends List<Circle>> data) {
					// TODO Auto-generated method stub
					System.out.printf("Generation %d: %s\n",
	                          data.getGenerationNumber(),
	                          data.getBestCandidate().size());
					
				}
			});
			
			//new Stagnation(1000, false)
			return engine.evolve(5, 1, abort  ); // , new TargetFitness( 1000, true), new GenerationCount(100));
		}
		
		@Override
        protected void postProcessing(List<Circle> result){
            abort.reset(); //reset so the program can be run again
        }
		
	}
}
