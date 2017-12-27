package it.polito.tdp.babs.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

import com.javadocmd.simplelatlng.LatLng;

import it.polito.tdp.babs.exception.BabsException;
import it.polito.tdp.babs.exception.NoTripsException;
import it.polito.tdp.babs.model.Event;
import it.polito.tdp.babs.model.Station;
import it.polito.tdp.babs.model.StationStatistics;
import it.polito.tdp.babs.model.Trip;

public class BabsDAO {

	public List<Station> getAllStations() throws BabsException {
		List<Station> result = new ArrayList<Station>();
		Connection conn = DBConnect.getInstance().getConnection();
		PreparedStatement st = null;
		ResultSet rs = null;
		String sql = "SELECT * FROM station";

		try {
			st = conn.prepareStatement(sql);
			rs = st.executeQuery();
			while (rs.next()) {
				Station station = new Station(rs.getInt("station_id"), rs.getString("name"), new LatLng(rs.getDouble("lat"), rs.getDouble("long")), rs.getInt("dockcount"), rs.getString("landmark"));
				result.add(station);
			}
		
		} catch (SQLException e) {
			e.printStackTrace();
			throw new BabsException("Error in database query", e);
		} finally{
			DBConnect.getInstance().closeResources(conn, st, rs);
		}

		return result;
	}

	public List<Trip> getAllTrips() throws BabsException {
		List<Trip> result = new LinkedList<Trip>();
		Connection conn = DBConnect.getInstance().getConnection();
		PreparedStatement st = null;
		ResultSet rs = null;
		String sql = "SELECT * FROM trip";

		try {
			st = conn.prepareStatement(sql);
			rs = st.executeQuery();

			while (rs.next()) {
				Trip trip = new Trip(rs.getInt("tripid"), rs.getInt("duration"), rs.getTimestamp("startdate").toLocalDateTime(), rs.getInt("startterminal"),
						rs.getTimestamp("enddate").toLocalDateTime(), rs.getInt("endterminal"));
				result.add(trip);
			}

		} catch (SQLException e) {
			e.printStackTrace();
			throw new BabsException("Error in database query", e);
		} finally{
			DBConnect.getInstance().closeResources(conn, st, rs);
		}

		return result;
	}
	
