package com.underplex.tickay.player;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.underplex.infowrap.InfoSource;
import com.underplex.tickay.game.City;
import com.underplex.tickay.game.Route;
import com.underplex.tickay.info.StationManagerInfo;

/**
 * 
 */
public class StationManager implements InfoSource<StationManagerInfo> {

	private List<City> cities; // first element represents first station, etc.
	private EuroPlayer euroPlayer;
	private StationManagerInfo info;

	public StationManager(EuroPlayer euroPlayer) {
		this.cities = new ArrayList<>();
		this.euroPlayer = euroPlayer;
		this.info = new StationManagerInfo(this);
	}

	public StationManagerInfo info() {
		return this.info;
	}

	public List<City> built() {
		return cities;
	}

	/**
	 * Gives all possible routes to choose from the city given this euroPlayer as
	 * the euroPlayer choosing a route for the station to cover.
	 * <p>
	 * Never returns <code>null</code>; will return an empty <code>Set</code> if
	 * there are no legal routes.
	 */
	public Set<Route> coverageFrom(City city) {
		Set<Route> rSet = new HashSet<>();

		Route alt = null;

		for (Route route : city.builtRoutes()) {

			if (route.getBuilder() != this.euroPlayer) {

				alt = route.getAlternate();

				if (alt == null) {
					rSet.add(route);
					// else if there's a double route AND if it isn't already on
					// the list, making the route redundent
				} else if (!rSet.contains(alt) && alt.isBuilt()) {
					if (alt.getBuilder() != this.euroPlayer)
						rSet.add(route);
				} // end if alt == null / else

			} // end if route.getBuilder()

		} // end for

		return rSet;
	}

	public int stationsLeft() {
		return 3 - cities.size();
	}
}
