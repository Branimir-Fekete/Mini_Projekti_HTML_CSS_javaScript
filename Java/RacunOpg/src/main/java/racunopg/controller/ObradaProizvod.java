/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package racunopg.controller;

import java.math.BigDecimal;
import java.util.List;
import racunopg.model.Proizvod;
import racunopg.util.RacunOpgException;

/**
 *
 * @author pc
 */
public class ObradaProizvod extends Obrada<Proizvod> {

    @Override
    public List<Proizvod> read() {
        return session.createQuery("from Proizvod", Proizvod.class).list();
    }

    @Override
    protected void kontrolaUnos() throws RacunOpgException {
        kontrolaNaziv();
        kontrolaCijena();
    }

    @Override
    protected void kontrolaPromjena() throws RacunOpgException {
        kontrolaUnos();
    }

    @Override
    protected void kontrolaBrisanje() throws RacunOpgException {

    }

    private void kontrolaNaziv() throws RacunOpgException {
        if (entitet.getNaziv() == null) {
            throw new RacunOpgException("Naziv proizvoda mora biti definiran");
        }
        if (entitet.getNaziv().isEmpty()) {
            throw new RacunOpgException("Polje za unos ne smije ostati prazno");
        }
    }

    private void kontrolaCijena() throws RacunOpgException {

        var c = entitet.getCijena();
        if (c == null) {
            return;
        }

        if (c.compareTo(BigDecimal.ZERO) <= 0
                || c.compareTo(new BigDecimal(1000)) > 0) {
            throw new RacunOpgException("Ako je cijena postavljena mora biti veća od 0 i manja ili jednaka 1000");
        }
    }

}
