import java.util.Observable;

public abstract class MachineComponent extends Observable {
    protected boolean broken = false;
    public abstract boolean isBroken();

    private void notifyBroken(){
        setChanged();
        notifyObservers(this);
    }

    public void setBroken() {
        if(!broken){
            broken = true;
            notifyBroken();
        }
    }

    public void repair() {
        if(broken){
            broken = false;
            notifyBroken();
        }
    }
}
