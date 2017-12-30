package it.polito.tdp.babs.model;

public class StationStatistics implements Comparable<StationStatistics>{

	private Station station;
	private int startTrips;
	private int endTrips;

	/**
	 * @param station
	 */
	public StationStatistics(Station station) {
		super();
		this.station = station;
	}

	/**
	 * @return the station
	 */
	public Station getStation() {
		return station;
	}

	/**
	 * @param station
	 *            the station to set
	 */
	public void setStation(Station station) {
		this.station = station;
	}

	/**
	 * @return the startTrips
	 */
	public int getStartTrips() {
		return startTrips;
	}

	/**
	 * @param startTrips
	 *            the startTrips to set
	 */
	public void setStartTrips(int startTrips) {
		this.startTrips = startTrips;
	}

	/**
	 * @return the endTrips
	 */
	public int getEndTrips() {
		return endTrips;
	}

	/**
	 * @param endTrips
	 *            the endTrips to set
	 */
	public void setEndTrips(int endTrips) {
		this.endTrips = endTrips;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "StationStatistics [station=" + station + ", startTrips=" + startTrips + ", endTrips=" + endTrips + "]";
	}

	@Override
	public int compareTo(StationStatistics arg0) {
		// TODO Auto-generated method stub
		return Double.compare(this.station.getPosition().getLatitude(),arg0.getStation().getPosition().getLatitude());
		/*
		double difference =  (this.station.getPosition().getLatitude()-arg0.getStation().getPosition().getLatitude());
		return difference>0?-1:(difference<0?1:0);
		*/
	}

}
