/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package racunopg.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import java.math.BigDecimal;

/**
 *
 * @author pc
 */
@Entity
public class Proizvod extends Entitet{


        
        private String naziv;
        private BigDecimal cijena; // Promijenjeno u BigDecimal
        private BigDecimal kolicina; // Promijenjeno u BigDecimal
        private String opis;

    public Proizvod() {
    }

    public Proizvod(String naziv, BigDecimal cijena, BigDecimal kolicina, String opis) {
        this.naziv = naziv;
        this.cijena = cijena;
        this.kolicina = kolicina;
        this.opis = opis;
    }


    public String getNaziv() {
        return naziv;
    }

    public void setNaziv(String naziv) {
        this.naziv = naziv;
    }

    public BigDecimal getCijena() {
        return cijena;
    }

    public void setCijena(BigDecimal cijena) {
        this.cijena = cijena;
    }

    public BigDecimal getKolicina() {
        return kolicina;
    }

    public void setKolicina(BigDecimal kolicina) {
        this.kolicina = kolicina;
    }

    public String getOpis() {
        return opis;
    }

    public void setOpis(String opis) {
        this.opis = opis;
    }

}
