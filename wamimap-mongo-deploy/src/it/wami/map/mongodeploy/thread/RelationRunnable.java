package it.wami.map.mongodeploy.thread;

import it.wami.map.mongodeploy.OsmSaxHandler;
import it.wami.map.mongodeploy.collections.Relation;

import java.util.List;
import java.util.Map;

import org.bson.BasicBSONObject;
import org.bson.types.BasicBSONList;

import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;

/**
 *	The Runnable responsible to create the relation object and adding it to the list ({@link RelationRunnable#relationQueue}).
 */
public class RelationRunnable implements Runnable {

	private Relation relation;
	private DB db;
	private List<DBObject> relationQueue;
	
	public RelationRunnable(DB db, Relation relation, List<DBObject> relationQueue) {
		this.relation = relation;
		this.db = db;
		this.relationQueue = relationQueue;
	}

	@Override
	public void run() {
		BasicDBObject[] members = relation.getMembers();
		if(members == null)
			return;
		
		Map<String, BasicBSONList> typeList = Relation.createTypeList(members);
		if(!typeList.containsKey("node") &&!typeList.containsKey("way") && !typeList.containsKey("relation")){
			relationQueue.add(relation);
			return;
		}
		if (!(typeList.get("way") instanceof List<?>)) {
			relationQueue.add(relation);
			return;
		}
		
		addObjectsToGeometryCollection(typeList.get("way"), relation, OsmSaxHandler.COLL_WAYS);
		addObjectsToGeometryCollection(typeList.get("node"), relation, OsmSaxHandler.COLL_NODES);
		
		relationQueue.add(relation);
	}
	
	/**
	 * This method add the geometries contained in the members' list.
	 * @param objs {@link BasicBSONList}
	 * @param geometry {@link BasicBSONList}
	 */
	private void addObjectsToGeometryCollection(BasicBSONList objs, Relation relation,
			String collectionType) {
		if(objs == null || objs.isEmpty())
			return;
		BasicDBObject query = new BasicDBObject("_id", new BasicDBObject("$in", objs));
		BasicDBObject keys  = new BasicDBObject("loc", true).append("_id", true).append("tags", true);
		
		DBCollection coll = db.getCollection(collectionType);
		DBCursor cursor = coll.find(query, keys);
		
		while(cursor.hasNext()) {
			DBObject element = cursor.next();
			if(!element.containsField("loc"))
				continue;
			BasicDBObject objGeo = new BasicDBObject(); // the geometry
			BasicBSONObject objLoc =  (BasicBSONObject) element.get("loc");
			objGeo.append("type", objLoc.get("type"));
			objGeo.append("coordinates", objLoc.get("coordinates"));
			if(element.containsField("tags")){
				objGeo.append("properties", element.get("tags"));
			}
			relation.addGeometryToCollection(objGeo);
		}
	}
}