package js.friendbuy;

import static org.junit.Assert.*;

import static org.mockito.Mockito.*;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class DefaultDatabaseCommandParserTest {

	IDatabaseManager databaseManagerMock;
	
	DefaultDatabaseCommandParser instance;
	
	
	@Before
	public void setUp() throws Exception {
		
		databaseManagerMock = mock(IDatabaseManager.class);
		
		instance = new DefaultDatabaseCommandParser(databaseManagerMock);
		
	}

	@Test
	public void test_invalidCommandName() throws Exception {
		
		boolean expectedExceptionThrown = false;
		
		try {
			instance.parseAndExecuteCommand("badCommand", new String[0]);
		} catch (InvalidCommandException ex) {
			expectedExceptionThrown = true;
		}
		
		Assert.assertTrue(expectedExceptionThrown);
		
	}
	
	@Test
	public void test_tooManyArguments() throws Exception {
		
		boolean expectedExceptionThrown = false;
		
		try {
			instance.parseAndExecuteCommand("SET", new String[10]);
		} catch (InvalidCommandException ex) {
			expectedExceptionThrown = true;
		}
		
		Assert.assertTrue(expectedExceptionThrown);
		
	}
	
	@Test
	public void test_tooFewArguments() throws Exception {
		
		boolean expectedExceptionThrown = false;
		
		try {
			instance.parseAndExecuteCommand("SET", new String[0]);
		} catch (InvalidCommandException ex) {
			expectedExceptionThrown = true;
		}
		
		Assert.assertTrue(expectedExceptionThrown);
		
	}
	
	
	@Test
	public void test_setValue() throws Exception {
		
		Assert.assertNull(instance.parseAndExecuteCommand("SET", new String[] {"key0", "value0"}));
		
		verify(databaseManagerMock).setValue("key0", "value0");		
		
	}
	
	@Test
	public void test_unsetValue() throws Exception {
		
		Assert.assertNull(instance.parseAndExecuteCommand("UNSET", new String[] {"key0"}));
		
		verify(databaseManagerMock).setValue("key0", null);		
		
	}
	
	@Test
	public void test_getValue() throws Exception {
		
		when(databaseManagerMock.getValue("key0")).thenReturn("value0");
		
		Assert.assertEquals("value0", instance.parseAndExecuteCommand("GET", new String[] {"key0"}));
		
	}
	
	@Test
	public void test_numequalto() throws Exception {
		
		when(databaseManagerMock.countValueOccurrences("key0")).thenReturn(6);
		
		Assert.assertEquals("6", instance.parseAndExecuteCommand("NUMEQUALTO", new String[] {"key0"}));
		
	}
	
	@Test
	public void test_begin() throws Exception {
		
		Assert.assertNull(instance.parseAndExecuteCommand("BEGIN", new String[] {}));
		
		verify(databaseManagerMock).beginTransaction();		
		
	}
	
	@Test
	public void test_commit() throws Exception {
		
		Assert.assertNull(instance.parseAndExecuteCommand("COMMIT", new String[] {}));
		
		verify(databaseManagerMock).commitAllCurrentTransactions();		
		
	}
	
	@Test
	public void test_rollback() throws Exception {
		
		Assert.assertNull(instance.parseAndExecuteCommand("ROLLBACK", new String[] {}));
		
		verify(databaseManagerMock).rollbackCurrentTopLevelTransaction();		
		
	}
	
	@Test
	public void test_rollback_throws_noTransactionException() throws Exception {
				
		doThrow(new NoCurrentTransactionException("Test Exception")).when(databaseManagerMock).rollbackCurrentTopLevelTransaction();
		
		boolean expectedExceptionThrown = false;
		
		try {
			instance.parseAndExecuteCommand("ROLLBACK", new String[] {});
		} catch (NoCurrentTransactionException ex) {
			expectedExceptionThrown = true;
		}
		
		Assert.assertTrue(expectedExceptionThrown);
		
	}
	
	@Test
	public void test_commit_throws_noTransactionException() throws Exception {
				
		doThrow(new NoCurrentTransactionException("Test Exception")).when(databaseManagerMock).commitAllCurrentTransactions();
		
		boolean expectedExceptionThrown = false;
		
		try {
			instance.parseAndExecuteCommand("COMMIT", new String[] {});
		} catch (NoCurrentTransactionException ex) {
			expectedExceptionThrown = true;
		}
		
		Assert.assertTrue(expectedExceptionThrown);
		
	}

}
