package com.LoveSea.fengCore.study.designPattern.flyweightPattern;

import java.util.HashMap;
import java.util.Map;

/**
 * @author xiahaifeng
 */

public class PaintingFactory {
    Map<String, FigurePainting> figurePaintingMap = new HashMap<>();
    public FigurePainting getFigurePainting(String figure) {
        return figurePaintingMap.computeIfAbsent(figure, k -> new FigurePainting(figure));
    }
}