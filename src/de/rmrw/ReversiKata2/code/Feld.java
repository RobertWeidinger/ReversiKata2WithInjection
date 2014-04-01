package de.rmrw.ReversiKata2.code;

public class Feld {

	private Pos pos = null;
	private Farben farbe = Farben.LEER;

	public Feld(Pos pos2, Farben farbe) {
		this.pos = pos2;
		this.farbe = farbe;
	}

	public Farben getFarbe() {
		return farbe;
	}

	public void setFarbe(Farben farbe) {
		this.farbe = farbe;
	}

	public Pos getPos() {
		return pos;
	}

	public Feld(Pos pos) {
		this.pos = pos;
	}


	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((farbe == null) ? 0 : farbe.hashCode());
		result = prime * result + ((pos == null) ? 0 : pos.hashCode());
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
		Feld other = (Feld) obj;
		if (farbe != other.farbe)
			return false;
		if (pos == null) {
			if (other.pos != null)
				return false;
		} else if (!pos.equals(other.pos))
			return false;
		return true;
	}
	
}
