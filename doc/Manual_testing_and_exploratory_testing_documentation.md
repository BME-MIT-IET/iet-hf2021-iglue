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

Leírás: Player's name-hez tartozó textbox üres, Character típus választónál Eskimo kiválasztva.

![](img/feladat_3_Manualis_Test/jatekos_hozzaadas1.jpg)

Végeredmény: Nem lett hozzáadva új karakter.

**b)** ha nincs megadva karakter típus

Leírás: Player's name-hez tartozó textbox tartalmaz valami stringet, Character típus választónál nincs semmi kiválasztva.

![](img/feladat_3_Manualis_Test/jatekos_hozzaadas2.jpg)

Végeredmény: Nem lett hozzáadva új karakter.

**c)** van név és karakter is kiválasztva

Leírás: Player's name-hez tartozó textbox tartalmaz valami stringet, Character típus választónál Eskimo kiválasztva.

![](img/feladat_3_Manualis_Test/jatekos_hozzaadas3.jpg)

Végeredmény: hozzá lett adva az új játékos/karakter.

**d)** van már azonos nevű játékos

![](img/feladat_3_Manualis_Test/jatekos_hozzaadas4.jpg)
![](img/feladat_3_Manualis_Test/jatekos_hozzaadas5.jpg)

!! Lehetséges probléma !!

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

Leírás: Iránymutató (Left, Up, Right, Down) kiválasztása után Step gomb megnyomása.

![](img/feladat_3_Manualis_Test/leptetes1.jpg)
![](img/feladat_3_Manualis_Test/leptetes2.jpg)

Végeredmény: a karakter pozíciója megváltozott. Csökkent az Actual work unit értéke 1-el.

**b)** karakter típus képesség használata

Eskimo:

Leírás: Use Ability gomb megnyomása.

![](img/feladat_3_Manualis_Test/kepesseg1.jpg)
![](img/feladat_3_Manualis_Test/kepesseg2.jpg)

Researcher:

Leírás: Iránymutató (Left, Up, Right, Down) megnyomása után Use Ability gomb megnyomása.

![](img/feladat_3_Manualis_Test/kepesseg3.jpg)
![](img/feladat_3_Manualis_Test/kepesseg4.jpg)

Végeredmény:
- Eskimo esetében megjelent az eskimo mezőjén egy iglu, csökkent Actual work unit értéke 1-el.
- Researcher esetében a kijlölt mező felett megjelent egy szám, csökkent az Actual work unit értéke 1-el.

**c)** ásás

Leírás: Dig gomb megnyomása.

![](img/feladat_3_Manualis_Test/asas1.jpg)
![](img/feladat_3_Manualis_Test/asas2.jpg)

Végeredmény: Karakter alól eltünt a hó, csökkent Actual work unit értéke 1-el.

**d)** tárgy felvétel

Leírás: Karakter olyan mező felett áll, ahol van tárgy és ki van ásva. Pick Up Item gomb megnyomása.

![](img/feladat_3_Manualis_Test/targyfelvetel1.jpg)
![](img/feladat_3_Manualis_Test/targyfelvetel2.jpg)

Végeredmény: Tárgy a karakter alól eltünt, megjelent az Items listában, csökkent Actual work unit értéke 1-el.

**e)** tárgy használat

Leírás: karakternél van Food tárgy, ki van választva az Items listából. Use Item gomb megnyomása.

![](img/feladat_3_Manualis_Test/targyhasznalat1.jpg)
![](img/feladat_3_Manualis_Test/targyhasznalat2.jpg)

Végeredmény: Tárgy elűnt az items listából, Actual work unit értéke csökkent 1-el.

**f)** korai kör befejezés

Leírás: End turn gomb megnyomása.

![](img/feladat_3_Manualis_Test/koraikorveg1.jpg)
![](img/feladat_3_Manualis_Test/koraikorveg2.jpg)

Végeredmémy: A jelenlegi játékos megváltozott egy másik játékosra.

## Játékmenet edge case tesztelés

### Karakter képességek

Teszt terv:
- a) Úgy ásni, hogy a karakter alatt már nincs semmi ásható
- b) Pálya szélén kifele lépni
- c) Több ugyan olyan tárgy felvétele
- d) Játékos használ egy swimsuit-ot, majd még 1 swimsuit-ot

**a)** úgy ásni, hogy a karakter alatt már nincs semmi ásható

