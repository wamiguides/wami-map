package it.wami.map.mongodeploy.collections;

import java.util.Map;

import com.mongodb.BasicDBObject;

/**
 * The class representing the element Tag.
 * <a href="http://wiki.openstreetmap.org/wiki/Tags">http://wiki.openstreetmap.org/wiki/Tags</a>
 */
public class Tag extends BasicDBObject {

	private static final long serialVersionUID = -7972574006331842135L;
	
	public static final String	KEY		= "key",
								VALUE	= "value";
								
	
	public static String[] TYPES = {	
										"aerialway","aeroway","amenity","barrier","boundary","building","craft","emergency",
								 		"geological","highway","historic","landuse","leisure","man made","military","natural",
								 		"office","places","power","public transport","railway","route","shop","sport","tourism","waterway"
								 	 };

	public Tag() {
		// TODO Auto-generated constructor stub
	}

	public Tag(int size) {
		super(size);
		// TODO Auto-generated constructor stub
	}

	public Tag(Map<String, String> m) {
		super(m);
		// TODO Auto-generated constructor stub
	}

	public Tag(String key, Object value) {
		super(key, value);
		// TODO Auto-generated constructor stub
	}
	
	public void setKey(String key){
		this.append(KEY, key);
	}
	
	public void setValue(String value){
		this.append(VALUE, value);
	}

	public void setKeyValue(String key, String value) {
		this.setKey(key);
		this.setValue(value);
	}
}
