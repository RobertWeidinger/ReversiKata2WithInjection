package de.rmrw.ReversiKata2.code;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Hashtable;
import java.util.List;

public class ReversiRegeln {
	
	private ArrayList<Pos> richtungen;
	
	private ISpielfeld spielfeld;
	
	public Hashtable<Pos,Feld> getStartStatus() {
		Hashtable<Pos,Feld> result = new Hashtable<Pos,Feld>();
		result.put(new Pos(getSize()/2-1,getSize()/2-1),new Feld(new Pos(getSize()/2-1,getSize()/2-1),Farben.WEISS));
		result.put(new Pos(getSize()/2,getSize()/2-1),new Feld(new Pos(getSize()/2,getSize()/2-1),  Farben.SCHWARZ));
		result.put(new Pos(getSize()/2-1,getSize()/2),new Feld(new Pos(getSize()/2-1,getSize()/2),  Farben.SCHWARZ));
		result.put(new Pos(getSize()/2,getSize()/2),new Feld(new Pos(getSize()/2,getSize()/2),    Farben.WEISS));
		return result;
	}

	private int getSize() {
		return spielfeld.getGroesse();
	}

	public void init(ISpielfeld spielfeld) {
		this.setISpielfeld(spielfeld);
		richtungen = new ArrayList<Pos>();
		richtungen.addAll(Arrays.asList(new Pos(1,0),new Pos(1,1),new Pos(0,1),new Pos(-1,1),new Pos(-1,0),new Pos(-1,-1), new Pos(0,-1),new Pos(1,-1)));
	}

	public ISpielfeld getISpielfeld() {
		return spielfeld;
	}

	private void setISpielfeld(ISpielfeld spielfeld) {
		this.spielfeld = spielfeld;
	}

	public boolean gueltigePosition(Pos pos){
		return pos.getZeile()>=0 && pos.getZeile()<spielfeld.getGroesse() &&
			   pos.getSpalte()>=0 && pos.getSpalte()<spielfeld.getGroesse();
	}
	
	public List<Pos> gueltigeRichtungen(Pos pos) {
		List<Pos> result = new ArrayList<Pos>();
		for (Pos pos2 : richtungen) {
			if (gueltigePosition(pos.plus(pos2)))
				result.add(pos2);
		}
		return result;
	}
	

	public List<Pos> sinnvolleRichtungen(Farben farbe, Pos pos){
		List<Pos> result = new ArrayList<Pos>();
		List<Pos> gueltigeRichtungen = gueltigeRichtungen(pos);
		for (Pos pos2 : gueltigeRichtungen) {
			Pos nachbar = pos.plus(pos2);
			if (!gueltigePosition(nachbar)) continue;
			Farben nachbarFarbe = spielfeld.getFarbe(nachbar);
			if (nachbarFarbe.complementaerZu(farbe)) 
				result.add(pos2);
		}
		return result;
	}
	

	public boolean kannHier(Farben farbe, Pos pos){
		List<Pos> richtungen = sinnvolleRichtungen(farbe, pos);
		for (Pos richtung : richtungen) {
			if (kannHierInRichtung(farbe, pos, richtung)) return true;
		}
		return false;
	}

	public List<Pos> woKann(Farben farbe) {
		List<Pos> result = new ArrayList<Pos>();
		for (Pos pos : spielfeld.getPositionen()) {
			if (kannHier(farbe,pos)) result.add(pos);
		}
		return result;
	}

	public boolean kannHierInRichtung(Farben farbe, Pos pos, Pos richtung) {
		if (!spielfeld.getFarbe(pos).equals(Farben.LEER)) return false;
		Pos it = pos.plus(richtung);
		if (!gueltigePosition(it)) return false; // wird eigentlich schon durch sinnvolleRichtungen geprueft
		if (!spielfeld.getFarbe(it).complementaerZu(farbe)) return false; // Nachbar muss gegensätzliche Farbe haben
		do {
			it = it.plus(richtung);
			if (!gueltigePosition(it)) return false;
			if (spielfeld.getFarbe(it).equals(Farben.LEER)) return false;
			if (spielfeld.getFarbe(it).equals(farbe)){
				return true;
			}
		} while (true);
	}
	
	

}
