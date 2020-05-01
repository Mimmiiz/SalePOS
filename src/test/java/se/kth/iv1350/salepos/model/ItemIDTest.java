package se.kth.iv1350.salepos.model;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class ItemIDTest {
  
    @Test
    public void testCheckItemIDMatchWhenTrue() {
        ItemID oneItemIDInstance = new ItemID(100);
        ItemID anotherItemIDInstance = new ItemID(100);
        boolean result = oneItemIDInstance.checkItemIDMatch(oneItemIDInstance, anotherItemIDInstance);
        assertTrue(result, "The result was not the expected result.");
    }
    
    @Test
    public void testCheckItemIDMatchWhenFalse() {
        ItemID oneItemIDInstance = new ItemID(199);
        ItemID anotherItemIDInstance = new ItemID(100);
        boolean result = oneItemIDInstance.checkItemIDMatch(oneItemIDInstance, anotherItemIDInstance);
        assertFalse(result, "The result was not the expected result.");
    }
}
