package test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.Test;
import logic.model.LoggedUser;
import logic.model.enums.UserLevel;

public class TestLoggedUser {
	
	@Test
	public void testAddPeakCoinAndLevelUp() {
		LoggedUser user = new LoggedUser();
		user.setPeakCoin(5);
		user.addPeakCoin();
		
		assertEquals(UserLevel.BOYSCOUT, user.getLevel());
	}
}
