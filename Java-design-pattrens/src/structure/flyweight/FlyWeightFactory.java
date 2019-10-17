package structure.flyweight;

import java.util.HashMap;
import java.util.Map;

/**
 * 享元工厂角色
 */
public class FlyWeightFactory {
    private Map<String,FlyWeight> flyWeights=new HashMap<>(16);
    public FlyWeight getFlyWeight(String key){
        FlyWeight flyWeight = flyWeights.get(key);
        if(flyWeight!=null){
            System.out.println("具体享元"+key+"已经存在，被成功获取！");
        } else{
            flyWeight=new ConcreteFlyWeight(key);
            flyWeights.put(key, flyWeight);
        }
        return flyWeight;
    }
}
