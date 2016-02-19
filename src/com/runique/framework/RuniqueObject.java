package com.runique.framework;

import org.rev317.min.api.methods.Menu;
import org.rev317.min.api.methods.SceneObjects;
import org.rev317.min.api.wrappers.SceneObject;
import org.rev317.min.api.wrappers.Tile;

/**
 * Class for Runique Objects since they're shitty.
 * @author Empathy
 *
 */
public class RuniqueObject {

	/**
	 * Gets a Sceneobject at a location.
	 * @param location The location.
	 * @return the object.
	 */
	public static SceneObject getObject(Location location) {
		return getObject(location.getTile());
	}
	
	/**
	 * Gets an object at a tile.
	 * @param tile The tile.
	 * @return the Object.
	 */
	public static SceneObject getObject(Tile tile) {
		for (SceneObject s : SceneObjects.getNearest()) {
			if (s.getLocation().equals(tile)) {
				return s;
			}
		}
		return null;
	}

	/**
	 * Interacts with an object.
	 * @param action1 The first action. 
	 * @param objectId The object id.
	 * @param s The scene object.
	 */
	public static void interact(int action1, int objectId, SceneObject s) {
		if (s != null) {
			int actionId = 502;
			switch (action1) {
			case 0:
				actionId = 502;
				break;
			case 1:
				actionId = 900;
				break;
			case 2:
				actionId = 113;
				break;
			case 3:
				actionId = 872;
				break;
			case 4:
				actionId = 1062;
				break;
			case 5:
				actionId = 62;
				break;
			}
			Menu.sendAction(actionId, s.getHash(), objectId, 0);
		}
	}
}
