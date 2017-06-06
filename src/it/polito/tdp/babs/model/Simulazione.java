package it.polito.tdp.babs.model;

import java.time.LocalDateTime;
import java.util.List;
import java.util.PriorityQueue;

public class Simulazione {

	SimulationResult simulationResult;
	
	private enum EventType{
		PICK, DROP;
	}
	
	PriorityQueue<Event> pt;
	
	public Simulazione(){
		pt = new PriorityQueue<Event>();
		simulationResult = new SimulationResult();
	}
	
	public void loadPick(List<Trip> trips){
		for (Trip trip : trips) {
			pt.add(new Event(EventType.PICK, trip.getStartDate(), trip));
		}
	}
	
	// schedulare il drop dopo aver insertio l'oggetto
	public void loadDrop(List<Trip> trips){
		for (Trip trip : trips) {
			pt.add(new Event(EventType.DROP, trip.getEndDate(), trip));
		}
	}
	
	public void loadStations(double k, List<Station> stazioni) {
		
	}
	
	public void run(){
		
	}
	
	public SimulationResult collectResults(){
		return simulationResult;
	}
	
	private class Event implements Comparable<Event> {

		EventType type;
		LocalDateTime ldt;
		Trip trip;
		
		public Event(EventType type, LocalDateTime dateTime, Trip trip){
			this.type = type;
			this.ldt = dateTime;
			this.trip = trip;
		}
		
		@Override
		public int compareTo(Event o) {
			// TODO Auto-generated method stub
			return 0;
		}
		
	}
	
}
