package Esame;

public class Riviste extends ElementoDelCatalogo{
private Periodicita periodicita;

    public Riviste(String isbn, String titolo, Integer annoPubblicazione, Integer numeroPagine, Periodicita periodicita) {
        super(isbn, titolo, annoPubblicazione, numeroPagine);
        this.periodicita = periodicita;
    }

    public Periodicita getPeriodicita() {
        return periodicita;
    }

    public void setPeriodicita(Periodicita periodicita) {
        this.periodicita = periodicita;
    }
    @Override
    public String toString() {
        return "Rivista [ISBN=" + getIsbn() + ", Titolo=" + getTitolo() + ", Anno=" + getAnnoPubblicazione() + ", Pagine=" + getNumeroPagine() + ", Periodicità=" + periodicita + "]";
    }
}
