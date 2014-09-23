/**
 * 
 */
package it.wami.map.mongodeploy.collections;

import java.util.Map;

import com.mongodb.BasicDBObject;

/**
 * The class representing the element Way.
 * <a href="http://wiki.openstreetmap.org/wiki/Way">http://wiki.openstreetmap.org/wiki/Way</a>
 */
public class Way extends BasicDBObject {

	private static final long serialVersionUID = -6297729654782262993L;
	
	public final static String NODES = "nodes";
	
	
	public class ND{
		public static final String REF = "ref";
	}

	/**
	 * 
	 */
	public Way() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param size
	 */
	public Way(int size) {
		super(size);
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param m
	 */
	public Way(Map<String, String> m) {
		super(m);
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param key
	 * @param value
	 */
	public Way(String key, Object value) {
		super(key, value);
		// TODO Auto-generated constructor stub
	}
	
	/**
	 * 
	 * @return
	 */
	public boolean isCircularId(){
		long[] nodes = (long[]) this.get("nodes");
		return nodes[0] == nodes[nodes.length-1];
	}
	
	/**
	 * 
	 * @return <b>long[]</b> the array containing the way's nodes.
	 */
	public long[] getNodes() {
		return (long[]) this.get(NODES);
	}
}
