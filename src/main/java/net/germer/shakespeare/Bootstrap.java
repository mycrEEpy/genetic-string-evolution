package net.germer.shakespeare;

public class Bootstrap {

	public static void main(String[] args) {
		String target = "to be or not to be";
		int maxPopulation = 20;
		double mutationRate = 0.05;
		// long seed = 16546412342345L;

		// Population pop = new Population(target, maxPopulation, mutationRate, seed);
		Population pop = new Population(target, maxPopulation, mutationRate);
		pop.evolve();
	}

}
