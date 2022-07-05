package com.sllair.sedg.algs4.utils;

import edu.princeton.cs.algs4.In;

import java.net.URL;

public class InFactory {

    public static <T> In get(String[] args, int fileNameIndex, Class<T> clazz) {
        URL path = null;

        if (args.length > fileNameIndex) {
            path = clazz.getResource(args[fileNameIndex]);
            // If the file is not found in the source directory, try the corresponding example directory
            if (path == null) {
                String[] fullPackageName =
                        clazz.getPackage().getName().replace("com.sllair.sedg.algs4.solutions.", "").split("[.]");
                StringBuilder relativeFilePathBuilder = new StringBuilder("../../");
                for (int i = 1; i < fullPackageName.length; ++i) {
                    relativeFilePathBuilder.append("../");
                }

                relativeFilePathBuilder.append("examples/");

                for (int i = 1; i < fullPackageName.length; ++i) {
                    relativeFilePathBuilder.append(fullPackageName[i]);
                    relativeFilePathBuilder.append('/');
                }

                relativeFilePathBuilder.append(args[fileNameIndex]);

                path = clazz.getResource(relativeFilePathBuilder.toString());
            }
        }


        return path == null ? new In() : new In(path);
    }

    public static <T> In get(String[] args, Class<T> clazz) {
        return get(args, 0, clazz);
    }

}
