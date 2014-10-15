/**
 * Created by 14007427 on 15/10/14.
 */
public class Camion extends Thread {

    private Site[] listeSite;
    private int stockCamion;

    public Camion(Site[] site){
        this.listeSite = site;
        stockCamion = 0;
    }

    @Override
    public void run(){
        
        Site siteCourant;
        int i=0;

        while(true){

            siteCourant = listeSite[i];
            i++;

            if(siteCourant.getNombreVelo()<siteCourant.borneInf){

                if(stockCamion>=(siteCourant.stockInit-siteCourant.getNombreVelo())){
                    siteCourant.setNombreVelo(siteCourant.getNombreVelo() + (siteCourant.stockInit-siteCourant.getNombreVelo()));
                    stockCamion -= (siteCourant.stockInit-siteCourant.getNombreVelo());
                }
                else {
                    siteCourant.setNombreVelo(siteCourant.getNombreVelo()+stockCamion);
                    stockCamion = 0;
                }
            }else if(siteCourant.getNombreVelo()>siteCourant.borneSup) {

                stockCamion = stockCamion + ((siteCourant.getNombreVelo() - siteCourant.borneSup));
                siteCourant.setNombreVelo(siteCourant.getNombreVelo() - (siteCourant.getNombreVelo() - siteCourant.stockInit));
            }
            else{
                System.out.println("Nombre de vélos OK sur le site " + siteCourant);
            }

            if(i==listeSite.length)
                i=0;

        }
    }
}
