# Odletová webové aplikace

Spring Boot webová aplikace napsaná v Javě, která zobrazuje seznam odletů z 
vybraného letiště za zvolené časové období. Data se stahují z OpenSky REST 
API a ukládají se do cache pro rychlejší opakované dotazy.

---

### Požadavky
- Java 11 a vyšší
- Maven 3.x
- (volitelně) IDE s podporou Springu, např. IntelliJ IDEA

### Spuštění aplikace
1) Naklonujte nebo rozbalte projekt do složky
2) V terminálu přejděte do kořenového adresáře projektu(kde je pom.xml)
3) Spustě příkaz mvn spring-boot:run
4) V prohlížeči otevřete url: http://localhost:8080 (Zde poběží uvedená webová aplikace)

### Použití a funkcionality programu
- Do pole <b> ICAO kód letiště </b> napište například <i> LKPR </i> (což je kod pražského letiště)
- Vyberte časové rozmezí pomocí kalendářních pickerů, ale musíte splnit podmínky - Interval nesmí být delší než 7 dní, a to z důvodu, že tato funkcionalita není povolena
na neregistrovaném účtu OpenSky, což je tento případ.
- Začátek nesmí být v budoucnosti ani starší než pet let - poté klikněte na tlačítko <b> hledej </b>
- Zde v druhém okně /departures uvidíte html tabulku s odlety letadel
u uvedeného výpisu máme zapnuté cachování, takže jestliže uživatel v následující hodině od hodnoty hledání 
bude hledat pomocí stejných parametrů, tak aplikace nebude volat api OpenSky, ale výsledek vypíše z cache paměti.