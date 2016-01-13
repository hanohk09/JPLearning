package Utilities;

import android.util.Base64;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * Created by Hano on 20/12/2015.
 */
public class HashUtils {
    public static String computeSHAHash(String password)
    {
        String SHAHash = null;

        try
        {
            MessageDigest digest = MessageDigest.getInstance("SHA-1");
            digest.update(password.getBytes("ASCII"));
            byte[] data = digest.digest();

            StringBuffer sb = new StringBuffer();
            String hex = Base64.encodeToString(data, 0, data.length, 0);
            sb.append(hex);

            SHAHash = sb.toString();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return SHAHash;
    }


    public static String computeMD5Hash(String password)
    {
        String MD5Hash = null;

        try {
            MessageDigest digest = MessageDigest.getInstance("MD5");
            digest.update(password.getBytes());
            byte[] data = digest.digest();

            StringBuffer sb = new StringBuffer();
            for (int i = 0; i < data.length; i++)
            {
                String h = Integer.toHexString(0xFF & data[i]);
                while (h.length() < 2)
                    h = "0" + h;
                sb.append(h);
            }

            MD5Hash = sb.toString();

        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return MD5Hash;
    }
}
