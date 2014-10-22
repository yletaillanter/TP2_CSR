import java.util.concurrent.Semaphore;

class Site {

/* Constantes associ�es au site */

static final int stockInit = 6;
static final int stockMax = 10;
static final int borneSup = 8;
static final int borneInf = 2;

private int numeroSite;
private int nombreVelo;

    public Site(int i){
        this.numeroSite = i;
        nombreVelo = stockInit;
    }

    public void afficherStock(){
        System.out.println("Stock de site n°" +getNumeroSite()+" = "+ getNombreVelo());
    }

    synchronized public void emprunter(){
        while( getNombreVelo()==0) {
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        // Si il y a plus de 0 vélo on en emprunte un.
        this.nombreVelo-=1;
        if(getNombreVelo()==stockMax-1)
            notify();
    }

    synchronized public void rendre(){

        while(stockMax== getNombreVelo()){
            try{
                wait();
            }catch(InterruptedException e){
                e.printStackTrace();
            }
        }

        this.nombreVelo+=1;
        if(getNombreVelo() == 1)
            notify();
    }

    public int getNumeroSite() { return numeroSite; }

    synchronized public int getNombreVelo() {
       return nombreVelo;
    }

    //synchronized public void setNombreVelo(int nombreVelo) {

    //    this.nombreVelo = nombreVelo;
    //    notify();
    //}

    /**
     * @param stockCamion
     * @return nouveau stockCamion
     */
    synchronized public int majStock(int stockCamion) {

        int nb;

        if(this.getNombreVelo()<this.borneInf){
            if(stockCamion>=(this.stockInit-this.getNombreVelo())){
                nb = this.getNombreVelo() + (this.stockInit-this.getNombreVelo());
                this.nombreVelo = nb;
                stockCamion -= (nb);
                System.out.println("J'ai ajouté "+nb+" vélos au site "+ this.getNumeroSite() +" stock CAMION = "+ stockCamion+" ; stock site n°"+ this.getNumeroSite()+" = " + this.getNombreVelo());
               notifyAll();
            }
            else {
                nb = this.getNombreVelo()+stockCamion;
                this.nombreVelo = nb;
                stockCamion = 0;
                System.out.println("J'ai ajouté "+nb+" vélos au site "+ this.getNumeroSite() +" stock CAMION = "+ stockCamion+" ; stock site n°"+ this.getNumeroSite()+" = " + this.getNombreVelo());
                notifyAll();
            }

        }
        else if(this.getNombreVelo()>this.borneSup) {
            int nbstock = (this.getNombreVelo() - this.stockInit);
            nb=this.getNombreVelo() - (this.getNombreVelo() - this.stockInit);
            stockCamion = stockCamion + nb;
            this.nombreVelo = nbstock;
            System.out.println("J'ai enlevé "+nb+" vélos au site "+ this.getNumeroSite() +" stock CAMION = "+ stockCamion+" ; stock site n°"+ this.getNumeroSite()+" = " + this.getNombreVelo());
            notifyAll();
        }
        else{
            System.out.println("Nombre de vélos OK sur le site " + this.getNumeroSite());
        }

        System.out.print("CAMION, mon stock =" + stockCamion);
        System.out.println(" ; stock site = "+this.getNumeroSite()+","+this.getNombreVelo());

        return stockCamion;


    }
}

