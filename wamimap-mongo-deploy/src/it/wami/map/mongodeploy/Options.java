package it.wami.map.mongodeploy;
/**
 * The Option Class. Contains the Arguments given to the parser.
 * 
 */
public class Options {
	
	/**
	 * print the usage of the script.
	 */
	public static final String USAGE  = "-i=<path> input path **mandatory \n" +
										"-db=<db>  mongo database **mandatory \n" +
										"-upsert=<true|false> [Default: false] \n" +
										"-host=<host> mongo database host [Default: 'localhost'] \n" +
										"-port=<port> mongo database port [Default: '27017'] \n" +
										"-wg Save the ways as geometry \n" +
										"-rg Save the relations as geometry \n" + 
										"-omit-metadata=<true|false> [Default: true] \n";
	
	/**
	 * the parameters used to process the osm file.
	 */
	public static final String  INPUT 			= "-i=",
								DB 				= "-db=",
								HOST 			= "-host=",
								PORT			= "-port=",
								WG				= "-wg",
								RG				= "-rg",
								UPSERT			= "-upsert=",
								OMIT_METADATA 	= "-omit-metadata=";
	
	private boolean wg = false, rg = false, upsert = false, omit_metadata = true;
	
	private String 	dbName, input, 
					host = "localhost";
	private int 	port = 27017;


	public Options() {
		super();
	}
	
	public Options(String dbName, String input) {
		super();
		this.dbName = dbName;
		this.input = input;
	}

	public Options(String dbName, String input, String host, int port) {
		super();
		this.dbName = dbName;
		this.input = input;
		this.host = host;
		this.port = port;
	}

	public Options(boolean wg, boolean rg, boolean upsert,
			boolean omit_metadata, String dbName, String input, String host,
			int port) {
		
		super();
		this.wg = wg;
		this.rg = rg;
		this.upsert = upsert;
		this.omit_metadata = omit_metadata;
		this.dbName = dbName;
		this.input = input;
		this.host = host;
		this.port = port;
	}

	/**
	 * Check if the tool must generate the geospatial structure for the ways.
	 * @return <b>true</b> if launched with -wg option; <b>false</b> otherwise.
	 */
	public boolean isWayGeometry() {
		return wg;
	}

	/**
	 * @param wg <b>boolean</b> the wg to set
	 */
	public void setWayGeometry(boolean wg) {
		this.wg = wg;
	}

	/**
	 * Check if the tool must generate the geospatial structure for the relations.
	 * @return <b>true</b> if launched with -rg option; <b>false</b> otherwise.
	 */
	public boolean isRelationGeometry() {
		return rg;
	}

	/**
	 * @param <b>boolean</b> rg the rg to set
	 */
	public void setRelationGeometry(boolean rg) {
		this.rg = rg;
	}

	/**
	 * Check if the tool option <b>upsert</b> is enabled.
	 * @return <b>true</b> if launched with -upsert option; <b>false</b> otherwise.
	 */
	public boolean isUpsert() {
		return upsert;
	}

	/**
	 * @param upsert the upsert to set
	 */
	public void setUpsert(boolean upsert) {
		this.upsert = upsert;
	}

	/**
	 * @return the omit_metadata
	 */
	public boolean isOmit_metadata() {
		return omit_metadata;
	}

	/**
	 * @param omit_metadata the omit_metadata to set
	 */
	public void setOmit_metadata(boolean omit_metadata) {
		this.omit_metadata = omit_metadata;
	}

	/**
	 * @return the dbName
	 */
	public String getDbName() {
		return dbName;
	}

	/**
	 * @param dbName the dbName to set
	 */
	public void setDbName(String dbName) {
		this.dbName = dbName;
	}

	/**
	 * Get the file path given to the script.
	 * @return the input
	 */
	public String getInput() {
		return input;
	}

	/**
	 * @param input the input to set
	 */
	public void setInput(String input) {
		this.input = input;
	}

	/**
	 * @return the host
	 */
	public String getHost() {
		return host;
	}

	/**
	 * @param host the host to set
	 */
	public void setHost(String host) {
		this.host = host;
	}

	/**
	 * @return the port
	 */
	public int getPort() {
		return port;
	}

	/**
	 * @param port the port to set
	 */
	public void setPort(int port) {
		this.port = port;
	}

}
