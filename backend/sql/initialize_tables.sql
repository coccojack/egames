-- account_role
insert into account_role (id, role_name)
values (1, 'ADMIN'),
        (2, 'USER');
       
-- customer
insert into customer (id, name, surname, birthdate, email, password, account_role_id)
values (1, 'Admin', 'Admin', '1985-07-01', 'admin@email.it','admin',1),
        (2, 'Giacomo', 'Rotondo', '1991-07-01', 'grotondo@email.it','grotondo',2),
        (3, 'User', 'User', '2009-07-01', 'user@email.it','user',2);

-- address
insert into address (id, country, state, zip_code, customer_id)
values (1, 'Italy', 'Italy', '95124', 2),
        (2, 'USA', 'Connecticut', '06051', 3);

-- status
insert into status (id, name, description)
values (1, 'IN LAVORAZIONE','Acquisto in fase di elaborazione. Preparazione per la spedizione in corso.'),
        (2, 'SPEDITO','Acquisto spedito presso il domicilio selezionato.'),
        (3, 'CONSEGNATO','Acquisto consegnato presso il domicilio selezionato.'),
        (4, 'CANCELLATO','Acquisto cancellato a seguito di disposizioni di sistema.');
        
-- purchase
insert into purchase (id, purchase_date, total, address_id, customer_id, status_id)
values (1,'2023-01-01', 50, 1, 2, 3),
        (2,'2023-01-23', 75, 1, 2, 2),
        (3,'2023-01-06', 50, 1, 2, 4),
        (4,'2023-01-30', 75, 1, 2, 1);

-- genre
insert into genre (id, name,description)
values (1,'Azione','Genere caratterizzato da fasi frenetiche di gioco.'),
        (2,'FPS','Genere caratterizzato da combattimenti con armi da fuoco.'),
        (3,'GDR','Genere caratterizzato da un una componente ruolistica.'),
        (4,'TCG','Genere caratterizzato da carte collezionabili.');

-- theme
insert into theme (id, name,description)
values (1,'Fantasy','Tematica ricca di magia e scontri tra creature fantastiche.'),
        (2,'FPS','Genere caratterizzato da combattimenti con armi da fuoco.'),
        (3,'GDR','Genera caratterizzato da un una componente ruolistica.');

-- game_mode
insert into game_mode (id, name,description, internet_required)
values (1,'Local Single Player','Modalità di gioco in locale a giocatore singolo.',0),
        (2,'Local Multi Player','Modalità di gioco in locale a giocatore multiplo.',0),
        (3,'Online Single Player','Modalità di gioco online a giocatore singolo.',1),
        (4,'Online Multi Player','Modalità di gioco online a giocatore multiplo.',1);

-- software_house
insert into software_house(id,name,foundation_date,country,website)
values (1,'Blizzard','1989-05-05','USA','www.blizzard.com'),
        (2,'Santa Monica Studio','1979-01-01','JAPAN','www.konami.com'),
        (3,'Activision','1995-01-01','USA','www.activision.com');

-- specialty
insert into specialty(id,name,description)
values (1,'GAME DEVELOPER','Sviluppatore che si è occupato del comparto tecnico del gioco.'),
        (2,'AUDIO DEVELOPER','Sviluppatore che si è occupato del comparto audio del gioco'),
        (3,'GRAPHIC DEVELOPER','Sviluppatore che si è occupato del comparto grafico del gioco.');

-- developer
insert into developer(id,name,surname,birth_date,software_house_id,specialty_id)
values (1,'Alfonso','Grasso','1989-06-06',1,1),
        (2,'Alfredo','Grossi','1983-06-06',1,2),
        (3,'Angelo','Grissi','1989-07-06',1,3),
        (4,'Barbaro','Regoli','1989-06-06',2,1),
        (5,'Bruno','Romoli','1983-06-06',2,2),
        (6,'Bartolo','Rovati','1989-07-06',2,3),
        (7,'Carmelo','Sciacca','1989-06-06',3,1),
        (8,'Carlo','Sturnioli','1983-06-06',3,2),
        (9,'Costantino','Simonetti','1989-07-06',3,3);

-- language
insert into language(id,name)
values (1,'EN'),
        (2,'IT'),
        (3,'FR'),
        (4,'SP'),
        (5,'DE');

-- producer
insert into producer(id,name,country,website)
values (1,'Microsoft','USA','www.microsoft.com'),
        (2,'Sony','JAPAN','www.sony.com'),
        (3, 'Nintendo','JAPAN','www.nintendo.com');

-- platform
insert into platform(id,name,production_date,cpu,ram,support,producer_id)
values (1,'XBOX','2022-10-01','AMD Jaguar Custom; 8 core a 2,3 GHz','12GB','Lettore laser, porte usb',1),
        (2,'Windows 11','2022-09-01','AMD Ryzen 7 3700X','12GB','Lettore laser, porte usb, bluetooth',1),
        (3,'Playstation 5','2022-11-01','AMD ZEN 2','12GB','Lettore laser, porte usb',2),
        (4,'Switch','2022-12-01','Nvidia Maxwell Gen 2','6GB','Lettore card, bluetooth',3);

-- input_type
insert into input_type(id,name,description)
values (1,'PS PAD','Controller classico di casa Sony per Playstation'),
        (2,'MS Controller','Controller classico di casa Microsoft per Windows 11 e XBOX'),
        (3,'Joycon','Controller classico di casa Nintendo per Nintendo Switch'),
        (4,'Mouse and Keyboard','Classici dispositivi di input per molte piattaforme.');

-- videogame
insert into videogame(id,title,description,release_date,pegi,player_num,adult_game,ingame_purchases,stock_quantity,price,platform_id,genre_id,software_house_id,game_dev_id,audio_dev_id,graph_dev_id)
values (1,'God of War Ragnarok','Le avventure di Kratos continuano al Nord','2023-01-01','16+','1',0,0,5,50,3,1,2,4,5,6),
        (2,'Hearthstone','Il gioco di carte con i protagonisti di World of Warcraft','2018-01-01','8+','1',0,0,5,10,2,4,1,1,2,3),
        (3,'GWENT','Il gioco di carte con i protagonisti di The Witcher 3','2018-01-01','8+','1',0,0,5,10,2,4,3,7,8,9);


-- purchase_videogame
insert into purchase_videogame(id,quantity,purchase_id,videogame_id)
values (1,3,1,1),
        (2,1,1,2),
        (3,1,3,2),
        (4,1,3,3);

-- preferred_videogame
insert into preferred_videogame(customer_id,videogame_id)
values (2,1),
        (3,1);

-- platform_input_type
insert into platform_input_type(platform_id,input_type_id)
values (1,2),
        (2,2),
        (2,4),
        (3,1),
        (4,3);

-- videogame_theme
insert into videogame_theme(videogame_id,theme_id)
values (1,1),
        (1,3),
        (2,1),
        (3,1);

-- videogame_game_mode
insert into videogame_game_mode(videogame_id,gamemode_id)
values (1,1),
        (1,3),
        (2,1),
        (2,3),
        (3,1),
        (3,3);

-- videogame_language
insert into videogame_language(videogame_id,language_id)
values (1,1),
        (1,2),
        (1,3),
        (1,4),
        (1,5),
        (2,1),
        (2,2),
        (2,3),
        (2,4),
        (2,5),
        (3,1),
        (3,2),
        (3,3),
        (3,4),
        (3,5);


-- videogame_input_type
insert into videogame_input_type(videogame_id,input_type_id)
values (1,1),
        (2,4),
        (3,4);