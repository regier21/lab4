package edu.up.cs371.epp.photofunpattern;

import android.graphics.Bitmap;

/**
 *  class PhotoFilter is the abstract filter parent class. Its default behavior
 *  is the leave an image unchanged.
 *
 *  @author Edward C. Epp
 *  @version November 2017
 *  https://github.com/edcepp/PhotoFunPattern
 */
public abstract class PhotoFilter {

    /*
    * constrain This method does not permit an RGB color value to over or under
    * saturate. It maintains values between 0 and 255 inclusive.
    *
    * @param inPixel is an integer input color component value that may be out
    *                of range
    * @return a new color component in range
    */
    protected int constrain(int color) {
        if (color > 255)
            return 255;
        else if (color < 0)
            return 0;
        else
            return color;
    }

    /*
    * tranformPixel This is the default transform method. It leaves the pixel
    * unchanged. It implements a copy image function.
    *
    * @param inPixel is a 32 bit pixel that contains RGB color values
    * @return a new Pixel in which unchanged color components
    */
    protected int transformPixel (int inPixel[]){
        return inPixel[8];
    }

    /*
    * apply This method visits every pixel in the input image. It applies a
    * transform to each pixel.
    *
    * @param inBmp is the original image
    * @return a new image in which each pixel has been transformed
    */
    public Bitmap apply(Bitmap inBmp) {
        int width = inBmp.getWidth();
        int height = inBmp.getHeight();

        Bitmap newBmp = Bitmap.createBitmap(width, height, inBmp.getConfig());

        for (int w = 0; w < width; w++) {
            for (int h = 0; h < height; h++) {
                int[] inPixel = getPixelGrid(w,h,inBmp);
                int outPixel = transformPixel(inPixel);
                newBmp.setPixel(w, h, outPixel);
            }
        }
        return newBmp;
    }

    protected int[] getPixelGrid (int x, int y, Bitmap inBmp) {
        int[] pixelGrid = new int[9];
        for(int i=0;i<pixelGrid.length;i++) pixelGrid[i] = 0;

        if( x > 0 ) {
            if( y > 0 ) {
                pixelGrid[0] = inBmp.getPixel(x-1,y-1); //Left-top
            }
            if( y < inBmp.getHeight()-1) {
                pixelGrid[1] = inBmp.getPixel(x-1,y+1); //Left-bottom
            }
            pixelGrid[2] = inBmp.getPixel(x-1,y); //Left-middle
        }
        if( x < inBmp.getWidth()-1) {
            if( y > 0 ) {
                pixelGrid[5] = inBmp.getPixel(x+1,y-1); //Right-top
            }
            if( y < inBmp.getHeight()-1) {
                pixelGrid[6] = inBmp.getPixel(x+1,y+1); //Right-bottom
            }
            pixelGrid[7] = inBmp.getPixel(x+1,y); //Right-middle
        }
        if( y > 0 ) {
            pixelGrid[3] = inBmp.getPixel(x,y-1); //Middle-Top
        }
        if( y < inBmp.getHeight()-1) {
            pixelGrid[4] = inBmp.getPixel(x,y+1); //Middle-bottom
        }
        pixelGrid[8] = inBmp.getPixel(x,y); //Middle

        return pixelGrid;
    }
}
