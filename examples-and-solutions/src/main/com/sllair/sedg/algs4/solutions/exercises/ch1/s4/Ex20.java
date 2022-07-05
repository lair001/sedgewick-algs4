package com.sllair.sedg.algs4.solutions.exercises.ch1.s4;

import com.sllair.sedg.algs4.utils.InFactory;
import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdOut;

public class Ex20 {

    public static class Bitonic3LgN {

        public static int rank(int key, int[] a) {
            int p = rankPeak(a);
            int rankLeft = binarySearch(key, a, p);
            return rankLeft == -1 ?
                    binarySearchDecreasing(key, a, p + 1) :
                    rankLeft;
        }

        private static int binarySearch(int key, int[] a, int stop) {
            int lo = 0;
            int hi = stop;

            while (lo < hi) {
                int mid = lo + (hi - lo) / 2;

                if (a[mid] < key) {
                    lo = mid + 1;
                } else {
                    hi = mid;
                }
            }

            return a[lo] == key ? lo : -1;
        }

        private static int binarySearchDecreasing(int key, int[] a, int start) {
            int lo = start;
            int hi = a.length - 1;

            while (lo < hi) {
                int mid = lo + (hi - lo) / 2;

                if (a[mid] > key) {
                    lo = mid + 1;
                } else {
                    hi = mid;
                }
            }

            return a[lo] == key ? lo : -1;
        }

        private static int rankPeak(int[] a) {
            int lo = 0;
            int hi = a.length - 1;

            while (lo < hi) {
                int mid = lo + (hi - lo) / 2;

                if (a[mid] < a[mid + 1]) {
                    lo = mid + 1;
                } else {
                    hi = mid;
                }
            }

            return lo;
        }
    }

    public static class Bitonic2LgN {

        public static int rank(int key, int[] a) {
            return bitonicSearch(key, a, 0, a.length - 1);
        }

        private static int bitonicSearch(int key, int[] a, int start, int stop) {
            if (stop < 0 || start >= a.length || start > stop) {
                return -1;
            }


            int mid = start + (stop - start) / 2;

            int midVal = a[mid];
            int midPlus1Value = a[mid + 1];

            if (midVal == key) {
                return mid;
            }

            if (midPlus1Value == key) {
                return mid + 1;
            }

            if (midVal < midPlus1Value) {
                int binarySearchResult = binarySearch(key, a, mid - 1);
                return binarySearchResult == -1 ?
                        bitonicSearch(key, a, mid + 2, stop) :
                        binarySearchResult;
            } else {
                int binarySearchResult = binarySearchDecreasing(key, a, mid + 2);
                return binarySearchResult == -1 ?
                        bitonicSearch(key, a, start, mid - 1) :
                        binarySearchResult;
            }
        }

        private static int binarySearch(int key, int[] a, int stop) {
            if (stop < 0) {
                return -1;
            }

            int lo = 0;
            int hi = stop;

            while (lo < hi) {
                int mid = lo + (hi - lo) / 2;

                if (a[mid] < key) {
                    lo = mid + 1;
                } else {
                    hi = mid;
                }
            }

            return a[lo] == key ? lo : -1;
        }

        private static int binarySearchDecreasing(int key, int[] a, int start) {
            if (start >= a.length) {
                return -1;
            }

            int lo = start;
            int hi = a.length - 1;

            while (lo < hi) {
                int mid = lo + (hi - lo) / 2;

                if (a[mid] > key) {
                    lo = mid + 1;
                } else {
                    hi = mid;
                }
            }

            return a[lo] == key ? lo : -1;
        }
    }

    public static class Bitonic2LgNIt {

        public static int rank(int key, int[] a) {
            int lo = 0;
            int hi = a.length - 1;

            while (lo < hi) {
                int mid = lo + (hi - lo) / 2;

                int midVal = a[mid];
                int midPlus1Value = a[mid + 1];

                if (midVal == key) {
                    return mid;
                }

                if (midPlus1Value == key) {
                    return mid + 1;
                }

                if (midVal < midPlus1Value) {
                    int binarySearchResult = binarySearch(key, a, mid - 1);
                    if (binarySearchResult != -1) {
                        return binarySearchResult;
                    } else {
                        lo = mid + 2;
                    }
                } else {
                    int binarySearchResult = binarySearchDecreasing(key, a, mid + 2);
                    if (binarySearchResult != -1) {
                        return binarySearchResult;
                    } else {
                        hi = mid - 1;
                    }
                }
            }
            return a[lo] == key ? lo : -1;
        }

        private static int binarySearch(int key, int[] a, int stop) {
            if (stop < 0) {
                return -1;
            }

            int lo = 0;
            int hi = stop;

            while (lo < hi) {
                int mid = lo + (hi - lo) / 2;

                if (a[mid] < key) {
                    lo = mid + 1;
                } else {
                    hi = mid;
                }
            }

            return a[lo] == key ? lo : -1;
        }

        private static int binarySearchDecreasing(int key, int[] a, int start) {
            if (start >= a.length) {
                return -1;
            }

            int lo = start;
            int hi = a.length - 1;

            while (lo < hi) {
                int mid = lo + (hi - lo) / 2;

                if (a[mid] > key) {
                    lo = mid + 1;
                } else {
                    hi = mid;
                }
            }

            return a[lo] == key ? lo : -1;
        }

    }

    private static int rank(int key, int a[], String[] args) {
        if (args.length > 1 && "2".equals(args[0])) {
            return Ex20.Bitonic2LgN.rank(key, a);
        } else if (args.length > 1 && "2it".equals(args[0])) {
            return Ex20.Bitonic2LgNIt.rank(key, a);
        } else {
            return Ex20.Bitonic3LgN.rank(key, a);
        }
    }

    public static void main(String[] args) {
        In in = InFactory.get(args, Ex20.class);

        int n = in.readInt();

        int[] a = new int[n];

        for (int i = 0; i < n; i++) {
            a[i] = in.readInt();
        }

        while (!in.isEmpty()) {
            int key = in.readInt();
            StdOut.println(String.format("%11d %d", key, rank(key, a, args)));
        }
    }
}
