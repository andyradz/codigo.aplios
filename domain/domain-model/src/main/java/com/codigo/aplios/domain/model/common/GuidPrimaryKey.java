package com.codigo.aplios.domain.model.common;

import java.util.Objects;
import java.util.UUID;
import javax.persistence.Embeddable;

/**
 *
 * @author andrzej.radziszewski
 */
@Embeddable
class GuidPrimaryKey {

    /**
     * Atrybut obiektu określa globalny i unikalny identyfikator encji w bazie danych
     */
    private final UUID keyGuidId;

    /**
     * Podstawowy konstruktor obiektu klasy <code>GuidPrimaryKey</code>
     */
    public GuidPrimaryKey() {

        this.keyGuidId = UUID.randomUUID();
    }

    /**
     * Właściwość pobiera unikalny identyfikator encji z bazie danych
     *
     * @return Ciąg znaków, identyfikator GUID
     */
    public UUID getKeyId() {

        return this.keyGuidId;
    }

    /**
     * Metoda wyznacza identyfikator HashCode obiektu
     *
     * @return Wartość numeryczna, identyfikator HashCode obiektu
     */
    @Override
    public int hashCode() {

        return Objects.hash(this.keyGuidId);
    }

    /**
     * Metoda wykonuje porównanie dwóch obiektów klasy <code>GuidPrimaryKey</code>
     *
     * @param instance Obiekt do porównania z bieżącym obiektem
     * @return Wartość logiczna,
     *         <pre>Wartość True - obiekty są jednoznaczne, False - obiekty są niejednoznaczne</pre>
     */
    @Override
    public boolean equals(Object instance) {

        if (this == instance)
            return true;

        if (Objects.isNull(instance) || (this.getClass() != instance.getClass()))
            return false;

        final GuidPrimaryKey primaryKey = GuidPrimaryKey.class.cast(instance);

        return (this.keyGuidId.equals(primaryKey.keyGuidId));
    }

    /**
     * Metoda przedstawia reprezentację tekstową obiektu klasy <code>GuidPrimaryKey</code>
     *
     * @return Ciąg znaków, reprezentacja tekstowa obiektu
     */
    @Override
    public String toString() {

        return "GuidPrimaryKey[id]->" +
                this.getKeyId();
    }
}
