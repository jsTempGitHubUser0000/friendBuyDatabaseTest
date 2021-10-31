package js.friendbuy;

import java.util.HashMap;
import java.util.Map;

public class DefaultDataStore implements IDataStore {

	private HashMap<String, String> valueMap;
	private HashMap<String, Integer> valueCountMap;
		
	public DefaultDataStore() {
		this.valueMap = new HashMap<String, String>();
		this.valueCountMap = new HashMap<String, Integer>();
	}
	
	
	public void setValue(String name, String value) {						
		String originalValue = valueMap.get(name);
		
		if (originalValue != null && originalValue.equals(value)) {
			//The value is being set to its current value again - there is no need to do anything
			return;
		} else if (originalValue != null && !originalValue.equals(value)) {
			//The previously-set value has changed - subtract from the occurrence count for the previous value
			Integer originalOccurrenceCount = valueCountMap.get(originalValue);
			
			if (originalOccurrenceCount != null) {
				Integer newOccurrenceCount = originalOccurrenceCount - 1;
				
				if (newOccurrenceCount > 0) {
					valueCountMap.put(originalValue, newOccurrenceCount);
				} else {
					valueCountMap.remove(originalValue);
				}
			}					
		}
		
		valueMap.put(name, value);
		
		if (value != null) {
			//A new non-null value has been set - increment the occurrence count for that value
			Integer originalOccurrenceCount = valueCountMap.get(value);
			
			if (originalOccurrenceCount != null) {
				valueCountMap.put(value, originalOccurrenceCount + 1);
			} else {
				valueCountMap.put(value, 1);
			}
		}				
	}

	public String getValue(String name) {
		return valueMap.get(name);
	}

	public int countValueOccurrences(String value) {		
		if (value == null) {
			return 0;
		}
		
		Integer valueCount = valueCountMap.get(value);
		
		if (valueCount != null) {
			return valueCount;
		} else {
			return 0;
		}
	}


	public void copyData(IDataStore targetDataStore) {
		for (Map.Entry<String, String> entry : valueMap.entrySet()) {
			targetDataStore.setValue(entry.getKey(), entry.getValue());
		}
	}

}
