/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package racunopg.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

/**
 *
 * @author pc
 */
@Entity
public class Kupac extends Entitet {

    private String nazivSubjekta;
    private String adresa;
    private String OIB;
    private String IBAN;

    public Kupac() {
    }

    public Kupac(String nazivSubjekta, String adresa, String OIB, String IBAN) {
        this.nazivSubjekta = nazivSubjekta;
        this.adresa = adresa;
        this.OIB = OIB;
        this.IBAN = IBAN;
    }

    public String getNazivSubjekta() {
        return nazivSubjekta;
    }

    public void setNazivSubjekta(String nazivSubjekta) {
        this.nazivSubjekta = nazivSubjekta;
    }

    public String getAdresa() {
        return adresa;
    }

    public void setAdresa(String adresa) {
        this.adresa = adresa;
    }

    public String getOIB() {
        return OIB;
    }

    public void setOIB(String OIB) {
        this.OIB = OIB;
    }

    public String getIBAN() {
        return IBAN;
    }

    public void setIBAN(String IBAN) {
        this.IBAN = IBAN;
    }

    @Override
    public String toString() {
        return nazivSubjekta;
    }
}
