package edu.up.cs371.epp.photofunpattern;

import android.graphics.Bitmap;
import android.graphics.Color;

import java.util.ArrayList;

/**
 *  class GrayFilter changes the image manipulation behavior of its parent
 *  PhotoFilter to convert the image to gray scale.
 *
 *  @author Edward C. Epp
 *  @version November 2017
 *  https://github.com/edcepp/PhotoFunPattern
 */

public class SmoothingFilter extends PhotoFilter {

    /*
    * tranformPixel This method overrides the transformPixel in the parent
    * class. It transforms a color pixel to gray by averaging its three RGB
    * components.
    *
    * @param inPixel is a 32 bit pixel that contains RGB color values
    * @return a new Pixel in which each of the RGB components is their averaged
    * value
    */

    public int transformPixel(int p5, int p1, int p2, int p3, int p4, int p6, int p7, int p8, int p9) {
        int intensity = (Color.red(p5) + Color.green(p5) +
                Color.blue(p5)) / 3;
        return Color.argb(Color.alpha(p5), intensity,intensity,intensity);
    }

    @Override
    public Bitmap apply(Bitmap inBmp) {
        int width = inBmp.getWidth();
        int height = inBmp.getHeight();

        Bitmap newBmp = Bitmap.createBitmap(width, height, inBmp.getConfig());

        for (int w = 0; w < width; w++) {
            for (int h = 0; h < height; h++) {
                int p5 = inBmp.getPixel(w,h);
                int p1 = p5;
                int p2 = p5;
                int p3 = p5;
                int p4 = p5;
                int p6 = p5;
                int p7 = p5;
                int p8 = p5;
                int p9 = p5;
                if(h+1<height){
                    p8 = inBmp.getPixel(w, h+1);
                    if (w-1 >= 0){
                        p7 = inBmp.getPixel(w-1, h+1);
                    } else if (w+1 < width){
                        p9 = inBmp.getPixel(w+1, h);
                    }
                } else if (h-1 >= 0){
                    p2 = inBmp.getPixel(w, h-1);
                    if(w-1>=0){
                        p1 = inBmp.getPixel(w-1, h);
                    } else if (w+1 < width){
                        p3 = inBmp.getPixel(w+1, h);
                    }
                }

                if(w-1>=0){
                    p4 = inBmp.getPixel(w-1, h);
                } else if (w+1 < width){
                    p6 = inBmp.getPixel(w+1, h);
                }

                int outPixel = transformPixel(p5, p1, p2, p3, p4, p6, p7, p8, p9);
                newBmp.setPixel(w, h, outPixel);
            }
        }
        return newBmp;

    }

    /*
    @Override
    public Bitmap apply(Bitmap inBmp){
        int width = inBmp.getWidth();
        int height = inBmp.getHeight();

        Bitmap newBmp = Bitmap.createBitmap(width, height, inBmp.getConfig());

        for (int w = 0; w < width; w++) {
            for (int h = 0; h < height; h++) {
                int index = 0;
                int surroundPix[] = new int[8];
                for(int x = -1; x<=1; x++){
                    for(int y = -1; y<=1; y++){
                        if(w+x >=0 && h+y >= 0){
                            if(w+x < width && h+y > height){
                                if(x != 0 || y != 0){
                                    surroundPix[index] =  inBmp.getPixel(w+x,h+y);
                                    index++;
                                }
                            }
                        }
                    }

                }

                int inPixel = inBmp.getPixel(w,h);
                if(w != 0){

                }
                int total = 0;
                for(int j =0; j<index; j++){
                    total += surroundPix[j]/10;
                }
                //total /= 10;

                total += inPixel/5;

                //int outPixel = transformPixel(inPixel);
                newBmp.setPixel(w, h, total);
            }
        }

        return newBmp;
    }
    */
}
