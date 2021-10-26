# Potager
Application de gestion de potagers permettant à un jardinier de savoir où sont ses légumes et ce qu’il a à faire. Cette application va permettre de gérer :
•	des potagers
•	des carrés de potagers dédiés à la culture d’une ou plusieurs plantes
•	des plantes sous trois catégories (racine, feuille, fruits)
•	des actions à faire (ex : 20/01 : planter mes pommes de terre)
  
Données
•	Potager :
  o	localisation (chaine de caractère)
  o	nom
  o	surface
  o	ville
•	Carré : 
  o	dans quel potager il se trouve
  o	surface
  o	type de sol (argileux etc.)
  o	type d’exposition (soleil, ombre, mi ombre)
•	Plante :
  o	nom
  o	type (racine, feuille, fruit)
  o	variété
  o	surface occupée par un plan (en cm2)
•	Une plante dans un carré (Implantation)
  o	quantité (nb de plan)
  o	date de mise en place
  o	date de récolte prévue
•	Action 
  o	date
  o	événement
  o	lieu (quel potager ou quel carré ou “serre” ou “autre”)
  
 
Fonctionnalités (Écrans ou WS)
1.	CRUD potagers
2.	CRUD carrés
3.	CRUD plantes (ici on ne gère pas un stock mais des types de plantes)
4.	Ajouter des plantes dans le potager
5.	Visualiser le potager (avoir une vue d’ensemble)
6.	Ajouter des actions
7.	Visualiser les actions des 2 prochaines semaines
8.	Visualiser la localisation d’une plante (nom ou nom et variété) dans les potagers (potager, carré, quantité etc.)
9.	Supprimer un plan du potager
10.	Web Service donnant l’ensemble des données d’un potager
11.	Web Service donnant l’ensemble des événements sur la serre
12.	Afficher une page montrant la météo des différents potagers.
13.	Web service présentant les carrés inoccupés
  
Contraintes
1.	La somme des tailles des carrés doit être inférieur à celle du potager
2.	La surface occupée par les plans ne peut être supérieure à celle du carré.
3.	Pas de doublon de plantes (nom, variété)
4.	On ne peut entrer une action que si la date est supérieure à la date du jour
5.	Pas plus de 3 plantes différentes dans un carré
