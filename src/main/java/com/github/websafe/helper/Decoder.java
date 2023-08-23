package com.github.websafe.helper;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.Arrays;
import java.util.Base64;

import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.CompressedStreamTools;

import java.util.zip.GZIPInputStream;

public class Decoder {
    public void itemBytes(String encoded) {
        String decompressed;
        try {
            byte[] decoded = Base64.getDecoder().decode(encoded);
            ByteArrayInputStream inputStream = new ByteArrayInputStream(decoded);
            ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
            GZIPInputStream gzipInput = new GZIPInputStream(inputStream);

            byte[] buffer = new byte[1024];
            int len;
            while ((len = gzipInput.read(buffer)) > 0) {
                outputStream.write(buffer, 0, len);
            }

            gzipInput.close();
            outputStream.close();

            decompressed = outputStream.toString();
            System.out.println(Arrays.toString(new String[]{"asdjfaisudhfoiusdah" + decompressed}));



        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }
}
