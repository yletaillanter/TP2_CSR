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
        setNombreVelo(getNombreVelo()-1);
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

        setNombreVelo(getNombreVelo()+1);
        if(getNombreVelo() == 1)
            notify();
    }

    public int getNumeroSite() { return numeroSite; }

    synchronized public int getNombreVelo() {
       return nombreVelo;
    }

    synchronized public void setNombreVelo(int nombreVelo) {
        this.nombreVelo = nombreVelo;
    }
}

