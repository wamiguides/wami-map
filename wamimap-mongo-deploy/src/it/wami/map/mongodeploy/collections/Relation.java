/**
 * 
 */
package it.wami.map.mongodeploy.collections;

import java.util.HashMap;
import java.util.Map;

import org.bson.types.BasicBSONList;

import com.mongodb.BasicDBObject;

/**
 * The class representing the element Relation.
 * <a href="http://wiki.openstreetmap.org/wiki/Relation">http://wiki.openstreetmap.org/wiki/Relation</a>
 */
public class Relation extends BasicDBObject {
	
	private static final long serialVersionUID = -3950469029794113378L;

	public final static String MEMBERS = "members";
	
	BasicBSONList geometry = null;
	/**
	 * 
	 */
	public Relation() {
	}

	/**
	 * @param size
	 */
	public Relation(int size) {
		super(size);
	}

	/**
	 * @param m
	 */
	public Relation(Map<String, String> m) {
		super(m);
	}

	/**
	 * @param key
	 * @param value
	 */
	public Relation(String key, Object value) {
		super(key, value);
	}
	
	public BasicDBObject[] getMembers(){
		return (BasicDBObject[]) this.get(Relation.MEMBERS);
	}

	public void addGeometryToCollection(BasicDBObject obj) {
		if(geometry == null)
			createGeometryCollection();
		geometry.add(obj);
	}

	private void createGeometryCollection() {
		BasicDBObject loc;
		geometry = new BasicBSONList();
		
		if(this.containsField("loc")){
			loc = (BasicDBObject) this.get("loc");
		}else{
			loc = new BasicDBObject();
			this.append("loc", loc);
		}
		loc.append("type", "GeometryCollection");
		loc.append("geometries", geometry);
	}
	
	/**
	 * Create a Map with a list of the mamers associate with their type.
	 * @param members {@link BasicDBObject}[] the Array of the Relation's members 
	 * @return {@link Map}<{@link String}, {@link BasicBSONList}> the collection with the types of objects
	 */
	public static  Map<String, BasicBSONList> createTypeList(BasicDBObject[] members) {
		Map<String, BasicBSONList> typeList = null;
		for (BasicDBObject member: members) {
			String type = member.getString("type");
		    long ref = Long.parseLong(member.getString("ref"));
		    if(typeList == null)
		    	typeList = new HashMap<String, BasicBSONList>();
		    BasicBSONList refs = typeList.get(type);
		    if(refs == null)
		    	refs = new BasicBSONList();
		    refs.add(ref);
		    typeList.put(type, refs);
		}
		return typeList;
	}

}
