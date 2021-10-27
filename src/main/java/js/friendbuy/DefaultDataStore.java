package js.friendbuy;

import java.util.HashMap;
import java.util.Map;

public class DefaultDataStore implements IDataStore {

	private HashMap<String, String> valueMap;
		
	public DefaultDataStore() {
		this.valueMap = new HashMap<String, String>();
	}
	
	
	public void setValue(String name, String value) {
		valueMap.put(name, value);
	}

	public String getValue(String name) {
		return valueMap.get(name);
	}

	public int countValueOccurrences(String value) {
		//Todo: Consider lazily cache these values or building them ahead of time (Depending on memory vs. speed preference for this feature)
		
		if (value == null) {
			return 0;
		}
		
		int occurrences = 0;
		
		for (String mappedValue : valueMap.values()) {
			if (value.equals(mappedValue)) {
				occurrences++;
			}			
		}
		
		
		return occurrences;
	}


	public void copyData(IDataStore targetHandler) {
		for (Map.Entry<String, String> entry : valueMap.entrySet()) {
			targetHandler.setValue(entry.getKey(), entry.getValue());
		}
	}

}
