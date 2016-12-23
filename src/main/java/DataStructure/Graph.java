package DataStructure;

import java.util.*;

/**
 * Created by SilverNarcissus on 2016/12/14.
 * Done on 2016/12/14
 */
public class Graph {
    private ArrayList<Edge> edges;

    private ArrayList<Vertex> vertices;

    private Map<Integer, Vertex> vertexMap;

    private ArrayList<Edge> edgeData;

    public Graph(ArrayList<Edge> fromEdges, ArrayList<Vertex> vertices) {
        this.vertices = vertices;
        //
        //初始化每一条边
        edges = new ArrayList<Edge>(fromEdges.size());
        for (Edge edge : fromEdges) {
            edges.add(new Edge(edge));
            //初始化顶点的出入度
            vertices.get(edge.from).out++;
            vertices.get(edge.to).in++;
        }
    }

    public void findCircle() {
        //拷贝一份数据
        copyData();
        findCircle(true);
    }

    private void findCircle(boolean isFirst) {
        //存放map的所有元素的索引
        ArrayList<Integer> index = new ArrayList<Integer>(vertexMap.keySet());
        //存放入度或出度为0的顶点的栈
        Stack<Vertex> stack = new Stack<Vertex>();
        //入度或出度为0的点压栈
        for (int i : index) {
            if (vertexMap.get(i).in == 0 || vertexMap.get(i).out == 0) {
                stack.push(vertexMap.get(i));
            }
        }
        //
        //删除每一个入度或出度为0的顶点的每一条边
        while (!stack.empty()) {
            Vertex v = stack.pop();
            //
            for (int i = 0; i < edgeData.size(); i++) {
                Edge edge = edgeData.get(i);
                if (edge.from == v.id || edge.to == v.id) {
                    //
                    vertexMap.get(edge.to).in--;
                    if (vertexMap.get(edge.to).in == 0) {
                        stack.add(vertexMap.get(edge.to));
                    }
                    //
                    vertexMap.get(edge.from).out--;
                    if (vertexMap.get(edge.from).out == 0) {
                        stack.add(vertexMap.get(edge.from));
                    }
                    edgeData.remove(i);
                    i--;
                }
            }
        }

        for (int i : index) {
            if (vertexMap.get(i).in == 0||vertexMap.get(i).out==0) {
                vertexMap.remove(i);
            }
        }
        //
        if (vertexMap.isEmpty() && isFirst) {
            System.out.println("No circle");
            clearCopy();
        } else if (vertexMap.isEmpty()) {
            System.out.println("Done!");
            clearCopy();
        } else {
            //System.out.println(num);
            printCircle();
        }
    }

    private void printCircle() {
        Vertex start = vertexMap.values().iterator().next();
        //找到入度不为0的顶点
        if (start == null) {
            System.err.println("Wrong in print circle");
            return;
        }
        //寻找环
        ArrayList<Integer> already = new ArrayList<Integer>();
        already.add(start.id);
        //System.out.println("startId:"+start.id);
        findLoop(start, already);
        for (int i = 0; i < edgeData.size(); i++) {
            if (edgeData.get(i).from == start.id) {
                start.out--;
                vertexMap.get(edgeData.get(i).to).in--;
                edgeData.remove(i);
                break;
            }
        }
        findCircle(false);
    }


    private int findNext(int from, int before) {
        boolean flag = false;
        if (before == -1) {
            flag = true;
        }

        for (Edge edge : edgeData) {
            if (edge.from == from && flag) {
                return edge.to;
            } else if (edge.from == from && edge.to == before) {
                flag = true;
            }
        }
        //not find
        return -1;
    }


    private void findLoop(Vertex now, ArrayList<Integer> already) {
        int before = -1;
        while (findNext(now.id, before) != -1) {
            int next = findNext(now.id, before);
            before = next;
            if (next == already.get(0)) {
                //System.out.println("nowID:"+now.id);
                //System.out.println("nextID:"+next);
                String result = "";
                for (int i = 0; i < already.size(); i++) {
                    result += already.get(i) + "->";
                }
                System.out.println(result + next);
                continue;
            }
            if (already.contains(next)) {
                continue;
            }
            already.add(next);
            findLoop(vertexMap.get(next), new ArrayList<Integer>(already));
            already.remove(already.size()-1);
        }
    }

    /**
     * 拷贝一份数据，便于对边和顶点集合进行操作
     */
    private void copyData() {
        vertexMap = new HashMap<Integer, Vertex>();
        for (Vertex v : vertices) {
            vertexMap.put(v.id, v);
        }
        edgeData = new ArrayList<Edge>(edges);
    }

    /**
     * 当临时数据被用过后，将其置为空，避免二次使用错误的临时数据
     */
    private void clearCopy() {
        vertexMap = null;
        edgeData = null;
    }
}
