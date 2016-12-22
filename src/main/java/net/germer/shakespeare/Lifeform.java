package net.germer.shakespeare;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.RandomStringUtils;

public class Lifeform {
	
	private final String TARGET;
	public static final String VALID_GENES = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz ,.!?-äüöß";

	private List<Gene> genePool;
	private double fitness;

	public Lifeform(String target) {
		this.TARGET = target;
		fitness = 0.0;
		setup();
	}
	
	public Lifeform(String target, String genes) {
		this.TARGET = target;
		breed(genes);
	}

	private void setup() {
		genePool = new ArrayList<Gene>();
		for (int i = 0; i < TARGET.length(); i++) {
			genePool.add(new Gene(
					RandomStringUtils.random(1, 0, 0, false, false, VALID_GENES.toCharArray(), Population.getPseudoRandom()).charAt(0)));
		}
	}
	
	private void breed(String genes) {
		genePool = new ArrayList<Gene>();
		for (int i = 0; i < genes.length(); i++) {
			genePool.add(new Gene(genes.charAt(i)));
		}
	}

	public double calculateFitness() {
		int score = 0;
		for (int i = 0; i < genePool.size(); i++) {
			if (genePool.get(i).getC() == TARGET.charAt(i)) {
				score++;
			}
		}
		this.fitness = (double) score / (double) TARGET.length();
		return this.fitness;
	}

	public double getFitness() {
		return fitness;
	}

	public String toString() {
		StringBuilder sb = new StringBuilder();
		for (Gene g : genePool) {
			sb.append(g.getC());
		}
		return sb.toString();
	}

}
