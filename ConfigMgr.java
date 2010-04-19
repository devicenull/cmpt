import java.util.HashMap;

public class ConfigMgr {
	private HashMap<String,String> config;

	public ConfigMgr(String args[]) {
		config = new HashMap<String,String>();
		for (String s: args)
		{
			String info[] = s.split("=");
			config.put(info[0].toLowerCase(),info[1].trim());
		}
	}
	
	public String getConfig(String name)
	{
		return config.get(name.toLowerCase());
	}

}
