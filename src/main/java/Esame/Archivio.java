package Esame;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class Archivio {

    private Map<String, ElementoDelCatalogo> catalogo = new HashMap<>();
//esercizio 1
    public void inserisciElementoNelCatalogo(ElementoDelCatalogo e) throws IllegalArgumentException{
        if(catalogo.containsKey(e.getIsbn())){
            throw new IllegalArgumentException("Non puoi inserire un elemento con lo stesso ISBN ");
        }
       catalogo.put(e.getIsbn(), e);

    }
    //esercizio 2
    public ElementoDelCatalogo cercaPerIsbn(String isbn) throws ElementoNonTrovatoException{
        ElementoDelCatalogo elemento = catalogo.get(isbn);
        if(elemento== null){
            throw new ElementoNonTrovatoException("Non esiste nessun elemento con questo ISBN");
        }
        return elemento;
    };
    //esercizio 3
    public void rimuoviElementoNelCatalagoConIsbn(String isbn)throws ElementoNonTrovatoException{
        if(!catalogo.containsKey(isbn)){
            throw new ElementoNonTrovatoException("Non c'è nessun elemento del catalago con questo ISBN" );

        }
        catalogo.remove(isbn);
    }
    //esercizio 4
    public List<ElementoDelCatalogo> cercaPerAnno(Integer anno){
        return catalogo.values().stream().filter(elementoDelCatalogo -> elementoDelCatalogo.getAnnoPubblicazione()== anno).collect(Collectors.toList());
    }
    //esercizio 5
    public  List<Libri> cercaPerAutore(String autore){
        return catalogo.values().stream().filter(elementoDelCatalogo -> elementoDelCatalogo instanceof Libri).map(elementoDelCatalogo -> (Libri)elementoDelCatalogo).filter(libri -> libri.getAutore().equals(autore)).collect(Collectors.toList());

    }
    //esercizio 6
    public void aggiornaElemento(String isbn , ElementoDelCatalogo e) throws ElementoNonTrovatoException{
if(!catalogo.containsKey(isbn)){
    throw new ElementoNonTrovatoException("Non c'è nessun elemento del catalago con questo ISBN");
}
catalogo.put(isbn , e);

    }
    //esercizio 7
    public void stampaStatistiche(){
        List<ElementoDelCatalogo> elementi = catalogo.values().stream().toList();
        Long numeroLibri = elementi.stream().filter(elementoDelCatalogo -> elementoDelCatalogo instanceof Libri).count();

        Long numeroRiviste= elementi.stream().filter(elementoDelCatalogo -> elementoDelCatalogo instanceof Riviste).count();

        ElementoDelCatalogo maxPagine = null;
        if (!elementi.isEmpty()) {
            maxPagine = elementi.stream()
                    .max((e1, e2) -> Integer.compare(e1.getNumeroPagine(), e2.getNumeroPagine()))
                    .get();
        }


        double mediaPagine = 0;
        if (!elementi.isEmpty()) {
            mediaPagine = elementi.stream()
                    .mapToInt(ElementoDelCatalogo::getNumeroPagine)
                    .average()
                    .getAsDouble();
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


