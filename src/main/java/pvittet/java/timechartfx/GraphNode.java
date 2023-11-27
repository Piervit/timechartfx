package pvittet.java.timechartfx;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

import javafx.scene.Node;
import processing.core.PApplet;
import quilchart.Api;
import quilchart.dataGraph.Graph;

/**
 * This is the main javafx graph node. It allow to drive the node, adding to it
 * new dataField, changing its theme... This object must be constructed from
 * OGraphFactory.
 */
public class GraphNode {

	private Api api;
	private Graph graph;
	private PApplet applet;

	GraphNode() {
		this.api = new Api();
		this.graph = this.api.createEmptyGraph();
	}

	public DataField getNewEmptyDataField(String name) {
		return new DataField(this.api, this, name);
	}

	Graph getGraph() {
		return this.graph;
	}

	/**
	 * Create a new dot using x,y coordinate. X is a double, so x-axis is a numeric
	 * axis.
	 * 
	 * Note: Numeric x-axis dot must must be added to a DataField containing only
	 * Numerical x-axis.
	 * 
	 * @param x: x value
	 * @param y: y value
	 * @return the created dot.
	 */
	public Dot createNumXDot(double x, double y) {
		return new Dot(this.api, x, y);
	}

	/**
	 * Create a new dot using x,y coordinate. X is an Instant, so x-axis is a time
	 * axis. Note:
	 * 
	 * Time x-axis dot must must be added to a DataField containing only Time
	 * x-axis.
	 * 
	 * @param x: x value
	 * @param y: y value
	 * @return the created dot.
	 */
	public Dot createTimeXDot(Instant x, double y) {
		return new Dot(this.api, x, y);
	}

	/**
	 * Add a datafield (a curve) to this graphic object.
	 * 
	 * @param df: the datafield to add.
	 */
	public void addDataFieldToGraph(DataField df) {
		this.graph = this.api.addDataFieldToGraph(this.graph, df.getInnerDataField());
	}

	/**
	 * Compute the display and provide the javafx Node.
	 * 
	 * @return the javafx node.
	 */
	public Node createAndDisplayNode() {
		this.applet = this.api.createDisplayGraph(this.graph);
		Node canvas = ((Node) this.applet.getSurface().getNative());
		return canvas;
	}

	public void updateGraph() {
		this.api.updateGraph(this.graph);
	}

	public void liveAddDot(String dfName, Dot dot) {
		List<quilchart.dataGraph.Dot> l = new ArrayList<quilchart.dataGraph.Dot>();
		l.add(dot.getInnerDot());
		this.api.liveAddDots(this.graph, dfName, l);
		;
	}

	/**
	 * Terminate the graph.
	 * 
	 * @return
	 */
	public void stopDisplay() {
		if (this.applet != null) {
			this.applet.noLoop();
			this.applet.exit();
		}
	}

}
