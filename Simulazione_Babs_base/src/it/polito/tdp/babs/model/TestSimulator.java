package it.polito.tdp.babs.model;

import java.time.LocalDate;
import java.time.Month;

public class TestSimulator {

	public static void main(String[] args) {
		Model model = new Model();
		LocalDate ld = LocalDate.of(2013, Month.AUGUST, 29);
		
		Simulator sim = new Simulator(ld, model) ;
		
		
		sim.run();
		
		System.out.println("Bici non prese:   " + sim.getBiciNonPrese());
		System.out.println("Bici non restituite: " + sim.getBiciNonRestituite());


	}
}
