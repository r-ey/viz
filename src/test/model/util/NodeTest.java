package model.util;

import model.component.Grade;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

// Unit tests for Node class
public class NodeTest {

    private Node<Integer> testIntegerNode;
    private Node<Float> testFloatNode;
    private Node<String> testStringNode;
    private Node<Grade> testGradeNode;

    public void setUp() {
        testIntegerNode = new Node<>(1);
        testFloatNode = new Node<>(1f);
        testStringNode = new Node<>("a");
        testGradeNode = new Node<>(new Grade(50f, 45f, false));
    }

    @Test
    public void testVariousNode() {
        setUp();

        assertEquals(1, testIntegerNode.object);
        assertEquals(1f, testFloatNode.object);
        assertEquals("a", testStringNode.object);
        assertEquals("Max Percentage : 50.00" + ", Real : 45.00\n", testGradeNode.object.toString());

        assertNull(testIntegerNode.next);
        assertNull(testFloatNode.next);
        assertNull(testStringNode.next);
        assertNull(testGradeNode.next);
    }
}
