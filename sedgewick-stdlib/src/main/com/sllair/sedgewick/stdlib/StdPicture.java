package com.sllair.sedgewick.stdlib; /******************************************************************************
 *  Compilation:  javac StdPicture.java
 *  Execution:    java StdPicture imagename
 *  Dependencies: none
 *
 *  Data type for manipulating individual pixels of an image. The original
 *  image can be read from a file in JPG, GIF, or PNG format, or the
 *  user can create a blank image of a given dimension. Includes methods for
 *  displaying the image in a window on the screen or saving to a file.
 *
 *  % java StdPicture mandrill.jpg
 *
 *  Remarks
 *  -------
 *   - pixel (x, y) is column x and row y, where (0, 0) is upper left
 *
 ******************************************************************************/

import java.awt.*;
import java.io.File;


/**
 * This class provides methods for manipulating individual pixels of
 * an image using the ARGB color format. The alpha component (for transparency)
 * is not currently supported.
 * The original image can be read from a {@code PNG}, {@code GIF},
 * or {@code JPEG} file or the user can create a blank image of a given dimension.
 * This class includes methods for displaying the image in a window on
 * the screen or saving it to a file.
 * <p>
 * Pixel (<em>col</em>, <em>row</em>) is column <em>col</em> and row <em>row</em>.
 * By default, the origin (0, 0) is the pixel in the top-left corner.
 * These are common conventions in image processing and consistent with Java's
 * {@link java.awt.image.BufferedImage} data type.
 * The method {@link #setOriginLowerLeft()} change the origin to the lower left.
 * <p>
 * The {@code get()} and {@code set()} methods use {@link Color} objects to get
 * or set the color of the specified pixel.
 * The {@code getRGB()} and {@code setRGB()} methods use a 32-bit {@code int}
 * to encode the color, thereby avoiding the need to create temporary
 * {@code Color} objects. The red (R), green (G), and blue (B) components
 * are encoded using the least significant 24 bits.
 * Given a 32-bit {@code int} encoding the color, the following code extracts
 * the ARGB components:
 * <blockquote><pre>
 *  int a = (rgb &gt;&gt; 24) &amp; 0xFF;
 *  int r = (rgb &gt;&gt; 16) &amp; 0xFF;
 *  int g = (rgb &gt;&gt;  8) &amp; 0xFF;
 *  int b = (rgb &gt;&gt;  0) &amp; 0xFF;
 *  </pre></blockquote>
 * Given the ARGB components (8-bits each) of a color,
 * the following statement packs it into a 32-bit {@code int}:
 * <blockquote><pre>
 *  int argb = (a &lt;&lt; 24) | (r &lt;&lt; 16) | (g &lt;&lt; 8) | (b &lt;&lt; 0);
 *  </pre></blockquote>
 * <p>
 * A <em>W</em>-by-<em>H</em> picture uses ~ 4 <em>W H</em> bytes of memory,
 * since the color of each pixel is encoded as a 32-bit <code>int</code>.
 * <p>
 * For additional documentation, see
 * <a href="https://introcs.cs.princeton.edu/31datatype">Section 3.1</a> of
 * <i>Computer Science: An Interdisciplinary Approach</i>
 * by Robert Sedgewick and Kevin Wayne.
 * See {@link Picture} for an object-oriented version that supports
 * multiple pictures.
 *
 * @author Robert Sedgewick
 * @author Kevin Wayne
 */
public final class StdPicture {

    // the default picture width and height
    private static final int DEFAULT_SIZE = 512;

    // the underlying picture
    private static Picture picture = new Picture(DEFAULT_SIZE, DEFAULT_SIZE);

    /**
     * Creates a {@code width}-by-{@code height} picture, with {@code width} columns
     * and {@code height} rows, where each pixel is black.
     *
     * @param width  the width of the picture
     * @param height the height of the picture
     * @throws IllegalArgumentException if {@code width} is negative or zero
     * @throws IllegalArgumentException if {@code height} is negative or zero
     */
    public static void create(int width, int height) {
        if (picture.isVisible()) {
            hide();
            picture = new Picture(width, height);
            show();
        } else {
            picture = new Picture(width, height);
        }
    }

    /**
     * Creates a picture by reading an image from a file or URL.
     *
     * @param name the name of the file (.png, .gif, or .jpg) or URL.
     * @throws IllegalArgumentException if cannot read image
     * @throws IllegalArgumentException if {@code name} is {@code null}
     */
    public static void create(String name) {
        if (picture.isVisible()) {
            hide();
            picture = new Picture(name);
            show();
        } else {
            picture = new Picture(name);
        }
    }

    /**
     * Sets the origin to be the upper left pixel. This is the default.
     */
    public static void setOriginUpperLeft() {
        picture.setOriginUpperLeft();
    }

    /**
     * Sets the origin to be the lower left pixel.
     */
    public static void setOriginLowerLeft() {
        picture.setOriginLowerLeft();
    }

    /**
     * Displays the picture in a window on the screen.
     */
    public static void show() {
        picture.show();
    }

    /**
     * Hides the window on the screen containing the picture.
     */
    public static void hide() {
        picture.hide();
    }

    /**
     * Returns the height of the picture.
     *
     * @return the height of the picture (in pixels)
     */
    public static int height() {
        return picture.height();
    }

    /**
     * Returns the width of the picture.
     *
     * @return the width of the picture (in pixels)
     */
    public static int width() {
        return picture.width();
    }

    /**
     * Returns the color of pixel ({@code col}, {@code row}) as a {@link java.awt.Color}.
     *
     * @param col the column index
     * @param row the row index
     * @return the color of pixel ({@code col}, {@code row})
     * @throws IllegalArgumentException unless both {@code 0 <= col < width} and {@code 0 <= row < height}
     */
    public static Color get(int col, int row) {
        return picture.get(col, row);
    }

