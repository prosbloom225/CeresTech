package com.prosbloom.cerestech.util;

import static com.gregtechceu.gtceu.api.GTValues.*;

public class ColorUtils {
    public static int getRedoxColorFromTier(int tier) {
        switch (tier) {
            case EV:
                return 0x5E5560;
            case IV:
                return 0x9A9B9A;
            case LuV:
                return 0xE08E8E;
            case ZPM:
                return 0x6FACAC;
            case UV:
                return 0x7EA47E;
            case UHV:
                return 0x847E8B;
            default:
                return 0xFFFFFF;
        }
    }
}
