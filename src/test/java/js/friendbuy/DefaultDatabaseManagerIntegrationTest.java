package js.friendbuy;

import static org.junit.Assert.*;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class DefaultDatabaseManagerIntegrationTest {
	
	DefaultDatabaseManager instance;

	@Before
	public void setUp() throws Exception {
		
		instance = new DefaultDatabaseManager(
				new DefaultDataStoreFactory(),
				new DefaultTransactionFactory()
				);
		
	}

	@Test
	public void test_example0() throws Exception {
		//Example 0 from the challenge instructions document
		
		instance.setValue("x", "10");
		Assert.assertEquals("10", instance.getValue("x"));
		instance.setValue("x", null);
		Assert.assertNull(instance.getValue("x"));	
		
	}
	
	@Test
	public void test_example1() throws Exception {
		//Example 1 from the challenge instructions document
		
		instance.setValue("a", "10");
		instance.setValue("b", "10");
		Assert.assertEquals(2, instance.countValueOccurrences("10"));
		Assert.assertEquals(0, instance.countValueOccurrences("20"));
		instance.setValue("b", "30");
		Assert.assertEquals(1, instance.countValueOccurrences("10"));	
		
	}
	
	@Test
	public void test_example2() throws Exception {
		//Example 2 from the challenge instructions document
		
		instance.beginTransaction();		
		instance.setValue("a", "10");
		Assert.assertEquals("10", instance.getValue("a"));
		instance.beginTransaction();
		instance.setValue("a", "20");
		Assert.assertEquals("20", instance.getValue("a"));
		instance.rollbackCurrentTopLevelTransaction();
		Assert.assertEquals("10", instance.getValue("a"));
		instance.rollbackCurrentTopLevelTransaction();
		Assert.assertNull(instance.getValue("a"));
		
	}
	
	@Test
	public void test_example3() throws Exception {
		//Example 3 from the challenge instructions document
		
		instance.beginTransaction();		
		instance.setValue("a", "30");
		instance.beginTransaction();
		instance.setValue("a", "40");
		instance.commitAllCurrentTransactions();
		Assert.assertEquals("40", instance.getValue("a"));
		
		boolean expectedExceptionThrown = false;
		try {
			instance.rollbackCurrentTopLevelTransaction();			
		} catch (NoCurrentTransactionException ex) {
			expectedExceptionThrown = true;
		}
		
		Assert.assertTrue(expectedExceptionThrown);
		
	}
	
	@Test
	public void test_example4() throws Exception {
		//Example 4 from the challenge instructions document
		
		instance.setValue("a", "50");
		instance.beginTransaction();
		Assert.assertEquals("50", instance.getValue("a"));
		instance.setValue("a", "60");
		instance.beginTransaction();
		instance.setValue("a", null);
		Assert.assertNull(instance.getValue("a"));
		instance.rollbackCurrentTopLevelTransaction();
		Assert.assertEquals("60", instance.getValue("a"));
		instance.commitAllCurrentTransactions();
		Assert.assertEquals("60", instance.getValue("a"));
		
	}

	@Test
	public void test_example5() throws Exception {
		//Example 4 from the challenge instructions document
		
		instance.setValue("a", "10");
		instance.beginTransaction();
		Assert.assertEquals(1, instance.countValueOccurrences("10"));
		instance.beginTransaction();
		instance.setValue("a", null);
		Assert.assertEquals(0, instance.countValueOccurrences("10"));
		instance.rollbackCurrentTopLevelTransaction();
		Assert.assertEquals(1, instance.countValueOccurrences("10"));
		instance.commitAllCurrentTransactions();
		
	}
	

}
