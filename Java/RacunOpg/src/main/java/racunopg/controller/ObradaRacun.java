/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package racunopg.controller;

import java.math.BigDecimal;
import java.util.List;
import racunopg.model.Racun;
import racunopg.util.RacunOpgException;

/**
 *
 * @author pc
 */
public class ObradaRacun extends Obrada<Racun> {

    @Override
    public List<Racun> read() {
        return session.createQuery("from Racun", Racun.class).list();
    }

    @Override
    protected void kontrolaUnos() throws RacunOpgException {
        kontrolaPDV();
        kontrolaVrijemeIzdavanja();
        kontrolaOpisPlacanja();
    }

    @Override
    protected void kontrolaPromjena() throws RacunOpgException {
        kontrolaUnos();
    }

    @Override
    protected void kontrolaBrisanje() throws RacunOpgException {

    }

    private void kontrolaPDV() throws RacunOpgException {
        if (entitet.getPDV() == null || entitet.getPDV().compareTo(BigDecimal.ZERO) < 0) {
            throw new RacunOpgException("PDV mora biti veći ili jednak nuli");
        }
    }

    private void kontrolaVrijemeIzdavanja() throws RacunOpgException {
        if (entitet.getVrijemeIzdavanja() == null) {
            throw new RacunOpgException("Vrijeme izdavanja računa je obavezno");
        }
    }

    private void kontrolaOpisPlacanja() throws RacunOpgException {
        if (entitet.getOpisPlacanja() == null || entitet.getOpisPlacanja().isEmpty()) {
            throw new RacunOpgException("Opis plaćanja je obavezan");
        }
    }

}
