package com.websafe.flipper.helper;

import me.nullicorn.nedit.NBTReader;
import me.nullicorn.nedit.type.NBTCompound;


import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.Base64;

public class Decoder {
    public NBTCompound itemBytes(String encoded) {
        //decode encoded data.
        byte[] decodedData = Base64.getDecoder().decode(encoded);
        ByteArrayInputStream input = new ByteArrayInputStream(decodedData);

        //read input
        NBTCompound itemData = null;
        try {
            itemData = NBTReader.read(input);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return itemData.getList("i").getCompound(0);

    }
}
