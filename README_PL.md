![logo](app/src/main/res/mipmap-xxxhdpi/ic_launcher_round.png "Logo")
# FOOD DECIDER

Aplikacja powstała podczas kursu nauki Kotlina.<br/>
Na kursie powstała wersja z możliwością dodawania potrawy/restauracji do listy. Następnie z utworzonej listy użytkownik losował pozycje.<br/>
Po wyłączeniu aplikacji dane były tracone. Pomysł na aplikację bardzo mi się spodobał, więc postanowiłam aplikację te nieco ubogacić.<br/>
W ten sposób powstała obecna wersja aplikacji. Jeśli masz pomysł jak ją ulepszyć daj znać.<br/>
Aplikacje możesz pobrać na urzadzenie z androidem i zainstalować ją przez plik apk znajdujący się na gałęzi release.<br/>
Jeśli nie wiesz jak zainstalować aplikacje z pliku APK zajrzyj pod ten[link](https://developer.android.com/distribute/marketing-tools/alternative-distribution#unknown-sources).
## Technologie

* Kotlin
* Shared Preferences
* GSON

## Funkcjonalności
### Dodawanie nowych potraw/restauracji
Aby dodać nową potrawę należy udać się do okna starowego i w polu tekstowym (zaznaczenie 1)
wpisać nazwę <b><u>NIE</u></b> istniejącą w spisie potraw/restauracji. Następnie użytkownik musi kliknąć w przycisk poniżej (zaznaczenie 2).
<br/>Jeśli dana potrawa lub restauracja nie istnieje w spisie zostanie ona dodana. W innym przypadku wyświetlony zostanie stosowny komunikat.

<div >
<img src="https://user-images.githubusercontent.com/33415084/83849847-5411f600-a710-11ea-8904-e9bd75b958ec.png" width="200px" height="350px" />
<img src="https://user-images.githubusercontent.com/33415084/83849956-80c60d80-a710-11ea-99eb-28f6e9d09274.png" width="200px" height="350px" />
</div>


### Przeglądanie i usuwanie dodanych potraw/restauracji
Chcąc przeglądnąć listę dodanych potraw, użytkownik musi przejść do ekranu z listą. W tym celu na ekranie głównym aplikacji, musi kliknąć w ikonę oka (zaznaczenie 1).<br/>
Jeśli w liście potraw/restauracji nie będzie żadnej pozycji zostanie wyświetlona odpowiednia informacja. Jeśli natomiast w liście będą pozycje wyświetlą się one w tym oknie.<br/>
Obok nazw potraw/restauracji zostanie wyświetlona ikona kosza. Która uymożliwa użytkownikowi usunięcie pozycji.
<div>
<img src="https://user-images.githubusercontent.com/33415084/83850452-3d1fd380-a711-11ea-8fd7-c23a0b561d02.png" width="200px" height="350px" />
<img src="https://user-images.githubusercontent.com/33415084/83850448-3beea680-a711-11ea-843a-dbf65629ec9c.png" width="200px" height="350px" />
<img src="https://user-images.githubusercontent.com/33415084/83850569-6a6c8180-a711-11ea-884c-977103873cf4.png" width="200px" height="350px" />
<img src="https://user-images.githubusercontent.com/33415084/83855054-d651e880-a717-11ea-853e-6503fa71cdb7.png" width="200px" height="350px" />
</div>


### Losowanie pomiędzy dodanymi potrawmi/restauracjami
Użytkownik na samym dole pierwszego ekranu może znaleźć przycisk "Decide!". Po kliknięcu w ten przycisk w miejscu pod ikoną wyświetli się nazwa
wylosowanej potrawy lub restauracji, która była wcześniej dodana.
<div>
<img src="https://user-images.githubusercontent.com/33415084/83850452-3d1fd380-a711-11ea-8fd7-c23a0b561d02.png" width="200px" height="350px" />
</div>

#### Aplikacja jest w trakcie rozwoju
