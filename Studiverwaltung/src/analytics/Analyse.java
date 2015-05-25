package analytics;

import java.beans.XMLDecoder;
import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.StreamTokenizer;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Stream;

import entities.Pruefung;
import entities.Student;

public interface Analyse {
	public static String DATEI = "./data.xml";

	public default void zeigePruefungenVon(int mat) {
		Stream<Pruefung> stream = lesePruefungen();
		stream.filter(p -> p.getStudent().getMatnr() == mat).forEach(
				p -> System.out.println(p.toString()));
	}

	public default void zeigeBestandenePruefungenVon(int mat) {
		Stream<Pruefung> stream = lesePruefungen();
		stream.filter(p -> p.getStudent().getMatnr() == mat)
				.filter(p -> p.getNote() < 500)
				.forEach(p -> System.out.println(p.toString()));
	}

	public default void zeigeGeordnetPruefungenVon(int mat) {
		Stream<Pruefung> stream = lesePruefungen();
		stream.filter(p -> p.getStudent().getMatnr() == mat).forEachOrdered(
				p -> System.out.println(p.toString()));
	}

	public default void zeigeDetailgeordnetPruefungenVon(int mat) {

	}

	public default long anzahlPruefungen() {
		Stream<Pruefung> stream = lesePruefungen();
		return stream.count();
	}

	public default long anzahlBestandenerPruefungen(int mat) {
		Stream<Pruefung> stream = lesePruefungen();
		return stream.filter(p -> p.getNote() < 500 && p.getStudent().getMatnr() == mat)
				.count();
	}

	public default double durchschnittsnote(int mat) {
		Stream<Pruefung>stream=lesePruefungen();
		return stream.filter(p-> p.getStudent()
					 .getMatnr()==mat)
					 .mapToLong(p->p.getNote())
					 .average()
					 .getAsDouble();

	}

	public default double durchschnittModul(String modul) {
		 Stream<Pruefung> stream = lesePruefungen();
		 return stream.filter(p -> p.getModul().equals(modul))
		 .mapToDouble(p -> p.getNote()).average().getAsDouble();
	}

	public default List<Integer> endgueltigDurchgefallen() {
		return null;
	}

	public default List<Integer> hatNochOffenenDrittenVersuch() {
		return null;

	}

	@SuppressWarnings("unchecked")
	public default Stream<Pruefung> lesePruefungen() {
		try (XMLDecoder dec = new XMLDecoder(new BufferedInputStream(
				new FileInputStream(DATEI)))) {
			return ((Set<Pruefung>) dec.readObject()).stream();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		return null;
	}

}
