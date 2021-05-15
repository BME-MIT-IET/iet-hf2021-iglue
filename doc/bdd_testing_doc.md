# BDD tesztek
A BDD (behavior-driven development) tesztek célja annak az ellenőrzése, hogy a programmal szemben támasztott funkcionális elvárások teljesülnek-e.
BBD teszteléskor nem a program egységeit vizsgáljuk, hanem azt, hogy bizonyos szcenáriók a specifikációba foglaltaknak megfelelően futnak-e le,
az egyes funkciók az elvártaknak megfelelően működnek-e.
## A választott eszköz
A teszteket a Cucumber segítségével készítettem el.
## Tesztelt feature-ök
A projekt mérete miatt nem volt lehetőség a teljes specifikáció BDD tesztekkel történő ellenőrzésére, így bizonyos, kiválasztott elemek kerültek csak ellenőrzésre,
ezeket viszont igyekeztem kellő alapossággal és részletességgel letesztelni, így a tesztelt részekben találtam is olyan hibákat, melyekben az implementált viselkedés eltért a specifikációban rögzítettektől.
### Jégtáblák
A jégtáblákról a következőképpen fogalmaz a specifikáció:
> Vannak stabil jégtáblák, amelyeken akárhány szereplő állhat, és vannak instabil jégtáblák, amik adott létszám felett átfordulnak és ilyenkor a rajtuk állók a vízbe esnek. Jégtábla átfordulásnál az adott jégtáblán lévő hóréteg eltűnik.

Ezt két feature-re bontottam, a stabil és instabil jégtáblák esetére. Mindkét feature teszt java implementációját a `test/java/IceFieldTests.java` fájlban valósítottam meg.
#### Stabil jégtáblák
Szcenárió: A stabil jégtáblákra, ha irreálisan sok szereplőt helyezünk el, akkor sem lesz a szereplők egyike sem vízben.

A feature Gherkin leírása a `test/resources/features/StableField.feature` fájlban található.
#### Instabil jégtáblák
1. Szcenárió: Ha egy instabil jégtáblára annyi szereplő kerül, ami nem haladja meg a tábla teherbírását egyik szereplő sem kerül vízbe.
2. Szcenárió: Ha egy instabil jégtáblát túlterhelünk az összes rajta álló szereplő vízbe kerül és a táblán található hóréteg 0 kell legyen.

A feature Gherkin leírása a `test/resources/features/InstableField.feature` fájlban található.
#### Felfedezett hiba
A tesztelés során fény derült arra, hogy az instabil jégtábla felfordulásakor nem tűnik el a hó. Ezt jeleztem a hibák javításán dolgozó csapattársaimnak, akik elhárították a hibát.

### Szereplők megmentése kötéllel
> A vízbe esett szereplők egy köteles szomszédjuk által menthetők meg. Kötéllel egy munkaegység ráfordításával egy szereplő menthető meg.
> A kimentett szereplő arra a jégtáblára kerül, amelyiken az őt kimentő szereplő tartózkodik. Ha egy szereplő egy instabil jégtáblára lépett, és a jégtábla átfordult

Fenti leírást egy feature-ként kezeltem, megvalósítás a `RescueWithRope.feature` és `RescueWithRopeTests.java` fájlokban.

1. Szcenárió: ha egy szereplő vízbe esik és a segítő szomszédos mezőn van, akkor a kimentésre irányuló próbálkozás hatására mind a ketten a megmentő szereplő mezőjére kerülnek és a megmentőnek eggyel csökken a munkaegységeinek száma.
2. Szcenárió: ha a segítő szereplő nem szomszédos mezőn tartózkodik a bajba jutott nem kerül kimenekítésre és a megmentő munkaegysége sem csökken
3. Szcenárió: ha a segítő egy szomszédos instabil jégtáblán áll, mely azzal haladja túl limitjét, hogy rákerül a megmentett személy, a folyamat végén mindkét szereplőnek vízben kell lennie. #szívás

*Megjegyzés: A kötél meglétének ellenőrzése nem szükséges, mivel modellből adódóan a megmentési próbálkozás a `Rope.Use()` függvény használatával kezdődik, így az mindenképpen feltétel.*

#### Felfedezett hiba
A tesztelés során fény derült arra, hogy nem szomszédos jégtábláról is lehet kötéllel menteni. Ezt jeleztem a hibák javításán dolgozó csapattársaimnak, akik elhárították a hibát.

*Funfact: a hiba oka egy ÉS helyetti VAGY volt az if elágazás feltételében*
### Jégbe fagyott tárgyak
> Az egyes jégtáblákba különféle tárgyak lehetnek belefagyva: lapát, kötél, búvárruha, élelem, pisztoly.
> Egy jégtáblában maximum egy tárgy lehet. Befagyott tárgyat csak akkor lehet meglátni és kiásni, ha a jégtábla tiszta,
> nem borítja hó és ekkor látszik, hogy milyen tárgy van a jégtáblában.

A fent leírtakat három feature-re bontottam, ezek `FrozenItems.feature`, a `GettingFrozenItem.feature` és a `OneForzenItemPerBlock.feature` fájlokba kerültek.
Az ezek mögötti java implementációt a `FrozenItemsTests.java` fájl tartalmazza.
#### Különféle tárgyak
Egy-egy szcenárióban leteszteltem, hogy lapát, kötél, búvárruha és élelem is kiásható egy jégtáblából.
#### Mezőnként egy tárgy
Ha egy mezőbe volt A tárgy és a mezőbe B tárgy kerül, akkor A tárgy nem lehet a mezőben, B tárgynak pedig a mezőben kell lennie
#### Tárgyak kiásása
1. Szcenárió: ha olyan tárgyat próbálnak kiásni, ami nem látható, mert a mezőt hó borítja, akkor a kiásás sikertelen kell legyen
2. Szcenárió: ha először eltakarításra kerül a hó a befagyott tárgy megszerezhető.
3. Szcenárió: egy befagyott tárgy kiásása egy munkaegységet igényel.
## Összegzés
Bár az összes funkcionalitás csak egy kis részhalmazát sikerült letesztelnem, így is fedeztem fel hibákat a projlab során egyéb módszerekkel már letesztelt projektben. Ez a BDD tesztek létjogosultságát bizonyítja.

A BDD tesztek felvétele a CI pipeline-hoz lehetővé teszi, hogy a későbbi fejlesztések során ne kerülhessen a projektbe olyan kód, mely a specifikációtól való eltérést okozná.

Mindezen felül a feladat végrehajtása során megismerkedhettem egy számomra új tesztelési technikával, amelyet a későbbiekben remélhetőleg hasznosítani fogok tudni.

Fentiek alapján kijelenthető, hogy az elvégzett munkám több szempontból is hasznosnak tekinthető, mind a projekt, mind jómagam fejlődéséhez hozzájárult. :)

