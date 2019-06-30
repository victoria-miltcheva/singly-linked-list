import org.junit.jupiter.api.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.jupiter.api.BeforeEach;

public class SinglyLinkedListTest {
  SinglyLinkedList<String> list;

  @BeforeEach
  public void BeforeEach() {
    list = new SinglyLinkedList<String>();

    list.push("Apple");
    list.push("Orange");
    list.push("Mango");
    list.push("Lemon");
    list.push("Star fruit");
  }

  @Test
  public void test_getLength() {
    assertEquals(5, list.getLength());
    
    list.pop();
    assertEquals(4, list.getLength());
    
    list.remove(1);
    assertEquals(3, list.getLength());
    
    list.clear();
    assertEquals(0, list.getLength());
  }

  @Test
  public void test_shift() {
    assertEquals("Apple", list.shift());
    assertEquals("Orange", list.shift());
    assertEquals("Mango", list.shift());
    assertEquals("Lemon", list.shift());
    assertEquals("Star fruit", list.shift());
  }

  @Test
  public void test_unshift() {
    list.unshift("Banana");
    
    assertEquals("Banana", list.getHead());
    assertEquals(6, list.getLength());
  }

  @Test
  public void test_getHead() {
    assertEquals("Apple", list.getHead());
    
    list.insert(0, "Blueberry");
    assertEquals("Blueberry", list.getHead());
  }

  @Test
  public void test_getTail() {
    assertEquals("Star fruit", list.getTail());
    
    list.pop();
    assertEquals("Lemon", list.getTail());
  }

  @Test
  public void test_push() {
    list.push("Jackfruit");
    list.push("Blackberry");
    list.push("Lychee");
    
    assertEquals(list.get(5), "Jackfruit");
    assertEquals(list.get(6), "Blackberry");
    assertEquals(list.get(7), "Lychee");
    assertEquals(8, list.getLength());
    
    list.clear();
    list.push("Melon");
    assertEquals(list.get(0), "Melon");
    assertEquals(1, list.getLength());
  }

  @Test
  public void test_pop() {
    list.pop();
    assertEquals("Lemon", list.get(3));
    assertEquals(4, list.getLength());
    
    list.pop();
    assertEquals("Mango", list.get(2));
    assertEquals(3, list.getLength());
    
    list.pop();
    list.pop();
    list.pop();
    
    assertEquals(0, list.getLength());
    assertTrue(list.isEmpty());
  }

  @Test
  public void test_insert() {
    list.insert(0, "Dragon fruit");
    assertEquals("Dragon fruit", list.peek());
    assertEquals(6, list.getLength());
    
    list.insert(5, "Rambutan");
    assertEquals("Rambutan", list.getTail());
    assertEquals(7, list.getLength());
    
    list.insert(3, "Durian");
    assertEquals("Durian", list.get(3));
    assertEquals(8, list.getLength());
  }

  @Test
  public void test_remove() {    
    list.remove(4);
    assertEquals(4, list.getLength());
    assertEquals("Lemon", list.get(3));
    
    list.remove(0);
    assertEquals(3, list.getLength());
    assertEquals("Orange", list.get(0));
    
    list.remove(2);
    assertEquals(2, list.getLength());
    assertEquals("Mango", list.get(1));
    
    list.remove(0);
    assertEquals(1, list.getLength());
    assertEquals("Mango", list.get(0));
    
    list.remove(0);
    assertEquals(0, list.getLength());
    assertTrue(list.isEmpty());
  }

  @Test
  public void test_get() {
    assertEquals("Apple", list.get(0));
    assertEquals("Star fruit", list.get(4));
    assertEquals("Orange", list.get(1));
    
    list.remove(0);
    assertEquals("Orange", list.get(0));
    
    list.remove(3);
    assertEquals("Lemon", list.get(2));
  }
  
  @Test
  public void test_peek() {
    assertEquals("Apple", list.peek());
    
    list.shift();
    assertEquals("Orange", list.peek());
    
    list.unshift("Guava");
    assertEquals("Guava", list.peek());
  }

  @Test
  public void test_clear() {
    list.clear();
    
    assertEquals(0, list.getLength());
    assertTrue(list.isEmpty());
  }
  
  @Test
  public void test_isEmpty() {
    assertFalse(list.isEmpty());
    
    list.pop();
    list.pop();
    list.pop();
    list.pop();
    list.pop();
    
    assertTrue(list.isEmpty());
  }
  
  @Test
  public void test_iterable() {
    int i = 0;
    
    for (String item : list) {
      assertEquals(item, list.get(i));
      i++;
    }
    
    assertEquals(i, list.getLength());
  }
}
