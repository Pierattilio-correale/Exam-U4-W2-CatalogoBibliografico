package Esame;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

//METODO ALTERNATIVO
public class Archivio2 {
    private Set<ElementoDelCatalogo> catalogo = new HashSet<>();

    //esercizio 1
    public void inserisciElementoNelCatalogo2(ElementoDelCatalogo e) throws IllegalArgumentException{
if(catalogo.stream().anyMatch(elementoDelCatalogo -> elementoDelCatalogo.getIsbn().equals(e.getIsbn()))){
    throw new IllegalArgumentException("Non puoi inserire un elemento con lo stesso ISBN");
}
catalogo.add(e);
    }
    //esercizio 2
    public ElementoDelCatalogo cercaPerIsbn2(String isbn) throws ElementoNonTrovatoException{
       List<ElementoDelCatalogo> risultati= catalogo.stream().filter(elementoDelCatalogo -> elementoDelCatalogo.getIsbn().equals(isbn)).toList();
       if(risultati.size()==0){
           throw new ElementoNonTrovatoException("Non esiste nessun elemento con questo ISBN");
       }
       return risultati.get(0);
    }
    // esercizio 3
    public void rimuoviElementoNelCatalogoConIsbn2(String isbn)throws ElementoNonTrovatoException{
        List<ElementoDelCatalogo>risultati = catalogo.stream().filter(elementoDelCatalogo -> elementoDelCatalogo.getIsbn().equals(isbn)).toList();
        if(risultati.size()==0){
            throw new ElementoNonTrovatoException("Non esiste nessun elemento con questo ISBN");
        }
        catalogo.remove(risultati.get(0));
    }
    //esercizio 4
    public List<ElementoDelCatalogo> cercaPerAnno2(Integer anno){
        return catalogo.stream().filter(elementoDelCatalogo -> elementoDelCatalogo.getAnnoPubblicazione().equals(anno)).toList();

    }
    //esercizio 5
    public List<Libri> cercaPerAutore2(String autore){
        return catalogo.stream().filter(elementoDelCatalogo -> elementoDelCatalogo instanceof Libri).map(elementoDelCatalogo -> (Libri)elementoDelCatalogo).filter(libri -> libri.getAutore().equals(autore)).toList();
    }
    //esercizio 6
    public void aggiornaElemento2(String isbn, ElementoDelCatalogo e)throws ElementoNonTrovatoException{
        List<ElementoDelCatalogo> trovati= catalogo.stream().filter(elementoDelCatalogo -> elementoDelCatalogo.getIsbn().equals(isbn)).toList();
        if(trovati.size()==0){
            throw new ElementoNonTrovatoException("Non esiste nessun elemento con questo ISBN");
        }
        catalogo.remove(trovati.get(0));
        catalogo.add(e);
    }
    //esercizio 7
    public void stampaStatistiche2() {

        List<ElementoDelCatalogo> elementi = catalogo.stream().toList();


        long numeroLibri = elementi.stream().filter(e -> e instanceof Libri).count();


        long numeroRiviste = elementi.stream().filter(e -> e instanceof Riviste).count();


        ElementoDelCatalogo maxPagine = null;
        if (!elementi.isEmpty()) {
            maxPagine = elementi.stream().max((e1, e2) -> Integer.compare(e1.getNumeroPagine(), e2.getNumeroPagine())).get();
        }


        double mediaPagine = 0;
        if (!elementi.isEmpty()) {
            mediaPagine = elementi.stream().mapToInt(ElementoDelCatalogo::getNumeroPagine).average().getAsDouble();
        }

        System.out.println("Numero totale di libri: " + numeroLibri);
        System.out.println("Numero totale di riviste: " + numeroRiviste);

        if (maxPagine != null) {
            System.out.println("Elemento con più pagine: " + maxPagine);
        } else {
            System.out.println("Nessun elemento nel catalogo.");
        }

        System.out.println("Media pagine: " + mediaPagine);
    }
}
