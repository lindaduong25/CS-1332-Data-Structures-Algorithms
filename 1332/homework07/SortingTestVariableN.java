import org.junit.AfterClass;
import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import java.io.IOException;
import java.io.Serializable;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Random;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThrows;
import static org.junit.Assert.assertTrue;


/**
 * Passing these tests doesn't guarantee any grade on the assignment.
 *
 * Some JUnit versions seem to have a problem with assertThrows.
 * I tested my code successfully using JUnit 4.13. If you cannot
 * get it to work for your version, remove the assertThrows lines
 * and you should be fine.
 *
 * IMPORTANT:
 *
 * This test does not check if the number of comparisons made were minimal,
 * but you can change n around to see how the sorting algorithms scale with
 * input.
 *
 * @author Prakhar Mittal
 * @version 1.0
 */
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class SortingTestVariableN {

    private static final int TIMEOUT = 200;
    private static final Random rand = new Random();

    // TRY CHANGING N
    private static final int n = 2000;

    private Student[] students = new Student[n];
    private Student[] studentsByName = new Student[n];
    private Student[] studentsByUniversity = new Student[n];
    private Student[] studentsById = new Student[n];
    private Student[] studentsBySeniority = new Student[n];

    private ComparatorWithCount<Student> comparatorName;
    private ComparatorWithCount<Student> comparatorUniversity;
    private ComparatorWithCount<Student> comparatorId;
    private ComparatorWithCount<Student> comparatorSeniority;

    private static Object[][] steps = new Object[5][5];

    @Before
    public void setUp() throws IOException {
        comparatorName = Student.getNameComparator();
        comparatorUniversity = Student.getUniversityComparator();
        comparatorId = Student.getIdComparator();
        comparatorSeniority = Student.getSeniorityComparator();

        for (int i = 0; i < n; i++) {
            StringBuilder builder = new StringBuilder(4);
            for (int j = 0; j < 4; j++) {
                int randInt = 97 + rand.nextInt(122 - 97 + 1);
                builder.append((char) randInt);
            }
            String name = builder.toString();
            String university = "Georgia Tech";
            int id = i + 1;
            int seniority = n - i;

            Student student = new Student(name, university, id, seniority);

            students[i] = student;
            studentsByName[i] = student;
            studentsByUniversity[i] = student;
            studentsById[i] = student;
            studentsBySeniority[n - 1 - i] = student;
        }

        Arrays.sort(studentsByName, comparatorName);

        comparatorName.resetCount();
        comparatorUniversity.resetCount();
        comparatorId.resetCount();
        comparatorSeniority.resetCount();
    }

    @Test(timeout = TIMEOUT)
    public void testNull() {
        assertThrows(IllegalArgumentException.class, () -> {
            Sorting.insertionSort(null, comparatorName);
        });

        assertThrows(IllegalArgumentException.class, () -> {
            Sorting.insertionSort(students, null);
        });

        assertThrows(IllegalArgumentException.class, () -> {
            Sorting.cocktailSort(null, comparatorName);
        });

        assertThrows(IllegalArgumentException.class, () -> {
            Sorting.cocktailSort(students, null);
        });

        assertThrows(IllegalArgumentException.class, () -> {
            Sorting.mergeSort(null, comparatorName);
        });

        assertThrows(IllegalArgumentException.class, () -> {
            Sorting.mergeSort(students, null);
        });

        assertThrows(IllegalArgumentException.class, () -> {
            Sorting.quickSort(null, comparatorName, rand);
        });

        assertThrows(IllegalArgumentException.class, () -> {
            Sorting.quickSort(students, null, rand);
        });

        assertThrows(IllegalArgumentException.class, () -> {
            Sorting.quickSort(students, comparatorName, null);
        });

        assertThrows(IllegalArgumentException.class, () -> {
            Sorting.lsdRadixSort(null);
        });
    }
    
    @Test(timeout = TIMEOUT)
    public void testCocktailSortRandom() {
        Sorting.cocktailSort(students, comparatorName);
        assertArrayEquals(studentsByName, students);
        steps[1][1] = comparatorName.getCount();
    }

    @Test(timeout = TIMEOUT)
    public void testCocktailSortUniform() {
        Sorting.cocktailSort(students, comparatorUniversity);
        assertArrayEquals(studentsByUniversity, students);
        steps[1][2] = comparatorUniversity.getCount();
    }

    @Test(timeout = TIMEOUT)
    public void testCocktailSortSorted() {
        Sorting.cocktailSort(students, comparatorId);
        assertArrayEquals(studentsById, students);
        steps[1][3] = comparatorId.getCount();
    }

    @Test(timeout = TIMEOUT)
    public void testCocktailSortReverse() {
        Sorting.cocktailSort(students, comparatorSeniority);
        assertArrayEquals(studentsBySeniority, students);
        steps[1][4] = comparatorSeniority.getCount();
    }

    @Test(timeout = TIMEOUT)
    public void testCocktailSortStable() {
        Integer i = new Integer(200);
        Integer j = new Integer(200);
        Integer[] unsortedArray = new Integer[] {1, 5, 2, i, 3, 4, j, 7, 6};
        Integer[] sortedArray   = new Integer[] {1, 2, 3, 4, 5, 6, 7, i, j};
        Sorting.cocktailSort(unsortedArray, Comparator.<Integer>naturalOrder());
        assertArrayEquals(sortedArray, unsortedArray);
    }

    @Test(timeout = TIMEOUT)
    public void testInsertionSortRandom() {
        Sorting.insertionSort(students, comparatorName);
        assertArrayEquals(studentsByName, students);
        steps[2][1] = comparatorName.getCount();
    }

    @Test(timeout = TIMEOUT)
    public void testInsertionSortUniform() {
        Sorting.insertionSort(students, comparatorUniversity);
        assertArrayEquals(studentsByUniversity, students);
        steps[2][2] = comparatorUniversity.getCount();
    }

    @Test(timeout = TIMEOUT)
    public void testInsertionSortSorted() {
        Sorting.insertionSort(students, comparatorId);
        assertArrayEquals(studentsById, students);
        steps[2][3] = comparatorId.getCount();
    }

    @Test(timeout = TIMEOUT)
    public void testInsertionSortReverse() {
        Sorting.insertionSort(students, comparatorSeniority);
        assertArrayEquals(studentsBySeniority, students);
        steps[2][4] = comparatorSeniority.getCount();
    }

    @Test(timeout = TIMEOUT)
    public void testInsertionSortStable() {
        Integer i = new Integer(200);
        Integer j = new Integer(200);
        Integer[] unsortedArray = new Integer[] {1, 5, 2, i, 3, 4, j, 7, 6};
        Integer[] sortedArray   = new Integer[] {1, 2, 3, 4, 5, 6, 7, i, j};
        Sorting.insertionSort(unsortedArray, Comparator.<Integer>naturalOrder());
        assertArrayEquals(sortedArray, unsortedArray);
    }

    @Test(timeout = TIMEOUT)
    public void testMergeSortRandom() {
        Sorting.mergeSort(students, comparatorName);
        assertArrayEquals(studentsByName, students);
        steps[3][1] = comparatorName.getCount();
    }

    @Test(timeout = TIMEOUT)
    public void testMergeSortUniform() {
        Sorting.mergeSort(students, comparatorUniversity);
        assertArrayEquals(studentsByUniversity, students);
        steps[3][2] = comparatorUniversity.getCount();
    }

    @Test(timeout = TIMEOUT)
    public void testMergeSortSorted() {
        Sorting.mergeSort(students, comparatorId);
        assertArrayEquals(studentsById, students);
        steps[3][3] = comparatorId.getCount();
    }

    @Test(timeout = TIMEOUT)
    public void testMergeSortReverse() {
        Sorting.mergeSort(students, comparatorSeniority);
        assertArrayEquals(studentsBySeniority, students);
        steps[3][4] = comparatorSeniority.getCount();
    }

    @Test(timeout = TIMEOUT)
    public void testMergeSortStable() {
        Integer i = new Integer(200);
        Integer j = new Integer(200);
        Integer[] unsortedArray = new Integer[] {1, i, 5, j, 3, 4, 2, 7, 6};
        Integer[] sortedArray   = new Integer[] {1, 2, 3, 4, 5, 6, 7, i, j};
        Sorting.mergeSort(unsortedArray, Comparator.<Integer>naturalOrder());
        assertArrayEquals(sortedArray, unsortedArray);
    }

    @Test(timeout = TIMEOUT)
    public void testQuickSortRandom() {
        Sorting.quickSort(students, comparatorName, rand);
        for (int i = 0; i < n; i++) {
            assertEquals(studentsByName[i].name, students[i].name);
        }
        steps[4][1] = comparatorName.getCount();
    }

    @Test(timeout = TIMEOUT)
    public void testQuickSortUniform() {
        Sorting.quickSort(students, comparatorUniversity, rand);
        for (int i = 0; i < n; i++) {
            assertEquals(studentsByUniversity[i].university, students[i].university);
        }
        steps[4][2] = comparatorUniversity.getCount();
    }

    @Test(timeout = TIMEOUT)
    public void testQuickSortSorted() {
        Sorting.quickSort(students, comparatorId, rand);
        for (int i = 0; i < n; i++) {
            assertEquals(studentsById[i].id, students[i].id);
        }
        steps[4][3] = comparatorId.getCount();
    }

    @Test(timeout = TIMEOUT)
    public void testQuickSortReverse() {
        Sorting.quickSort(students, comparatorSeniority, rand);
        for (int i = 0; i < n; i++) {
            assertEquals(studentsBySeniority[i].seniority, students[i].seniority);
        }
        steps[4][4] = comparatorSeniority.getCount();
    }

    @Test(timeout = TIMEOUT)
    public void testLsdRadixRandom() {
        int[] unsortedArray = new int[] {54, 28, 58, 84, 20, 122, 85,   3};
        int[] sortedArray   = new int[] { 3, 20, 28, 54, 58,  84, 85, 122};
        Sorting.lsdRadixSort(unsortedArray);
        assertArrayEquals(sortedArray, unsortedArray);
    }

    @Test(timeout = TIMEOUT)
    public void testLsdRadixNegative() {
        int[] unsortedArray = new int[] {- 54, -28, -58, -84, -20, -122, -85, -3};
        int[] sortedArray   = new int[] {-122, -85, -84, -58, -54, - 28, -20, -3};
        Sorting.lsdRadixSort(unsortedArray);
        assertArrayEquals(sortedArray, unsortedArray);
    }

    @Test(timeout = TIMEOUT)
    public void testLsdRadixMixed() {
        int[] unsortedArray = new int[] {  54,  28, -58, -84, 20, -122, 85, - 3};
        int[] sortedArray   = new int[] {-122, -84, -58, - 3, 20,   28, 54,  85};
        Sorting.lsdRadixSort(unsortedArray);
        assertArrayEquals(sortedArray, unsortedArray);
    }

    @Test(timeout = TIMEOUT)
    public void testLsdRadixMinMax() {
        int[] unsortedArray = new int[] {Integer.MAX_VALUE, -54, -28,  58,  84, -20, 122, -85,   3, Integer.MIN_VALUE};
        int[] sortedArray   = new int[] {Integer.MIN_VALUE, -85, -54, -28, -20,   3,  58,  84, 122, Integer.MAX_VALUE};
        Sorting.lsdRadixSort(unsortedArray);
        assertArrayEquals(sortedArray, unsortedArray);
    }

    @Test(timeout = TIMEOUT)
    public void testLsdRadixTrick() {
        int[] unsortedArray = new int[] {-504,  208, -508,  804, -200, 1202, -805,     3};
        int[] sortedArray   = new int[] {-805, -508, -504, -200,    3,  208,  804,  1202};
        Sorting.lsdRadixSort(unsortedArray);
        assertArrayEquals(sortedArray, unsortedArray);
    }

    @AfterClass
    public static void testCompleted() {
        System.out.println("\nComparisons Made:");
        steps[0] = new Object[] {"", "Random", "Uniform", "Sorted", "Reverse"};
        steps[1][0] = "Cocktail";
        steps[2][0] = "Insertion";
        steps[3][0] = "Merge";
        steps[4][0] = "Quick";
        for (Object[] row: steps) {
            System.out.printf("%15s %10s %10s %10s %10s %n", row);
        }
        System.out.println("\nNote that the number of comparisons made do not define the run " +
                "time alone. \nQuicksort typically makes more comparisons than Mergesort, but is " +
                "faster \nfor random arrays. The other three cases - uniform, sorted, and reverse " +
                "\nsorted arrays - lead to the worst case of quicksort ie. O(n^2) but these \nare " +
                "extremely rare to come across out in the wild.");
    }

    private static class Student implements Serializable {

        private final String name;          // Random
        private final String university;    // Uniform
        private final int id;               // Sorted
        private final int seniority;        // Reverse Sorted

        public Student(String name, String university, int id, int seniority) {
            this.name = name;
            this.university = university;
            this.id = id;
            this.seniority = seniority;
        }

        public static ComparatorWithCount<Student> getNameComparator() {
            return new ComparatorWithCount<>() {
                @Override
                public int compare(Student student1, Student student2) {
                    incrementCount();
                    return student1.name.compareTo(student2.name);
                }
            };
        }

        public static ComparatorWithCount<Student> getUniversityComparator() {
            return new ComparatorWithCount<>() {
                @Override
                public int compare(Student student1, Student student2) {
                    incrementCount();
                    return student1.university.compareTo(student2.university);
                }
            };
        }

        public static ComparatorWithCount<Student> getIdComparator() {
            return new ComparatorWithCount<>() {
                @Override
                public int compare(Student student1, Student student2) {
                    incrementCount();
                    return Integer.compare(student1.id, student2.id);
                }
            };
        }

        public static ComparatorWithCount<Student> getSeniorityComparator() {
            return new ComparatorWithCount<>() {
                @Override
                public int compare(Student student1, Student student2) {
                    incrementCount();
                    return Integer.compare(student1.seniority, student2.seniority);
                }
            };
        }

        @Override
        public String toString() {
            return String.format("(%s, %s, %d, %d)", name, university, id, seniority);
        }

        @Override
        public boolean equals(Object o) {
            if (o instanceof Student) {
                Student s = (Student) o;
                return s.name.equals(name)
                        && s.university.equals(university)
                        && s.id == id
                        && s.seniority == seniority;
            }
            return false;
        }
    }

    private abstract static class ComparatorWithCount<T> implements Comparator<T> {

        private int count;

        public int getCount() {
            return count;
        }

        public void incrementCount() {
            count++;
        }

        public void resetCount() {
            count = 0;
        }
    }
}
