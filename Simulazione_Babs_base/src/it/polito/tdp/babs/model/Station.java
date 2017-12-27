package it.polito.tdp.babs.model;

import com.javadocmd.simplelatlng.LatLng;

public class Station {

	private int stationID;
	private String name;
	private LatLng position;
	private int dockCount;
	private String city;
	private int busyPlaces;

	/**
	 * @param stationID
	 * @param name
	 * @param position
	 * @param dockCount
	 * @param city
	 */
	public Station(int stationID, String name, LatLng position, int dockCount, String city) {
		this.stationID = stationID;
		this.name = name;
		this.position = position;
		this.dockCount = dockCount;
		this.city = city;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + stationID;
		return result;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Station other = (Station) obj;
		if (stationID != other.stationID)
			return false;
		return true;
	}

	/**
	 * @return the stationID
	 */
	public int getStationID() {
		return stationID;
	}

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @return the position
	 */
	public LatLng getPosition() {
		return position;
	}

	/**
	 * @return the dockCount
	 */
	public int getDockCount() {
		return dockCount;
	}

	/**
	 * @return the city
	 */
	public String getCity() {
		return city;
	}



	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Station [stationID=" + stationID + ", name=" + name + ", position=" + position + ", dockCount="
				+ dockCount + ", city=" + city + ", busyPlaces=" + busyPlaces + "]";
	}

	/**
	 * @return the busyPlaces
	 */
	public int getBusyPlaces() {
		return busyPlaces;
	}

	/**
	 * @param busyPlaces the busyPlaces to set
	 */
	public void setBusyPlaces(int busyPlaces) {
		this.busyPlaces = busyPlaces;
	}



}
