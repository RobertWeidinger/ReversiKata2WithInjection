package de.rmrw.ReversiKata2.codeTest;
/* unter netbeans muss noch die richtige junit-Bibliothek eingebunden werden */
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import de.rmrw.ReversiKata2.code.Farben;

public class FarbenTest {

	@Before
	public void setUp() throws Exception {
	}

        /* Dieser Test ist wichtig */
	@Test
	public final void testComplementaerZu() {
		Assert.assertTrue(Farben.WEISS.complementaerZu(Farben.SCHWARZ));
		Assert.assertTrue(Farben.SCHWARZ.complementaerZu(Farben.WEISS));
		Assert.assertFalse(Farben.SCHWARZ.complementaerZu(Farben.SCHWARZ));
		Assert.assertFalse(Farben.LEER.complementaerZu(Farben.SCHWARZ));
		Assert.assertFalse(Farben.WEISS.complementaerZu(Farben.LEER));
	}
	
	@Test
	public final void testComplementaerFarbe() {
		Assert.assertEquals(Farben.WEISS, Farben.SCHWARZ.complementaerFarbe());
		Assert.assertEquals(Farben.SCHWARZ, Farben.WEISS.complementaerFarbe());
		Assert.assertEquals(Farben.LEER, Farben.LEER.complementaerFarbe());
	}

}
