/*
 * BufferedImageComparator.java
 *
 * Created on July 18, 2006, 7:24 PM
 *
 * Copyright Robert Cooper, Temple of the Screaming Penguin
 *
 * This library is free software; you can redistribute it and/or
 * modify it under the terms of the GNU Lesser General Public
 * License as published by the Free Software Foundation; either
 * version 2.1 of the License, or (at your option) any later version.
 *
 * This library is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU
 * Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public
 * License along with this library; if not, write to the Free Software
 */

package com.totsp.sotu.app;

import java.awt.image.BufferedImage;
import java.awt.image.Raster;
import java.util.Comparator;

/**
 *
 * @author cooper
 */
public class BufferedImageComparator implements Comparator {
    
    /** Creates a new instance of BufferedImageComparator */
    public BufferedImageComparator() {
        super();
    }
    
    public int compare(Object o1, Object o2 ) {
        BufferedImage b1 = (BufferedImage) o1;
        BufferedImage b2 = (BufferedImage) o2;
        if( b1 == b2 )
            return 0;
        if( (b1 == null || b2 == null) && ( b1 != null || b2 != null) ){
            return -1;
        }
        Raster r1 = b1.getData();
        Raster r2 = b2.getData();
        if( r1.getWidth() != r2.getWidth() || r1.getHeight() != r2.getHeight() ){
            return -1;
        }
        boolean bad = false;
        for( int x= 0; x < r1.getWidth(); x++ ){
            for( int y = 0; y < r1.getHeight()  ; y++){
                int[] pixel1 = r1.getPixel( x, y, new int[32] );
                int[] pixel2 = r2.getPixel( x, y, new int[32] );
                for( int i=0; i < 3 ; i++ ){
                    if( pixel1[i] != pixel2[i] )
                        return -1;
                }
                
            }
        }
        return 0;
        
    }
    
}
