package js.friendbuy;

import static org.junit.Assert.*;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class DefaultDataStoreTest {

	DefaultDataStore instance;
	
	@Before
	public void setUp() {
		instance = new DefaultDataStore();		
	}
	
	@Test
	public void test_getValue_initial() {
		String result = instance.getValue("notYetSet");	
		Assert.assertNull(result);
	}
	
	@Test
	public void test_setValue_getValue() {
		instance.setValue("key0", "value0");
		
		Assert.assertEquals("value0", instance.getValue("key0"));			
	}
	
	@Test
	public void test_setNull_getValue() {
		instance.setValue("key0", null);
		
		Assert.assertNull(instance.getValue("key0"));			
	}
	
	@Test
	public void test_setValue_overwrite_geValue() {
		instance.setValue("key0", "value0");
		instance.setValue("key1", "value1");
		
		instance.setValue("key0", "value2");
		
		Assert.assertEquals("value2", instance.getValue("key0"));
		Assert.assertEquals("value1", instance.getValue("key1"));		
	}
	
	@Test
	public void test_setValue_overwriteNull_geValue() {
		instance.setValue("key0", "value0");
		instance.setValue("key1", "value1");
		
		instance.setValue("key0", null);
		
		Assert.assertNull(instance.getValue("key0"));
		Assert.assertEquals("value1", instance.getValue("key1"));		
	}
	
	
	@Test
	public void test_noValues_countOccurrences() {			
		Assert.assertEquals(0, instance.countValueOccurrences("value0"));			
	}
	
	@Test
	public void test_countOccurrences_null() {
		//This isn't a useful query, but should not throw exceptions
		Assert.assertEquals(0, instance.countValueOccurrences(null));			
	}
	
	
	@Test
	public void test_setValue_countOccurrences() {
		
		instance.setValue("key0", "value0");
		instance.setValue("key1", "value1");
		instance.setValue("key2", "value2");
		
		Assert.assertEquals(1, instance.countValueOccurrences("value0"));
		
		instance.setValue("key1", "value0");
		
		Assert.assertEquals(2, instance.countValueOccurrences("value0"));
		
		instance.setValue("key2", "value0");
		
		Assert.assertEquals(3, instance.countValueOccurrences("value0"));
		
		instance.setValue("key1", null);
		
		Assert.assertEquals(2, instance.countValueOccurrences("value0"));
		
		instance.setValue("key2", "value2");
		
		Assert.assertEquals(1, instance.countValueOccurrences("value0"));
		
		instance.setValue("key0", null);
		
		Assert.assertEquals(0, instance.countValueOccurrences("value0"));
		
	}
	
	@Test
	public void test_copyData() {
		
		instance.setValue("key0", "value0");
		instance.setValue("key1", null);
		instance.setValue("key2", "value2");
		instance.setValue("key3", "value3");
		
		DefaultDataStore copyInstance = new DefaultDataStore();
		
		Assert.assertNull(copyInstance.getValue("key0"));
		Assert.assertNull(copyInstance.getValue("key1"));
		Assert.assertNull(copyInstance.getValue("key2"));
		Assert.assertNull(copyInstance.getValue("key3"));
		
		
		instance.copyData(copyInstance);		
		
		Assert.assertEquals("value0", copyInstance.getValue("key0"));
		Assert.assertNull(copyInstance.getValue("key1"));
		Assert.assertEquals("value2", copyInstance.getValue("key2"));
		Assert.assertEquals("value3", copyInstance.getValue("key3"));
		
	}

}
