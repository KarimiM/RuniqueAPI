package com.runique.framework;

import org.parabot.environment.api.utils.Time;
import org.rev317.min.api.methods.Players;
import org.rev317.min.api.wrappers.Tile;

/**
 * Handles locations.
 * @author Empathy
 *
 */
public class Location {

	/**
	 * The tile to create.
	 */
	public Tile tile;

	/**
	 * 
	 * Constructs a new {@code Location} object.
	 * @param x The x parameter.
	 * @param y The y parameter.
	 */
	public Location(int x, int y) {
		this(x, y, 0);
	}

	/**
	 * Constructs a new {@code Location} object.
	 * @param x The x.
	 * @param y The y.
	 * @param z The z.
	 */
	public Location(int x, int y, int z) {
		tile = new Tile(x, y, z);
	}

	/**
	 * Constructs a new {@code Location} object.
	 * @param tile The tile.
	 */
	public Location(Tile tile) {
		this(tile.getX(), tile.getY(), tile.getPlane());
	}

	/**
	 * @return The x.
	 */
	public final int getX() {
		return tile.getX();
	}

	/**
	 * @return The y.
	 */
	public final int getY() {
		return tile.getY();
	}

	/**
	 * @return The plane.
	 */
	public final int getPlane() {
		return tile.getPlane();
	}
	
	/**
	 * Converts the tile to string.
	 */
	public String toString() {
		return "Tile: [" + getX() + ", " + getY() + "]";
	}

	/**
	 * Checks if a tile is equal to this.
	 */
	public boolean equals(Object obj) {
		if ((obj == null) || (obj.getClass() != getClass() || obj.getClass() == Tile.class)) {
			return false;
		}
		if (obj == this) {
			return true;
		}
		if (obj.getClass() == Tile.class) {
			Tile t = (Tile) obj;
			return (t.getX() == getX()) && (t.getY() == getY()) && (t.getPlane() == getPlane());
		}

		Location l = (Location) obj;
		return (l.getX() == getX()) && (l.getY() == getY()) && (l.getPlane() == getPlane());
	}

	/**
	 * Walks to the location.
	 */
	public void walkTo() {
		walkTo(15000);
	}
	
	/**
	 * Walks to the location.
	 * @param sleep The sleep time.
	 */
	public void walkTo(int sleep) {
		tile.walkTo();
		Time.sleep(() -> Players.getMyPlayer().getLocation().equals(getTile()), sleep);
	}

	/**
	 * Returns the tile.
	 * @return The tile.
	 */
	public Tile getTile() {
		return tile;
	}
}
