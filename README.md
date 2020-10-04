# simulointiprojekti

Vaatimukset simulaattorille Projektityössä toteutetaan simulaattori, joka täyttää kaikki seuraavista vaatimuksista: 
1.   Käsitteellisessä mallissa on vähintään 4 palvelupistettä.  
2.   Mallin syötteinä olevia jakaumia pitää voida vaihtaa (ainakin osittain). 
3.   Simulaattorissa on graafinen käyttöliittymä. 
4.   Simuloinnin kulkua voidaan visualisoida tai animoida. 
5.   Ohjelmassa hyödynnetään ulkoisia tietovarastoja. 
6.   Simulaattori toimii käynnistyttyään itsenäisesti tai käyttäjän ohjaamana (visualuisointi-/animointitilassa). 
7.   Ohjelmiston käyttöliittymän on oltava yleisökäyttöön sopiva (fontit, värit jne.), ja käytön täytyy olla helppoa satunnaiskäyttäjälle. 

Tässä projektissa käytetään seuraavia suureita:
•	saapuneiden asiakkaiden lukumäärä (A, arrival count)
  -	valikkoon asti päässeiden asiakkaiden lukumäärä
•	palveltujen asiakkaiden lukumäärä (C, completed count)
  -	asiakas poistuu tyytyväisenä palvelusta
•	palvelupisteen aktiiviaika (B, busy time)
  -	kun asiakkaan toiminta valikosta eteenpäin
•	simuloinnin kokonaisaika(T, time)
  -	simuloinnille asetetaan kokonaisaika
•	palvelupisteen käyttöaste (U, utilization) on käytön suhde kapasiteettiin U= B/T
  -	asiakkaan toiminnan aika suhteessa kokonaisaikaan
•	suoritusteho (X, throughput) on palveltujen asiakkaiden lukumäärä aikayksikössä  X = C/T
  -	kuinka montaa asiakasta ehditään palvella suhteessa kokonaisaikaan 
•	palvelupisteen keskimääräinen palveluaika (S, service time) S = B/C
, kuinka kauan palveluun keskimäärin menee
•	palvelupisteen läpimenoaika, vasteaika (Ri, response time) = aika asiakkaan palvelu-jonoon saapumisesta palvelun päättymiseen (kuvaa sitä, että kyseessä on tietyn asi-akkaan kokema läpimenoaika). 
, sen jälkeen, kun asiakas on valinnut valikosta tietyn palvelun ja häntä on pal-veltu
•	kokonaisoleskeluaika palvelupisteessä (W, waiting time) kaikkien asiakkaiden läpime-noaikojen summa
, kaikkien asiakkaiden läpimenoajat lasketaan yhteen 
•	keskimääräinen läpimenoaika (R, response time) R = W/C
•	keskimääräinen jononpituus (palveltava mukana) N = W/
,palvelupistekohtainen

