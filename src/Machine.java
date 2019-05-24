
public class Machine extends MachineComponent {

    @Override
    public void setBroken() {
        if(!broken){
            broken = true;
            notifyBroken();
        }
    }

    @Override
    public void repair() {
        if(broken){
            broken = false;
            notifyBroken();
        }
    }

    @Override
    public boolean isBroken() {
        return broken;
    }

    private void notifyBroken(){
        setChanged();
        notifyObservers(broken);
    }
}
