## TODO:
aggiungere la quantita in stock al videogame
capire perche le tabelle su overleaf si sono rotte

---
# E-R NOTES:

- Inputype ha una relazione molti a molti sia con platform che con videogame. Questo per via del fatto che, seppur una piattaforma possa essere compatibile con diversi accessori, il videogioco spesso non li supporta tutti, ma solo alcuni
- technicalFeatures si potrebbe collassare su platform, per via del fatto che è una onetone peculiare di ogni console
- la ridondanza di country potrebbe essere attenuata inserendo un entità country, che potrà far parte di un servizio topografico
