
/**Importation des libraires**/
/*Pour rendre mutable une collection d'objets*/
import scala.collection.mutable
/*Pour lire un fichier source*/
import scala.io.Source
/*Pour lire ce qui est entre dans le terminal*/
import scala.io.StdIn.readLine

/**Objet IMPORT comporte toutes les instructions du programme**/
object IMPORT extends App {
  /*Etape 1: Import du fichier*/
  def FonctionImport() : Array[String] = {
    var StockageFichier = ""

    /*Insertion du chemin du fichier dans la console*/
    print("Veillez entrer le chemin de votre fichier: ")
    val chemin = readLine() /*lecture du chemin*/

    /*Si le fichier est existant on le stocke*/
    if (new java.io.File(chemin).exists) {
      StockageFichier = chemin
    }

    /*Si le fichier n'existe pas on affiche un message d'erreur*/
    else {print("ERREUR\nLe fichier que vous entrez n'est pas valide.\nVeillez tourner de nouveau le programme.\n\n")}

    /*lecture du fichier*/
    val LectureDuChemin = Source.fromFile(StockageFichier)
    val LignesFichier = LectureDuChemin.mkString + " " /*Ajout d'un espace afin de separer les lignes*/
    val LignesFichierListe = LignesFichier.split("\n") /*Transformation des lignes en listes*/
    return  LignesFichierListe /*La fonction retourne les lignes du fichier*/
  }

  /*Etape 2: Determination des coordonnees initiales: coin sur le rectangle de la pelouse*/
  def CoordonneesInitiale(CoinSup: String): Boolean = {

    val DeterminationCoordonneesInitiales = {
      /*Si la longueur de la ligne est de 3 ET qu'elle est composee d'un espace en position 2*/
      if ((CoinSup.length == 3) & (CoinSup.split(" ").length == 2)) {
        /*.charAt permet de recuperer les elements selon leurs positions, .isDigit pour verifier que c'est un chiffre*/
        /*Si les elements a la position 0 OU a la position 2 sont des chiffres*/
        if ((CoinSup.charAt(0).isDigit | CoinSup.charAt(2).isDigit))

          true
        else false
      } else false
    }

    return DeterminationCoordonneesInitiales /*La fonction retourne le coin dans lequel se trouve la tondeuse*/
  }

  /*Etape 3: Validation de la position initiale et orientation de la tondeuse*/
  def ValidationPositionInit ( ValidePositionInitiale: String  ) : Boolean = {

    /*Definition de l'orientation Sud, Nord, Est, West*/
    val OrientationListe = List("S", "N", "E", "W")

    val PositionInitialeFind =
    /*Si la ligne est composee d'un espace en position 3*/
      if (ValidePositionInitiale.split(" ").length == 3) {
        /*Si la longueur de la ligne est de 5*/
        if (ValidePositionInitiale.length == 5)
        /*Si l'element a la position 4 est n'est pas un chiffre*/
          if (!ValidePositionInitiale.charAt(4).isDigit
            /* ET si les elements a la position 0 OU 2 sont des chiffres*/
            &  ( ValidePositionInitiale.charAt(0).isDigit  |  ValidePositionInitiale.charAt(2).isDigit  )
            /* ET si la liste contient une lettre majuscule en position 4*/
            &  OrientationListe.contains(  ValidePositionInitiale.charAt(4).toString.toUpperCase()  ) )
            true
          else false
        else false
      } else false

    return PositionInitialeFind /*La fonction retourne l'orientation de la tondeuse*/
  }

  /*Etape 4: Controle de la tondeuse par des instructions a transmettre */
  def InstructionsInitiales ( CommandesDonnees: String  ) : Int = {

    /*Liste des commandes: A en avant, G vers la gauche, D vers la droite*/
    val CommandesListe = List("A", "D", "G")
    /*Initialisation des erreurs a 0*/
    var IterateurDeCommandes = 0

    /*Pour chaque element de la liste, tant que a est inferieur a la longueur de la liste*/
    /*On valide ou non l'instruction et compte les erreurs*/
    for (  a <- 0   until   CommandesDonnees.length  ) {
      /*A chaque iteration cette var prend une commande en entree*/
      val AllInstructions = CommandesDonnees(a).toString

      /*Si la liste ne contient pas l'instruction, on ajoute 1 au compteur*/
      if (  !CommandesListe.contains( AllInstructions )  )
      { IterateurDeCommandes += 1 }
    }
    return IterateurDeCommandes /*On retourne le nombre d'erreurs*/
  }

