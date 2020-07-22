package cl.cocio.androidtestionix.utilities;

import android.content.Context;

import androidx.test.InstrumentationRegistry;
import androidx.test.core.app.ApplicationProvider;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;


public class DesEncryptionTest {
    private Context context = ApplicationProvider.getApplicationContext();

    @Before
    public void setUp() {
        context = ApplicationProvider.getApplicationContext();
    }

    @Test
    public void encrypt() {
        assertEquals("FyaSTkGi8So=", DesEncryption.encrypt(context, "1-9"));
    }
}