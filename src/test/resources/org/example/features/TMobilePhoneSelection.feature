Feature: Wybranie telefonu z listy ofert

  Scenario: Wybranie telefonu z listy ofert
    Given Otwórz odpowiednią przeglądarkę
    When Przejdź na stronę "https://www.t-mobile.pl/"
    And Z górnej belki wybierz Urządzenia
    And Kliknij "Bez abonamentu" z kolumny "Smartwatche i opaski"
    And Kliknij w pierwszy element z listy
    And Dodaj produkt do koszyka
    Then Przejdź na stronę główną T-Mobile i sprawdz ze w koszyku jest 1 element
