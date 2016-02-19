package com.runique.framework;

import org.rev317.min.api.methods.Inventory;
import org.rev317.min.api.wrappers.Item;

/**
 * Handles potions for Runique.
 * @author Empathy
 *
 */
public enum Potion {
	
	SUPER_RESTORE(3025, 3027, 3029, 3031),
	RANGING(2445, 170, 172, 174),
	OVERLOAD(15334, 15333, 15335, 15336)
	;
	
	/**
	 * The ids in each 4 dose potion.
	 */
	private int[] ids;
	
	/**
	 * Constructs a new {@code Potion} object.
	 * @param ids The ids.
	 */
	Potion(int...ids) {
		this.ids = ids;
	}
	
	/**
	 * @return The ids.
	 */
	public int[] getIds() {
		return ids;
	}
	
	/**
	 * Drinks the potion.
	 */
	public void drink() {
		Item[] item = Inventory.getItems(getIds());
		if (item.length > 0) {
			RuniqueInventory.consume(item[0]);
		}
	}
	
	/**
	 * Checks if the player has a potion.
	 * @return True if so.
	 */
	public boolean hasPotion() {
		for (Item i : Inventory.getItems()) {
			for (int id : ids) {
				if (i.getId() == id) {
					return true;
				}
			}
		}
		return false;
	}
}
