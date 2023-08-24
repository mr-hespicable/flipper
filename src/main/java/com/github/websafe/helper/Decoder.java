package com.github.websafe.helper;

import dev.dewy.nbt.Nbt;
import dev.dewy.nbt.api.Tag;
import dev.dewy.nbt.tags.collection.CompoundTag;

import java.io.File;
import java.io.IOException;

public class Decoder {
    public static final Nbt NBT = new Nbt();
    private static final File JSON_FILE = new File("samples/sample.json");

    public CompoundTag itemBytes(String encoded) throws IOException {
        try {
            CompoundTag tag = NBT.fromBase64(encoded);
            NBT.toJson(tag, JSON_FILE);
            return tag;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
