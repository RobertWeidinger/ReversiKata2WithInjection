package de.rmrw.ReversiKata2.codeTest;

import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

import de.rmrw.ReversiKata2.code.Farben;
import de.rmrw.ReversiKata2.code.Feld;
import de.rmrw.ReversiKata2.code.Pos;
import de.rmrw.ReversiKata2.code.ReversiRegeln;
import de.rmrw.ReversiKata2.code.Spielfeld;

@RunWith(MockitoJUnitRunner.class)
public class SpielfeldTest {
	
	@InjectMocks
	Spielfeld spielfeld;
	/* Kommentar von Robert: Mocking lernen wir später */
	@Mock
	ReversiRegeln regeln;

	@Before
	public void setUp() throws Exception {
		spielfeld.init(4);
		Hashtable<Pos,Feld> list = new Hashtable<Pos,Feld>();
		list.put(new Pos(1,1),new Feld(new Pos(1,1),Farben.WEISS));
		list.put(new Pos(1,2),new Feld(new Pos(1,2),Farben.SCHWARZ));
		list.put(new Pos(2,2),new Feld(new Pos(2,2),Farben.WEISS));
		list.put(new Pos(2,1),new Feld(new Pos(2,1),Farben.SCHWARZ));
		Mockito.when(regeln.getStartStatus()).thenReturn(list);
		
		// - - - -
		// - w s -
		// - s w -
		// - - - -
		
		List<Pos> listWoKann = new ArrayList<Pos>();
		listWoKann.add(new Pos(0,2));
		listWoKann.add(new Pos(1,3));
		listWoKann.add(new Pos(3,1));
		listWoKann.add(new Pos(2,0));
		Mockito.when(regeln.woKann(Farben.WEISS)).thenReturn(listWoKann);
}
	
	@Test
	public final void testGetAnzahlFelderMitFarbeLeer() {
		Assert.assertEquals(16,spielfeld.getAnzahlFelderMitFarbe(Farben.LEER));
	}


	@Test
	public final void testErzeugeStartstatus() {
		spielfeld.erzeugeStartStatus();
		Assert.assertEquals(Farben.WEISS,   spielfeld.getFarbe(new Pos(1,1)));
		Assert.assertEquals(Farben.SCHWARZ, spielfeld.getFarbe(new Pos(1,2)));
		Assert.assertEquals(Farben.WEISS,   spielfeld.getFarbe(new Pos(2,2)));
		Assert.assertEquals(Farben.SCHWARZ, spielfeld.getFarbe(new Pos(2,1)));
		Assert.assertEquals(12,             spielfeld.getAnzahlFelderMitFarbe(Farben.LEER));
	}
	
	@Test
	public final void testWoKannNachStartstatus() {
		spielfeld.init(4);
		spielfeld.erzeugeStartStatus();
		List<Pos> listWoKann = spielfeld.woKann(Farben.WEISS);
		Assert.assertEquals(4, listWoKann.size());
		Assert.assertTrue(listWoKann.contains(new Pos(0,2)));
		Assert.assertTrue(listWoKann.contains(new Pos(1,3)));
		Assert.assertTrue(listWoKann.contains(new Pos(3,1)));
		Assert.assertTrue(listWoKann.contains(new Pos(2,0)));
	}
	
	

}
