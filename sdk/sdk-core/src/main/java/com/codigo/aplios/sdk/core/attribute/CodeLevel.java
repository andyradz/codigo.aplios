package com.codigo.aplios.sdk.core.attribute;

/**
 * Klasa typu wyliczeniowego określającego typy wydań aplikacji inaczej cykl
 * życia wydań aplikacji
 *
 * @author andrzej.radziszewski
 * @version 1.0.0.0
 * @since 2017
 * @category marker
 */
public enum CodeLevel {

    /**
     * Wersja niestabilna (testowa) – seria wydań, podczas której dodawane są przede
     * wszystkim nowe możliwości
     */
    DEVELOP,
    /**
     * Wersja robocza (pre-alpha) – dostępna zazwyczaj tylko dla twórców programu w
     * postaci repozytorium kodu źródłowego (np. CVS, SVN, GIT), kiedy
     * implementowany jest algorytm programu, tworzony jest interfejs i dodawane są
     * nowe funkcje;
     */
    PRE_ALPHA,
    /**
     * Wersja alfa (pre-beta) – autorzy doprowadzają do rzeczywistego działania
     * programu, nawet w ograniczonym zakresie
     */
    PRE_BETA,
    /**
     * Wersja beta – kiedy program ma już pierwszych użytkowników, zwanych często
     * beta testerami, wyłapywane są błędy związane z różnymi środowiskami i
     * warunkami pracy programu
     */
    BETA,
    /**
     * RC (ang. Release Candidate, czyli kandydat do wydania) – wydanie kandydujące,
     * których może być nawet kilka, ale jeżeli nie zostanie w nim znalezione żadne
     * istotne odstępstwo od planu wersji, zmienia się jedynie numer wersji na
     * wyższy i uznaje wersję za stabilną.
     */
    RELEASE_CANDIDATE,
    /**
     * RTM (ang. Release To Manufacture, Ready To Manufacture lub Ready To Market
     * czyli gotowy do wydania) – produkt uznany za stabilny i gotowy do
     * wypuszczenia na rynek; nie jest dostępny publicznie do czasu premiery;
     */
    RTM

}
