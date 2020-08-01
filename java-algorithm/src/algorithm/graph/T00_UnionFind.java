package algorithm.graph;

import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Stack;

/**
 * @Description: 并查集
 * @Author: zhangwei
 * @Date: 2020/7/14 14:44
 */
public class T00_UnionFind {
    public static class Node<T>{
        T value;

        public Node(T t){
            this.value = t;
        }
    }


    public static class UnionSet<T>{
        private HashMap<T,Node<T>> nodes;

        // 维护父关系
        private HashMap<Node<T>,Node<T>> parents;

        // 父节点
        private HashMap<Node<T>,Integer> sizeMap;



        public UnionSet(Collection<T> values){
            nodes = new HashMap<>();
            parents = new HashMap<>();
            sizeMap = new HashMap<>();
            for (T cur : values){
                Node<T> node = new Node<>(cur);
                nodes.put(cur, node);
                parents.put(node,node);
                sizeMap.put(node,1);
            }
        }

        public Node<T> findFather(Node<T> cur){
            Stack<Node<T>> path = new Stack<>();

            while (cur != parents.get(cur)){
                path.push(cur);
                cur = parents.get(cur);
            }

            // 父子链平铺优化
            while (!path.isEmpty()){
                parents.put(path.pop(),cur);
            }

            return cur;
        }

        public boolean isSameSet(T v1,T v2){
            if (!nodes.containsKey(v1) || !nodes.containsKey(v2)){
                return false;
            }

            return findFather(nodes.get(v1)) == findFather(nodes.get(v2));
        }


        public void union(T v1,T v2){

            if (!nodes.containsKey(v1) || !nodes.containsKey(v2)){
                return ;
            }

            Node<T> v1Father = findFather(nodes.get(v1));

            Node<T> v2Father = findFather(nodes.get(v2));

            if (v1Father != v2Father){
                Integer v1Size = sizeMap.get(v1Father);
                Integer v2Size = sizeMap.get(v2Father);

                Node<T> big = v2Size > v1Size ? v2Father : v1Father;
                Node<T> small = big == v1Father? v2Father : v1Father;
                sizeMap.put(big,v1Size + v2Size);
                parents.put(small,big);
                sizeMap.remove(small);

            }

        }

        public int getSize(){
            return sizeMap.size();
        }

    }












}
