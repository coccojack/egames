
-- tutti gli ordini di tutti i clienti
select * from customer c JOIN purchase p on c.id = p.customer_id 
JOIN purchase_videogame pv on pv.purchase_id = p.id 
JOIN videogame v on pv.videogame_id = v.id 