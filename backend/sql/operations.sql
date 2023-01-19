-- Op. 1: Registrazione di un nuovo utente (ruolo USER pre-esistente sul sistema)
insert into customer (name,surname,birthdate,email,password,account_role_id)
values ('Nome','Cognome','2023-01-01','nome.cognome@email.it','strongpwd',(select id from account_role ar where ar.role_name='USER'));

-- Op. 2: Aggiunta di un nuovo videogame (piattaforma, genere, software house e sviluppatori pre-esistenti sul sistema)
insert into videogame(title,description,release_date,pegi,player_num,adult_game,ingame_purchases,stock_quantity,price,platform_id,genre_id,software_house_id,game_dev_id,audio_dev_id,graph_dev_id)
values (
        'Call of duty 9',
        'Affronta la tua battaglia',
        '2023-01-01',
        '18+',
        '1',
        0,
        1,
        5,
        50,
        (select id from platform p where p.name = 'Playstation 5'),
        (select id from genre g where g.name = 'FPS'),
        (select id from software_house sh where sh.name='Activision'),
        (select id from developer d where d.software_house_id = (select id from software_house sh where sh.name = 'Activision') and d.specialty_id = (select id from specialty s where s.name='GAME DEVELOPER')),
        (select id from developer d where d.software_house_id = (select id from software_house sh where sh.name = 'Activision') and d.specialty_id = (select id from specialty s where s.name='AUDIO DEVELOPER')),
        (select id from developer d where d.software_house_id = (select id from software_house sh where sh.name = 'Activision') and d.specialty_id = (select id from specialty s where s.name='GRAPHIC DEVELOPER'))
);

-- Op. 3: Aggiunta di una nuova software house 
insert into software_house(name,foundation_date,country,website)
values ('Riot Games','2005-07-01','USA','www.riot.com');

-- Op. 4: Acquisto di un videogame (valorizzare i parametri address_id e customer_id con l'id dell'indirizzo e l'id del cliente)
insert into purchase (purchase_date, total, address_id, customer_id, status_id)
values (
	'2023-01-07', 
	30, 
	:address_id, 
	:customer_id, 
	(select id from status s where s.name = 'IN LAVORAZIONE')
);

-- Op. 5: Inserimento in wishlist di un videogame (valorizzare i parametri customer_id e videogame_id con l'id del cliente e l'id del videogame)
insert into preferred_videogame (customer_id,videogame_id)
values (:customer_id,:videogame_id);

-- Op. 6: Visualizzare i videogame per una piattaforma (valorizzare il parametro platform_id con l'id della piattaforma)
select * from videogame v join platform p on v.platform_id = p.id where p.id = :platform_id

-- Op. 7: Visualizzare i videogame correlati ad uno specifico videogame (valorizzare il parametro videogame_id con l'id del videogame)
select v.* from videogame v join genre g on  v.genre_id = g.id join platform p on v.platform_id =p.id 
where v.genre_id = (select genre_id from videogame v2 where v2.id= :videogame_id) and 
v.platform_id = (select platform_id  from videogame v3 where v3.id= :videogame_id) and 
v.id <> :videogame_id;

--Op. 8: Visualizzare il totale di un acquisto (valorizzare il parametro purchase_id con l'id dell'acquisto)
select p.total
from purchase p
where p.id = :purchase_id;

--Op. 9: Cambiare lo stato dell’ordine (valorizzare i parametri purchase_id e status_id con l'id dell'acquisto e l'id del nuovo stato)
update purchase p 
set p.status_id = :status_id 
where p.id = :purchase_id;

-- Op. 10: Cancellazione di un acquisto non ancora evaso (valorizzare il parametro purchase_id con l'id dell'acquisto)
update purchase p 
set p.status_id = (select id from status s where s.name = 'CANCELLATO')
where p.id = :purchase_id and p.status_id = (select s.id from status s where s.name = 'IN LAVORAZIONE');

-- Op. 11: Visualizzazione stato dell’acquisto (valorizzare il parametro purchase_id con l'id dell'acquisto)
select s.name 
from purchase p join status s on p.status_id = s.id 
where p.id = :purchase_id;