  /*Etape 5: Definition des deplacements de la tondeuse depuis sa position initiale*/
  def ActionsFinales( SurfaceRectangle: String , InitialisationDesPositions: String, CommandesEntrees: String ): String = {

    /*Definition des postions initales*/
    var X = InitialisationDesPositions.charAt(0).asDigit
    var Y = InitialisationDesPositions.charAt(2).asDigit
    /*Definition de l'orientation*/
    /*Represente le 5e element sous forme de string en majuscule*/
    var Direction = InitialisationDesPositions.charAt(4).toString.toUpperCase()


    /*Initalisation de la surface parcourue a 0*/
    var AireCouverte = 0

    /*Pour chaque element de la liste, tant que a est inferieur a la longueur de la liste*/
    for( a <- 0 until CommandesEntrees.length )
    {
      /*A chaque iteration cette var prend une instruction en entree:*/
      val Deplacements = CommandesEntrees( a ).toString.toUpperCase() /*Instruction en string majuscule*/


      /*Deplacements vers la droite*/
      if (Deplacements == "D") {
        Direction match {
          case "S" => Direction = "W" /*Si position Sud, la tondeuse va vers l'Ouest*/
          case "N" => Direction = "E" /*Si position Nord, la tondeuse va vers l'Est*/
          case "E" => Direction = "S" /*Si position Est, la tondeuse va vers le Sud*/
          case "W" => Direction = "N" /*Si position Ouest, la tondeuse va vers le Nord*/
        }
      }

      /*Deplacements vers la gauche*/
      if (Deplacements == "G") {
        Direction match {
          case "S" => Direction = "E" /*Si position Sud, la tondeuse va vers l'Est*/
          case "N" => Direction = "W" /*Si position Nord, la tondeuse va vers l'Ouest*/
          case "E" => Direction = "N" /*Si position Est, la tondeuse va vers le Nord*/
          case "W" => Direction = "S" /*Si position Ouest, la tondeuse va vers le Sud*/
        }
      }

      /*Deplacement vers l'avant*/
      if (Deplacements == "A") {

        /*Si les conditions sont respectees alors la tondeuse avance d'une case*/
        if (  Direction == "N") {
          Y += 1
          AireCouverte  += 1
        }
        else if ( Direction == "S") {
          Y -= 1
          AireCouverte  += 1
        }

        else if ( Direction == "E") {
          X += 1
          AireCouverte  += 1
        }
        else if ( Direction == "W") {
          X -= 1
          AireCouverte  += 1
        }
      }
    }
    /*On met les deux positions et l'orientation finale de la tondeuse au sein d'une variable*/
    val PositionsFinalesTondeuse = X.toString + " " + Y.toString + " " + Direction
    return PositionsFinalesTondeuse /*La fonction retourne les positions finales des tondeuses*/
  }

  /*Etape 6: Stockage des resultats pour chaque tondeuse*/
  def StochageResultat ( LignesFichiers: Array[String]  ) : mutable.Map[String, String] = {

    /*var qui stock les points initiaux*/
    var ValidationDesCoinsInitiaux =  ""
    /*Initialisation du nombre de tondeuse a 0*/
    var Tondeuses = 0
    /*Definition d'une map qui prend en string la tondeuse et en string le resultat*/
    val ResultatFinalStock= scala.collection.mutable.Map[String, String ]( )

    /*Pour chaque ligne du fichier*/
    for (  l <-  LignesFichiers.indices ) {

      /*Si on se situe sur la premiere ligne (positision 0) on a les coordonnees du coin*/
      if ( l == 0 ) {ValidationDesCoinsInitiaux = LignesFichiers(0).substring(0 , LignesFichiers(0).length -1 )
      }

      /*Si on est sur une ligne ayant un indice pair*/
      else if ( l % 2 == 0 ) {
        /*On ajoute 1 car on a une nouvelle tondeuse*/
        Tondeuses += 1

        /*Si on est dans une ligne paire la ligne precedente donne la position et orientation de la tondeuse*/
        val ValidationsDesPositions = LignesFichiers(l-1).substring( 0 , LignesFichiers(l-1).length - 1 ).toUpperCase()
        /*Si on est dans une ligne paire on a les commandes pour la tondeuse*/
        val ValidationDesIntructions = LignesFichiers(l).substring( 0 , LignesFichiers(l).length - 1 )

        /*Application des actions*/
        val NouvellePositionTondeuse = ActionsFinales(ValidationDesCoinsInitiaux, ValidationsDesPositions, ValidationDesIntructions )
        /*Stockage des resultats dans une map*/
        ResultatFinalStock+=  "Tondeuse %d".format(Tondeuses) -> NouvellePositionTondeuse

      }
    }
    return ResultatFinalStock  /*La fonction stocke les resultats*/
  }

  /*Etape 7: Creation d'une fonction qui appelle la fonction d'importation et de stockage des resultats*/
  def ExecutionFontion () : Unit = {
    /*Appel de la fonction d'importation du fichier*/
    val FichierImportation = FonctionImport()
    /*Appel de la fonction qui stocke les resultats*/
    val SeqMapResultats = StochageResultat(FichierImportation).toSeq.sortBy(_._1) /*toSeq permet d'afficher une sequence d'une map*/
    /*Pour chaque element de la map on affiche les valeurs*/
    for ((key, value) <- SeqMapResultats) println( key + " : " + value)
  }

  /*On execute la fonction finale pour obtenir le resultat dans la console*/
  ExecutionFontion ()

}