![](img/feladat_3_Manualis_Test/tobbszoriasas1.jpg)
![](img/feladat_3_Manualis_Test/tobbszoriasas2.jpg)
![](img/feladat_3_Manualis_Test/tobbszoriasas3.jpg)

!! Lehetséges probléma !!

Végeredmény: Actual work unit minden ásásnál csökken, pedig nincs mit ásni.

**b)** Pálya szélén kifele lépni

![](img/feladat_3_Manualis_Test/borderlepes1.jpg)
![](img/feladat_3_Manualis_Test/borderlepes2.jpg)
![](img/feladat_3_Manualis_Test/borderlepes3.jpg)

Végeredmény: Pálya szélén állva kifele nem lehetett lépni, nem csökkent az Actual work unit, 
de a széle mentén lehetett lépni, ahol Actual work unit csökkent.

**c)** Több ugyan olyan tárgy felvétele

![](img/feladat_3_Manualis_Test/ugyanolyantargy1.jpg)
![](img/feladat_3_Manualis_Test/ugyanolyantargy2.jpg)

Végeredmény: két ugyan olyan tárgy lesz az items listában.

**d)** Játékos használ egy swimsuit-ot, majd még 1 swimsuit-ot

![](img/feladat_3_Manualis_Test/swimsuit1.jpg)
![](img/feladat_3_Manualis_Test/swimsuit2.jpg)
![](img/feladat_3_Manualis_Test/swimsuit3.jpg)

!! Lehetséges probléma !!

Végeredmény: Első és második swimsuit használatnál sem történt semmi, csak az Actual work unit értéke csökkent 1-el.


# Exploratory testing

Megjegyzés: Az alábbi esetek mind lehetséges problémát jelentenek funkcinalitás szempontjából.

## Játékos lista

Karakterek létrehozásának sorrendje:

1. Jatekos - Eskimo
2. Jatekos1 - Eskimo
3. Jatekos2 - Researcher
4. Jatek - Researcher
5. Jatek2 - Eskimo

Játékosok listájának sorrendje:

- Jatekos - Eskimo (1.)
- Jatek - Researcher (4.) 
- Jatekos2 - Researcher (3.)
- Jatek2 - Eskimo (5.)
- Jatekos1 - Eskimo (2.)

![](img/feladat_3_Manualis_Test/tobbkarakter1.jpg)
![](img/feladat_3_Manualis_Test/tobbkarakter2.jpg)
![](img/feladat_3_Manualis_Test/tobbkarakter3.jpg)
![](img/feladat_3_Manualis_Test/tobbkarakter4.jpg)

Jól látható, hogy a sorrend felcserélődött.

## Játékmenet

Előző inputtal elindítva a játékot:

A játékosok körének sorrendje megegyezik a játékosok listáján lévő sorrenddel.


![](img/feladat_3_Manualis_Test/JatekmenetTobb1.jpg)
![](img/feladat_3_Manualis_Test/JatekmenetTobb2.jpg)

## Food item használat

![](img/feladat_3_Manualis_Test/husHasznalat1.jpg)
![](img/feladat_3_Manualis_Test/husHasznalat2.jpg)

A food akkor is elhasználható, ha a játékosnak maximum élete van, tehát el tudja pazarolni.

## Ásás

![](img/feladat_3_Manualis_Test/asas1Hasznalat.jpg)
![](img/feladat_3_Manualis_Test/asas2Hasznalat.jpg)

Akkor is lehet ásni, amikor már nincs semmi a játékos alatt és az is csökkenti az Actual work unitot, feleslegesen.

## Kilépés vízből

![](img/feladat_3_Manualis_Test/vizes1.jpg)
![](img/feladat_3_Manualis_Test/vizes2.jpg)

Miután egy játékos kilépett a vízből, a karaktere utána is vizesnek néz ki, mintha vízben lenne.

##  Vasteg rétegen ásás

![](img/feladat_3_Manualis_Test/hoasas1.jpg)
![](img/feladat_3_Manualis_Test/hoasas2.jpg)

A képeken jól látható, hogy egy 4-es van a jobb felső sarokban, tehát 4 réteg vastag hó van a mezőn, az 1. és 2. kép között  1 dig művelet volt, de a hó eltünt, ráadásul még a szám sem csökkent pedig már minimum 1-el kisebb lett a hóréteg.

## Game over start

Tesztelés közben megesett, hogy játé elindítása után rögtön Game over képernyő jelent meg.