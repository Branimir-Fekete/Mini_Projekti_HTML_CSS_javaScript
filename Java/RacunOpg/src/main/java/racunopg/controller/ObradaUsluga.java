/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package racunopg.controller;

import java.math.BigDecimal;
import java.util.List;
import racunopg.model.Usluga;
import racunopg.util.RacunOpgException;

/**
 *
 * @author pc
 */
public class ObradaUsluga extends Obrada<Usluga>{

    @Override
    public List<Usluga> read() {
         return session.createQuery("from Usluga", Usluga.class).list();
    }

    @Override
    protected void kontrolaUnos() throws RacunOpgException {
        kontrolaNaziv();
        kontrolaCijenaPoHa();
    }

    @Override
    protected void kontrolaPromjena() throws RacunOpgException {
        kontrolaUnos();
    }

    @Override
    protected void kontrolaBrisanje() throws RacunOpgException {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    private void kontrolaNaziv() throws RacunOpgException {
   if (entitet.getNaziv() == null || entitet.getNaziv().isEmpty()) {
            throw new RacunOpgException("Naziv usluge je obavezan");
        }
    }

    private void kontrolaCijenaPoHa() throws RacunOpgException {
            BigDecimal cijenaPoHa = entitet.getCijenaPoHa();
        if (cijenaPoHa == null || cijenaPoHa.compareTo(BigDecimal.ZERO) <= 0) {
            throw new RacunOpgException("Cijena po hektaru mora biti veća od 0");
        }
    }
    
      
}
