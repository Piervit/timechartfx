package pvittet.java.timechartfx;

import java.time.Instant;

import quilchart.Api;

/**
 * A dot is an element of a DataField, a basic dot displayed on a graph. It has
 * an X,Y coordinate. X can be a double or an Instant.
 */
public class Dot {
	Api api;

	quilchart.dataGraph.Dot dot;

	Dot(Api api, double x, double y) {
		this.api = api;
		this.dot = api.createNumXDot(x, y);
	}

	Dot(Api api, Instant x, double y) {
		this.api = api;
		this.dot = api.createTimeXDot(x, y);
	}

	quilchart.dataGraph.Dot getInnerDot() {
		return this.dot;
	}

}