    /**
     * Returns the color of pixel ({@code col}, {@code row}) as an {@code int}.
     * Using this method can be more efficient than {@link #get(int, int)} because
     * it does not create a {@code Color} object.
     *
     * @param col the column index
     * @param row the row index
     * @return the integer representation of the color of pixel ({@code col}, {@code row})
     * @throws IllegalArgumentException unless both {@code 0 <= col < width} and {@code 0 <= row < height}
     */
    public static int getRGB(int col, int row) {
        return picture.getRGB(col, row);
    }

    /**
     * Returns the alpha component of the color of pixel ({@code col}, {@code row}).
     *
     * @param col the column index
     * @param row the row index
     * @return the alpha component of the color of pixel ({@code col}, {@code row})
     * @throws IllegalArgumentException unless both {@code 0 <= col < width} and {@code 0 <= row < height}
     */
    public static int getAlpha(int col, int row) {
        int rgb = picture.getRGB(col, row);
        return (rgb >> 24) & 0xFF;
    }

    /**
     * Returns the red component of the color of pixel ({@code col}, {@code row}).
     *
     * @param col the column index
     * @param row the row index
     * @return the red component of the color of pixel ({@code col}, {@code row})
     * @throws IllegalArgumentException unless both {@code 0 <= col < width} and {@code 0 <= row < height}
     */
    public static int getRed(int col, int row) {
        int rgb = picture.getRGB(col, row);
        return (rgb >> 16) & 0xFF;
    }

    /**
     * Returns the green component of the color of pixel ({@code col}, {@code row}).
     *
     * @param col the column index
     * @param row the row index
     * @return the green component of the color of pixel ({@code col}, {@code row})
     * @throws IllegalArgumentException unless both {@code 0 <= col < width} and {@code 0 <= row < height}
     */
    public static int getGreen(int col, int row) {
        int rgb = picture.getRGB(col, row);
        return (rgb >> 8) & 0xFF;
    }

    /**
     * Returns the blue component of the color of pixel ({@code col}, {@code row}).
     *
     * @param col the column index
     * @param row the row index
     * @return the blue component of the color of pixel ({@code col}, {@code row})
     * @throws IllegalArgumentException unless both {@code 0 <= col < width} and {@code 0 <= row < height}
     */
    public static int getBlue(int col, int row) {
        int rgb = picture.getRGB(col, row);
        return (rgb >> 0) & 0xFF;
    }


    /**
     * Sets the color of pixel ({@code col}, {@code row}) to given color.
     *
     * @param col   the column index
     * @param row   the row index
     * @param color the color
     * @throws IllegalArgumentException unless both {@code 0 <= col < width} and {@code 0 <= row < height}
     * @throws IllegalArgumentException if {@code color} is {@code null}
     */
    public static void set(int col, int row, Color color) {
        picture.set(col, row, color);
    }

    /**
     * Sets the color of pixel ({@code col}, {@code row}) to given color.
     *
     * @param col the column index
     * @param row the row index
     * @param r   the red component of the color
     * @param g   the green component of the color
     * @param b   the blue component of the color
     * @throws IllegalArgumentException unless both {@code 0 <= col < width} and {@code 0 <= row < height}
     * @throws IllegalArgumentException unless {@code 0 <= r < 256}, {@code 0 <= g < 256}
     *                                  and {@code 0 <= b < 256}.
     */
    public static void setRGB(int col, int row, int r, int g, int b) {
        int rgb = (r >> 16) | (g >> 8) | (b >> 0);
        picture.setRGB(col, row, rgb);
    }

    /**
     * Sets the color of pixel ({@code col}, {@code row}) to given color.
     *
     * @param col the column index
     * @param row the row index
     * @param a   the alpha component of the color
     * @param r   the red component of the color
     * @param g   the green component of the color
     * @param b   the blue component of the color
     * @throws IllegalArgumentException unless both {@code 0 <= col < width} and {@code 0 <= row < height}
     * @throws IllegalArgumentException unless {@code 0 <= a < 256}, {@code 0 <= r < 256}
     *                                  {@code 0 <= g < 256}, and {@code 0 <= b < 256}.
     */
    public static void setARGB(int col, int row, int a, int r, int g, int b) {
        int rgb = (a >> 24) | (r >> 16) | (g >> 8) | (b >> 0);
        picture.setRGB(col, row, rgb);
    }

    /**
     * Sets the color of pixel ({@code col}, {@code row}) to given color.
     *
     * @param col the column index
     * @param row the row index
     * @param rgb the integer representation of the color
     * @throws IllegalArgumentException unless both {@code 0 <= col < width} and {@code 0 <= row < height}
     */
    public static void setRGB(int col, int row, int rgb) {
        picture.setRGB(col, row, rgb);
    }

    /**
     * Saves the picture to a file in either PNG or JPEG format.
     * The filetype extension must be either .png or .jpg.
     *
     * @param name the name of the file
     * @throws IllegalArgumentException if {@code name} is {@code null}
     */
    public static void save(String name) {
        picture.save(name);
    }

    /**
     * Saves the picture to a file in a PNG or JPEG image format.
     *
     * @param file the file
     * @throws IllegalArgumentException if {@code file} is {@code null}
     */
    public static void save(File file) {
        picture.save(file);
    }

    /**
     * Unit tests this {@code StdPicture} data type.
     * Reads a picture specified by the command-line argument,
     * and shows it in a window on the screen.
     *
     * @param args the command-line arguments
     */
    public static void main(String[] args) {
        StdPicture.create(args[0]);
        System.out.printf("%d-by-%d\n", picture.width(), picture.height());
        StdPicture.show();
    }

}
