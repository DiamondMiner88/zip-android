package com.github.diamondminer88.zip;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@SuppressWarnings("unused")
public class ZipEntry {
    private final long innerPtr = 0;

    /**
     * Called by JNI.
     */
    private ZipEntry() {
    }

    /**
     * Get the name of the file.
     * <br/>
     * It is dangerous to use this name directly when extracting an archive.
     * It may contain an absolute path (/etc/shadow), or break out of the current directory (../runtime).
     * Carelessly writing to these paths allows an attacker to craft a ZIP archive that will overwrite critical files.
     */
    @NotNull
    public native String getName();

    /**
     * Get the comment of the file
     */
    @NotNull
    public native String getComment();

//    /**
//     * Get the time the file was last modified.
//     * @return UNIX time
//     */
//    private native long getLastModified();

    /**
     * Returns whether the file is a directory.
     */
    public native long isDir();

    /**
     * Get the unix mode for this file.
     */
    @Nullable
    public native Long getMode();

    /**
     * Get the CRC32 hash of the original file.
     */
    public native int getCRC32();

    /**
     * Get the extra data of the zip header for this file.
     */
    public native byte[] getExtraData();

    /**
     * Get the size of the file (bytes) when uncompressed.
     */
    public native long getSize();

    /**
     * Get the size of the file (in bytes) in the archive.
     */
    public native long getCompressedSize();

    /**
     * Reads this file entry's data (decompressed or not depending on how this entry was opened)
     */
    public native byte[] readEntry();
}