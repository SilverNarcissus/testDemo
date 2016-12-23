package DataStructure;

import org.junit.*;

import java.io.ByteArrayOutputStream;
import java.io.OutputStream;
import java.io.PrintStream;
import java.util.ArrayList;

import static org.junit.Assert.*;

/**
 * Created by SilverNarcissus on 2016/12/14.
 */
public class GraphTest {
    private static PrintStream out;
    private static ByteArrayOutputStream output;
    private static final String lineSeparator=System.getProperty("line.separator", "\n");
    private static final String DONE="Done!"+lineSeparator;
    private static final String NO_CIRCLE="No circle"+lineSeparator;
    @BeforeClass
    public static void setOutToString(){
        out=System.out;
        output = new ByteArrayOutputStream();
        System.setOut(new PrintStream(output));
    }

    @Test
    public void findCircle1() throws Exception {
        ArrayList<Vertex> vertices = new ArrayList<Vertex>();
        for (int i = 0; i < 4; i++) {
            vertices.add(new Vertex(i));
        }
        ArrayList<Edge> edges=new ArrayList<Edge>();
        edges.add(new Edge(0,1,2));
        edges.add(new Edge(1,2,3));
        edges.add(new Edge(2,3,4));
        edges.add(new Edge(3,0,5));
        Graph graph=new Graph(edges,vertices);
        graph.findCircle();
        assertEquals("0->1->2->3->0"
                +lineSeparator+DONE,output.toString());
        out.println(output.toString());
    }

    @Test
    public void findCircle2() throws Exception {
        ArrayList<Vertex> vertices = new ArrayList<Vertex>();
        for (int i = 0; i < 4; i++) {
            vertices.add(new Vertex(i));
        }
        ArrayList<Edge> edges=new ArrayList<Edge>();
        edges.add(new Edge(0,1,2));
        edges.add(new Edge(1,2,3));
        edges.add(new Edge(2,3,4));
        Graph graph=new Graph(edges,vertices);
        graph.findCircle();
        assertEquals(NO_CIRCLE,output.toString());
        out.println(output.toString());
    }

    @Test
    public void findCircle3() throws Exception {
        ArrayList<Vertex> vertices = new ArrayList<Vertex>();
        for (int i = 0; i < 6; i++) {
            vertices.add(new Vertex(i));
        }
        ArrayList<Edge> edges=new ArrayList<Edge>();
        edges.add(new Edge(0,1,2));
        edges.add(new Edge(1,2,3));
        edges.add(new Edge(2,3,4));
        edges.add(new Edge(3,4,4));
        edges.add(new Edge(4,5,2));
        edges.add(new Edge(5,2,2));
        edges.add(new Edge(5,3,4));
        Graph graph=new Graph(edges,vertices);
        graph.findCircle();
        assertEquals("2->3->4->5->2"+lineSeparator
                +"3->4->5->3"+lineSeparator
                +DONE,output.toString());
        out.println(output.toString());
    }

    @Test
    public void findCircle4() throws Exception {
        ArrayList<Vertex> vertices = new ArrayList<Vertex>();
        for (int i = 0; i < 5; i++) {
            vertices.add(new Vertex(i));
        }
        ArrayList<Edge> edges=new ArrayList<Edge>();
        edges.add(new Edge(0,1,2));
        edges.add(new Edge(1,2,3));
        edges.add(new Edge(2,3,4));
        edges.add(new Edge(3,1,2));
        edges.add(new Edge(2,4,2));
        Graph graph=new Graph(edges,vertices);
        graph.findCircle();
        assertEquals("1->2->3->1"
                +lineSeparator+DONE,output.toString());
        out.println(output.toString());
    }

    @Test(timeout = 500)
    public void findCircle5() throws Exception {
        ArrayList<Vertex> vertices = new ArrayList<Vertex>();
        for (int i = 0; i < 6; i++) {
            vertices.add(new Vertex(i));
        }
        ArrayList<Edge> edges=new ArrayList<Edge>();
        edges.add(new Edge(0,1,2));
        edges.add(new Edge(1,2,3));
        edges.add(new Edge(2,3,4));
        edges.add(new Edge(3,4,4));
        edges.add(new Edge(4,5,2));
        edges.add(new Edge(5,3,2));
        edges.add(new Edge(5,2,4));
        Graph graph=new Graph(edges,vertices);
        graph.findCircle();
        assertEquals("2->3->4->5->2"+lineSeparator
                +"3->4->5->3"+lineSeparator
                +DONE,output.toString());
        out.println(output.toString());
    }

    @Test
    public void findCircle6() throws Exception {
        ArrayList<Vertex> vertices = new ArrayList<Vertex>();
        for (int i = 0; i < 7; i++) {
            vertices.add(new Vertex(i));
        }
        ArrayList<Edge> edges=new ArrayList<Edge>();
        edges.add(new Edge(0,1,2));
        edges.add(new Edge(1,2,3));
        edges.add(new Edge(2,3,4));
        edges.add(new Edge(3,4,4));
        edges.add(new Edge(4,5,2));
        edges.add(new Edge(5,2,2));
        edges.add(new Edge(5,3,4));
        edges.add(new Edge(5,6,4));
        edges.add(new Edge(6,2,4));
        Graph graph=new Graph(edges,vertices);
        graph.findCircle();
        assertEquals("2->3->4->5->2"+lineSeparator
                +"2->3->4->5->6->2"+lineSeparator
                +"3->4->5->3"+lineSeparator
                +DONE,output.toString());
        out.println(output.toString());
    }

