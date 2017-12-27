package it.polito.tdp.babs.model;

import java.time.LocalDateTime;

import it.polito.tdp.babs.exception.BabsException;

public class Event implements Comparable<Event>{
	
	public enum EventType{
		PARTENZA("P"){
			@Override
			public void process(Simulator sim, Event e) {
				Station s = e.getStation();
				try {
					sim.checkBiciDaPrendere(s);
					sim.prendiBici(s,e);
				} catch (BabsException be) {
					sim.incrementBiciNonPrese();
				}
			}

		},
		ARRIVO("A"){
			@Override
			public void process(Simulator sim, Event e) {
				Station s = e.getStation();
				try {
					sim.checkBiciDaLasciare(s);
					sim.lasciaBici(s,e);
				} catch (BabsException be) {
					sim.incrementBiciNonRestituite();
				}
			}
		};
		
		private String code;
		
		String getCode(){
			return code;
		}
		
		EventType(String code){
			this.code=code;
		}
		
		public static EventType resoveByCode(String code){
			for(EventType et : EventType.values()){
				if(et.getCode().equals(code)){
					return et;
				}
			}
			
			throw new IllegalArgumentException("Il codice " + code + " non è valido.");
		}

		public void process(Simulator sim, Event e) {
			
		}
	}
	
	private LocalDateTime time;
	private EventType type;
	private Station station;
	
	
	/**
	 * @param time
	 * @param type
	 * @param station
	 * @param duration 
	 */
	public Event(LocalDateTime time, EventType type, Station station) {
		this.time = time;
		this.type = type;
		this.station = station;
	}



	/**
	 * @return the station
	 */
	public Station getStation() {
		return station;
	}


	/**
	 * @return the time
	 */
	public LocalDateTime getTime() {
		return time;
	}



	/**
	 * @return the type
	 */
	public EventType getType() {
		return type;
	}



	@Override
	public int compareTo(Event o) {
		return time.compareTo(o.getTime());
	}






	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Event [time=" + time + ", type=" + type + ", station=" + station + "]";
	}


	

	
	
}
