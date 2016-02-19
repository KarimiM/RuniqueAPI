package com.runique.framework;

/**
 * Handles a location path.
 * @author Empathy
 *
 */
public class LocationPath {
	
	/**
	 * The locations.
	 */
	private Location[] locations;
	
	/**
	 * Constructs a new {@code LocationPath} object.
	 * @param locations The locations.
	 */
	public LocationPath(Location[] locations) {
		this.locations = locations;
	}
	
	/**
	 * Travels the path.
	 */
	public void travel() {
		for (Location l : locations) {
			l.walkTo();
		}
	}

	/**
	 * @return The locations.
	 */
	public Location[] getLocations() {
		return locations;
	}
}
