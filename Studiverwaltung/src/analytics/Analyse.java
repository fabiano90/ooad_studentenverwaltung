package analytics;

import java.beans.XMLDecoder;
import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.StreamTokenizer;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.function.Supplier;
import java.util.stream.Collector;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import entities.Pruefung;
import entities.Student;

public interface Analyse {
	public static String DATEI = "./data.xml";

	public default void zeigePruefungenVon(int mat) {
		Stream<Pruefung> stream = lesePruefungen();
		stream.filter(p -> p.getStudent().getMatnr() == mat)
			  .forEach(p -> System.out.println(p.toString()));
	}

	public default void zeigeBestandenePruefungenVon(int mat) {
		Stream<Pruefung> stream = lesePruefungen();
		stream.filter(p -> p.getStudent().getMatnr() == mat)
				.filter(p -> p.getNote() < 500)
				.forEach(p -> System.out.println(p.toString()));
	}

	public default void zeigeGeordnetPruefungenVon(int mat) {
		Stream<Pruefung> stream = lesePruefungen();
		stream
			  .filter(p -> p.getStudent().getMatnr() == mat)
			  .collect(Collectors. groupingBy ( p->p.getModul()))
			  .forEach((m,p)->{
				  					System.out.print(m.getTitel());
				  					p.forEach(pr->System.out.println(pr.getNote()));
			  				  }
			  );
	}

	public default void zeigeDetailgeordnetPruefungenVon(int mat) {
//		Stream<Pruefung> stream = lesePruefungen();
//		stream
//			  .filter(p -> p.getStudent().getMatnr() == mat)
//			  .distinct()
//			  .sorted(( p1, p2)->p2.getModul().getTitel().compareTo(p1.getModul().getTitel()))
//			  .collect(Collector. groupingBy ( p->p.getModul().getTitel()));
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
		Stream <Pruefung>stream=lesePruefungen();
		return stream.filter(p-> p.getStudent().getMatnr()==mat&&p.getNote()<500)
					 .mapToDouble(Pruefung::getNote)
					 .average()
					 .getAsDouble();

	}

	public default double durchschnittModul(String modul) {
		 Stream<Pruefung> stream = lesePruefungen();
		 return stream.filter(p -> p.getModul().getTitel().equals(modul))
				 	  .mapToDouble(Pruefung::getNote)
				 	  .average()
				 	  .getAsDouble();
	}

	public default List<Integer> endgueltigDurchgefallen() {
		Stream<Pruefung> stream = lesePruefungen();
		return stream.filter(p -> p.getVersuch() == 3 && p.getNote() == 500)
					 .map(p->p.getStudent().getMatnr())
					 .collect(Collectors.toList());
		
	}

	public default List<Integer> hatNochOffenenDrittenVersuch() {
		Stream<Pruefung> stream = lesePruefungen();
		return stream.filter(p -> p.getVersuch() == 2 && p.getNote() == 500)
					 .map(p->p.getStudent().getMatnr())
					 .collect(Collectors.toList());

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
