    888888888888  88  88888888888                        ad88888ba  
         88       ""  88                                d8"     "8b
         88           88                                Y8,        
         88       88  88aaaaa   ,adPPYba,   8b,dPPYba,  `Y8aaaaa,    
         88       88  88"""""  a8"     "8a  88P'   "Y8    `"""""8b,  
         88       88  88       8b       d8  88                  `8b  
         88       88  88       "8a,   ,a8"  88          Y8a     a8P  
         88       88  88        `"YbbdP"'   88           "Y88888P"   
         
         
Do uruchomienia backendu, odpalcie najpierw 'mvn install', zeby mvn wygenerował mappera dla DAO Customera, Vendora i Komentarzy Vendorowych.

GUI swaggera znajdziecie pod: http://localhost:8080/swagger-ui.html
Są gety, są posty. Jest uzywanie api Microsoft Mapsów (Map Component). Da sie sczytać odleglosci miedzy lokalizacjami. Niestety filtrowanie userów w zależności od odległości trwa mega długo, więc TODO zoptymalizowanie tego.
Lokalizacji uzywamy w formacie stringa, np. 59.50274,13.49872
Jest controller pod obrazki i zapsuje je do bazy danych (h2 na ten moment).

Pod /api/v1/vendors/{ID}/show macie dane vendora, gdzie da sie wgrac jego obrazek.

Do zrobienia zostało fizyczne wykonanie połączenia + ewentualny chat, ale to mozna sobie darować.
Jsona parsowałem na pałe, bo nie miałem czasu na coś bardziej wyrafinowanego, ale trzeba będzie to zrobić ObjectMapperem.
