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

    public void emprunter(){
        while(nombreVelo==0) {
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public void rendre(){
        setNombreVelo(getNombreVelo()+1);
        if(nombreVelo == 1)
            notify();
    }

    public int getNumeroSite() {
        return numeroSite;
    }

    synchronized public int getNombreVelo() {
       return nombreVelo;
    }

    synchronized public void setNombreVelo(int nombreVelo) {
        this.nombreVelo = nombreVelo;
    }
}

