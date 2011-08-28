package com.wordpress.zenjiro.slidingpuzzle;

/**
 * File: Centerable.java
 * Author: Brian Borowski
 * Date created: January 2006
 * Date last modified: November 27, 2010
 */
import java.awt.Container;
import javax.swing.JFrame;

public interface Centerable {
	public void setCenter(Container me, JFrame parent);
}
