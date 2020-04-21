package map;

import java.util.HashMap;
import java.util.Map;

/**
 * HashMap   链的树化
 */
public class MapTest {
    public static void main(String[] args) {
        //树化有个条件 链表长度>=64 才会树化 否则会先进行扩容 所有初始化链表长度为64
        Map<Obj, Obj> possion = new HashMap<>(64);

        /*
         * 链表树化 泊松分布  node数=9时 进行树化
         * 当 put 第8个节点的时候，
         * 取到首节点p，
         * bincount =0 这里 if p.next == null
         * 第一次循环 比较的就是第二个节点
         * 0 1 2 3 4 5 6
         * 2 3 4 5 6 7 8
         * 在循环到第7次的时候 bincount=6
         * p.next ==null bincount = 6
         * p.next = newNode(hash, key, value, null);
         * if binCount >= TREEIFY_THRESHOLD - 1   6>=7?false->break;
         * put 第九个节点
         * 在循环到第7次的时候 bincount=7
         * p.next ==null bincount = 7
         * p.next = newNode(hash, key, value, null);
         * if binCount >= TREEIFY_THRESHOLD - 1   7>=7?true->treeifyBin(tab, hash);//树化
         *
         * 树化条件 table.length < MIN_TREEIFY_CAPACITY 64  链表长度>=64 才会树化 否则会先进行扩容
         */
        for(int i=0;i<10;i++){
            Obj obj = new Obj(String.valueOf(1));
            possion.put(obj,obj);
        }
    }

}
class Obj {
    String obId;
    Obj(String obId){
        this.obId = obId;
    }
    @Override
    public int hashCode(){
        return 1;
    }
}