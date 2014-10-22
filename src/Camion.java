import java.rmi.UnexpectedException;

/**
 * Created by 14007427 on 15/10/14.
 */
public class Camion extends Thread {

    private Site[] listeSite;
    private int stockCamion;

    public Camion(Site[] site) {
        this.listeSite = site;
        stockCamion = 5;
        this.setDaemon(true);
    }

    public int getStockCamion() {
        return stockCamion;
    }


    @Override
    public void run() {

        Site siteCourant;
        int i = 0;

        while (true) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                System.out.println(e.getMessage());
            }

            siteCourant = listeSite[i];
            stockCamion = siteCourant.majStock(stockCamion);

            i++;
            if (i == listeSite.length) {
                i = 0;
            }
        }
    }
}


/*
            siteCourant = listeSite[i];
                  if(siteCourant.getNombreVelo()<siteCourant.borneInf){
                    if(getStockCamion()>=(siteCourant.stockInit-siteCourant.getNombreVelo())){
                        nb = siteCourant.getNombreVelo() + (siteCourant.stockInit-siteCourant.getNombreVelo());
                        siteCourant.setNombreVelo(nb);
                        stockCamion -= (nb);
                        System.out.println("J'ai ajouté "+nb+" vélos au site "+ siteCourant.getNumeroSite() +" stock CAMION = "+ getStockCamion()+" ; stock site n°"+ siteCourant.getNumeroSite()+" = " + siteCourant.getNombreVelo());
                        //notify();
                    }
                    else {
                        nb = siteCourant.getNombreVelo()+getStockCamion();
                        siteCourant.setNombreVelo(nb);
                        stockCamion = 0;
                        System.out.println("J'ai ajouté "+nb+" vélos au site "+ siteCourant.getNumeroSite() +" stock CAMION = "+ getStockCamion()+" ; stock site n°"+ siteCourant.getNumeroSite()+" = " + siteCourant.getNombreVelo());
                        //notify();
                    }

                }else if(siteCourant.getNombreVelo()>siteCourant.borneSup) {
                    nb=siteCourant.getNombreVelo() - (siteCourant.getNombreVelo() - siteCourant.stockInit);
                    stockCamion = getStockCamion() + nb;
                    siteCourant.setNombreVelo(nb);
                    System.out.println("J'ai enlevé "+nb+" vélos au site "+ siteCourant.getNumeroSite() +" stock CAMION = "+ getStockCamion()+" ; stock site n°"+ siteCourant.getNumeroSite()+" = " + siteCourant.getNombreVelo());
                    //notify();
                }
                else{
                    System.out.println("Nombre de vélos OK sur le site " + siteCourant.getNumeroSite());
                }


                System.out.print("CAMION, mon stock =" + getStockCamion());
                System.out.println(" ; stock site = "+siteCourant.getNumeroSite()+","+siteCourant.getNombreVelo());
        }*/


