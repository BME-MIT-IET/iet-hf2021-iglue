 # Manuális tesztelés
 
## Játékmenet alapfunkciók tesztelése

### Játékos hozzáadása

Teszt terv, játékos hozzáadásának megpróbálása ha:
- a) nincs megadva név
- b) nincs megadva karakter típus
- c) van név és karakter kiválasztva
- d) van már azonos nevű karakter

Tesztek eredménye:

**a)** ha nincs megadva név:

![](img/feladat_3_Manualis_Test/jatekos_hozzaadas1.jpg)

Végeredmény: Nem lett hozzáadva új karakter.

**b)** ha nincs megadva karakter típus

![](img/feladat_3_Manualis_Test/jatekos_hozzaadas2.jpg)

Végeredmény: Nem lett hozzáadva új karakter.

**c)** van név és karakter is kiválasztva

![](img/feladat_3_Manualis_Test/jatekos_hozzaadas3.jpg)

Végeredmény: hozzá lett adva az új játékos/karakter.

**d)** van már azonos nevű játékos

![](img/feladat_3_Manualis_Test/jatekos_hozzaadas4.jpg)
![](img/feladat_3_Manualis_Test/jatekos_hozzaadas5.jpg)

Végeredmény: A második játékos létrehozásakor az első játékos neve eltűnt a lenti listából, helyére az új játékos került, hibáról nem tájékoztatott a program.

### Karakter képességek kipróbálása

Teszt terv:

- a) karakter léptetése
- b) karakter típus képesség használata
- c) ásás
- d) tárgy felvétel
- e) tárgy használat
- f) korai kör befejezés

**a)** karakter léptetése

![](img/feladat_3_Manualis_Test/leptetes1.jpg)
![](img/feladat_3_Manualis_Test/leptetes2.jpg)

Végeredmény: a karakter pozíciója megváltozott. Csökkent az Actual work unit értéke 1-el.

**b)** karakter típus képesség használata

Eskimo:

![](img/feladat_3_Manualis_Test/kepesseg1.jpg)
![](img/feladat_3_Manualis_Test/kepesseg2.jpg)

Researcher:

![](img/feladat_3_Manualis_Test/kepesseg3.jpg)
![](img/feladat_3_Manualis_Test/kepesseg4.jpg)

Végeredmény:
- Eskimo esetében megjelent az eskimo mezőjén egy iglu, csökkent Actual work unit értéke 1-el.
- Researcher esetében a kijlölt mező felett megjelent egy szám, csökkent az Actual work unit értéke 1-el.

**c)** ásás

![](img/feladat_3_Manualis_Test/asas1.jpg)
![](img/feladat_3_Manualis_Test/asas2.jpg)

Végeredmény: Karakter alól eltünt a hó, csökkent Actual work unit értéke 1-el.

**d)** tárgy felvétel
![](img/feladat_3_Manualis_Test/targyfelvetel1.jpg)
![](img/feladat_3_Manualis_Test/targyfelvetel2.jpg)

Végeredmény: Tárgy a karakter alól eltünt, megjelent az Items listában, csökkent Actual work unit értéke 1-el.

**e)** tárgy használat

![](img/feladat_3_Manualis_Test/targyhasznalat1.jpg)
![](img/feladat_3_Manualis_Test/targyhasznalat2.jpg)

Végeredmény: Tárgy elűnt az items listából, Actual work unit értéke csökkent 1-el.

**f)** korai kör befejezés

![](img/feladat_3_Manualis_Test/koraikorveg1.jpg)
![](img/feladat_3_Manualis_Test/koraikorveg2.jpg)

Végeredmémy: A jelenlegi játékos megváltozott egy másik játékosra.

## Játékmenet edge case tesztelés


# Exploratory testing