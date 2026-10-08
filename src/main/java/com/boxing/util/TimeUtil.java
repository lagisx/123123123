package com.boxing.util;

public final class TimeUtil {
    private TimeUtil() {}
    public static String format(int s) { return String.format("%02d:%02d", s / 60, s % 60); }
}