package it.polito.tdp.babs.model;

import java.time.LocalDate;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

import it.polito.tdp.babs.db.BabsDAO;
import it.polito.tdp.babs.exception.BabsException;

public class Model {
	private static final BabsDAO dao = new BabsDAO();

	public List<StationStatistics> getStationStatistics(LocalDate ld) throws BabsException {

		List<StationStatistics> ssList = this.dao.getStationStastics(ld);
		Collections.sort(ssList);

		return ssList;

	}

	public HashMap<Integer, Station> getStationsMapForSimulation() {
		HashMap<Integer, Station> stationsMap = new HashMap<Integer, Station>();
		try {
			List<Station> stationsList = this.dao.getAllStations();
			for(Station s : stationsList){
				s.setBusyPlaces((int)(Math.random()*100));
				stationsMap.put(s.getStationID(), s);
			}
						
		} catch (BabsException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return stationsMap;
	}

	public List<Event> getEventsList(LocalDate ld) throws BabsException {
		// TODO Auto-generated method stub
		return dao.getEventsList(ld);
	}

}
