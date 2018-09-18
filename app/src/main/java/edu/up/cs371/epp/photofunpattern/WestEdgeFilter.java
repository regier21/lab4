package edu.up.cs371.epp.photofunpattern;

import android.graphics.Color;

/**
 *  class BrightFilter changes the image manipulation behavior of its parent
 *  PhotoFilter to increase an image brightness by value of 100.
 *
 *  @author Edward C. Epp
 *  @version November 2017
 *  https://github.com/edcepp/PhotoFunPattern
 */

public class WestEdgeFilter extends PhotoFilter {

    /*
    * tranformPixel This method overrides the transformPixel in the parent
    * class. It adds 100 to each RGB color component. The maxium value of each
    * component is limited to 255
    *
    * @param inPixel is a 32 bit pixel that contains RGB color values
    * @return a new Pixel in which each of the RGB components has been increased
    */
    public int transformPixel(int inPixel[]) {

        int westEdgePixelRed = 0;
        for (int i = 0; i < inPixel.length; i ++) {
            westEdgePixelRed += inPixel[i];
            if(i > 4) westEdgePixelRed += (inPixel[i] * -1);
            else if(i == 8) westEdgePixelRed += (inPixel[i] * -2);
        }

        westEdgePixelRed = westEdgePixelRed/9;
        int red = constrain(Color.red(westEdgePixel));
        int green = constrain(Color.green(westEdgePixel));
        int blue = constrain(Color.blue(westEdgePixel));
        int outPixel = Color.argb(Color.alpha(westEdgePixel), red, green, blue);
        return outPixel;
    }

    public int getPixelColor(int westEdgePixel) {
        for (int i = 0; i < inPixel.length; i ++) {
            westEdgePixel += inPixel[i];
            if(i > 4) westEdgePixel += (inPixel[i] * -1);
            else if(i == 8) westEdgePixel += (inPixel[i] * -2);
        }
    }

}
