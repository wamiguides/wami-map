package it.wami.map.mongodeploy.collections;

import java.util.Map;

import com.mongodb.BasicDBObject;

/**
 * The class representing the element Node.
 * <a href="http://wiki.openstreetmap.org/wiki/Node">http://wiki.openstreetmap.org/wiki/Node</a>
 */
public class Node extends BasicDBObject {

	private static final long serialVersionUID = -3581930578976191529L;
	
	public static final String TAGS	= "tags";
	
	public class Tag{
		public static final String K = "k",
								   V = "v";
	}
	
	/**
	 * The class representing the Coordinates.
	 *
	 */
	public class Coordinates extends BasicDBObject{
		
		private static final long serialVersionUID = 3139199200045252844L;
		
		protected static final String COORDS	= "coordinates";
		protected static final String LOC		= "loc";
		
		public static final String POINT	= "Point";
		public static final String TYPE		= "type";

		public Coordinates(float lat, float lng){
			this.append(TYPE, POINT);
			this.append(COORDS, new float[]{lng,lat});
		}
		
	}
	
	/**
	 * 
	 * @param lat
	 * @param lng
	 * @return
	 */
	public static Coordinates appendCoordinates(Node node, float lat, float lng){
		Coordinates coordinates = node.new Coordinates(lat, lng);
		node.append(Coordinates.LOC, coordinates);
		return coordinates;
	}

	public Node() {
		super();
	}

	/**
	 * @param size
	 */
	public Node(int size) {
		super(size);
	}

	/**
	 * @param m
	 */
	public Node(Map<String, String> m) {
		super(m);
	}

	/**
	 * @param key
	 * @param value
	 */
	public Node(String key, Object value) {
		super(key, value);
	}
}