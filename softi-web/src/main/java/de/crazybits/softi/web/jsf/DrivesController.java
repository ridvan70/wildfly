/*
 * CommerceBay GmbH
 *
 * Copyright (c) 2015 CommerceBay GmbH. All Rights Reserved
 *
 */
package de.crazybits.softi.web.jsf;

import de.crazybits.softi.model.Account;
import de.crazybits.softi.model.Currency;
import de.crazybits.softi.model.InOut;
import de.crazybits.softi.web.ejb.AccountFacade;
import de.crazybits.softi.web.ejb.InOutFacade;
import de.crazybits.softi.web.jsf.util.JsfUtil;
import java.io.IOException;
import java.io.InputStream;
import java.io.Serializable;
import java.net.MalformedURLException;
import java.net.URL;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.HashSet;
import java.util.Iterator;
import java.util.ResourceBundle;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;
import javax.json.Json;
import javax.json.JsonObject;
import javax.json.JsonReader;

/**
 * <code>DrivesController</code> intends to ...
 *
 * @version 1.0 20.09.2015
 * @author <a href="mailto:ridvan.agar@commercebay.de">Rıdvan Ağar</a>
 */
@Named("drivesController")
@SessionScoped
public class DrivesController implements Serializable {

    @EJB
    private de.crazybits.softi.web.ejb.InOutFacade ejbFacade;
    @EJB
    private AccountFacade ejbAccountFacade;

    private InOut selected;
    private Date toDate;
    private SimpleDateFormat df = new SimpleDateFormat("dd.MM.yyyy");

    public DrivesController() {
    }

    public Date getToDate() {
        return toDate;
    }

    public void setToDate(Date toDate) {
        this.toDate = toDate;
    }

    public InOut getSelected() {
        return selected;
    }

    public void setSelected(InOut selected) {
        this.selected = selected;
    }

    public void create() {
        try {
            persist(JsfUtil.PersistAction.CREATE, ResourceBundle.getBundle("/Bundle").getString("InOutCreated"));
        } catch (Exception ex) {
            Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, null, ex);
            JsfUtil.addErrorMessage(ex, ResourceBundle.getBundle("/Bundle").getString("PersistenceErrorOccured"));
        }

    }

    public InOut prepareCreate() {
        try {
            selected = new InOut();
            selected.setIoCurrency(new Currency("EUR"));
            selected.setIoTimestamp(new Date());
            selected.setIoVat(0);
            selected.setIoDate(df.parse("01.01.2015"));
            toDate = (df.parse("28.02.2015"));
            selected.setIoDesc("Fahrt nach CB Hochheim-FFM 70 Km.");
            selected.setIoOut(21f);
            selected.setIoAccount(getFahrtkostenAccount());
            initializeEmbeddableKey();
            return selected;
        } catch (ParseException ex) {
            Logger.getLogger(DrivesController.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    protected void initializeEmbeddableKey() {
    }

    private void persist(JsfUtil.PersistAction persistAction, String successMessage) throws MalformedURLException, IOException, ParseException {

        LocalDate start = selected.getIoDate().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
        LocalDate end = toDate.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
        Set<LocalDate> holidays;

        if (start.getYear() == end.getYear()) {
            holidays = getHolidays(start.getYear());
        } else {
            holidays = getHolidays(start.getYear(), end.getYear());
        }
        int i = 0;

        for (LocalDate date = start; !date.isAfter(end); date = date.plusDays(1)) {
            if ((date.getDayOfWeek() == DayOfWeek.WEDNESDAY
                    || date.getDayOfWeek() == DayOfWeek.FRIDAY
                    || date.getDayOfWeek() == DayOfWeek.MONDAY) 
                    && !holidays.contains(date)) {
                
                i++;
                
                InOut fahrt=new InOut();
                
                fahrt.setIoAccount(selected.getIoAccount());
                fahrt.setIoCurrency(selected.getIoCurrency());
                fahrt.setIoDate(Date.from(date.atStartOfDay(ZoneId.systemDefault()).toInstant()));
                fahrt.setIoDesc(selected.getIoDesc());
                fahrt.setIoOut(selected.getIoOut());
                fahrt.setIoPaymentType(selected.getIoPaymentType());
                fahrt.setIoTimestamp(selected.getIoTimestamp());
                fahrt.setIoVat(0);
                
                getFacade().edit(fahrt);
                //System.out.println(++i + ". " + date + " ist ein " + date.getDayOfWeek().getDisplayName(TextStyle.FULL, Locale.GERMAN));
            }
        }

        System.out.println(i+" Drive Tage, Tatiller: " + holidays);

        JsfUtil.addSuccessMessage("*** "+i+" Drive Tage *** Tatiller: " + holidays);
        JsfUtil.addSuccessMessage("Von Datum:" + selected.getIoDate());
        JsfUtil.addSuccessMessage("To Datum:" + toDate);
    }

    private Account getFahrtkostenAccount(){
        return ejbAccountFacade.find("Fahrtkosten");
    }
    
    private InOutFacade getFacade() {
        return ejbFacade;
    }

    protected void setEmbeddableKeys() {
    }

    private Set<LocalDate> getHolidays(int... years) throws MalformedURLException, IOException, ParseException {
        HashSet<LocalDate> ret = new HashSet<>();
        SimpleDateFormat df=new SimpleDateFormat("yyyy-MM-dd");
        
        for (int i = 0; i < years.length; i++) {
            int year = years[i];
            URL url = new URL("http://feiertage.jarmedia.de/api/?nur_land=HE&jahr=" + year);
            InputStream is = url.openStream();
            JsonReader rdr = Json.createReader(is);

            JsonObject results = rdr.readObject();
            System.out.println("** " + results);
            Set<String> keySet = results.keySet();

            for (Iterator<String> iterator = keySet.iterator(); iterator.hasNext();) {
                String next = iterator.next();
                ret.add(df.parse(results.getJsonObject(next).getString("datum")).toInstant().atZone(ZoneId.systemDefault()).toLocalDate());
                System.out.println(results.getJsonObject(next).getString("datum")+": " + next);
                
            }
        }
        return ret;
    }

}
