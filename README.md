# PROJET SCALA: Marie Clara FANCELLO & Eva GERMINI 

## Objectif: 
La société MowItNow a décidé de développer une tondeuse à gazon automatique, destinée aux surfaces rectangulaires. Il s'agit ainsi de concevoir et écrire un programme Scala, permettant l'implémentation des déplacements de la tondeuse et donnant la position finale de chaque tondeuse. 

## Enoncé
La tondeuse peut être programmée pour parcourir l'intégralité de la surface. La position de la tondeuse est représentée par une combinaison de coordonnées (x,y) et d'une lettre indiquant l'orientation selon la notation cardinale anglaise (N,E,W,S). La pelouse estdivisée en grille pour simplifier la navigation.

Par exemple, la position de la tondeuse peut être « 0, 0, N », ce qui signifie qu'elle se
situe dans le coin inférieur gauche de la pelouse, et orientée vers le Nord.

Pour contrôler la tondeuse, on lui envoie une séquence simple de lettres. Les lettres
possibles sont « D », « G » et « A ».
« D » et « G » font pivoter la tondeuse de 90° à droite ou à gauche respectivement, sans la
déplacer. « A » signifie que l'on avance la tondeuse d'une case dans la direction à laquelle
elle fait face, et sans modifier son orientation.

Si la position après mouvement est en dehors de la pelouse, la tondeuse ne bouge pas,
conserve son orientation et traite la commande suivante.

On assume que la case directement au Nord de la position (x, y) a pour coordonnées (x,
y+1).

Pour programmer la tondeuse, on lui fournit un fichier d'entrée construit comme suit :

• La première ligne correspond aux coordonnées du coin supérieur droit de la pelouse,
celles du coin inférieur gauche sont supposées être (0,0).

• La suite du fichier permet de piloter toutes les tondeuses qui ont été déployées.
Chaque tondeuse a deux lignes la concernant :
- la première ligne donne la position initiale de la tondeuse, ainsi que son
orientation. La position et l'orientation sont fournies sous la forme de 2 chiffres
et une lettre, séparés par un espace.
- la seconde ligne est une série d'instructions ordonnant à la tondeuse d'explorer
la pelouse. Les instructions sont une suite de caractères sans espaces.

Chaque tondeuse se déplace de façon séquentielle, ce qui signifie que la seconde
tondeuse ne bouge que lorsque la première a exécuté intégralement sa série
d'instructions.

Lorsqu'une tondeuse achève une série d'instruction, elle communique sa position et son
orientation.

## Contenu des dossiers 
### Dossier 1: nommé FICHIER 
Ce dossier donne le fichier d'entrée: 

5 5
<br/>1 2 N
<br/>GAGAGAGAA
<br/>3 3 E
<br/>AADAADADDA

Ici, il y a deux tondeuses: 
- La première ligne correspond aux coordonnées du coin supérieur de la pelouse. 
- Les deuxième et quatrième lignes donnent la position initiale et l'orientation des tondeuses. 
- Les troisième et dernière lignes donnent leurs commandes de déplacements. 

Par exemple, la première tondeuse a la position initiale (1, 2) et est orientée Nord. Elle se déplace à gauche, puis en avant, puis à gauche...

### Dossier 2: nommé CODE 
Ce dossier donne le code à exécuter pour obtenir les positions finales des tondeuses. 

#### Présentation du code: 
1. **Fonction qui importe le fichier**
Nom de la fonction: *FonctionImport*
<br/>Cette fonction demande à l'utilisateur d'entrer le chemin de son fichier. Le chemin du fichier peut s'écrire de la façon suivante: C:\user\...\test.txt
Si son fichier est valide alors le programme s'exécute. A l'inverse, si son fichier n'est pas valide la programme ne s'exécute pas et il faut faire retourner le programme. 

2. **Coordonnées initiales de la pelouse** 
Nom de la fonction: *CoordonneesIinitiale* 
<br/>Cette fonction permet de récupérer les coordonnées de la pelouse qui se situent en première ligne du fichier d'entrée. Dans le fichier d'entrée cela correspond à "5 5". 

3. **Validation de la position initiale et de l'orientation de la tondeuse**
Nom de la fonction: *ValidationPositionInit*
<br/>Cette fonction permet de récupérer la position initiale (2 chiffres) de la tondeuse ainsi que son orientation (lettre). Cela correspond à la deuxième et quatrième ligne du fichier d'entrée. Par exemple, la tondeuse 1 à la position initiale (1, 2) et est orientée vers le Nord. 
Son orientation peut prendre 4 valeurs: 
- "N": Nord 
- "S": Sud 
- "E": Est 
- "W": Ouest 

4. **Contrôle de la tondeuse par des instructions à transmettre**
Nom de la fonction: *InstructionsInitiales* 
<br/>Cette fonction permet de s'assurer que les instructions données pour les déplacements de la tondeuse sont valides: 
- "A": vers l'avant 
- "G": vers la gauche 
- "D": vers la droite 
Cette fonction retourne le nombre de commandes non valides. Par exemple, si F est rentré, le compteur ajoute 1. 

5. **Définition des déplacements de la tondeuse depuis sa position initiale**
Nom de la fonction: *ActionsFinales*
<br/>Cette fonction donne la dernière position de la tondeuse en fonction de ces déplacements. 
• Si son déplacement se fait vers la droite: 
- Position Sud: changement de postion vers l'Ouest
- Position Nord: changement de postion vers l'Est
- Position Est: changement de postion vers le Sud
- Position Ouest: changement de postion vers le Nord
• Si son déplacement se fait vers la gauche:  
- Position Sud: changement de postion vers l'Est
- Position Nord: changement de postion vers l'Ouest
- Position Est: changement de postion vers le Nord
- Position Ouest: changement de postion vers le Sud
• Si son déplacement se fait vers l'avant:  
- La tondeuse se déplace d'un pas en avant. 

6. **Stockage des résultats pour chaque tondeuse**
Nom de la fonction: *StochageResultat*
<br/>Cette fonction reprend le fichier d'entrée (ici nommé test) et stocke les déplacements de la tondeuse.
En sortie, un dictionnaire est donné avec comme clés chaque tondeuse et comme valeurs sa dernière position. 

7. **Fonction d'exécution**
Nom de la fonction: *ExecutionFonction*
<br/>Cette fonction a trois utilités majeures: 
- elle appelle la fonction d'importation qui permet de demander à l'utilisateur d'entrer le chemin de son fichier
- elle reprend la fonction 6 qui stocke les résultats dans une map
- elle permet d'afficher les dernières postions des tondeuses 


## Sortie: positions finales des deux tondeuses 
Tondeuse 1 : 1 3 N
<br/>Tondeuse 2 : 5 1 E
