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

        System.out.println("Client " + nbClient +
                "("+siteDepart.getNumeroSite()+","+siteArrivee.getNumeroSite()+")"+
                " EMPRUNTE sur le site " + siteDepart.getNumeroSite()+
                "[Stock site n°" +siteDepart.getNumeroSite()+" = "+ siteDepart.getNombreVelo()+" vélo(s)]");

        try {
            Thread.sleep(distance*1000);
        } catch(InterruptedException e) {}

        siteArrivee.rendre();

        System.out.println("Client " + nbClient +
                "("+siteDepart.getNumeroSite()+","+siteArrivee.getNumeroSite()+")"+
                " REND sur le site " + siteArrivee.getNumeroSite()+
                "[Stock site n°" +siteArrivee.getNumeroSite()+" = "+ siteArrivee.getNombreVelo()+" vélo(s)]");
    }
}
