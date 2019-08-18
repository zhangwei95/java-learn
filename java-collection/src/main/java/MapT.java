import java.util.*;

public class MapT {


    public static void main(String[] args) {
        // TODO Auto-generated method stub
        People p1=new People(10,"zw");
        People p2=new People(11,"zw");
        Teacher t1=new Teacher(21,"lll");
        Teacher t2=new Teacher(20,"lll");
        /**
         * Collection下面有2个子类，一个是 List ，一个是 set
         *
         */
        Collection arry=new ArrayList();

        Collection linkedList=new LinkedList();

        Collection vector=new Vector();

        Collection hashset=new HashSet();
        Collection treeSet=new TreeSet();
        /**
         * List常用子类
         * ArrayList：数组结构，特点：查询、修改速度快；增删稍慢，线程不同步，默认自动扩展0.5倍
         * LinkedList：链表结构，特点：增删速度快；查询、修改稍慢
         * Vector：数组结构，特点：查询、修改速度快，增删稍慢，程序同步，默认自动扩展1倍，可设置
         */
        ArrayList<Integer> arrayList=new ArrayList<Integer>();
        arrayList.add(1);
        arrayList.add(2);
        arrayList.add(3);
        arrayList.add(2);
        arrayList.add(3);
        ArrayList<Integer> arrayList2=new ArrayList<Integer>();
        arrayList.add(1);
        arrayList.add(2);
        arrayList.add(3);
        arrayList.containsAll(arrayList2);
//        Iterator<Integer> iterator =arrayList.iterator();
        LinkedList linkList=new LinkedList();

        Vector vector1=new Vector();
        Collections.synchronizedList(arrayList);
        /**
         * Set常用子类
         * hashSet：哈希表结构，保证唯一性的关键（hashCode  和 equals），
         * 首先对比hashCode，hashCode一样，才比较equals。
         * TreeSet：二叉树 结构，保证唯一性的关键（ Comparator 接口中的 Compare()  
         *  和  Compareble 接口中的 CompareTo()），用来排序 ,需要自己实现Compareble接口
         */
        Set hash=new HashSet();
        hash.add(t1);
        hash.add(t2);
        hash.add(p1);
        System.out.println(hash.size());

        TreeSet<People> hhs = new TreeSet<People>();
        hhs.add(t1);
        hhs.add(t2);
        hhs.add(p1);
        System.out.println(hhs.size());
        /**
         * Map常用子类
         * TreeMap、 HashMap、Hashtable
         *
         * TreeMap
         */
        Map<String,People> treeMap=new TreeMap<String,People>();//对象实现Comparable不然
        treeMap.put("zw", p1);
        treeMap.put("zw1", p1);
        //一般用法
        System.out.println("遍历keyset，找对应的value");
        for(Object key:treeMap.keySet())
        {
            System.out.println(((People)treeMap.get(key)).getName()+"  "+((People)treeMap.get(key)).getAge());
        }
        //通过Map.entrySet使用iterator遍历
        Iterator<Map.Entry<String,People>> iterator=treeMap.entrySet().iterator();
        System.out.println("通过Map.entrySet使用iterator遍历");
        while (iterator.hasNext()) {
            Map.Entry<String, People> entry = iterator.next();
            System.out.println("key  " + entry.getKey() + "\nvalue name " + entry.getValue().getName()+" age "
                    +entry.getValue().getAge());
        }
        //通过Map.entrySet遍历key和value 推荐，大容量时
        Map<String,People> hashMap=new HashMap<String,People>();//对象实现Comparable不然
        hashMap.put("zw", p1);
        hashMap.put("zw1", p1);
        System.out.println("通过Map.entrySet遍历key和value 推荐，大容量时");
        for(Map.Entry<String, People> entry:hashMap.entrySet())
        {
            System.out.println("key\n"+entry.getKey()+"\nvalue  "+
                    entry.getValue().getName()+" "+entry.getValue().getAge());
        }
        System.out.println("只遍历value");
        //只遍历value
        for(People p:hashMap.values())
        {
            System.out.println(p.getName()+"   "+p.getAge());
        }




    }
}
