package de.rmrw.ReversiKata2.code;

public class Pos {

	private int zeile;
	private int spalte;

	public Pos(int zeile, int spalte) {
		this.zeile = zeile;
		this.spalte = spalte;
	}

	public int getZeile() {
		return zeile;
	}

	public int getSpalte() {
		return spalte;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + spalte;
		result = prime * result + zeile;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Pos other = (Pos) obj;
		if (spalte != other.spalte)
			return false;
		if (zeile != other.zeile)
			return false;
		return true;
	}

	public Pos plus(Pos pos) {
		return new Pos(pos.getZeile()+getZeile(),pos.getSpalte()+getSpalte());
	}
	
	public String toString(){
		return "("+getZeile()+","+getSpalte()+")";
	}
	

}
