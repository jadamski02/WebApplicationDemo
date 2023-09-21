function funkcje() {
    let news1 = document.getElementById("news1");
    let news2 = document.getElementById("news2");
    news1.innerHTML = powitanie() + "<br/>"+data()+"<br/>";
    news2.innerHTML = dniDoUrodzin();
}

function powitanie() {
    let dzisiaj = new Date();
    let godzina = dzisiaj.getHours();
    if((godzina<18) && (godzina>6)) {
        return 'Dzień dobry';
    } else {
        return 'Dobry wieczór';
    }
}

function data() {
    let dzisiaj = new Date();
    let dzien = dzisiaj.getDate();
    let miesiac=miesiace[dzisiaj.getMonth()];
    let rok = dzisiaj.getFullYear();

    return "Dzisiaj jest " + dzien + " " + miesiac + " " + rok + " r.";
}

var miesiace = ['stycznia', 'lutego', 'marca', 'kwietnia', 'maja', 'czerwca', 'lipca', 'sierpnia', 'września', 'października', 'listopada', 'grudnia'];

function zegarek() {
    let dzisiaj = new Date();
    let godzina = dzisiaj.getHours();
    let minuta = dzisiaj.getMinutes();
    let sekunda = dzisiaj.getSeconds();

    if(minuta<10) minuta="0"+minuta;
    if(sekunda<10) sekunda="0"+sekunda;
    let stopka = document.getElementById("stopka");
    stopka.innerHTML = "&copy;2023 JA | "+godzina+":"+minuta+":"+sekunda;
}

function dniDoUrodzin() {
    let urodzinyDzien = 15;
    let urodzinyMiesiac = 1;

    let dzisiaj = new Date();
    let najblizszeUrodziny = new Date(dzisiaj.getFullYear(), urodzinyMiesiac-1, urodzinyDzien);

    if(najblizszeUrodziny<dzisiaj) {
        najblizszeUrodziny.setFullYear(najblizszeUrodziny.getFullYear()+1);
    }

    let ile_czasu = najblizszeUrodziny.getTime() - dzisiaj.getTime();
    let ile_dni = Math.floor(ile_czasu / (1000 * 3600 * 24));

    return 'Autor tej strony będzie miał urodziny za ' + ile_dni + ' dni.';
}