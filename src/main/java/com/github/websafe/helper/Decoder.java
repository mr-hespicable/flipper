package com.github.websafe.helper;

import dev.dewy.nbt.Nbt;
import dev.dewy.nbt.tags.collection.CompoundTag;

import java.io.IOException;

public class Decoder {
    public static final Nbt NBT = new Nbt();

    public CompoundTag itemBytes(String encoded) throws IOException {
        try {
            CompoundTag tag = NBT.fromBase64(encoded).toJson(512, );
            return tag;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
