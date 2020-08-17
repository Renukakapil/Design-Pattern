package com.code.common.helpers;

import android.os.Build;
import android.os.StatFs;


public class DeviceMemoryHelper {

    public static boolean isMemoryAvailable(String path, long requiredSizeInMB){
        StatFs stat = new StatFs(path);
        long blockSize, availableBlocks;
        blockSize = stat.getBlockSizeLong();
        availableBlocks = stat.getAvailableBlocksLong();

        long availableMB=blockSize*availableBlocks;
        return (availableMB*1024*1024)>requiredSizeInMB;
    }
}
