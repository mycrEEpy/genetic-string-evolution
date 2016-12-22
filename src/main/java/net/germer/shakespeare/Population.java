package net.germer.shakespeare;

import java.util.ArrayList;
import java.util.Random;

public class Population {

	private final String TARGET;
	private final int MAX_POPULATION;
	private final double MUTATION_RATE;
	private final long SEED;

	private ArrayList<Lifeform> populationPool;
	private ArrayList<Lifeform> matingPool;
	private Random pseudoRandom;
	private double totalFitness;

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
			populationPool.add(new Lifeform(TARGET, pseudoRandom.nextLong()));
		}
	}

	public void evolve() {
		while (!isEvolutionComplete()) {
			calculateFitness();
			performNaturalSelection();
			generateNewPopulation();
		}
	}

	private void calculateFitness() {
		totalFitness = 0.0;
		for (Lifeform l : populationPool) {
			totalFitness += l.calculateFitness();
			System.out.println(l.toString() + " --> " + l.getFitness());
		}
	}

	private void performNaturalSelection() {
		matingPool = new ArrayList<Lifeform>();
		for (Lifeform l : populationPool) {
			double normFitness = l.getFitness() / totalFitness;
			for (int i = 0; i < (normFitness * 100); i++) {
				matingPool.add(l);
			}
		}
		// TODO use mating pool for natural selection
	}

	private void generateNewPopulation() {

	}

	private boolean isEvolutionComplete() {
		for (Lifeform l : populationPool) {
			if (l.toString().equals(TARGET)) {
				return true;
			}
		}
		return false;
	}

}
