package com.codigo.aplios.domain.model.stores;

import java.io.Serializable;

import com.codigo.aplios.domain.model.contacts.Address;
import com.codigo.aplios.domain.model.locale.Currency;

public interface IStore extends Serializable {

	String getName();

	void setName(String name);

	String getDescription();

	void setDescription(String description);

	String getStoreNumber();

	void setStoreNumber(String storeNumber);

	Boolean getOpen();

	void setOpen(Boolean open);

	String getStoreHours();

	void setStoreHours(String storeHours);

	Address getAddress();

	void setAddress(Address address);

	Double getLongitude();

	void setLongitude(Double longitude);

	Double getLatitude();

	void setLatitude(Double latitude);

	void setPrimaryStoreCurrency(Currency currency);

	Currency getPrimaryStoreCurrency();

	void setPrimaryExchangeRateCurrency(Currency currency);

	Currency getPrimaryExchangeRateCurrency();
}
