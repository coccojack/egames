## Idee su entità e relazioni da aggiungere BE:

account: (manytone ruolo)
- email password ruolo

ruolo:
- id nomeruolo

cliente: (onetone account)
- account_id nome cognome indirizzo indirizzo2 country stato zipcode 

Ordine: (manytoone cliente)
- id id_cliente prezzo_totale stato

OrdineProdotto: (onytomany-manytoone )
- ordine_id prodotto_id quantita

wishlist: 
- id, id_cliente (onetoone)

WishlistProdotto (manytomany)
- id_wishlist id_prodotto

Lingua:
- id, nome

Videogioco_lingua(manytomany):
- id_videogioco, id_lingua

Input_type:
- id, nome

Videogioco_input_type(manytomany): 
- id_videogame, id_inputype

Platform_input_type(manytomany):
- id_platforr, id_inputtype

doppia relazione molti a molti con platform e videogame pensata per via del fatto che, seppur una piattaforma possa essere compatibile con diversi accessori, il videogioco spesso non li supporta tutti, ma solo alcuni

## Cambiamenti alle entità esistenti:
- aggiungere a software_house: country, foundation_date, website
- aggiungere a game_mode: description, internet_required(boolean)
- aggiungere a videogame: acquisti-in-game(boolean), adult_game(boolean)
- aggiungere a theme: 
- technical da collassare su platform(forse)