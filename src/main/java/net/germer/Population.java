package net.germer;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.apache.commons.lang3.RandomStringUtils;

public class Population {
	
	private static final boolean DEBUG = false;

	private final String TARGET;
	private final int MAX_POPULATION;
	private final double MUTATION_RATE;
	private final long SEED;

	private List<Lifeform> populationPool;
	private List<Lifeform> matingPool;
	private static Random pseudoRandom;
	private double totalFitness;
	private int generation;

	public Population(String target, int maxPopulation, double mutationRate) {
		this(target, maxPopulation, mutationRate, System.currentTimeMillis());
	}

	public Population(String target, int maxPopulation, double mutationRate, long seed) {
		this.TARGET = target;
		this.MAX_POPULATION = maxPopulation;
		this.MUTATION_RATE = mutationRate;
		this.SEED = seed;
		setup();
	}

	private void setup() {
		pseudoRandom = new Random(SEED);
		populationPool = new ArrayList<Lifeform>();
		for (int i = 0; i < MAX_POPULATION; i++) {
			populationPool.add(new Lifeform(TARGET));
		}
	}

	public void evolve() {
		generation = 0;
		while (!isEvolutionComplete()) {
			System.out.println("Generation: " + generation);
			calculateFitness();
			performNaturalSelection();
			generateNewPopulation();
			generation++;
		}
	}

	private void calculateFitness() {
		totalFitness = 0.0;
		Lifeform best = null;
		for (Lifeform l : populationPool) {
			if (best == null || l.getFitness() > best.getFitness()) {
				best = l;
			}
			totalFitness += l.calculateFitness();
			if (DEBUG) {
				System.out.println(l.toString() + " --> " + l.getFitness());
			}
		}
		System.out.println("Best Lifeform: " + best.toString() + " --> " + best.getFitness());
	}

	private void performNaturalSelection() {
		matingPool = new ArrayList<Lifeform>();
		for (Lifeform l : populationPool) {
			double normFitness = l.getFitness() / totalFitness;
			for (int i = 0; i < (int) (normFitness * 100); i++) {
				matingPool.add(l);
			}
		}		
		if (matingPool.isEmpty()) {
			matingPool = populationPool;
		}
	}

	private void generateNewPopulation() {
		List<Lifeform> nextPopulationPool = new ArrayList<Lifeform>();
		for (int i = 0; i < populationPool.size(); i++) {
			Lifeform parent1 = matingPool.get(pseudoRandom.nextInt(matingPool.size()));
			Lifeform parent2 = matingPool.get(pseudoRandom.nextInt(matingPool.size()));
			int midpoint = pseudoRandom.nextInt(TARGET.length());
			StringBuilder sb = new StringBuilder();
			for (int j = 0; j < TARGET.length(); j++) {
				if (j <= midpoint) {
					sb.append(parent1.toString().charAt(j));
				} else {
					sb.append(parent2.toString().charAt(j));
				}
			}
			for (int k = 0; k < sb.length(); k++) {
				int changeOfMutation = pseudoRandom.nextInt(100);
				if ((MUTATION_RATE * 100) > changeOfMutation) {
					sb.setCharAt(k, RandomStringUtils
							.random(1, 0, 0, false, false, Lifeform.VALID_GENES.toCharArray(), pseudoRandom).charAt(0));
				}
			}
			Lifeform child = new Lifeform(TARGET, sb.toString());
			nextPopulationPool.add(child);
		}
		populationPool = nextPopulationPool;
	}

	private boolean isEvolutionComplete() {
		for (Lifeform l : populationPool) {
			if (l.toString().equals(TARGET)) {
				System.out.println("Generation: " + generation);
				System.out.println("Got Target: " + l.toString());
				return true;
			}
		}
		return false;
	}

	public static Random getPseudoRandom() {
		return pseudoRandom;
	}

}
