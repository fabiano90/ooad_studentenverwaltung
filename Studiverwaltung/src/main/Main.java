package main;

import analytics.Analyse;

public class Main {
	public static void main(String[] args) {
		Analyse a=null;//TODO insert code here;
		System.out.println("---zeigePruefungenVon");
		a.zeigePruefungenVon(103);
		System.out.println("---zeigeBestandenePruefungenVon");
		a.zeigeBestandenePruefungenVon(103);
		System.out.println("---zeigeGeordnetPruefungenVon");
		a.zeigeGeordnetPruefungenVon(103);
		System.out.println("---zeigeDetailgeordnetPruefungenVon");
		a.zeigeDetailgeordnetPruefungenVon(103);
		System.out.println("---anzahlPruefungen");
		System.out.println(a.anzahlPruefungen());
		System.out.println("---anzahlBestandenerPruefungen");
		System.out.println("101: " + a.anzahlBestandenerPruefungen(101));
		System.out.println("103: " + a.anzahlBestandenerPruefungen(103));
		System.out.println("---durchschnittsnote");
		System.out.println("102: " + a.durchschnittsnote(102));
		System.out.println("---durchschnittModul");
		System.out.println("Programmierung 1: "+ a.durchschnittModul("Programmierung 1"));
		System.out.println("SW-Projekt: "+ a.durchschnittModul("SW-Projekt"));
		System.out.println("---endgueltigDurchgefallen");
		System.out.println(a.endgueltigDurchgefallen());
		System.out.println("---hatNochOffenenDrittenVersuch");
		System.out.println(a.hatNochOffenenDrittenVersuch());
		System.out.println("------");
	}
}
