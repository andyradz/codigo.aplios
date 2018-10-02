package com.codigo.aplios.domain.model.evidence.pesel;

import com.codigo.aplios.domain.model.identity.PeselIdentity;

public class PeselData {

    private PeselIdentity pesel;

    public void setPesel(PeselIdentity pesel) {

        this.pesel = pesel;
    }

    public PeselIdentity getPesel() {

        return this.pesel;
    }

}
