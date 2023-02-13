/*
Given number of pages in n different books and m students. The books are arranged in ascending order of number of pages.
Every student is assigned to read some consecutive books.
The task is to assign books in such a way that the maximum number of pages assigned to a student is minimum.

idea:
another typical binary search
binary to find minimum
but find among some possible max pages

ref: https://www.geeksforgeeks.org/allocate-minimum-number-pages/
*/

public class AllocateMinimumNumberOfPages {
    public static void main(String[] args) {
        AllocateMinimumNumberOfPages eg = new AllocateMinimumNumberOfPages();
        int arr[] = {12, 34, 67, 90};
        // No. of students
        int m = 2;
        System.out.println("Minimum number of pages = " + eg.findPages(arr, m));
    }

    // Method to find minimum pages
    public int findPages(int arr[], int m) {
        // Return -1 if # of books is less than # of students
        if (arr.length < m) {
            return -1;
        }

        int totalPages = 0;
        // Count total number of pages
        for (int pagesCnt : arr) {
            totalPages += pagesCnt;
        }
    
        int start = 0, end = totalPages;
        int result = Integer.MAX_VALUE;
    
        while (start <= end) {
            // check if it is possible to distribute books by using mid is current minimum
            int mid = (start + end) / 2;
            if (isPossible(arr, m, mid)) {
                // if yes then find the minimum distribution
                result = Math.min(result, mid);
                end = mid - 1;
            } else {
                // if not possible means pages should be increased so update start = mid + 1
                start = mid + 1;
            }
        }

        return result;
    }

    // Method to determine a possible max pages count is possible
    // This method is not responsible for looking for the min of possibleMaxPages
    // It is only to check if possibleMaxPages is possible
    // Finding the min the binary search does
    public boolean isPossible(int pages[], int m, int possibleMaxPages) {
        int pagesSum = 0;
        int studentsRequired = 1;
    
        for (int bookPageCnt : pages) {
            // One book cannot be split
            // possibleMaxPages is less than a book's pages count, which means a smaller possible max pages is okay, return false to skip it
            // That means we will get the result after mid no. of pages
            if (bookPageCnt > possibleMaxPages) {
                return false;
            }
            // Count how many students are required to distribute possibleMaxPages pages
            if (pagesSum + bookPageCnt > possibleMaxPages) {
                // Update pagesSum
                pagesSum = bookPageCnt;
                // Increment student count
                studentsRequired++;    
                // If students required becomes greater than given no. of students, return false
                if (studentsRequired > m) {
                    return false;
                }
            } else { // else update pagesSum
                pagesSum += bookPageCnt;
            }
        }

        return true;
    }
}
