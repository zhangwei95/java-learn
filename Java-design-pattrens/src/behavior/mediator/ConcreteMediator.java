package behavior.mediator;

import java.util.ArrayList;
import java.util.List;

/**
 * 具体中介者
 * 实现中介者接口，定义一个 List 来管理同事对象，协调各个同事角色之间的交互关系，因此它依赖于同事角色
 */
public class ConcreteMediator extends Mediator {
    private List<Colleague> colleagues = new ArrayList<>();

    @Override
    public void register(Colleague colleague) {
        if (!colleagues.contains(colleague)) {
            colleagues.add(colleague);
            colleague.setMedium(this);
        }
    }

    @Override
    public void relay(Colleague colleague) {
        for (Colleague ob : colleagues) {
            if (!ob.equals(colleague)) {
                ((Colleague) ob).receive();
            }
        }
    }
}
