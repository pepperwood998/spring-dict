package com.tuan.exercise.sprdict.util;

public class CommonUtil {
    private CommonUtil() {
    }

    public static int getTotalPageCount(long totalWordCount, int wordCount) {
        return (int) Math.ceil((double) totalWordCount / wordCount);
    }

    public static int getPageStart(int page, int pageCount, int totalPageCount) {
        int pageStart = 1;

        int lowerMoveThreshold = (int) (Math.ceil((double) pageCount / 2) + 1L);
        if (page < lowerMoveThreshold) {
            pageStart = 1;
        } else {
            int upperStopThreshold = totalPageCount - pageCount / 2 + 1;
            if (page < upperStopThreshold) {
                pageStart = page - (int) (Math.ceil((double) pageCount / 2) - 1L);
            } else {
                pageStart = totalPageCount - pageCount + 1;
            }
        }

        return pageStart;
    }

    public static int getPageEnd(int pageStart, int pageCount) {
        return pageStart + pageCount - 1;
    }
}
