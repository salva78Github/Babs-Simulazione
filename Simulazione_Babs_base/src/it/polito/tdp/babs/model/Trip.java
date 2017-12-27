package it.polito.tdp.babs.model;

import java.time.LocalDateTime;

public class Trip {

	private int tripID;
	private int duration;
	private LocalDateTime startDate;
	private int startStationID;

	private LocalDateTime endDate;
	private int endStationID;

	public Trip(int tripID, int duration, LocalDateTime startDate, int startStationID, LocalDateTime endDate,
			int endStationID) {
		this.tripID = tripID;
		this.duration = duration;
		this.startDate = startDate;
		this.startStationID = startStationID;
		this.endDate = endDate;
		this.endStationID = endStationID;
	}

	public int getTripID() {
		return tripID;
	}

	public int getDuration() {
		return duration;
	}

	public LocalDateTime getStartDate() {
		return startDate;
	}

	public int getStartStationID() {
		return startStationID;
	}

	public LocalDateTime getEndDate() {
		return endDate;
	}

	public int getEndStationID() {
		return endStationID;
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
		result = prime * result + tripID;
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
		Trip other = (Trip) obj;
		if (tripID != other.tripID)
			return false;
		return true;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Trip [tripID=" + tripID + ", duration=" + duration + ", startDate=" + startDate + ", startStationID="
				+ startStationID + ", endDate=" + endDate + ", endStationID=" + endStationID + "]";
	}

}
