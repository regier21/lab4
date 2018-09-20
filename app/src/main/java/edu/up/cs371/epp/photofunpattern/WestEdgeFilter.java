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
        int westEdgePixelGreen = 0;
        int westEdgePixelBlue = 0;
        int westEdgePixelAlpha = 0;

        for (int i = 0; i < inPixel.length; i++) {
            if (i <= 4) westEdgePixelRed += constrain(Color.red(inPixel[i]));
            if(i > 4 && i < 8) westEdgePixelRed += constrain(Color.red(inPixel[i])* -1);
            else if(i == 8) westEdgePixelRed += constrain(Color.red(inPixel[i]) * -2);
        }

        for (int i = 0; i < inPixel.length; i++) {
            if (i <= 4) westEdgePixelGreen += constrain(Color.green(inPixel[i]));
            if(i > 4 && i < 8) westEdgePixelGreen += constrain(Color.green(inPixel[i]) * -1);
            else if(i == 8) westEdgePixelGreen += constrain(Color.green(inPixel[i]) * -2);
        }

        for (int i = 0; i < inPixel.length; i++) {
            if (i <= 4) westEdgePixelBlue += constrain(Color.blue((inPixel[i])));
            if(i > 4 && i < 8) westEdgePixelBlue += constrain(Color.blue(inPixel[i]) * -1);
            else if(i == 8) westEdgePixelBlue += constrain(Color.blue(inPixel[i]) * -2);
        }

        for (int i = 0; i < inPixel.length; i++) {
            if (i <= 4) westEdgePixelAlpha += Color.alpha(inPixel[i]);
            if(i > 4 && i < 8) westEdgePixelAlpha += Color.alpha(inPixel[i]) * -1;
            else if(i == 8) westEdgePixelAlpha +=Color.alpha(inPixel[i]) * -2;
        }

        //westEdgePixelRed = westEdgePixelRed/9;
        //westEdgePixelBlue /= 9;
        //westEdgePixelGreen /= 9;
        //westEdgePixelAlpha /= 9;
        int outPixel = Color.argb(westEdgePixelAlpha, westEdgePixelRed, westEdgePixelBlue, westEdgePixelGreen);
        return outPixel;
    }

    /*public int getPixelColor(int westEdgePixel) {
        for (int i = 0; i < inPixel.length; i ++) {
            westEdgePixel += inPixel[i];
            if(i > 4) westEdgePixel += (inPixel[i] * -1);
            else if(i == 8) westEdgePixel += (inPixel[i] * -2);
        }
    }*/
}
