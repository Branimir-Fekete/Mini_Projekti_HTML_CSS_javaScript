/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package racunopg.model;

/**
 *
 * @author pc
 */
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import java.math.BigDecimal;
import java.util.Date;

@Entity
public class Racun extends Entitet {

    private String nazivRacuna;
    private BigDecimal PDV;

    private Date vrijemeIzdavanja;
    private String opisPlacanja;

    @ManyToOne
    private Kupac kupac;

    @ManyToOne
    private Usluga usluga;

    @ManyToOne
    private Proizvod proizvod;

    public Racun() {
    }

    public Racun(BigDecimal PDV, Date vrijemeIzdavanja, String opisPlacanja, int kupacId, int uslugaId,
            int proizvodId) {
        this.PDV = PDV;
        this.vrijemeIzdavanja = vrijemeIzdavanja;
        this.opisPlacanja = opisPlacanja;

    }

    public String getNazivRacuna() {
        return nazivRacuna;
    }

    public void setNazivRacuna(String nazivRacuna) {
        this.nazivRacuna = nazivRacuna;
    }

    public BigDecimal getPDV() {
        return PDV;
    }

    public void setPDV(BigDecimal PDV) {
        this.PDV = PDV;
    }

    public Date getVrijemeIzdavanja() {
        return vrijemeIzdavanja;
    }

    public void setVrijemeIzdavanja(Date vrijemeIzdavanja) {
        this.vrijemeIzdavanja = vrijemeIzdavanja;
    }

    public String getOpisPlacanja() {
        return opisPlacanja;
    }

    public void setOpisPlacanja(String opisPlacanja) {
        this.opisPlacanja = opisPlacanja;
    }

    public Kupac getKupac() {
        return kupac;
    }

    public void setKupac(Kupac kupac) {
        this.kupac = kupac;
    }

    public Usluga getUsluga() {
        return usluga;
    }

    public void setUsluga(Usluga usluga) {
        this.usluga = usluga;
    }

    public Proizvod getProizvod() {
        return proizvod;
    }

    public void setProizvod(Proizvod proizvod) {
        this.proizvod = proizvod;
    }

    @Override
    public String toString() {
        return nazivRacuna;
    }
}
