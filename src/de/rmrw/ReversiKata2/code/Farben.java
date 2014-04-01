package de.rmrw.ReversiKata2.code;

public enum Farben {
	WEISS("w"), SCHWARZ("s"), LEER("-");
	
	private String s;
	
	Farben(String s_){
		this.s = s_;
	}

	public boolean complementaerZu(Farben farbe) {
		if (farbe==WEISS && this==SCHWARZ) return true;
		if (farbe==SCHWARZ && this==WEISS) return true;
		return false;
	}
	
	public String toString(){
		return this.s;
	}

	public Farben complementaerFarbe() {
		if (this==WEISS) return SCHWARZ;
		if (this==SCHWARZ) return WEISS;
		return LEER;
	}

}
