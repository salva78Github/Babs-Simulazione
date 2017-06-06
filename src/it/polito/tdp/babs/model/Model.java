package it.polito.tdp.babs.model;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import it.polito.tdp.babs.db.BabsDAO;

public class Model {

	private BabsDAO babsDAO;
	private List<Station> stazioni;
	private Map<Integer, Station> mappaStazioni;
	
	public Model(){
		babsDAO = new BabsDAO();
		mappaStazioni = new HashMap<Integer, Station>();
	}
	
	public List<Station> getStazioni() {
		if (stazioni == null) {
			stazioni = babsDAO.getAllStations();
			for (Station station : stazioni) {
				mappaStazioni.put(station.getStationID(), station);
			}
		}
		return stazioni;
	}
	
	public List<Statistics> getStats(LocalDate ld) {
		List<Statistics> stats = new ArrayList<Statistics>();
		
		for (Station stazione : getStazioni()){
			int picks = babsDAO.getPickNumber(stazione, ld);
			int drops = babsDAO.getDropNumber(stazione, ld);
			Statistics stat = new Statistics(stazione, picks, drops);
			stats.add(stat);
		}
		
		return stats;
	}

	public Station getStationById(int idStation){
		return mappaStazioni.get(idStation);
	}
	
	public List<Trip> getTripsWithPickForDay(LocalDate ld) {
		return babsDAO.getTripsForDayPick(ld);
	}
	
	public List<Trip> getTripsWithDropForDay(LocalDate ld) {
		return babsDAO.getTripsForDayDrop(ld);
	}

}
