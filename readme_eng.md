
BRIEF PROJECT GUIDE

Repository contains ProjektPO-Java directory with full NetBeans project ready
to import. Plain app code can be found in ProjektPO-Java/src/classes.

While launched, main window allows us to manage three sets of objects:

1) Products (series, movies and streams)
2) Clients
3) Distributors

Double click on any object on a list will open a new window with its info.
It works for any kind of object - either for products, clients or
distributors. It allows peeking given object's data and manually change 
price of any product.

Program also allows us to filter products by putting any substring into a
textfield and then clicking "Search" ("Wyszukaj"). Products can be filtered
by name or cast, by choosing related option from buttons below "Search"
button. Search results will be displayed in another window, which also
provide info about chosen object in similar way as the main window does.

In the "Settings" ("Ustawienia") tab we can adjust every subscription price
(apart from "Free", which is... free :D) or reset the whole simulation.

Despite the flow of time, to auto-generate clients there should be at least
one product in the system. Since products appear only when any distributor
is present, to actually start a simulation we need to add at least
one distributor manually (they do not appear automatically).