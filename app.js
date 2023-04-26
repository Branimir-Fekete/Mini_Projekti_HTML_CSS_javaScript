/*-------------------Slider(O nama) start-------------------*/
$(document).ready(function() {
    var slike = $('#slike img');
    var brojSlika = slike.length;
    var trenutnaSlika = 0;

    function prikaziSliku() {
        // sakrij sve slike osim trenutne
        slike.removeClass('prikaz');
        $(slike[trenutnaSlika]).addClass('prikaz');

        // povećaj broj trenutne slike, ali vrati na 0 kada dođe do kraja
        trenutnaSlika++;
        if (trenutnaSlika >= brojSlika) {
            trenutnaSlika = 0;
        }
    }

    // prikaži prvu sliku i pokreni rotaciju slika
    prikaziSliku();
    setInterval(prikaziSliku, 2000);
});
  /*-------------------Slider(O nama) end-------------------*/
  