    @Test
    public void findCircle7() throws Exception {
        ArrayList<Vertex> vertices = new ArrayList<Vertex>();
        for (int i = 0; i < 9; i++) {
            vertices.add(new Vertex(i));
        }
        ArrayList<Edge> edges=new ArrayList<Edge>();
        edges.add(new Edge(0,1,2));
        edges.add(new Edge(1,2,3));
        edges.add(new Edge(2,3,4));
        edges.add(new Edge(3,4,4));
        edges.add(new Edge(4,1,2));
        edges.add(new Edge(3,5,2));
        edges.add(new Edge(5,6,4));
        edges.add(new Edge(6,7,4));
        edges.add(new Edge(6,8,4));
        edges.add(new Edge(7,5,4));
        Graph graph=new Graph(edges,vertices);
        graph.findCircle();
        assertEquals("1->2->3->4->1"+lineSeparator
                +"5->6->7->5"+lineSeparator
                +DONE,output.toString());
        out.println(output.toString());
    }

    @Test
    public void findCircle8() throws Exception {
        ArrayList<Vertex> vertices = new ArrayList<Vertex>();
        for (int i = 0; i < 9; i++) {
            vertices.add(new Vertex(i));
        }
        ArrayList<Edge> edges=new ArrayList<Edge>();
        edges.add(new Edge(0,1,2));
        edges.add(new Edge(1,2,3));
        edges.add(new Edge(2,0,4));
        edges.add(new Edge(3,4,4));
        edges.add(new Edge(4,5,2));
        edges.add(new Edge(5,3,2));
        edges.add(new Edge(6,7,4));
        edges.add(new Edge(7,8,4));
        edges.add(new Edge(8,6,4));
        Graph graph=new Graph(edges,vertices);
        graph.findCircle();
        assertEquals("0->1->2->0"+lineSeparator
                +"3->4->5->3"+lineSeparator
                +"6->7->8->6"+lineSeparator
                +DONE,output.toString());
        out.println(output.toString());
    }

    @Test
    public void findCircle9() throws Exception {
        ArrayList<Vertex> vertices = new ArrayList<Vertex>();
        for (int i = 0; i < 10; i++) {
            vertices.add(new Vertex(i));
        }
        ArrayList<Edge> edges=new ArrayList<Edge>();
        edges.add(new Edge(0,1,2));
        edges.add(new Edge(1,2,3));
        edges.add(new Edge(2,0,4));
        edges.add(new Edge(2,3,2));
        edges.add(new Edge(3,9,4));
        edges.add(new Edge(9,2,4));
        edges.add(new Edge(3,4,4));
        edges.add(new Edge(4,5,2));
        edges.add(new Edge(5,3,2));
        edges.add(new Edge(6,7,4));
        edges.add(new Edge(7,8,4));
        edges.add(new Edge(8,6,4));
        Graph graph=new Graph(edges,vertices);
        graph.findCircle();
        assertEquals("0->1->2->0"+lineSeparator
                +"2->3->9->2"+lineSeparator
                +"3->4->5->3"+lineSeparator
                +"6->7->8->6"+lineSeparator
                +DONE,output.toString());
        out.println(output.toString());
    }

    @Test
    public void findCircle10() throws Exception {
        ArrayList<Vertex> vertices = new ArrayList<Vertex>();
        for (int i = 0; i < 9; i++) {
            vertices.add(new Vertex(i));
        }
        ArrayList<Edge> edges=new ArrayList<Edge>();
        edges.add(new Edge(1,2,2));
        edges.add(new Edge(2,3,3));
        edges.add(new Edge(3,4,4));
        edges.add(new Edge(4,5,2));
        edges.add(new Edge(4,8,4));
        edges.add(new Edge(5,7,4));
        edges.add(new Edge(7,6,4));
        edges.add(new Edge(8,7,2));
        edges.add(new Edge(6,5,2));
        edges.add(new Edge(5,1,4));
        edges.add(new Edge(5,2,4));

        Graph graph=new Graph(edges,vertices);
        graph.findCircle();
        out.println(output.toString());
        assertEquals("1->2->3->4->5->1"+lineSeparator
                +"1->2->3->4->8->7->6->5->1"+lineSeparator
                +"2->3->4->5->2"+lineSeparator
                +"2->3->4->8->7->6->5->2"+lineSeparator
                +"5->7->6->5"+lineSeparator
                +DONE,output.toString());
    }

    @Test
    public void findCircle11() throws Exception {
        ArrayList<Vertex> vertices = new ArrayList<Vertex>();
        for (int i = 0; i < 5; i++) {
            vertices.add(new Vertex(i));
        }
        ArrayList<Edge> edges=new ArrayList<Edge>();
        edges.add(new Edge(1,2,2));
        edges.add(new Edge(2,3,3));
        edges.add(new Edge(3,1,4));
        edges.add(new Edge(2,4,2));
        edges.add(new Edge(4,1,4));

        Graph graph=new Graph(edges,vertices);
        graph.findCircle();
        out.println(output.toString());
        assertEquals("1->2->3->1"+lineSeparator
                +"1->2->4->1"+lineSeparator
                +DONE,output.toString());
    }
    @After
    public void clear(){
        output.reset();
    }

    @AfterClass
    public static void resetOutPut(){
        System.setOut(out);
    }
}