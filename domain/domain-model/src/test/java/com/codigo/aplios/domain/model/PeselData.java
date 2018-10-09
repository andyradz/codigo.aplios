package com.codigo.aplios.domain.model;

import com.codigo.aplios.domain.model.identity.PeselIdentity;

public class PeselData {

	private PeselIdentity pesel;

	public void setPesel(final PeselIdentity pesel) {
		this.pesel = pesel;
	}

	public PeselIdentity getPesel() {
		return this.pesel;
	}
}
