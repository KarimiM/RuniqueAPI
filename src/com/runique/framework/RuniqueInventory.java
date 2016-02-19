package com.runique.framework;

import org.parabot.environment.api.utils.Time;
import org.rev317.min.api.methods.Inventory;
import org.rev317.min.api.methods.Menu;
import org.rev317.min.api.wrappers.Item;

/**
 * Handles the inventory for Runique.
 * @author Empathy
 *
 */
public class RuniqueInventory {

	/**
	 * Checks if the players inventory contains an item.
	 * @param id The id.
	 * @return True if so.
	 */
	public boolean contains(int id) {
		return Inventory.getItem(id) != null;
	}
	
	/**
	 * Checks if the players inventory contains any of the parameters
	 * @param ids The ids.
	 * @return True if so.
	 */
	public boolean containsAny(int...ids) {
		for (int id : ids) {
			return contains(id);
		}
		return false;
	}
	
	/**
	 * Checks if the players inventory contains all the items.
	 * @param ids The ids
	 * @return True if so.
	 */
	public boolean containsAll(int...ids) {
		for (int id : ids) {
			if (!contains(id)) {
				return false;
			}
		}
		return true;
	}
	
	/**
	 * Gets the total count of non stackable items.
	 * @param id The id.
	 * @return True if so.
	 */
	public int getCount(int id) {
		int count = 0;
		for (Item i : Inventory.getItems()) {
			if (i.getId() == id) {
				count++;
			}
		}
		return count;
	}

	/**
	 * Consumes an item.
	 * @param item The item.
	 */
	public static void consume(Item item) {
		if (item != null) {
			Menu.sendAction(74, item.getId() - 1, item.getSlot(), 3214);
			Time.sleep(250);
		}
	}

	/**
	 * Consumes an item.
	 * @param id The id.
	 */
	public static void consume(int id) {
		RuniqueInventory.consume(Inventory.getItem(id));
	}

	/**
	 * Equips an item
	 * @param id The id.
	 */
	public static void equip(int id) {
		Item item = Inventory.getItem(id);
		if (item != null) {
			Menu.sendAction(454, item.getId() - 1, item.getSlot(), 3214);
			Time.sleep(250);
		}
	}
}
