package cl.cocio.androidtestionix.utilities;


import android.content.Context;
import android.util.Base64;
import android.util.Log;

import java.nio.charset.StandardCharsets;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESKeySpec;

import cl.cocio.androidtestionix.R;

public class DesEncryption {

    public static String encrypt(Context context, String plainText) {

        String encrypted = "";
        try {
            DESKeySpec keySpec = new DESKeySpec(context.getString(R.string.des_password).getBytes());
            SecretKeyFactory keyFactory = SecretKeyFactory.getInstance("DES");
            SecretKey key = keyFactory.generateSecret(keySpec);
            byte[] cleartext = plainText.getBytes(StandardCharsets.UTF_8);

            Cipher cipher = Cipher.getInstance("DES");
            cipher.init(Cipher.ENCRYPT_MODE, key);
            encrypted = Base64.encodeToString(cipher.doFinal(cleartext), Base64.DEFAULT);

        } catch (Exception e) {
            Log.e("Encrypt", "Error to encrypt", e);
        }

        return encrypted;
    }
}
