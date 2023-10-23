/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package racunopg.view;

import com.github.javafaker.File;
import com.github.lgooddatepicker.components.DatePickerSettings;
import com.github.lgooddatepicker.components.TimePickerSettings;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import racunopg.controller.ObradaRacun;
import racunopg.model.Racun;
import racunopg.util.Alati;
import racunopg.util.RacunOpgException;
import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.DefaultComboBoxModel;
import javax.swing.DefaultListModel;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.filechooser.FileSystemView;
import static org.apache.commons.math3.stat.inference.TestUtils.g;
import org.apache.poi.xwpf.converter.pdf.PdfConverter;
import org.apache.poi.xwpf.usermodel.ParagraphAlignment;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFParagraph;
import org.apache.poi.xwpf.usermodel.XWPFRun;
import org.checkerframework.checker.units.qual.g;
import racunopg.controller.ObradaKupac;
import racunopg.controller.ObradaProizvod;
import racunopg.controller.ObradaUsluga;
import racunopg.model.Kupac;
import racunopg.model.Proizvod;
import racunopg.model.Usluga;

/**
 *
 * @author Katedra
 */
public class ProzorRacun extends javax.swing.JFrame implements RacunOpgViewSucelje {

    private ObradaRacun obrada;
    private DecimalFormat df;

    /**
     * Creates new form ProzorRacun
     */
    public ProzorRacun() {
        initComponents();
        obrada = new ObradaRacun();

        DecimalFormatSymbols dfs = new DecimalFormatSymbols(Locale.of("hr", "HR"));
        df = new DecimalFormat("###,##0.00", dfs);

        setTitle(Alati.NAZIV_APP + " | PROIZVODI");

        ucitajProizvode();
        ucitajUsluge();
        ucitajKupce();
        definirajVrijeme();
        ucitaj();
    }

    private void definirajVrijeme() {
        DatePickerSettings dps = new DatePickerSettings(Locale.of("hr", "HR"));
        dps.setFormatForDatesCommonEra("dd. MM. YYYY.");
        dps.setTranslationClear("Očisti");
        dps.setTranslationToday("Danas");
        dtpVrijeme.datePicker.setSettings(dps);

        TimePickerSettings tps = dtpVrijeme.timePicker.getSettings();

        tps.setFormatForDisplayTime("HH:mm");
        tps.use24HourClockFormat();

        ArrayList<LocalTime> lista = new ArrayList<>();
        for (int i = 0; i < 24; i++) {
            for (int j = 0; j < 60; j = j + 10) {
                lista.add(LocalTime.of(i, j));
            }
        }

        tps.generatePotentialMenuTimes(lista);

    }

