package com.websafe.flipper.algorithm;

import com.websafe.flipper.algorithm.GetItemInfo;
import me.nullicorn.nedit.type.NBTCompound;

public class GetValue {
    public static final GetItemInfo info = new GetItemInfo();

    public void Value(NBTCompound nbtData) {
        info.ItemInfo(nbtData);
    }
}
