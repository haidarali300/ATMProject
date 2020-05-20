/**
 *  This class provides the bubble sort method 
 */
public class SortsClass {

    public static void bubbleSort(Comparable[] theArray, int n) {
        // ---------------------------------------------------
        // Sorts the items in an array into ascending order.
        // Precondition: theArray is an array of n items.
        // Postcondition: theArray is sorted into ascending
        // order.
        // ---------------------------------------------------
        boolean sorted = false; // false when swaps occur
        for (int pass = 1; (pass < n) && !sorted; ++pass) {
            // Invariant: theArray[n+1-pass..n-1] is sorted
            // and > theArray[0..n-pass]
            sorted = true; // assume sorted
            for (int index = 0; index < n-pass; ++index) {
                // Invariant: theArray[0..index-1] <= theArray[index]
                int nextIndex = index + 1;
                if (theArray[index].compareTo(theArray[nextIndex]) > 0) {
                    // exchange items
                    Comparable temp = theArray[index];
                    theArray[index] = theArray[nextIndex];
                    theArray[nextIndex] = temp;
                    sorted = false; // signal exchange
                } // end if
            } // end for
            // Assertion: theArray[0..n-pass-1] < theArray[n-pass]
        } // end for
    } // end bubbleSort
} // end SortsClass 