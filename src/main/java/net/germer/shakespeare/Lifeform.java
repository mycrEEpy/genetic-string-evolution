package net.germer.shakespeare;

import java.util.Random;

import org.apache.commons.lang3.RandomStringUtils;

public class Lifeform {

	private final long SEED;
	private final String TARGET;
	private final String VALID_GENES = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz ";

	private Gene[] genePool;
	private Random pseudoRandom;
	private double fitness;
	private double normalizedFitness;

	public Lifeform(String target, long seed) {
		this.SEED = seed;
		this.TARGET = target;
		fitness = 0.0;
		setup();
	}

	private void setup() {
		pseudoRandom = new Random(SEED);
		genePool = new Gene[TARGET.length()];
		for (int i = 0; i < genePool.length; i++) {
			genePool[i] = new Gene(
					RandomStringUtils.random(1, 0, 0, false, false, VALID_GENES.toCharArray(), pseudoRandom).charAt(0));
		}
	}

	public double calculateFitness() {
		int score = 0;
		for (int i = 0; i < genePool.length; i++) {
			if (genePool[i].getC() == TARGET.charAt(i)) {
				score++;
			}
		}
		this.fitness = (double) score / (double) TARGET.length();
		return this.fitness;
	}

	public double getFitness() {
		return fitness;
	}

	public double getNormalizedFitness() {
		return normalizedFitness;
	}

	public void setNormalizedFitness(double normalizedFitness) {
		this.normalizedFitness = normalizedFitness;
	}

	public String toString() {
		StringBuilder sb = new StringBuilder();
		for (Gene g : genePool) {
			sb.append(g.getC());
		}
		return sb.toString();
	}

}
