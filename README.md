# SAE-2.01

### DISCLAMER 
Tous les fichiers que vous voyez à part ce du projet se trouvant dans le dossier "logicielchevalblancdemerde" sont des fichiers générés par IntelliJ quand j'ai créé le projet.
Si vous êtes curieux de savoir à quoi correspond les fichiers pom.xml ou le dossier target renseignez-vous sur `Maven` (qui est un gestionnaire de librairy (ça permet d'ajouter facilement des dépendances à notre projet(comme JavaFX))

## Descriptions des dossiers
- ressources: contient le logo de l'application. Pour le modifier il faut se rendre dans le fichier  MainApplication et modifier le chemin de l'icon.
- src: contient tout le logiciel.

## Contenu du src
- data : contiendra les fichiers (Consommations par chambre, petits-déjeuners par chambre, etc..) (pour l'instant vide)
- enums : contient la liste des types de services décrit dans la sel (pressing, navette) et l'ensemble des tailles de titre disponible (H1 à h4) (comme en HTML)
- MainApplication: fichier principal pour lancer l'application
- MainMenu : Menu principal affiché quand on lance l'application
- modals : Une fenêtre modal est une fenêtre qui s'ouvre par dessus la fenêtre actuel. Elle sert pour entrer une consommation par exemple ou alors elle servira pour rechercher
des consommations
- modules : contient les modules principales (Une consommation, Les consommations d'une chambre, etc...) pour l'instant elle ne contient que la description d'une consommation
- ui : contient tous les éléments visuels "customisés". En effet nous n'allons pas utiliser TextField etc mais plutôt CustomTextField (qui permet directement de mettre un label et si on veut un tooltip).
Je vous invite à regarder les classes disponibles pour comprendre et où elles sont utilisés
- ui/StageTemplate : Cette classe là je vais l'expliquer. Au lieu d'utiliser Stage nous allons utiliser StageTemplate qui est abstraite. ELle permet de générer 3 méthodes pour les 3 parties du contenu d'une page:
Le haut de la page, le contenu principal et le bas de page.
- Utils : quelques utilitaires comme un utilitaire pour vérifier le formulaire
- views : contient les différentes fonctionnalités à coder. c'est là qu'on va ajouter les fonctionnalités et ou les modifier.

## Questions ? 
Contacte moi par discord
