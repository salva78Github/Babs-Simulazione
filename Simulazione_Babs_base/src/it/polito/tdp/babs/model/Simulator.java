package it.polito.tdp.babs.model;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.PriorityQueue;

import it.polito.tdp.babs.exception.BabsException;
import it.polito.tdp.babs.exception.NoPlacesAvailableException;

public class Simulator {
	
	// Simulation parameters
	private LocalDate ld; 

	// World model
	HashMap<Integer, Station> stations;

	// Measures of Interest
	private int biciNonPrese = 0;
	private int biciNonRestituite = 0;
	

	// Event queue
	private PriorityQueue<Event> queue;
	private List<Event> eventsList;

	
	public Simulator(LocalDate ld, Model model){
		this.ld = ld; 
		this.stations = model.getStationsMapForSimulation();
		try {
			this.eventsList = model.getEventsList(ld);
		} catch (BabsException e) {
			e.printStackTrace();
		}
		
		initQueue();
	}

	public void initQueue() {
		this.queue = new PriorityQueue<Event>();
		for(Event e : this.eventsList){
			queue.add(e) ;
		}		
	}

	
	public void run() {
		while (!queue.isEmpty()) {
			Event e = queue.poll();
			System.out.println(e);

			e.getType().process(this,e);
		}
	}

	public void checkBiciDaPrendere(Station s) throws BabsException {
		Station station = this.stations.get(s.getStationID());
		if(station.getBusyPlaces() == 0){
			throw new NoPlacesAvailableException("Non ci sono bici disponibili nella stazione " + s);
		}
	}
	public void prendiBici(Station s, Event e) {
		Station station = this.stations.get(s.getStationID());
		station.setBusyPlaces(station.getBusyPlaces()-1);		
	}

	public void incrementBiciNonPrese() {
		this.biciNonPrese++;
	}

	public void checkBiciDaLasciare(Station s) throws BabsException {
		Station station = this.stations.get(s.getStationID());
		if(station.getBusyPlaces() == station.getDockCount()){
			throw new NoPlacesAvailableException("Non ci sono posti disponibili nella staizone " + s);
		}
		
	}

	public void lasciaBici(Station s, Event e) {
		Station station = this.stations.get(s.getStationID());
		station.setBusyPlaces(station.getBusyPlaces()+1);		
	}

	public void incrementBiciNonRestituite() {
		this.biciNonRestituite++;
	}
	
	/**
	 * @return the biciNonPrese
	 */
	public int getBiciNonPrese() {
		return biciNonPrese;
	}
	
	/**
	 * @return the biciNonRestituite
	 */
	public int getBiciNonRestituite() {
		return biciNonRestituite;
	}
	

}
