package analytics;

import java.beans.XMLDecoder;
import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.List;
import java.util.Set;
import java.util.stream.Stream;

import entities.Pruefung;

public interface Analyse {
	public static String DATEI ="./data.xml";
	
	public abstract void zeigePruefungenVon(int mat);

	public abstract void zeigeBestandenePruefungenVon(int mat);

	public abstract void zeigeGeordnetPruefungenVon(int mat);

	public abstract void zeigeDetailgeordnetPruefungenVon(int mat);

	public abstract long anzahlPruefungen();

	public abstract long anzahlBestandenerPruefungen(int mat);

	public abstract double durchschnittsnote(int mat);

	public abstract double durchschnittModul(String modul);

	public abstract List<Integer> endgueltigDurchgefallen();

	public abstract List<Integer> hatNochOffenenDrittenVersuch();
	
	@SuppressWarnings("unchecked")
	public default Stream<Pruefung> lesePruefungen(){
		try (XMLDecoder dec = new XMLDecoder(new BufferedInputStream(new FileInputStream(DATEI)))){
			return ((Set<Pruefung>) dec.readObject()).stream();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		return null;
	}

}
