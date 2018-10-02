package com.codigo.aplios.domain.model.evidence;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class CreditCard {

    //@Convert(converter = CryptoConverter.class)
    @Column(name = "ccNumber")
    private String ccNumber;

    public String getCcNumber() {

        return this.ccNumber;
    }

    public void setCcNumber(String ccNumber) {

        this.ccNumber = ccNumber;
    }

}
