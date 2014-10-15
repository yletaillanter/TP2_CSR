/**
 * Created by 14007427 on 15/10/14.
 */
public class Client extends Thread{

    private int nbClient;
    private Site siteDepart;
    private Site siteArrivee;
    private int distance;

    public Client(int nbClient,Site siteDepart, Site siteArrive){
        this.nbClient = nbClient;
        this.siteDepart = siteDepart;
        this.siteArrivee = siteArrive;
    }

    @Override
    public void run(){

        distance = Math.abs(siteArrivee.getNumeroSite()-siteDepart.getNumeroSite());
        siteDepart.emprunter();
        System.out.println("Client " + nbClient + "emprunte sur le site " + siteDepart);

        try {
            Thread.sleep(distance*100);
        } catch(InterruptedException e) {}

        siteArrivee.rendre();
        System.out.println("Client " + nbClient + "rend sur le site " + siteDepart);
    }
}
