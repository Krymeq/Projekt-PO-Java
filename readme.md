
KRÓTKA INSTRUKCJA OBS£UGI PROJEKTU

Repozytorium zawiera folder z gotowym projektem wykonanym w
NetBeans który mo¿na zaimportowaæ, a tak¿e folder src - z surowym
kodem aplikacji (g³ówna logika aplikacji zawiera siê w klasach 
w src/classes).

Po uruchomieniu w oknie mo¿emy zarz¹dzaæ trzema zbiorami obiektów:

1) Produktami (serialami, filmami, streamami)
2) Klientami
3) Dystrybutorami

Dwukrotne klikniêcie na dowoln¹ pozycjê na liœcie wyœwietli 
nowe okno z informacjami o wybranym obiekcie. Dzia³a to
zarówno dla produktów jak i klientów oraz dystrybutorów.
Mo¿na w ten sposób podejrzeæ dane nt. danego obiektu, a tak¿e
zmieniæ cenê wybranego produktu.

Przefiltrowanie listy polega na wpisaniu w pole tekstowe 
ci¹gu znaku wed³ug którego chcielibyœmy przefiltrowaæ
produkty i zaznaczeniu odpowiedniej opcji ("Aktorzy" b¹dŸ
"Nazwa"). Po wciœniêciu przycisku "Wyszukaj" w nowym oknie
wyœwietl¹ nam siê wyniki filtrowania. W tym oknie mo¿na równie¿
przejrzeæ informacje o wybranym produkcie poprzez dwukrotne
klikniêcie, analogicznie do g³ównego okna aplikacji.

W zak³adce "Ustawienia" mo¿emy dostosowaæ ceny trzech rodzajów
subskrypcji dostêpnych dla klientów (poza darmow¹ subskrypcj¹, 
która zawsze bêdzie...Darmowa) a tak¿e zresetowaæ ca³¹ symulacjê.

Mimo, ¿e czas bêdzie up³ywaæ, do automatycznego pojawiania siê 
klientów wymagana jest obecnoœæ jakiegokolwiek produktu, a do 
automatycznego wypuszczania produktów - obecnoœæ jakiegokolwiek
dystrybutora. St¹d, aby symulacja mog³a ruszyæ nale¿y rêcznie
stworzyæ dystrybutora, który zacznie wypuszczaæ automatycznie 
produkty.