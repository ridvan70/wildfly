/*
 * CommerceBay GmbH
 *
 * Copyright (c) 2015 CommerceBay GmbH. All Rights Reserved
 *
 */

package de.crazybits.softi.web.ejb;

import de.crazybits.softi.model.Currency;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 * <code>CurrencyFacade</code> intends to ...
 *
 * @version 1.0 03.04.2015
 * @author <a href="mailto:ridvan.agar@commercebay.de">Rıdvan Ağar</a>
 */
@Stateless
public class CurrencyFacade extends AbstractFacade<Currency> {
    @PersistenceContext(unitName = "de.crazybits.softi_softi-web_war_1.0-SNAPSHOTPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public CurrencyFacade() {
        super(Currency.class);
    }

}
