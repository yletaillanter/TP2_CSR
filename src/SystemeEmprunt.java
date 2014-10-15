/* Importation de la classe Lecture. */


class SystemeEmprunt {


/* Constantes (final indique que la valeur ne peut pas changer) */

static final int nbSites = 5;
static final int maxClients = 100;

/* Ces attributs sont statiques */

private Site[] sites = new Site[nbSites];
private Client[] clients = new Client[maxClients];
private int nbClients = 0;


/* Cette fonction crée un seul client à la fois (à la limite aucun).
 * Elle renvoie vrai si et seulement si un client a été créé.
 * Elle renvoie faux dès que la création des clients est terminée. */

private boolean nouveauClient() {

	Site depart;
	Site arrivee;

	if(nbClients == maxClients) {
		System.out.println("Le nombre maximum de clients est"
			+ " atteint.");
		return false;
	}

	depart = sites[0];
	arrivee = sites[1];

	clients[nbClients] = new Client(nbClients, depart, arrivee);
	nbClients++;

	return true;
	
}


/* Constructeur. Il est appelé lors de l'instanciation du système d'emprunt. */

SystemeEmprunt() {

	int i;

	/* Instanciation des sites */
	for(i = 0; i < nbSites; i++)
		sites[i] = new Site(i);

	/* Instanciation des clients */
	while(nouveauClient());

	/* Instanciation du camion et lancement du thread associé */
	new Camion(sites).start();

	/* Lancement des threads associés aux clients */
	for(i = 0; i < nbClients; i++)
		clients[i].start();

}


/* Point d'entrée du programme */

public static void main(String[] args) {

	new SystemeEmprunt();

}


} // class SystemeEmprunt
