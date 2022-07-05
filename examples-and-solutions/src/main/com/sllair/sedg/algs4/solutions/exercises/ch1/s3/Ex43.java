package com.sllair.sedg.algs4.solutions.exercises.ch1.s3;

import edu.princeton.cs.algs4.Queue;
import edu.princeton.cs.algs4.Stack;
import edu.princeton.cs.algs4.StdOut;

import java.io.File;
import java.util.Collections;

public class Ex43 {

    private static class FileRecord {
        public FileRecord(File file, int level) {
            this.file = file;
            this.level = level;
        }

        private final File file;
        private final int level;

        public String toString() {
            StringBuilder sb = new StringBuilder();
            sb.append(String.join("", Collections.nCopies(2 * this.level, " ")));
            sb.append(file.getName());
            sb.append("\n");

            return sb.toString();
        }
    }

    public static class Recursively {

        private static void printChildren(FileRecord parent) {
            if (parent.file.isDirectory()) {
                for (File file : parent.file.listFiles()) {
                    FileRecord fileRecord = new FileRecord(file, parent.level + 1);
                    StdOut.print(fileRecord);
                    printChildren(fileRecord);
                }
            }
        }

        public static void run(String rootPath) {
            File root = new File(rootPath);
            if (!root.exists()) {
                StdOut.println(String.format("%s does not exist", rootPath));
                return;
            }
            FileRecord rootRecord = new FileRecord(root, 0);
            StdOut.print(rootRecord);
            printChildren(rootRecord);
        }
    }

    public static class Stackly {

        public static void run(String rootPath) {
            File root = new File(rootPath);
            if (!root.exists()) {
                StdOut.println(String.format("%s does not exist", rootPath));
                return;
            }
            Stack<FileRecord> fileRecords = new Stack<>();

            FileRecord rootRecord = new FileRecord(root, 0);
            fileRecords.push(rootRecord);

            while (!fileRecords.isEmpty()) {
                FileRecord curr = fileRecords.pop();
                StdOut.print(curr);
                if (curr.file.isDirectory()) {
                    Stack<FileRecord> tmp = new Stack<>();
                    for (File file : curr.file.listFiles()) {
                        tmp.push(new FileRecord(file, curr.level + 1));
                    }
                    while (!tmp.isEmpty()) {
                        fileRecords.push(tmp.pop());
                    }
                }
            }
        }
    }

    public static class Queuely {

        public static void run(String rootPath) {
            File root = new File(rootPath);
            if (!root.exists()) {
                StdOut.println(String.format("%s does not exist", rootPath));
                return;
            }
            Queue<FileRecord> fileRecords = new Queue<>();

            FileRecord rootRecord = new FileRecord(root, 0);
            fileRecords.enqueue(rootRecord);

            while (!fileRecords.isEmpty()) {
                FileRecord curr = fileRecords.dequeue();
                StdOut.print(curr);
                if (curr.file.isDirectory()) {
                    Queue<FileRecord> tmp = new Queue<>();
                    while (!fileRecords.isEmpty()) {
                        tmp.enqueue(fileRecords.dequeue());
                    }
                    for (File file : curr.file.listFiles()) {
                        fileRecords.enqueue(new FileRecord(file, curr.level + 1));
                    }
                    while (!tmp.isEmpty()) {
                        fileRecords.enqueue(tmp.dequeue());
                    }
                }
            }
        }
    }

    public static void main(String[] args) {
        if (args.length > 1 && "r".equals(args[1])) {
            Recursively.run(args[0]);
        } else if (args.length > 1 && "s".equals(args[1])) {
            Stackly.run(args[0]);
        } else {
            Queuely.run(args[0]);
        }
    }

}