	public List<StationStatistics> getStationStastics(LocalDate ld) throws BabsException {
		List<StationStatistics> ssList = new LinkedList<StationStatistics>();
		Connection conn = DBConnect.getInstance().getConnection();
		PreparedStatement st = null;
		ResultSet rs = null;
		/*
		String sql = "SELECT start_trips.stationId, start_trips.name, start_trips.lat, start_trips.xxx, start_trips.dockcount, start_trips.landmark, start_trips.numberStartTrips AS numberStartTrips, end_trips.numberEndTrips AS numberEndTrips" +
					 "FROM " +
					 "( " +
						 "SELECT s.station_id AS stationId, s.name AS name, s.lat AS lat, s.long AS xxx, s.dockcount AS dockcount, s.landmark AS landmark, COUNT(*) AS numberStartTrips " +
						 "FROM trip t , station s " +
						 "WHERE t.StartTerminal = s.station_id " + 
						 "AND DATE(t.StartDate) = ? " +
						 "GROUP BY s.station_id, s.name, s.lat, s.long, s.dockcount, s.landmark " +
					 ") start_trips, " +
					 "( " +
						 "SELECT s.station_id AS stationId, s.name AS name, s.lat AS lat, s.long AS xxx, s.dockcount AS dockcount, s.landmark AS landmark, COUNT(*) AS numberEndTrips " +
						 "FROM trip t , station s " +
						 "WHERE t.EndTerminal = s.station_id " + 
						 "AND DATE(t.EndDate) = ? " +
						 "GROUP BY s.station_id, s.name, s.lat, s.long, s.dockcount, s.landmark " +
					 ") end_trips " +
					 "WHERE start_trips.stationId = end_trips.stationId";
		*/
		String sql = "SELECT start_trips.stationId, start_trips.name, start_trips.lat, start_trips.longi,start_trips.dockcount, start_trips.landmark, start_trips.numberStartTrips, end_trips.numberEndTrips FROM (SELECT s.station_id AS stationId, s.name AS name, s.lat AS lat, s.long AS longi, s.dockcount AS dockcount, s.landmark AS landmark, COUNT(*) AS numberStartTrips FROM trip t , station s WHERE t.StartTerminal = s.station_id AND DATE(t.StartDate) = ? GROUP BY s.station_id, s.name, s.lat, s.long, s.dockcount, s.landmark) start_trips,( SELECT s.station_id AS stationId, s.name AS name, s.lat AS lat, s.long AS longi, s.dockcount AS dockcount, s.landmark AS landmark, COUNT(*) AS numberEndTrips FROM trip t , station s WHERE t.EndTerminal = s.station_id AND DATE(t.EndDate) = ? GROUP BY s.station_id, s.name, s.lat, s.long, s.dockcount, s.landmark) end_trips WHERE start_trips.stationId = end_trips.stationId";
		try {
			st = conn.prepareStatement(sql);
			st.setDate(1, java.sql.Date.valueOf(ld));
			st.setDate(2, java.sql.Date.valueOf(ld));
			rs = st.executeQuery();
			
			while (rs.next()) {
				Station station = new Station(rs.getInt("stationId"), rs.getString("name"), new LatLng(rs.getDouble("lat"), rs.getDouble("longi")), 
						rs.getInt("dockcount"), rs.getString("landmark"));
				StationStatistics ss = new StationStatistics(station);
				ss.setStartTrips(rs.getInt("numberStartTrips"));
				ss.setEndTrips(rs.getInt("numberEndTrips"));
				ssList.add(ss);
			}
			
			if(ssList.size()==0){
				throw new NoTripsException("Non ci sono trip in partenza nella data " + ld.toString());
			}

		} catch (SQLException e) {
			e.printStackTrace();
			throw new BabsException("Error in database query", e);
		} finally{
			DBConnect.getInstance().closeResources(conn, st, rs);
		}

		return ssList;
	}


	
	public List<Event> getEventsList(LocalDate ld) throws BabsException {
		List<Event> eventsList = new ArrayList<Event>();
		Connection conn = DBConnect.getInstance().getConnection();
		PreparedStatement st = null;
		ResultSet rs = null;
		
		String sql = "SELECT stationId, name, lat, longi, dockcount, landmark, type, time FROM (SELECT s.station_id AS stationId, s.name AS name, s.lat AS lat, s.long AS longi, s.dockcount AS dockcount, s.landmark AS landmark, 'P' type, t.StartDate time FROM trip t , station s WHERE t.StartTerminal = s.station_id AND DATE(t.StartDate) = ? UNION SELECT s.station_id AS stationId, s.name AS name, s.lat AS lat, s.long AS longi, s.dockcount AS dockcount, s.landmark AS landmark, 'A' type, t.EndDate time FROM trip t , station s WHERE t.EndTerminal = s.station_id AND DATE(t.EndDate) = ?) t ORDER BY time";
		try {
			st = conn.prepareStatement(sql);
			st.setDate(1, java.sql.Date.valueOf(ld));
			st.setDate(2, java.sql.Date.valueOf(ld));
			rs = st.executeQuery();
			
			while (rs.next()) {
				Station station = new Station(rs.getInt("stationId"), rs.getString("name"), new LatLng(rs.getDouble("lat"), rs.getDouble("longi")), 
						rs.getInt("dockcount"), rs.getString("landmark"));
				Event e = new Event(rs.getTimestamp("time").toLocalDateTime(), Event.EventType.resoveByCode(rs.getString("type")), station);
				eventsList.add(e);
			}

		} catch (SQLException e) {
			e.printStackTrace();
			throw new BabsException("Error in database query", e);
		} finally{
			DBConnect.getInstance().closeResources(conn, st, rs);
		}

		return eventsList;
	}
	
}