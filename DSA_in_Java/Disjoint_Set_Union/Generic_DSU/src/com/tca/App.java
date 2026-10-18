package com.tca;

import java.util.NoSuchElementException;

import com.tca.util.LinkedDisjointSet;

public class App {
	public static void main(String[] args) {
        testMakeSet();
        testUnion();
        testConnectivity();
        testTotalSets();
        testPathCompressionScenario();
        testInvalidElements();

        System.out.println("\nAll tests passed!");
    }

    private static void testMakeSet() {
        LinkedDisjointSet<Integer> ds = new LinkedDisjointSet<>();

        assert ds.makeSet(1);
        assert ds.makeSet(2);
        assert !ds.makeSet(1); // Duplicate

        assert ds.size() == 2;
        assert ds.totalSets() == 2;

        System.out.println("makeSet: PASSED");
    }

    private static void testUnion() {
        LinkedDisjointSet<Integer> ds = new LinkedDisjointSet<>();

        ds.makeSet(1);
        ds.makeSet(2);
        ds.makeSet(3);

        assert ds.union(1, 2);
        assert !ds.union(1, 2); // Already connected
        assert ds.union(2, 3);

        assert ds.size() == 3;
        assert ds.totalSets() == 1;

        System.out.println("union: PASSED");
    }

    private static void testConnectivity() {
        LinkedDisjointSet<Integer> ds = new LinkedDisjointSet<>();

        for (int i = 1; i <= 5; i++) {
            ds.makeSet(i);
        }

        ds.union(1, 2);
        ds.union(3, 4);

        assert ds.isConnected(1, 2);
        assert ds.isConnected(3, 4);
        assert !ds.isConnected(1, 3);
        assert !ds.isConnected(1, 5);

        ds.union(2, 3);

        assert ds.isConnected(1, 4);
        assert !ds.isConnected(1, 5);

        System.out.println("connectivity: PASSED");
    }

    private static void testTotalSets() {
        LinkedDisjointSet<String> ds = new LinkedDisjointSet<>();

        ds.makeSet("A");
        ds.makeSet("B");
        ds.makeSet("C");
        ds.makeSet("D");

        assert ds.totalSets() == 4;

        ds.union("A", "B");
        assert ds.totalSets() == 3;

        ds.union("C", "D");
        assert ds.totalSets() == 2;

        ds.union("A", "C");
        assert ds.totalSets() == 1;

        // Redundant union must not decrease the count.
        assert !ds.union("B", "D");
        assert ds.totalSets() == 1;

        System.out.println("totalSets: PASSED");
    }

    private static void testPathCompressionScenario() {
        LinkedDisjointSet<Integer> ds = new LinkedDisjointSet<>();

        for (int i = 1; i <= 8; i++) {
            ds.makeSet(i);
        }

        // Build balanced merges to exercise rank-based unions.
        ds.union(1, 2);
        ds.union(3, 4);
        ds.union(1, 3);

        ds.union(5, 6);
        ds.union(7, 8);
        ds.union(5, 7);

        ds.union(1, 5);

        // All elements should now share the same representative.
        int representative = ds.find(1);

        for (int i = 2; i <= 8; i++) {
            assert ds.find(i) == representative;
        }

        assert ds.totalSets() == 1;

        System.out.println("path compression scenario: PASSED");
    }

    private static void testInvalidElements() {
        LinkedDisjointSet<Integer> ds = new LinkedDisjointSet<>();
        ds.makeSet(1);

        assert !ds.isConnected(1, 99);
        assert !ds.elementExists(99);

        boolean findThrew = false;
        try {
            ds.find(99);
        } catch (NoSuchElementException e) {
            findThrew = true;
        }
        assert findThrew;

        boolean unionThrew = false;
        try {
            ds.union(1, 99);
        } catch (NoSuchElementException e) {
            unionThrew = true;
        }
        assert unionThrew;

        boolean nullThrew = false;
        try {
            ds.makeSet(null);
        } catch (IllegalArgumentException e) {
            nullThrew = true;
        }
        assert nullThrew;

        System.out.println("invalid elements: PASSED");
    }
}
