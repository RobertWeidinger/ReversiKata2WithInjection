package de.rmrw.ReversiKata2.MainSpikes;

import java.util.List;

import org.jboss.weld.environment.se.Weld;

import de.rmrw.ReversiKata2.code.Farben;
import de.rmrw.ReversiKata2.code.Pos;
import de.rmrw.ReversiKata2.code.Spielfeld;

public class MainSpike {
	
	private static Spielfeld spielfeld;
	

	public static void main(String[] args) {
		MainSpike mainSpike = new MainSpike();
		// CDI mit Weld implementiert ==============================================================
		spielfeld = new Weld().initialize().instance().select( Spielfeld.class ).get();
		// =========================================================================================
		mainSpike.run();

	}

	private void run() {
		spielfeld.init(8);
		spielfeld.erzeugeStartStatus();
		List<Pos> woKannWeiss = spielfeld.woKann(Farben.WEISS);
		System.out.println("woKannWeiss = "+woKannWeiss);
		List<Pos> woKannSchwarz = spielfeld.woKann(Farben.SCHWARZ);
		System.out.println("woKannSchwarz = "+woKannSchwarz);
		
	}

}
