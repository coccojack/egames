-- account_role / account/ address / customer
insert into account_role (id, role_name)
values (1, 'ADMIN'),
       (2, 'USER');
insert into customer (id, birthdate, name, surname)
values (1, '1991-07-16', 'Giacomo', 'Rotondo');
insert into address (id, country, state, zip_code, customer_id)
values (1, 'USA', 'Connecticut', '06051', 1);
insert into account (id, email, password, account_role_id, customer_id)
values (1, 'admin@email.it', 'admin', 1, NULL),
       (2, 'user@email.it', 'user', 2, 1);

-- sofware_house / developer
insert into software_house (id, country, foundation_date, name, website)
values (1, 'USA', '2005-01-01', 'Blizzard', 'https://www.blizzard.com/');
insert into developer (id, birth_date, name, surname, software_house_id)
values (1, '1985-01-01', 'Dev1', 'Dev1', 1),
       (2, '1985-01-01', 'Dev2', 'Dev2', 1),
       (3, '1985-01-01', 'Dev3', 'Dev3', 1);

-- game_mode / genere / input_type / language/ theme 
insert into game_mode (id, description, name, internet_required)
values (1, 'A mode where you play alone in your system', 'Local Singleplayer', false),
       (2, 'A mode where you play with your friends in your system', 'Local Multiplayer', false);
insert into genre (id, description, name)
values (1, 'Trading Card Game: a game in which you use cards to play', 'TCG'),
       (2,
        'Massively Multiplayer Online Role-Playing Game: a game in which you play online with your friends leveling and defeating monsters',
        'MMORPG');
insert into input_type (id, description, name)
values (1, 'Classic input devices for PC', 'Keyboard/Mouse'),
       (2, 'Controller pad for PC', 'PC Controller');
insert into language (id, name)
values (1, 'EN'),
       (2, 'IT');
insert into theme (id, description, name)
values (1, 'Dragons and Magic', 'Fantasy'),
       (2, 'Soldiers and war with weapons', 'War');

-- producer / technical_featuers / platform / platform_input_type
insert into producer (id, country, name, website)
values ('1', 'USA', 'Microsoft', 'https://www.miscrosoft.com'),
       (2, 'JP', 'Sony', 'https://www.sony.com');
insert into technical_features (id, cpu, ram, support)
values (1, 'AMD RYZEN', '16GB', 'OPT. READER');
insert into platform (id, name, production_date, producer_id, technical_features_id)
values (1, 'Windows 11', '2021-10-05', 1, 1);
insert into platform_input_type (platform_id, input_type_id)
values (1, 1),
       (1, 2);

-- videogame / videogame_game_mode / videogame_language / videogame_input_type / videogame_theme
INSERT INTO videogame
(id, adult_game, description, image, ingame_purchases, pegi, player_num, price, release_date, title, audio_dev_id,
 game_dev_id, genre_id, graph_dev_id, platform_id, software_house_id)
VALUES (1, false, 'Best Card Game', NULL, true, '7+', '1', 5, '2023-01-01', 'Hearthstone', 1, 1, 1, 1, 1, 1),
       (2, false, 'Best MMORPG Game', NULL, true, '7+', '1', 5, '2023-01-01', 'World of Warcraft', 1, 2, 2, 3, 1, 1);

insert into videogame_game_mode (videogame_id, gamemode_id)
values (1, 1),
       (1, 2);
insert into videogame_input_type (videogame_id, input_type_id)
values (1, 1);
insert into videogame_language (videogame_id, language_id)
values (1, 1),
       (1, 2);
insert into videogame_theme (videogame_id, theme_id)
values (1, 1),
       (2, 1);

-- wishlist / wishlist_videogame
insert into wishlist (id, customer_id)
values (1, 1);
insert into wishlist_videogame (wishlist_id, videogame_id)
values (1, 1),
       (1, 2);

-- purchase / purchase_videogame
insert into purchase (id, status, total, address_id, customer_id)
values (1, 'PREPARING', 10, 1, 1);
insert into purchase_videogame (id, quantity, purchase_id, videogame_id)
values (1, 1, 1, 1),
       (2, 1, 1, 2);


