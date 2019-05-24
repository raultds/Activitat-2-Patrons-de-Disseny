import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class MachineComponentTest {
    private MachineComposite mc;
    private MachineComposite mc1;
    private Machine m1;
    private Machine m2;
    private Machine m3;
    private Machine m4;

    @BeforeEach
    void init(){
        mc = new MachineComposite();
        mc1 = new MachineComposite();
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
    void addBrokenComponentAndRepairTes(){
        m1.setBroken();
        mc.addComponent(m1);
        assertEquals(mc.isBroken(), true);
        m1.repair();
        assertEquals(mc.isBroken(), false);
    }

    @Test
    void notAllRepairedTest(){
        mc.addComponent(mc1);
        mc.addComponent(m1);
        mc.addComponent(m2);
        m1.setBroken();
        m2.setBroken();
        m1.repair();
        assertEquals(mc.isBroken(), true);
    }

    @Test
    void addBrokenExpandsTest(){
        mc.addComponent(mc1);
        mc.addComponent(m1);
        mc.addComponent(m2);
        mc1.addComponent(m3);
        m4.setBroken();
        mc1.addComponent(m4);
        assertEquals(mc1.isBroken(), true);
        assertEquals(mc.isBroken(), true);
    }

    @Test
    void brokenExpandsTest(){
        mc.addComponent(mc1);
        mc.addComponent(m1);
        mc.addComponent(m2);
        mc1.addComponent(m3);
        mc1.addComponent(m4);
        m4.setBroken();
        assertEquals(mc1.isBroken(), true);
        assertEquals(mc.isBroken(), true);
    }

    @Test
    void repairExpandsTest(){
        mc.addComponent(mc1);
        mc.addComponent(m1);
        mc.addComponent(m2);
        mc1.addComponent(m3);
        m4.setBroken();
        mc1.addComponent(m4);
        m4.repair();
        assertEquals(mc1.isBroken(), false);
        assertEquals(mc.isBroken(), false);
    }

}
