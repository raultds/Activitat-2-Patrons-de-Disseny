import java.util.ArrayList;
import java.util.List;
import java.util.Observable;
import java.util.Observer;


public class MachineComposite extends MachineComponent implements Observer {
    private List<MachineComponent> components = new ArrayList<>();
    private int brokenComponents = 0;

    public void addComponent(MachineComponent mc) {
        components.add(mc);
        mc.addObserver(this);
        if(mc.isBroken()){
            brokenComponents += 1;
            if(!broken){
                setBroken();
            }
        }
    }

    @Override
    public void setBroken() {
        if(!isBroken()){
            broken = true;
            notifyBroken();
        }
    }

    @Override
    public void repair() {
        if(isBroken()){
            broken = false;
            notifyBroken();
        }
    }

    public boolean isBroken() {
        return broken || brokenComponents > 0;
    }

    @Override
    public void update(Observable o, Object arg) {
        MachineComponent mc = (MachineComponent) arg;
        if (mc.isBroken()){
            brokenComponent();
        } else{
            repairedComponent();
        }
    }

    private void brokenComponent(){ /*Si la maquina estava trencada abans no notifica, si no estava trencada notifica */
        boolean wasBroken = isBroken();
        brokenComponents += 1;
        if(!wasBroken){
            setBroken();
        }
    }

    private void repairedComponent(){ /*Si la maquina estava arreglada abans no notifica, si no estava arreglada notifica */
        boolean wasBroken = isBroken();
        brokenComponents -= 1;
        if(wasBroken){
            repair();
        }
    }

    private void notifyBroken(){
        setChanged();
        notifyObservers(this);
    }
}