    private void ucitajProizvode() {
        DefaultComboBoxModel<Proizvod> m = new DefaultComboBoxModel<>();

        Proizvod p = new Proizvod();
        p.setSifra(0);
        p.setNaziv("Odaberite proizvode");

        m.addElement(p);

        m.addAll(new ObradaProizvod().read());

        cmbProizvod.setModel(m);
        cmbProizvod.repaint();

        cmbProizvod.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Proizvod selectedProizvod = (Proizvod) cmbProizvod.getSelectedItem();
                if (selectedProizvod != null && selectedProizvod.getSifra() != 0) {
                    // Postavite količinu proizvoda u txtKolicinaProizvoda polje
                    txtKolicinaProizvoda.setText(String.valueOf(selectedProizvod.getKolicina()));
                    // Postavite cijenu proizvoda u txtCijenaProizvoda polje
                    txtCijenaProizvoda.setText(String.valueOf(selectedProizvod.getCijena()));
                } else {
                    // Ako nije odabran proizvod, postavite prazan tekst za količinu i cijenu proizvoda
                    txtKolicinaProizvoda.setText("");
                    txtCijenaProizvoda.setText("");
                }
            }
        });

    }

    private void ucitajUsluge() {
        DefaultComboBoxModel<Usluga> m = new DefaultComboBoxModel<>();

        Usluga defaultUsluga = new Usluga();
        defaultUsluga.setSifra(0);
        defaultUsluga.setNaziv("Odaberite uslugu");

        m.addElement(defaultUsluga);

        m.addAll(new ObradaUsluga().read());

        cmbUsluga.setModel(m);
        cmbUsluga.repaint();

        cmbUsluga.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Usluga selectedUsluga = (Usluga) cmbUsluga.getSelectedItem();
                if (selectedUsluga != null && selectedUsluga.getSifra() != 0) {

                    txtKolicinaUsluga.setText(String.valueOf(selectedUsluga.getKolicina()));

                    txtCijenaPoHa.setText(String.valueOf(selectedUsluga.getCijenaPoHa()));
                } else {

                    txtKolicinaUsluga.setText("");
                    txtCijenaPoHa.setText("");
                }
            }
        });
    }

    private void ucitajKupce() {
        DefaultComboBoxModel<Kupac> m = new DefaultComboBoxModel<>();

        Kupac defaultKupac = new Kupac();
        defaultKupac.setSifra(0);
        defaultKupac.setNazivSubjekta("Odaberite kupca");

        m.addElement(defaultKupac);

        m.addAll(new ObradaKupac().read());

        cmbKupac.setModel(m);
        cmbKupac.repaint();

        cmbKupac.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Kupac selectedKupac = (Kupac) cmbKupac.getSelectedItem();
                if (selectedKupac != null && selectedKupac.getSifra() != 0) {

                    txtAdresa.setText(selectedKupac.getAdresa());

                    txtOib.setText(selectedKupac.getOIB());

                    txtIban.setText(selectedKupac.getIBAN());
                } else {

                    txtAdresa.setText("");
                    txtOib.setText("");
                    txtIban.setText("");
                }
            }
        });
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        lstPodaci = new javax.swing.JList<>();
        jLabel1 = new javax.swing.JLabel();
        txtPdv = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        txtOpisPlacanja = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        btnDodaj = new javax.swing.JButton();
        btnObrisi = new javax.swing.JButton();
        btnPromjena = new javax.swing.JButton();
        jLabel4 = new javax.swing.JLabel();
        txtNazivRacuna = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        cmbProizvod = new javax.swing.JComboBox<>();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        txtKolicinaProizvoda = new javax.swing.JTextField();
        txtCijenaProizvoda = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        cmbUsluga = new javax.swing.JComboBox<>();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        txtKolicinaUsluga = new javax.swing.JTextField();
        txtCijenaPoHa = new javax.swing.JTextField();
        jLabel11 = new javax.swing.JLabel();
        cmbKupac = new javax.swing.JComboBox<>();
        jLabel12 = new javax.swing.JLabel();
        txtAdresa = new javax.swing.JTextField();
        jLabel13 = new javax.swing.JLabel();
        txtOib = new javax.swing.JTextField();
        jLabel14 = new javax.swing.JLabel();
        txtIban = new javax.swing.JTextField();
        dtpVrijeme = new com.github.lgooddatepicker.components.DateTimePicker();
        btnWord = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        lstPodaci.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        lstPodaci.addListSelectionListener(new javax.swing.event.ListSelectionListener() {
            public void valueChanged(javax.swing.event.ListSelectionEvent evt) {
                lstPodaciValueChanged(evt);
            }
        });
        jScrollPane1.setViewportView(lstPodaci);

        jLabel1.setText("PDV");

        jLabel2.setText("Opis plačanja");

        txtOpisPlacanja.setHorizontalAlignment(javax.swing.JTextField.LEFT);

        jLabel3.setText("Vrijeme izdavanja");

        btnDodaj.setText("Dodaj");
        btnDodaj.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDodajActionPerformed(evt);
            }
        });

        btnObrisi.setText("Obriši");
        btnObrisi.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnObrisiActionPerformed(evt);
            }
        });

        btnPromjena.setText("Promjeni");
        btnPromjena.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnPromjenaActionPerformed(evt);
            }
        });

        jLabel4.setText("Naziv računa");

        txtNazivRacuna.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtNazivRacunaActionPerformed(evt);
            }
        });

        jLabel5.setText("Proizvod");

        jLabel7.setText("Količina");

        jLabel8.setText("Cijena");

        jLabel6.setText("Usluga");

        jLabel9.setText("Količina");

        jLabel10.setText("Cijena");

        txtKolicinaUsluga.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtKolicinaUslugaActionPerformed(evt);
            }
        });

        jLabel11.setText("Kupac");

        jLabel12.setText("Adresa");

        jLabel13.setText("OIB");

        jLabel14.setText("IBAN");

        btnWord.setText("Word");
        btnWord.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnWordActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 290, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                            .addComponent(btnDodaj, javax.swing.GroupLayout.PREFERRED_SIZE, 86, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(18, 18, 18)
                            .addComponent(btnPromjena)
                            .addGap(26, 26, 26)
                            .addComponent(btnObrisi, javax.swing.GroupLayout.PREFERRED_SIZE, 95, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(txtOpisPlacanja)
                        .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 129, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 138, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(txtPdv))
                    .addComponent(txtNazivRacuna, javax.swing.GroupLayout.PREFERRED_SIZE, 147, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 91, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(dtpVrijeme, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnWord, javax.swing.GroupLayout.PREFERRED_SIZE, 86, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(txtIban)
                    .addComponent(jLabel13, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(txtAdresa)
                    .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 96, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cmbProizvod, 0, 156, Short.MAX_VALUE)
                    .addComponent(jLabel7)
                    .addComponent(jLabel8)
                    .addComponent(txtKolicinaProizvoda)
                    .addComponent(txtCijenaProizvoda)
                    .addComponent(jLabel11, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(cmbKupac, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel12, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(txtOib)
                    .addComponent(jLabel14, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel10)
                    .addComponent(txtKolicinaUsluga, javax.swing.GroupLayout.DEFAULT_SIZE, 156, Short.MAX_VALUE)
                    .addComponent(txtCijenaPoHa, javax.swing.GroupLayout.DEFAULT_SIZE, 156, Short.MAX_VALUE)
                    .addComponent(jLabel9)
                    .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 134, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cmbUsluga, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(96, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel1)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txtPdv, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel3)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(dtpVrijeme, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(13, 13, 13)
                                .addComponent(jLabel4)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txtNazivRacuna, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(10, 10, 10)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel5)
                                    .addComponent(jLabel6))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(cmbProizvod, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(cmbUsluga, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel7)
                                    .addComponent(jLabel9))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(txtKolicinaProizvoda, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(txtKolicinaUsluga, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel8)
                                    .addComponent(jLabel10))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(txtCijenaProizvoda, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(txtCijenaPoHa, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel2)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(txtOpisPlacanja, javax.swing.GroupLayout.PREFERRED_SIZE, 93, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(91, 91, 91)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(btnObrisi, javax.swing.GroupLayout.DEFAULT_SIZE, 37, Short.MAX_VALUE)
                                    .addComponent(btnPromjena, javax.swing.GroupLayout.DEFAULT_SIZE, 37, Short.MAX_VALUE)
                                    .addComponent(btnDodaj, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(33, 33, 33)
                                .addComponent(jLabel11)
                                .addGap(18, 18, 18)
                                .addComponent(cmbKupac, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jLabel12)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txtAdresa, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jLabel13)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txtOib, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jLabel14)))
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(txtIban, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(29, 29, 29)
                                .addComponent(btnWord, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(0, 58, Short.MAX_VALUE)))
                .addContainerGap())
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void lstPodaciValueChanged(javax.swing.event.ListSelectionEvent evt) {//GEN-FIRST:event_lstPodaciValueChanged
        if (evt.getValueIsAdjusting()) {
            return;
        }

        if (lstPodaci.getSelectedValue() == null) {
            return;
        }

        obrada.setEntitet(lstPodaci.getSelectedValue());

        popuniView();


    }//GEN-LAST:event_lstPodaciValueChanged

    private void btnDodajActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDodajActionPerformed

        obrada.setEntitet(new Racun());
        popuniModel();
        try {
            obrada.create();
            ucitaj();
        } catch (RacunOpgException ex) {
            JOptionPane.showMessageDialog(getRootPane(), ex.getPoruka());
        }


    }//GEN-LAST:event_btnDodajActionPerformed

    private void btnObrisiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnObrisiActionPerformed
        if (lstPodaci.getSelectedValue() == null) {
            return;
        }

        var e = lstPodaci.getSelectedValue();

        if (JOptionPane.showConfirmDialog(getRootPane(), e.getNazivRacuna(), "Sigurno obrisati?",
                JOptionPane.YES_NO_OPTION) != JOptionPane.YES_OPTION) {
            return;
        }

        obrada.setEntitet(e);

        try {
            obrada.delete();
            ucitaj();
        } catch (RacunOpgException ex) {
            JOptionPane.showMessageDialog(getRootPane(), ex.getPoruka());
        }


    }//GEN-LAST:event_btnObrisiActionPerformed

    private void btnPromjenaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPromjenaActionPerformed
        if (lstPodaci.getSelectedValue() == null) {
            return;
        }

        var e = lstPodaci.getSelectedValue();

        obrada.setEntitet(e);
        popuniModel();

        try {
            obrada.update();
            ucitaj();
        } catch (RacunOpgException ex) {
            JOptionPane.showMessageDialog(getRootPane(), ex.getMessage());
            obrada.refresh();
        }


    }//GEN-LAST:event_btnPromjenaActionPerformed

    private void txtKolicinaUslugaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtKolicinaUslugaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtKolicinaUslugaActionPerformed

    private void txtNazivRacunaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtNazivRacunaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtNazivRacunaActionPerformed

    private void btnWordActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnWordActionPerformed
        var racun = obrada.getEntitet();

        if (racun == null) {
            return;
        }

        XWPFDocument document = new XWPFDocument();
        XWPFParagraph title = document.createParagraph();
        title.setAlignment(ParagraphAlignment.BOTH.CENTER);
        XWPFRun titleRun = title.createRun();
        titleRun.setText(racun.getNazivRacuna());
        titleRun.setColor("009933");
        titleRun.setBold(true);
        titleRun.setFontFamily("Courier");
        titleRun.setFontSize(20);

        if (racun.getKupac() != null) {
            XWPFParagraph kupacParagraph = document.createParagraph();
            XWPFRun kupacRun = kupacParagraph.createRun();
            kupacRun.setText("Kupac: " + racun.getKupac().getNazivSubjekta());
        }

        List<Proizvod> proizvodi = (List<Proizvod>) racun.getProizvod();

        if (proizvodi != null && !proizvodi.isEmpty()) {
            for (Proizvod p : proizvodi) {
                XWPFParagraph proizvodParagraph = document.createParagraph();
                XWPFRun proizvodRun = proizvodParagraph.createRun();
                proizvodRun.setText("Proizvod: " + p.getNaziv());
            }
        } else {
            XWPFParagraph proizvodParagraph = document.createParagraph();
            XWPFRun proizvodRun = proizvodParagraph.createRun();
            proizvodRun.setText("Nema dostupnih proizvoda.");
        }

        try {
            JFileChooser jfc = new JFileChooser(FileSystemView.getFileSystemView().getHomeDirectory());
            jfc.setFileFilter(new FileNameExtensionFilter("Word datoteka", "docx"));
            int returnValue = jfc.showSaveDialog(getRootPane());

            if (returnValue != JFileChooser.APPROVE_OPTION) {
                return;
            }

            FileOutputStream out = new FileOutputStream(jfc.getSelectedFile());
            document.write(out);
            out.close();
            document.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }//GEN-LAST:event_btnWordActionPerformed
    @Override
    public void popuniModel() {
        var e = obrada.getEntitet();

        e.setNazivRacuna(txtNazivRacuna.getText());
        e.setOpisPlacanja(txtOpisPlacanja.getText());

        try {
            e.setPDV(BigDecimal.valueOf(df.parse(txtPdv.getText()).doubleValue()));
        } catch (Exception ex) {
            e.setPDV(null);
        }

        LocalDate ld = dtpVrijeme.datePicker.getDate();
        LocalTime lt = dtpVrijeme.timePicker.getTime();

        LocalDateTime ldt = LocalDateTime.of(ld, lt);

        e.setVrijemeIzdavanja(Date.from(ldt.atZone(ZoneId.systemDefault()).toInstant()));

        Proizvod selectedProizvod = (Proizvod) cmbProizvod.getSelectedItem();

        Usluga selectedUsluga = (Usluga) cmbUsluga.getSelectedItem();

        Kupac selectedKupac = (Kupac) cmbKupac.getSelectedItem();

        if (selectedKupac != null && selectedKupac.getSifra() != 0) {
            e.setKupac(selectedKupac);
        } else {
            e.setKupac(null);
        }

        if (selectedProizvod != null && selectedProizvod.getSifra() != 0) {
            e.setProizvod(selectedProizvod);
            e.setUsluga(null);
        } else if (selectedUsluga != null && selectedUsluga.getSifra() != 0) {
            e.setProizvod(null);
            e.setUsluga(selectedUsluga);
        } else {
            e.setProizvod(null);
            e.setUsluga(null);
        }

    }

    @Override
    public void popuniView() {
        var e = obrada.getEntitet();

        txtNazivRacuna.setText(e.getNazivRacuna());
        txtOpisPlacanja.setText(e.getOpisPlacanja());

        try {
            txtPdv.setText(String.valueOf(e.getPDV()));
        } catch (Exception ex) {
            txtPdv.setText("");
        }

        if (e.getVrijemeIzdavanja() == null) {
            dtpVrijeme.datePicker.setDate(null);
            dtpVrijeme.timePicker.setTime(null);
        } else {
            LocalDate ld = e.getVrijemeIzdavanja().toInstant()
                    .atZone(ZoneId.systemDefault())
                    .toLocalDate();
            dtpVrijeme.datePicker.setDate(ld);

            LocalTime lt = e.getVrijemeIzdavanja().toInstant()
                    .atZone(ZoneId.systemDefault())
                    .toLocalTime();
            dtpVrijeme.timePicker.setTime(lt);
        }

        Proizvod proizvod = e.getProizvod();
        Usluga usluga = e.getUsluga();
        Kupac kupac = e.getKupac();

        if (proizvod != null) {
            cmbProizvod.setSelectedItem(proizvod);
            txtKolicinaProizvoda.setText(String.valueOf(proizvod.getKolicina()));
            txtCijenaProizvoda.setText(String.valueOf(proizvod.getCijena()));
        } else {
            cmbProizvod.setSelectedIndex(0);
            txtKolicinaProizvoda.setText("");
            txtCijenaProizvoda.setText("");
        }

        if (usluga != null) {
            cmbUsluga.setSelectedItem(usluga);
            txtKolicinaUsluga.setText(String.valueOf(usluga.getKolicina()));
            txtCijenaPoHa.setText(String.valueOf(usluga.getCijenaPoHa()));
        } else {
            cmbUsluga.setSelectedIndex(0);
            txtKolicinaUsluga.setText("");
            txtCijenaPoHa.setText("");
        }

        if (kupac != null) {
            cmbKupac.setSelectedItem(kupac);
            txtAdresa.setText(kupac.getAdresa());
            txtOib.setText(kupac.getOIB());
            txtIban.setText(kupac.getIBAN());
        } else {
            cmbKupac.setSelectedIndex(0);
            txtAdresa.setText("");
            txtOib.setText("");
            txtIban.setText("");
        }

    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnDodaj;
    private javax.swing.JButton btnObrisi;
    private javax.swing.JButton btnPromjena;
    private javax.swing.JButton btnWord;
    private javax.swing.JComboBox<Kupac> cmbKupac;
    private javax.swing.JComboBox<Proizvod> cmbProizvod;
    private javax.swing.JComboBox<Usluga> cmbUsluga;
    private com.github.lgooddatepicker.components.DateTimePicker dtpVrijeme;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JList<Racun> lstPodaci;
    private javax.swing.JTextField txtAdresa;
    private javax.swing.JTextField txtCijenaPoHa;
    private javax.swing.JTextField txtCijenaProizvoda;
    private javax.swing.JTextField txtIban;
    private javax.swing.JTextField txtKolicinaProizvoda;
    private javax.swing.JTextField txtKolicinaUsluga;
    private javax.swing.JTextField txtNazivRacuna;
    private javax.swing.JTextField txtOib;
    private javax.swing.JTextField txtOpisPlacanja;
    private javax.swing.JTextField txtPdv;
    // End of variables declaration//GEN-END:variables

    @Override
    public void ucitaj() {
        DefaultListModel<Racun> m = new DefaultListModel<>();
        m.addAll(obrada.read());
        lstPodaci.setModel(m);
        lstPodaci.repaint();
    }

}
