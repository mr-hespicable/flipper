package com.github.websafe.helper;

import me.nullicorn.nedit.NBTReader;
import me.nullicorn.nedit.type.NBTCompound;


import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.Base64;

public class Decoder {
    public NBTCompound itemBytes(String encoded) throws IOException {
        //decode encoded data.
        byte[] decodedData = Base64.getDecoder().decode(encoded);
        ByteArrayInputStream input = new ByteArrayInputStream(decodedData);

        //do nbt stuff
        NBTCompound itemData = NBTReader.read(input);
        return itemData; //TODO: add more ways to get the data off of itemBytes

    }
}
