package structure.composite;

public class CompositePatternTest {
    public static void main(String[] args) {
        Component c1=new Composite();
        Component c2=new Composite();
        Component l1=new Leaf("leaf1");
        Component l2=new Leaf("leaf2");
        Component l3=new Leaf("leaf3");
        c1.add(l1);
        c1.add(l2);
        c2.add(l3);
        c1.operation();
        c2.operation();
    }
}

