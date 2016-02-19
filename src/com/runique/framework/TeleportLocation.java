package com.runique.framework;

import java.awt.AWTException;
import java.awt.Robot;
import java.util.HashMap;
import java.util.Map;

import org.parabot.environment.api.utils.Time;
import org.rev317.min.api.methods.Menu;


/**
 * Handles the creation of a new teleport location
 * @author Empathy
 *
 */
public class TeleportLocation {

	/**
	 * The name of the teleport location.
	 */
	private final String name;

	/**
	 * The letters of the teleport location.
	 */
	private final String[] letters;

	/**
	 * Constructs a new {@code TeleportLocation} object.
	 * @param name The name.
	 * @param letters The letters.
	 */
	public TeleportLocation(String name, String... letters) {
		this.name = name;
		this.letters = letters;
	}

	/**
	 * @return The name.
	 */
	public String getName() {
		return name;
	}

	/**
	 * @return The letters
	 */
	public String[] getLetters() {
		return letters;
	}


	/**
	 * Handles all the teleports for Runique.
	 * @author Empathy
	 *
	 */
	public enum Teleport {

		TRAINING(1164, new TeleportLocation("Rock Crabs", "R"), new TeleportLocation("Yaks", "Y"), new TeleportLocation("Desert Bandits", "B"), new TeleportLocation("Gorillas", "G"), new TeleportLocation("Experiments", "E"), new TeleportLocation("Fire Giants", "F"), new TeleportLocation("Cockroaches", "c")), 
		DUNGEON(1167, new TeleportLocation("Brimhaven Dungeon", "B"), new TeleportLocation("Taverly Dungeon", "V"), new TeleportLocation("Dungeon of Abyss", "A"), new TeleportLocation("Slayer Dungeon", "Z", "S")), 
		BOSS(1540, new TeleportLocation("Corporeal Beast", "C"), new TeleportLocation("God Wars Dungeon", "W"), new TeleportLocation("Tormented Demons", "T"), new TeleportLocation("Giant Mole", "M"), new TeleportLocation("Phoenix Lair", "P"), new TeleportLocation("Dagannoth Kings", "D"), new TeleportLocation("Nex", "N"), new TeleportLocation("Bork", "B"), new TeleportLocation("King Black Dragon", "K"), new TeleportLocation("Nomad", "V"), new TeleportLocation("Sea Troll Queen", "Q"), new TeleportLocation("Glacors", "G"), new TeleportLocation("Revenant Cave", "R"), new TeleportLocation("Chaos Elemental", "E"), new TeleportLocation("WildyWyrm", "Y"), new TeleportLocation("Zulrah", "H"), new TeleportLocation("Venenatis", "O", "V"), new TeleportLocation("Vet'ion", "O", "E"), new TeleportLocation("Callisto", "O", "C"), new TeleportLocation("Scorpia", "O", "S"), new TeleportLocation("Barrelchest", "Z", "B"), new TeleportLocation("Bal-lak", "Z", "P"), new TeleportLocation("Gluttonous Behemoth", "Z", "G"), new TeleportLocation("Rammernaut", "Z", "R"), new TeleportLocation("Khighorahk", "Z", "K"), new TeleportLocation("Unholy Cursebearer", "Z", "U"), new TeleportLocation("Kal-ger", "Z", "W"), new TeleportLocation("Avatar of Destruction", "Z", "D"), new TeleportLocation("Avatar of Creation", "Z", "N")),
		SKILLING(1174, new TeleportLocation("Summoning Obelisk", "G", "T"))
		;

		/**
		 * The map with the teleports.
		 */
		private static final Map<String, TeleportLocation> TELEPORT_LOCATIONS = new HashMap<>();

		private static Robot robot = null;

		/**
		 * Populates the teleport map.
		 */
		static {
			for (Teleport t : values()) {
				for (TeleportLocation tl : t.getLocations()) {
					TELEPORT_LOCATIONS.put(tl.getName(), tl);
				}
			}
		}

		/**
		 * The action of the main teleport.
		 */
		private int action;

		/**
		 * The possible teleport locations.
		 */
		private TeleportLocation[] locations;

		/**
		 * Constructs a new {@code Teleports} object.
		 * @param action The actions
		 * @param locations The locations
		 */
		Teleport(int action, TeleportLocation... locations) {
			this.action = action;
			this.locations = locations;
		}

		/**
		 * @return The action
		 */
		public int getAction() {
			return action;
		}

		/**
		 * @return The locations
		 */
		public TeleportLocation[] getLocations() {
			return locations;
		}

		public static Map<String, TeleportLocation> getTeleportLocations() {
			return TELEPORT_LOCATIONS;
		}

		/**
		 * Gets the teleport for location.
		 * @param location The location. 
		 * @return The teleport.
		 */
		public static Teleport forLocation(TeleportLocation location) {
			for (Teleport t : values()) {
				for (TeleportLocation tl : t.getLocations()) {
					if (tl.equals(location)) {
						return t;
					}
				}
			}
			return null;
		}

		/**
		 * Teleports the player
		 * @param name The teleport name.
		 */
		public static void teleport(String name) {
			TeleportLocation tl = TELEPORT_LOCATIONS.get(name);
			if (tl != null) {
				teleport(tl);
			}
		}

		/**
		 * Teleports to a location.
		 * @param location The teleport location.
		 */
		public static void teleport(TeleportLocation location) {
			if (robot == null) {
				try {
					robot = new Robot();
				} catch (AWTException e) {
					e.printStackTrace();
				}
			}
			Teleport t = forLocation(location);
			Menu.sendAction(315, 0, 0, t.getAction());
			Time.sleep(1000);
			for (String s : location.getLetters()) {
				robot.keyPress(s.charAt(0));
				robot.keyRelease(s.charAt(0));
				Time.sleep(500);
			}
		}
		
		/**
		 * Teleports home.
		 */
		public static void home() {
			Menu.sendAction(315, 0, 0, 19210);
			Time.sleep(3000);
		}
	}
}
