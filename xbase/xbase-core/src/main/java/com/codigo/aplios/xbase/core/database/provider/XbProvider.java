package com.codigo.aplios.xbase.core.database.provider;

/*
 * Klasa ma realizować odczyt danych i zapis danych z pliku na podsatwie przekazanej struktury
 */
public class XbProvider {

    // odczytaj nagłówek
    // zapisz nagłówek (w metodzie otrzymuje blok danych)
    // odczytaj dane(ilośc wierszy, stronnicowanie)
    // blokuje nagłowek pliku
    // odblokuj nagłowek pliku
    // zbiera informacje o odczytach i zapisach
    // na bazie otrzymanych specyfikacji wiec co jak czytać (Database.getProvider(XbVersion.DBASE3))
    // ma referencje do połaczenia XbConnection, które zarządza połączeniem do zbioru danych
    // zapis, odczyt lub zapis odczyt
    // zapis w transakcji
    public static void main(final String[] args) {

        int counter = 0;
        for (int idx = 100; idx <= 999; counter += (idx / 100) == 1 ? 1 : 0, idx++)
			;

        // if ((idx / 100) == 1) counter++;
        System.out.println(counter);

        // privKey = keypair.getPrivate();
        // pubKey = keypair.getPublic();
    }

}
