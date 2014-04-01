package de.rmrw.ReversiKata2.codeTest;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import de.rmrw.ReversiKata2.code.Pos;

public class PosTest {

	@Before
	public void setUp() throws Exception {
	}

	@Test
	public final void testPlus() {
		Pos pos = new Pos(1,2);
		Pos result = pos.plus(new Pos(-1,1));
		Assert.assertEquals(new Pos(0,3), result);
	}

}
