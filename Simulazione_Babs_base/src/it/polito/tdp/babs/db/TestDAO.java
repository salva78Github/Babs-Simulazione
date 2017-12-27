package it.polito.tdp.babs.db;

import java.util.List;

import it.polito.tdp.babs.exception.BabsException;
import it.polito.tdp.babs.model.Station;
import it.polito.tdp.babs.model.Trip;

public class TestDAO {

	public static void main(String args[]) {
		
		BabsDAO dao = new BabsDAO();

		List<Station> stations;
		try {
			stations = dao.getAllStations();
			for (Station s : stations) {
				System.out.format("%2d %-20s\n", s.getStationID(), s.getName());
			}
			List<Trip> trips = dao.getAllTrips();
			System.out.println("We have " + trips.size() + " trips");
		} catch (BabsException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}



	}
}
