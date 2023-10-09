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
public class Usluga extends Entitet{

        
	    private String naziv;
            private BigDecimal cijenaPoHa; 
            private BigDecimal kolicina; 
            private String opis;

    public Usluga() {
    }

    public Usluga( String naziv, BigDecimal cijenaPoHa, BigDecimal kolicina, String opis) {
        this.naziv = naziv;
        this.cijenaPoHa = cijenaPoHa;
        this.kolicina = kolicina;
        this.opis = opis;
    }


    public String getNaziv() {
        return naziv;
    }

    public void setNaziv(String naziv) {
        this.naziv = naziv;
    }

    public BigDecimal getCijenaPoHa() {
        return cijenaPoHa;
    }

    public void setCijenaPoHa(BigDecimal cijenaPoHa) {
        this.cijenaPoHa = cijenaPoHa;
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