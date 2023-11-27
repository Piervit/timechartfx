package pvittet.java.timechartfx;

import java.util.List;
import java.util.stream.Collectors;

import quilchart.Api;

/**
 * A datafield is a set of dots appearing as a curve in a graph. It belongs to a
 * graph.
 */
final public class DataField {

	Api api;
	GraphNode parentGraph;

	quilchart.dataGraph.DataField datafield;

	DataField(Api api, GraphNode parentGraph, String dfName) {
		this.api = api;
		this.datafield = api.createEmptyDataField(parentGraph.getGraph(), dfName);
		this.parentGraph = parentGraph;
	}

	public void addDotToDataField(List<Dot> dots) {
		List<quilchart.dataGraph.Dot> innerDots = dots.stream().map(Dot::getInnerDot).collect(Collectors.toList());
		this.datafield = this.api.addDotsToDataField(this.datafield, innerDots);
	}

	/**
	 * Internal function only.
	 * 
	 * @return
	 */
	quilchart.dataGraph.DataField getInnerDataField() {
		return this.datafield;
	}
}
