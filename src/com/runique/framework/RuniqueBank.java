package com.runique.framework;

import java.util.ArrayList;
import java.util.List;

import org.parabot.environment.api.utils.Time;
import org.rev317.min.api.methods.Bank;
import org.rev317.min.api.methods.Menu;
import org.rev317.min.api.wrappers.Item;

/**
 * Handles banking for Runique.
 * @author Empathy
 *
 */
public class RuniqueBank {

	/**
	 * Gets all the items in the bank of an array.
	 * @param ids The ids to check
	 * @return The items with the same ids in the array.
	 */
	public static Item[] getItems(int...ids) {
		List<Item> item = new ArrayList<>();
		for (Item i : Bank.getBankItems()) {
			for (int id : ids) {
				if (i.getId() == id) {
					item.add(i);
				}
			}
		}
		return item.toArray(new Item[item.size()]);
	}
	
	/**
	 * Withdraw one of each id in the parameter.
	 * @param ids The ids.
	 */
	public static void withdraw(int...ids ) {
		for (int i : ids) {
			withdraw(i, 1);
		}
	}
	
	/**
	 * Withdraw one item.
	 * @param id The id.
	 */
	public static void withdraw(int id) {
		withdraw(id, 1);
	}
	/**
	 * Withdraws an item based off id.
	 * @param id The id.
	 * @param amt The amount.
	 */
	public static void withdraw(int id, int amt) {
		withdraw(Bank.getItem(id), amt);
	}
	
	/**
	 * Withdraw all but one
	 * @param id The id
	 * @param allButOne True if all but one.
	 */
	public static void withdraw(int id, boolean allButOne) {
		withdraw(Bank.getItem(id), allButOne ? 2000 : 1);
	}
	
	/**
	 * Withdraws any item/amount (unstackables only).
	 * @param item The item.
	 * @param amt The amount.
	 */
	public static void withdraw(Item item, int amt) {
		if (item != null) {
			int withdrawAmount = getAmount(amt);
			int times = amt / withdrawAmount;
			for (int i = 0; i < times; i++) {
				Menu.sendAction(getAction(withdrawAmount), item.getId() - 1, item.getSlot(), 5382);
				Time.sleep(250);
				if (amt >= 28) {
					return;
				}
			}
			if (amt % withdrawAmount > 0) {
				withdraw(item, amt - (withdrawAmount * times));
			}
		}
	}
	
	/**
	 * Gets the amount to withdraw the item at.
	 * @param amount The total amount.
	 * @return The withdraw amount.
	 */
	public static int getAmount(int amount) {
		return amount < 5 ? 1 : amount < 10 ? 5 : amount < 28 ? 10 : amount == 2000 ? 30 : 28;
	}
	
	/**
	 * Gets the action for the amount.
	 * @param amount The amount.
	 * @return The action.
	 */
	public static int getAction(int amount) {
		int action = 632;
		switch(amount) {
		case 1:
			action = 632;
			break;
		case 5:
			action = 78;
			break;
		case 10:
			action = 867;
			break;
		case 28:
			action = 431;
			break;
		case 30:
			action = 67;
			break;
		}
		return action;
	}
}
