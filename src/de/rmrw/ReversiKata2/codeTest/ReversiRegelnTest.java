package de.rmrw.ReversiKata2.codeTest;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Hashtable;
import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import de.rmrw.ReversiKata2.code.Farben;
import de.rmrw.ReversiKata2.code.Feld;
import de.rmrw.ReversiKata2.code.ISpielfeld;
import de.rmrw.ReversiKata2.code.Pos;
import de.rmrw.ReversiKata2.code.ReversiRegeln;

public class ReversiRegelnTest {
	
	private ISpielfeld spielfeld = null;
	private ReversiRegeln regeln = null;

	@Before
	public void setUp() throws Exception {
		regeln = new ReversiRegeln();
		spielfeld = Mockito.mock(ISpielfeld.class);
		regeln.init(spielfeld);
	}
	
	public void mockSpielfeld4x4mitStartKonstellation() {
		Mockito.when(spielfeld.getGroesse()).thenReturn(4);
		Hashtable<Pos,Feld> list = regeln.getStartStatus();
		Collection<Pos> positionen = new ArrayList<Pos>();
		for (int zeile=0; zeile<4; zeile++)
			for (int spalte=0; spalte<4; spalte++) {
				Pos pos = new Pos(zeile,spalte);
				if (list.containsKey(pos))
					Mockito.when(spielfeld.getFarbe(pos)).thenReturn(list.get(pos).getFarbe());
				else
					Mockito.when(spielfeld.getFarbe(pos)).thenReturn(Farben.LEER);
				positionen.add(new Pos(zeile,spalte));
			}
		Mockito.when(spielfeld.getPositionen()).thenReturn(positionen);
	}

	public void assertStartKonfigurationBei1_1(Hashtable<Pos,Feld> list) {
		Assert.assertTrue(list.contains(new Feld(new Pos(1,1),Farben.WEISS)));
		Assert.assertTrue(list.contains(new Feld(new Pos(1,2),Farben.SCHWARZ)));
		Assert.assertTrue(list.contains(new Feld(new Pos(2,2),Farben.WEISS)));
		Assert.assertTrue(list.contains(new Feld(new Pos(2,1),Farben.SCHWARZ)));
	}


	@Test
	public final void testGetStartStatusBeiGroesse4() {
		Mockito.when(spielfeld.getGroesse()).thenReturn(4);
		Hashtable<Pos,Feld> list = regeln.getStartStatus();
		assertStartKonfigurationBei1_1(list);
	}


	@Test
	public final void testGetStartStatusBeiGroesse5() {
		Mockito.when(spielfeld.getGroesse()).thenReturn(5);
		Hashtable<Pos,Feld> list = regeln.getStartStatus();
		assertStartKonfigurationBei1_1(list);
	}

	
	@Test 
	public final void testGueltigePosition() {
		mockSpielfeld4x4mitStartKonstellation();
		Assert.assertTrue(regeln.gueltigePosition(new Pos(0,0)));
		Assert.assertTrue(regeln.gueltigePosition(new Pos(3,0)));
		Assert.assertTrue(regeln.gueltigePosition(new Pos(3,3)));
		Assert.assertTrue(regeln.gueltigePosition(new Pos(0,3)));
		Assert.assertFalse(regeln.gueltigePosition(new Pos(0,-1)));
		Assert.assertFalse(regeln.gueltigePosition(new Pos(3,4)));
		Assert.assertFalse(regeln.gueltigePosition(new Pos(4,3)));
		Assert.assertFalse(regeln.gueltigePosition(new Pos(-1,3)));
	}
	
	
	@Test
	public final void testGueltigeRichtungen() {
		mockSpielfeld4x4mitStartKonstellation();
		List<Pos> result = regeln.gueltigeRichtungen(new Pos(0,3));
		Assert.assertEquals(3, result.size());
		Assert.assertTrue(result.contains(new Pos(0,-1)));
		Assert.assertTrue(result.contains(new Pos(1,-1)));
		Assert.assertTrue(result.contains(new Pos(1,0)));
	}
	
	
	// - - - -
	// - w s -
	// - s w -
	// - - - -


	@Test
	public final void testSinnvolleRichtungen() {
		mockSpielfeld4x4mitStartKonstellation();
		List<Pos> result = regeln.sinnvolleRichtungen(Farben.WEISS,new Pos(3,0));
		Assert.assertEquals(1, result.size());
		Assert.assertTrue(result.contains(new Pos(-1,1)));
	}	
	
//	private void mockSpielfeldToString(){
//		for (int zeile=0; zeile<4; zeile++){
//			for (int spalte=0; spalte<4; spalte++){
//				System.out.print(" "+spielfeld.getFarbe(new Pos(zeile,spalte)));
//			}
//			System.out.println();
//		}
//				
//	}
	
	
	@Test
	public final void testKannHierStartkonstellation() {
		mockSpielfeld4x4mitStartKonstellation();
		Assert.assertTrue(regeln.kannHier(Farben.WEISS, new Pos(0,2)));
		Assert.assertTrue(regeln.kannHier(Farben.WEISS, new Pos(1,3)));
		Assert.assertTrue(regeln.kannHier(Farben.WEISS, new Pos(3,1)));
		Assert.assertTrue(regeln.kannHier(Farben.WEISS, new Pos(2,0)));
		Assert.assertFalse(regeln.kannHier(Farben.WEISS, new Pos(3,2)));
		Assert.assertFalse(regeln.kannHier(Farben.WEISS, new Pos(0,0)));
	}
	
	@Test
	public final void testKannHierInRichtungMitStartkonstellation() {
		mockSpielfeld4x4mitStartKonstellation();
		Assert.assertTrue(regeln.kannHierInRichtung(Farben.WEISS, new Pos(0,2), new Pos(1,0)));
		Assert.assertFalse(regeln.kannHierInRichtung(Farben.WEISS, new Pos(0,2), new Pos(1,-1)));
		Assert.assertFalse(regeln.kannHierInRichtung(Farben.WEISS, new Pos(3,2), new Pos(-1,-1)));
	}


	@Test
	public final void testWoKannStartkonstellation() {
		mockSpielfeld4x4mitStartKonstellation();
		List<Pos> listWoKann = regeln.woKann(Farben.WEISS);
		Assert.assertEquals(4, listWoKann.size());
		Assert.assertTrue(listWoKann.contains(new Pos(0,2)));
		Assert.assertTrue(listWoKann.contains(new Pos(1,3)));
		Assert.assertTrue(listWoKann.contains(new Pos(3,1)));
		Assert.assertTrue(listWoKann.contains(new Pos(2,0)));
	}
	

	
}
