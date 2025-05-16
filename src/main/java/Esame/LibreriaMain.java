package Esame;

import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class LibreriaMain {
    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        Archivio2 archivio = new Archivio2();

        while (true) {
            System.out.println("Seleziona un'opzione:");
            System.out.println("1. Inserisci un libro");
            System.out.println("2. Inserisci una rivista");
            System.out.println("3. Cerca un elemento per ISBN");
            System.out.println("4. Rimuovi un elemento per ISBN");
            System.out.println("5. Aggiorna un elemento");
            System.out.println("6. Stampa statistiche");
            System.out.println("7. Cerca per anno di pubblicazione");
            System.out.println("8. Cerca libri per autore");
            System.out.println("9. Esci");

            int scelta = scanner.nextInt();
            scanner.nextLine();

            switch (scelta) {
                case 1:
                    inserisciLibro(archivio, scanner);
                    break;
                case 2:
                    inserisciRivista(archivio, scanner);
                    break;
                case 3:
                    cercaPerIsbn(archivio, scanner);
                    break;
                case 4:
                    rimuoviPerIsbn(archivio, scanner);
                    break;
                case 5:
                    aggiornaElemento(archivio, scanner);
                    break;
                case 6:
                    archivio.stampaStatistiche2();
                    break;
                case 7:
                    cercaPerAnno(archivio, scanner);
                    break;
                case 8:
                    cercaPerAutore(archivio, scanner);
                    break;
                case 9:
                    System.out.println("Grazie per essere stato con noi alla prossima!");
                    scanner.close();
                    return;
                default:
                    System.out.println("Opzione non valida, riprova.");
            }
        }
    }

    private static void inserisciLibro(Archivio2 archivio, Scanner scanner) {
        try {
            System.out.print("Inserisci l'ISBN: ");
            String isbn = scanner.nextLine();
            System.out.print("Inserisci il titolo: ");
            String titolo = scanner.nextLine();
            System.out.print("Inserisci l'anno di pubblicazione: ");
            int anno = scanner.nextInt();
            System.out.print("Inserisci il numero di pagine: ");
            int numeroPagine = scanner.nextInt();
            scanner.nextLine();
            System.out.print("Inserisci l'autore del libro: ");
            String autore = scanner.nextLine();
            System.out.print("Inserisci il genere del libro: ");
            String genere = scanner.nextLine();

            Libri libro = new Libri(isbn, titolo, anno, numeroPagine, autore, genere);
            archivio.inserisciElementoNelCatalogo2(libro);
            System.out.println("Libro inserito nel catalogo.");
        } catch (IllegalArgumentException e) {
            System.out.println("Errore nell'inserimento del libro: " + e.getMessage());
        }
    }

    private static void inserisciRivista(Archivio2 archivio, Scanner scanner) {
        try {
            System.out.print("Inserisci l'ISBN: ");
            String isbn = scanner.nextLine();
            System.out.print("Inserisci il titolo: ");
            String titolo = scanner.nextLine();
            System.out.print("Inserisci l'anno di pubblicazione: ");
            int anno = scanner.nextInt();
            System.out.print("Inserisci il numero di pagine: ");
            int numeroPagine = scanner.nextInt();
            scanner.nextLine();
            System.out.print("Inserisci la periodicità della rivista (SEMESTRALE, MENSILE, SETTIMANALE): ");
            String periodicitaStr = scanner.nextLine();
            Periodicita periodicita = Periodicita.valueOf(periodicitaStr.toUpperCase());

            Riviste rivista = new Riviste(isbn, titolo, anno, numeroPagine, periodicita);
            archivio.inserisciElementoNelCatalogo2(rivista);
            System.out.println("Rivista inserita nel catalogo.");
        } catch (IllegalArgumentException e) {
            System.out.println("Errore nell'inserimento della rivista: " + e.getMessage());
        }
    }

    private static void cercaPerIsbn(Archivio2 archivio, Scanner scanner) {
        try {
            System.out.print("Inserisci l'ISBN da cercare: ");
            String isbn = scanner.nextLine();
            ElementoDelCatalogo elemento = archivio.cercaPerIsbn2(isbn);
            System.out.println("Elemento trovato: " + elemento);
        } catch (ElementoNonTrovatoException e) {
            System.out.println(e.getMessage());
        }
    }

    private static void rimuoviPerIsbn(Archivio2 archivio, Scanner scanner) {
        try {
            System.out.print("Inserisci l'ISBN da rimuovere: ");
            String isbn = scanner.nextLine();
            archivio.rimuoviElementoNelCatalogoConIsbn2(isbn);
            System.out.println("Elemento rimosso dal catalogo.");
        } catch (ElementoNonTrovatoException e) {
            System.out.println(e.getMessage());
        }
    }

    private static void aggiornaElemento(Archivio2 archivio, Scanner scanner) {
        try {
            System.out.print("Inserisci l'ISBN dell'elemento da aggiornare: ");
            String isbn = scanner.nextLine();

            ElementoDelCatalogo elementoEsistente = archivio.cercaPerIsbn2(isbn);

            System.out.print("Inserisci il nuovo titolo: ");
            String titolo = scanner.nextLine();
            System.out.print("Inserisci il nuovo anno di pubblicazione: ");
            int anno = scanner.nextInt();
            System.out.print("Inserisci il nuovo numero di pagine: ");
            int numeroPagine = scanner.nextInt();
            scanner.nextLine();

            ElementoDelCatalogo elementoAggiornato;

            if (elementoEsistente instanceof Libri) {
                System.out.print("Inserisci il nuovo autore (lascia vuoto per mantenere quello attuale): ");
                String autore = scanner.nextLine();
                System.out.print("Inserisci il nuovo genere (lascia vuoto per mantenere quello attuale): ");
                String genere = scanner.nextLine();

                String autoreFinale = autore.length()==0 ? ((Libri) elementoEsistente).getAutore() : autore;
                String genereFinale = genere.length()==0 ? ((Libri) elementoEsistente).getGenere() : genere;

                elementoAggiornato = new Libri(isbn, titolo, anno, numeroPagine, autoreFinale, genereFinale);
            } else if (elementoEsistente instanceof Riviste) {
                System.out.print("Inserisci la nuova periodicità (SEMESTRALE, MENSILE, SETTIMANALE - lascia vuoto per mantenere attuale): ");
                String periodicitaInput = scanner.nextLine();
                Periodicita periodicita = periodicitaInput.length()==0 ? ((Riviste) elementoEsistente).getPeriodicita() : Periodicita.valueOf(periodicitaInput.toUpperCase());

                elementoAggiornato = new Riviste(isbn, titolo, anno, numeroPagine, periodicita);
            } else {
                System.out.println("Tipo di elemento sconosciuto.");
                return;
            }

            archivio.aggiornaElemento2(isbn, elementoAggiornato);
            System.out.println("Elemento aggiornato.");
        } catch (ElementoNonTrovatoException e) {
            System.out.println(e.getMessage());
        } catch (IllegalArgumentException e) {
            System.out.println("Periodicità non valida.");
        }
    }

    private static void cercaPerAnno(Archivio2 archivio, Scanner scanner) {
        System.out.print("Inserisci l'anno da cercare: ");
        int anno = scanner.nextInt();
        scanner.nextLine();
        List<ElementoDelCatalogo> risultati = archivio.cercaPerAnno2(anno);
        if (risultati.size()==0) {
            System.out.println("Nessun elemento trovato per l'anno " + anno);
        } else {
            System.out.println("Elementi trovati:");
            risultati.forEach(System.out::println);
        }
    }

    private static void cercaPerAutore(Archivio2 archivio, Scanner scanner) {
        System.out.print("Inserisci il nome dell'autore: ");
        String autore = scanner.nextLine();
        List<Libri> risultati = archivio.cercaPerAutore2(autore);
        if (risultati.size()==0) {
            System.out.println("Nessun libro trovato per l'autore " + autore);
        } else {
            System.out.println("Libri trovati:");
            risultati.forEach(System.out::println);
        }
    }
}
