/**
 * @author Emily Vo
 * Date: September 9th 2020
 * Face Class
 * includes components that will randomize and make up
 * a face
 */

package edu.up.facemaker;

import java.util.Random;

public class Face {
    private int skinColor;
    private int eyeColor;
    private int hairColor;
    private int hairStyle;

    /**
     * Face() Constructor
     * Constructor creates a randomized face using randomize().
     */

    Face() {
        randomize();
    }

    /**
     * randomize() method
     * Randomizes instance variables for a Face object.
     */

    public void randomize() {
        Random r = new Random();

        //randomizes the values of colors using hexadecimal
        this.skinColor = r.nextInt(0xffffff+1);
        this.eyeColor = r.nextInt(0xffffff+1);
        this.hairColor = r.nextInt(0xffffff+1);

        //randomizes the assigned hair number
        this.hairStyle = r.nextInt(5);
        }
            /**
             * External Citation
             * Date: September 9, 2020
             * Problem: Did not remember how to get random numbers
             * Resource: https://stackoverflow.com/questions/5887709/getting-random-numbers-in-java
             * Solution: Found the import and functions required.
             *
             * Date: September 9, 2020
             * Problem: Forgot that we can use hexadecimals to store colors in java
             * Resource: Andrew Nuxoll
             * Solution: Reminded me that colors can be stored using hexadecimals
             */
    }

