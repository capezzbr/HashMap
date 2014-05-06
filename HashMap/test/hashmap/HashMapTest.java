package hashmap;

import org.junit.Test;
import static org.junit.Assert.*;

/**
 * @author capezzbr
 */
public class HashMapTest {

   /**
    * Test of hasElement method, of class HashMap.
    */
   @Test
   public void hasElementCheck() {
      System.out.println("* HashMap JUnit4Test: hasElementCheck()");
      HashMap<String, Integer> map = new HashMap<>(5);
      
      // not existent key
      assertNull(map.get(null));
      assertNull(map.get("key"));
      
      // existent null key
      map.put(null, 0);
      assertTrue(map.get(null) == 0);
      
      // existent key
      map.put("key", 1);
      assertTrue(map.get("key") == 1);
   }

   /**
    * Test of put / get method, of class HashMap.
    */
   @Test
   public void putGetCheck() {
      System.out.println("* HashMap JUnit4Test: putGetCheck()");
      HashMap<String, String> map = new HashMap<>(5);
      
      // null key test
      map.put(null, "nullKey");
      assertNotNull(map.hasElement(null));
      assertEquals(map.get(null), "nullKey");

      String[] keys = {"key1", "key2"};
      
      // insert all the keys inside the HashMap
      for (String key : keys) {
         map.put(key, key);
      }
      
      // check if the keys exist
      for (String key : keys) {
         assertNotNull(map.hasElement(key));
      }

      // check if the keys have the correct values
      for (String key : keys) {
         assertEquals(map.get(key), key);
      }
   }

   /**
    * Test of toString method, of class HashMap.
    */
   @Test
   public void toStringCheck() {
      System.out.println("* HashMap JUnit4Test: toStringCheck()");
      HashMap<String, Integer> map = new HashMap<>(5);

      assertEquals(map.toString(), "");
      
      map.put("first", 1);
      assertEquals(map.toString(), "[4] -> {first = 1}\n");
  
      map.put("second", 2);
      assertEquals(map.toString(), "[1] -> {second = 2}\n[4] -> {first = 1}\n");
      
      map.put("double", 4);
      assertEquals(map.toString(), "[0] -> {double = 4}\n[1] -> {second = 2}\n"
              + "[4] -> {first = 1}\n");
   }

}
