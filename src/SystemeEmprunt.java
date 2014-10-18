/* Importation de la classe Lecture. */


class SystemeEmprunt {


/* Constantes (final indique que la valeur ne peut pas changer) */

static final int nbSites = 5;
static final int maxClients = 100;

/* Ces attributs sont statiques */

private Site[] sites = new Site[nbSites];
private Client[] clients = new Client[maxClients];
private int nbClients = 0;


/* Cette fonction cr�e un seul client � la fois (� la limite aucun).
 * Elle renvoie vrai si et seulement si un client a �t� cr��.
 * Elle renvoie faux d�s que la cr�ation des clients est termin�e. */

private boolean nouveauClient() {

	Site depart;
	Site arrivee;

	if(nbClients == maxClients) {
		System.out.println("Le nombre maximum de clients est"
			+ " atteint.");
		return false;
	}

	depart = sites[(int)(Math.random()*(nbSites-0))];
	arrivee = sites[(int)(Math.random()*(nbSites-0))];

    clients[nbClients] = new Client(nbClients, depart, arrivee);
	nbClients++;

	return true;
}


/* Constructeur. Il est appel� lors de l'instanciation du syst�me d'emprunt. */

SystemeEmprunt() {

	int i;

	/* Instanciation des sites */
	for(i = 0; i < nbSites; i++)
		sites[i] = new Site(i);

	/* Instanciation des clients */
	while(nouveauClient());

	/* Instanciation du camion et lancement du thread associ� */
	new Camion(sites).start();

	/* Lancement des threads associ�s aux clients */
	for(i = 0; i < nbClients; i++)
        clients[i].start();
}


/* Point d'entr�e du programme */

public static void main(String[] args) {
	new SystemeEmprunt();
}

} // class SystemeEmprunt
