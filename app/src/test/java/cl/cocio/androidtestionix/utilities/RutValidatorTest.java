package cl.cocio.androidtestionix.utilities;

import org.junit.Test;

import static org.junit.Assert.*;

public class RutValidatorTest {

    @Test
    public void validate() {
        assertTrue(RutValidator.validate("195176698"));
        //assertTrue(RutValidator.validate("19.517.669-8"));
    }

    @Test
    public void format() {
        assertEquals("19.517.669-8", RutValidator.format("195176698"));
    }
}