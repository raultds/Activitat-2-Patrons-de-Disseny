import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class MachineComponentTest {
    private MachineComposite mc;
    private Machine m1;
    private Machine m2;
    private Machine m3;
    private Machine m4;

    @BeforeEach
    void init(){
        mc = new MachineComposite();
        m1 = new Machine();
        m2 = new Machine();
        m3 = new Machine();
        m4 = new Machine();
    }

    @Test
    void setBrokenAndIsBrokenTestMachineTest(){
        m1.setBroken();
        m2.setBroken();
        assertEquals(m1.broken, true);
        assertEquals(m2.isBroken(), true);
        assertEquals(m3.isBroken(), false);
        assertEquals(m3.broken, false);
    }

    @Test
    void repairAndIsBrokenTestMachineTest(){
        m1.setBroken();
        m2.setBroken();
        m1.repair();
        m2.repair();
        m3.repair();
        assertEquals(m1.isBroken(), false);
        assertEquals(m2.broken, false);
        assertEquals(m3.isBroken(), false);
        assertEquals(m3.broken, false);
    }

    @Test
    void addComponentAndBreaksTest(){
        mc.addComponent(m1);
        assertEquals(mc.isBroken(), false);
        m1.setBroken();
        assertEquals(mc.isBroken(), true);
    }

    @Test
    void addBrokenComponentTest(){
        assertEquals(mc.isBroken(), false);
        m1.setBroken();
        mc.addComponent(m1);
        assertEquals(mc.isBroken(), true);
    }

    @Test
    void addBrokenComponentAndRepairTest(){
        m1.setBroken();
        mc.addComponent(m1);
        m1.repair();
        assertEquals(mc.isBroken(), false);
    }

}
