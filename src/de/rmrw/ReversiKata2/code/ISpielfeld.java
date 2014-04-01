package de.rmrw.ReversiKata2.code;

import java.util.Collection;

public interface ISpielfeld {

	int getGroesse();
	Farben getFarbe(Pos pos);
	Collection<Pos> getPositionen();
}
