import java.util.HashMap;

public class ConfigMgr {
	private HashMap<String,String> config;

	/**
	 * Load all the arguments so they can be accessed later.  Arguments should be in the form:
	 * 		argname=argvalue
	 * @param args Arguments (from command line or otherwise) to load
	 */
	public ConfigMgr(String args[]) {
		config = new HashMap<String,String>();
		for (String s: args)
		{
			String info[] = s.split("=");
			config.put(info[0].toLowerCase(),info[1].trim());
		}
	}
	
	/**
	 * @param name Name of the configuration option to retrieve.  Case insensitive
	 * @return String value of the configuration option, or null if it was not set
	 */
	public String getConfig(String name)
	{
		return config.get(name.toLowerCase());
	}

}
