/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package antivirus;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.zip.CRC32;

/**
 *
 * @author Alex
 */
public class GenCrc extends CRC32
{
    public long crcInput(String file)
    {
        try
        {
            InputStream input = new BufferedInputStream(new FileInputStream(file));
            int reader;
        
            while ((reader = input.read()) != -1)
            {
                this.update(reader);
            }
            input.close();
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
        return this.getValue();
    }
}