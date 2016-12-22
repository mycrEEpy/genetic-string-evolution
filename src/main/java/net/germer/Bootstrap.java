package net.germer;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;

public class Bootstrap {

	public static void main(String[] args) {
		String target = "Lorem ipsum dolor sit amet, consetetur sadipscing elitr.";
		int maxPopulation = 100;
		double mutationRate = 0.01;
//		long seed = 16546412342345L;

//		Population pop = new Population(target, maxPopulation, mutationRate, seed);
		Population pop = new Population(target, maxPopulation, mutationRate);
		
		long start = System.currentTimeMillis();
		pop.evolve();
		long stop = System.currentTimeMillis();
		
		SimpleDateFormat isoFormat = new SimpleDateFormat("HH:mm:ss");
		isoFormat.setTimeZone(TimeZone.getTimeZone("UTC"));
		String dateFormated = isoFormat.format(new Date(stop - start));
		System.out.println("Took: " + dateFormated);
	}

}
