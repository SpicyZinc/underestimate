/*
[
(1920, 1939),
(1911, 1944),
(1920, 1955),
(1938, 1939),
(1937, 1940)]


You are given a 2D integer array logs where each logs[i] = [birthi, deathi] indicates the birth and death years of the ith person.
The population of some year x is the number of people alive during that year. The ith person is counted in year x's population if x is in the inclusive range [birthi, deathi - 1].
Note that the person is not counted in the year that they die.
Return the earliest year with the maximum population.

Example 1:
Input: logs = [[1993,1999],[2000,2010]]
Output: 1993
Explanation: The maximum population is 1, and 1993 is the earliest year with this population.

Example 2:
Input: logs = [[1950,1961],[1960,1971],[1970,1981]]
Output: 1960
Explanation: 
The maximum population is 2, and it had happened in years 1960 and 1970.
The earlier year between them is 1960.

Constraints:
1 <= logs.length <= 100
1950 <= birthi < deathi <= 2050

idea:
see 3 implementations
*/

import java.util.*;

class Person {
    int year;
    int flag;

    public Person(int year, int flag) {
        this.year = year;
        this.flag = flag;
    }

    public String toString() {
        return this.year + ": " + this.flag;
    }
}

class MaximumPopulationYear {
    public static void main(String[] args) {
        MaximumPopulationYear eg = new MaximumPopulationYear();
        // int[][] logs = {{1993,1999}, {2000,2010}};
        int[][] logs = {{1950,1961},{1960,1971},{1970,1981}};
        // int[][] logs = {{2008,2026},{2004,2008},{2034,2035},{1999,2050},{2049,2050},{2011,2035},{1966,2033},{2044,2049}};

        int year = eg.maximumPopulation(logs);
        System.out.println("Year == " + year);
    }

    // since just 2050 years, just open a new array, constant time, also sorted
    public int maximumPopulation(int[][] logs) {
        int[] years = new int[2051];

        for (int[] log : logs) {
            int birthYear = log[0];
            int deathYear = log[1];

            years[birthYear] += 1;
            years[deathYear] -= 1;
        }
        
        int maxCnt = years[0];
        int maxCntYear = Integer.MIN_VALUE;
        
        for (int i = 1; i < years.length; i++) {
            years[i] += years[i - 1];
            
            if (maxCnt < years[i]) {
                maxCnt = years[i];

                maxCntYear = i;
            }
        }

        return maxCntYear;
    }

    // not working for consecutive birth death case, why? It should
    public int maximumPopulation(int[][] logs) {
        List<Person> list = new ArrayList<>();

        for (int[] log : logs) {
            int birthYear = log[0];
            int deathYear = log[1];

            list.add(new Person(birthYear, 1));
            list.add(new Person(deathYear, -1));
        }

        Collections.sort(list, new Comparator<Person>() {
            @Override
            public int compare(Person a, Person b) {
                return a.year - b.year;
            }
        });

        int count = 0;
        int maxCnt = 0;
        int maxCntYear = Integer.MIN_VALUE;

        for (Person p : list) {
            int year = p.year;
            int flag = p.flag;

            count += flag;

            if (count > maxCnt) {
                maxCnt = count;
                maxCntYear = year;
            }
        }

        return maxCntYear;
    }

    // public int maximumPopulation(int[][] logs) {
    //     int minBirthYear = Integer.MAX_VALUE;
    //     int maxDeathYear = Integer.MIN_VALUE;

    //     for (int[] log : logs) {
    //         int birthYear = log[0];
    //         int deathYear = log[1];

    //         if (minBirthYear > birthYear) {
    //             minBirthYear = birthYear;
    //         }

    //         if (maxDeathYear < deathYear) {
    //             maxDeathYear = deathYear;
    //         }
    //     }

    //     int maxCnt = 0;
    //     int maxCntYear = Integer.MIN_VALUE;
    //     for (int year = minBirthYear; year <= maxDeathYear; year++) {
    //         int count = getYearPopulate(year, logs);
    //         if (count > maxCnt) {
    //             maxCnt = count;
    //             maxCntYear = year;
    //         }
    //     }

    //     return maxCntYear;
    // }

    // public int getYearPopulate(int year, int[][] logs) {
    //     int count = 0;

    //     for (int[] log : logs) {
    //         int birthYear = log[0];
    //         int deathYear = log[1];
    //         if (year >= birthYear && year < deathYear) {
    //             count++;
    //         }
    //     }

    //     return count;
    // }
}
