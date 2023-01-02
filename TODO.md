idee su entità e relazioni da aggiungere:

account: (manytone ruolo)
    email password ruolo
ruolo:
    id nomeruolo

cliente: (onetone account)
    account_id nome cognome indirizzo indirizzo2 country stato zipcode 

Ordine: (manytoone cliente)
    id id_cliente prezzo_totale stato

OrdineProdotto: (onytomany-manytoone )
    ordine_id prodotto_id quantita

wishlist: id, id_cliente (onetoone)
WishlistProdotto (manytomany)
    id_wishlist id_prodotto