package it.polito.tdp.babs.model;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;

public class Simulazione {

	private SimulationResult simulationResult;
	private Map<Integer, Integer> mapStationOccupacy;
	private PriorityQueue<Event> pt;
	private Model model;

	private enum EventType {
		PICK, DROP;
	}

	public Simulazione(Model model) {
		pt = new PriorityQueue<Event>();
		simulationResult = new SimulationResult();
		mapStationOccupacy = new HashMap<Integer, Integer>();
		this.model = model;
	}

	public void loadPick(List<Trip> trips) {
		for (Trip trip : trips) {
			pt.add(new Event(EventType.PICK, trip.getStartDate(), trip));
		}
	}

	// public void loadDrop(List<Trip> trips){
	// for (Trip trip : trips) {
	// pt.add(new Event(EventType.DROP, trip.getEndDate(), trip));
	// }
	// }

	public void loadStations(double k, List<Station> stazioni) {
		for (Station station : stazioni) {
			int occupacy = (int) (station.getDockCount() * k);
			mapStationOccupacy.put(station.getStationID(), occupacy);
		}
	}

	public void run() {
		while (!pt.isEmpty()) {
			Event event = pt.poll();

			switch (event.type) {
			case PICK:
				int occupacy = mapStationOccupacy.get(event.trip.getStartStationID());
				if ( occupacy > 0) {
					 occupacy--;
					 mapStationOccupacy.put(event.trip.getStartStationID(), occupacy);
					 pt.add(new Event(EventType.DROP, event.trip.getEndDate(), event.trip));
				} else {
					simulationResult.increaseNumberOfPickMiss();
				}
				break;
				
			case DROP:
				occupacy = mapStationOccupacy.get(event.trip.getEndStationID());
				int availability = model.getStationById(event.trip.getEndStationID()).getDockCount();
				if (occupacy >= availability) {
					simulationResult.increaseNumberOfDropMiss();
				} else {
					occupacy ++;
					mapStationOccupacy.put(event.trip.getEndStationID(), occupacy);
				}
				break;
			}
		}
	}

	public SimulationResult collectResults() {
		return simulationResult;
	}

	private class Event implements Comparable<Event> {

		EventType type;
		LocalDateTime ldt;
		Trip trip;

		public Event(EventType type, LocalDateTime dateTime, Trip trip) {
			this.type = type;
			this.ldt = dateTime;
			this.trip = trip;
		}

		@Override
		public int compareTo(Event o) {
			return this.ldt.compareTo(o.ldt);
		}

	}

}
