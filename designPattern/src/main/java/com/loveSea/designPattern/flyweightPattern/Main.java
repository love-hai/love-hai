package com.loveSea.designPattern.flyweightPattern;

/**
 * @author xiahaifeng
 */

public class Main {
    public static void main(String[] args) {
        PaintingFactory paintingFactory = new PaintingFactory();
        FigurePainting figurePainting1 = paintingFactory.getFigurePainting("circle");
        FigurePainting figurePainting2 = paintingFactory.getFigurePainting("circle");
        if(figurePainting1!=figurePainting2){
            System.out.println("不是同一个对象");
        }else {
            System.out.println("是同一个对象");
        }
    }
}