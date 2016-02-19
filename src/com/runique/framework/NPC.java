package com.runique.framework;

import org.parabot.environment.api.utils.Filter;
import org.rev317.min.api.methods.Npcs;
import org.rev317.min.api.wrappers.Npc;

/**
 * Handles interactions with NPCs easily if you have a combat script.
 * @author Empathy
 *
 */
public class NPC {
	
	/**
	 * The npc.
	 */
	private Npc npc;
	
	/**
	 * 
	 * Constructs a new {@code NPC} object.
	 */
	public NPC() {
		/**
		 * empty
		 */
	}
	
	/**
	 * Constructs a new {@code NPC} object.
	 * @param npc The npc.
	 */
	public NPC(Npc npc) {
		this.npc = npc;
	}

	/**
	 * @return The npc.
	 */
	public Npc getNpc() {
		return npc;
	}
	
	/**
	 * Set the npc.
	 * @param npc The npc.
	 */
	public void setNpc(Npc npc) {
		if (npc == null) {
			return;
		}
		this.npc = npc;
	}

	@SuppressWarnings("deprecation")
	/**
	 * Attacks the npc.
	 */
	public void attack() {
		Npc[] n = Npcs.getNearest(npcFilter);
		if (n.length == 0) {
			System.out.println("No npc to attack");
			return;
		}
		n[0].interact(1);
 	}
	
	/**
	 * Transforms the npc.
	 * @param id The id to transform it into.
	 */
	public void transformNpc(int id) {
		Filter<Npc> filter = new Filter<Npc>() {
			@Override
			public boolean accept(Npc n) {
				return n != null && n.getDef().getId() == id && !n.isInCombat() && n.getInteractingCharacter() == null;
			}
		};
		setNpc(Npcs.getNearest(filter)[0]);
	}
	
	/**
	 * The filter for the npc.
	 */
	private final Filter<Npc> npcFilter = new Filter<Npc>() {
			@Override
			public boolean accept(Npc n) {
				return n != null && n.getDef().getId() == getNpc().getDef().getId() && !n.isInCombat() && n.getInteractingCharacter() == null;
			}

		};
}
