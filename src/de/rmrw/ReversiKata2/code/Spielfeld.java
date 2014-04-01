package de.rmrw.ReversiKata2.code;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Hashtable;
import java.util.List;

import javax.inject.Inject;

public class Spielfeld implements ISpielfeld{
	
	@Inject
	ReversiRegeln regeln;
	
	private int groesse = 0;
	private Hashtable<Pos,Feld> felder = null;

	public void init(int groesse) {
		this.groesse = groesse;
		felder = new Hashtable<Pos,Feld>();
		for (int zeile=0; zeile<getGroesse(); zeile++)
			for (int spalte=0; spalte<getGroesse(); spalte++)
				erzeugeFeld(new Pos(zeile,spalte));
		regeln.init(this);
	}

	private void erzeugeFeld(Pos pos) {
		felder.put(pos,new Feld(pos));
	}

	@Override
	public Collection<Pos> getPositionen() {
		Collection<Pos> positionen = new ArrayList<Pos>();
		for (Feld feld : felder.values()) 
			positionen.add(feld.getPos());
		return positionen;
	}

	
	public void erzeugeStartStatus() {
		Hashtable<Pos,Feld> startStatusGemaessRegeln = regeln.getStartStatus();
		for (Feld feld : startStatusGemaessRegeln.values()) {
			setzeFeldRoh(feld.getPos(),feld.getFarbe());
		}
	}

	private void setzeFeldRoh(Pos pos, Farben farbe) {
		felder.get(pos).setFarbe(farbe);
	}

	public Farben getFarbe(Pos pos) {
		return felder.get(pos).getFarbe();
	}

	public List<Pos> woKann(Farben farbe) {
		return regeln.woKann(farbe);
	}

	public int getAnzahlFelderMitFarbe(Farben farbe) {
		int count=0;
		for (Feld feld : felder.values()) {
			if (feld.getFarbe().equals(farbe))
				count++;
		}
		return count;
	}

	@Override
	public int getGroesse() {
		return groesse;
	}


}
