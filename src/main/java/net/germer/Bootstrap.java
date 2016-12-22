package net.germer;

public class Bootstrap {

	public static void main(String[] args) {
		String target = "To be or not to be, that is the question.";
		int maxPopulation = 100;
		double mutationRate = 0.01;
//		long seed = 16546412342345L;

//		Population pop = new Population(target, maxPopulation, mutationRate, seed);
		Population pop = new Population(target, maxPopulation, mutationRate);
		pop.evolve();
	}

}
