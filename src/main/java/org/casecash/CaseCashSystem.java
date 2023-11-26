package org.casecash;

import org.sort.MergeSort;
import org.sort.QuickSort;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;


public class CaseCashSystem {
    private HashMap<String, Student> students;

    /**
     * Instantiate the org.casecash.CaseCashSystem.
     */
    public CaseCashSystem () {
        students = new HashMap<String, Student>();
    }

    /**
     * Function used to run the simulation provided by the commands. This function should parse the
     * commands and call their respective helper function to complete a task. More details regarding
     * what inputs look like are on the next page. Note: Every call to runSimulation() should clear the
     * previous list of students (starts clean).
     *
     * @param commands List of commands to run for simulation.
     * @return The output of the result of running each respective command.
     */
    public List<String> runSimulation (List<String> commands) {
        CaseCashSystem system = new CaseCashSystem();
        List<String> output = new LinkedList<>();

        for (String commandStr : commands) {
            List<String> command = List.of(commandStr.split(" "));
            switch (command.get(0)) {
                case "INIT" -> output.add(String.valueOf(system.init(command.get(1), Integer.parseInt(command.get(2)))));
                case "GET" -> output.add(String.valueOf(system.getBalance(command.get(1))));
                case "DEPOSIT" -> output.add(String.valueOf(
                        system.deposit(command.get(1), Integer.parseInt(command.get(2))))
                );
                case "TRANSFER" -> output.add(String.valueOf(
                        system.transfer(command.get(1), command.get(2), Integer.parseInt(command.get(3))))
                );
                case "SORT" -> {
                    switch (command.get(1)) {
                        case "balance" -> output.add(system.sortBalance().toString());
                        case "name" -> output.add(system.sortName().toString());
                    }
                }
                case "WITHDRAW" -> output.add(
                        String.valueOf(
                                system.withdraw(command.get(1), Integer.parseInt(command.get(2)))
                        )
                );
            }
        }
        return output;
    }

    /**
     * Initializes a student with a name and an initial account balance. InitialBalance cannot be negative.
     *
     * @param name           Name of the student to create.
     * @param initialBalance Initial balance to give the student being created.
     * @return True if the student has not yet been created, false if the student with this name already exists.
     */
    public boolean init (String name, int initialBalance) {
        if (students.containsKey(name)) return false;
        else {
            students.put(name, new Student(name, initialBalance));
            return true;
        }
    }

    /**
     * Obtain the current balance of a given student by name.
     *
     * @param name The student to get the balance of.
     * @return The balance of the student.
     */
    public int getBalance (Student name) {
        return name.getBalance();
    }

    public int getBalance (String name) {
        return students.get(name).getBalance();
    }

    /**
     * Deposit money from a student account.
     *
     * @param student The student to deposit money to.
     * @param amount The amount of money to deposit to into the account.
     * @return True if the input amount is positive, otherwise false.
     */
    public boolean deposit (Student student, int amount) {
        if (amount < 0)
            return false;
        else {
            student.updateBalance(student.getBalance() + amount);
            return true;
        }
    }

    public boolean deposit (String student, int amount) {
        return deposit(students.get(student), amount);
    }

    /**
     * Transfers the amount from studentA account to studentB account. This function should return true
     * if transferring is successful, and return false if transferring money from A to B will result in a
     * negative balance. The parameter amount cannot be negative. If false, the balance of account A
     * and B should not be changed at all.
     *
     * @param studentA Student to transfer money from.
     * @param studentB Student to transfer money to.
     * @param amount Amount of money to transfer.
     * @return Success of money transfer.
     */
    public boolean transfer (Student studentA, Student studentB, int amount) {
        if (studentA.getBalance() - amount < 0)
            return false;
        else {
            studentA.updateBalance(studentA.getBalance() - amount);
            studentB.updateBalance(studentB.getBalance() + amount);
            return true;
        }
    }

    public boolean transfer (String studentA, String studentB, int amount) {
        return transfer(students.get(studentA), students.get(studentB), amount);
    }

    /**
     * Remove money from a student account. If the withdraw fails because it would make the balance negative, the
     * balance of the student account should not be changed at all
     *
     * @return True if remove is successful, and return false if removing will result in a negative balance.
     */
    public boolean withdraw (Student student, int amount) {
        if (student.getBalance() - amount < 0)
            return false;
        else {
            student.updateBalance(student.getBalance() - amount);
            return true;
        }
    }

    public boolean withdraw (String student, int amount) {
        return withdraw(students.get(student), amount);
    }

    /**
     * Purge the CaseCashSystem.
     */
    public void purge () {
        students = new HashMap<String, Student>();
    }

    /**
     * Returns a list of student names in alphabetical order. You are not allowed to use the Java sorting
     * functions, and should write your own.
     *
     * @implNote Implements merge sort.
     */
    public List<Student> sortName() {
        ArrayList<Student> sortedStudents = new ArrayList<Student>(students.values());
        sortedStudents.forEach(student -> student.setCompareBy(Student.CompareOptions.NAME));
        MergeSort.sort(sortedStudents);
        return sortedStudents;
    }

    /**
     * List of students sorted by current balance.
     *
     * @implNote Implements quick sort.
     * @return a list of student names in the order of smallest balance to largest balance in their account
      */
    public List<Student> sortBalance() {
        ArrayList<Student> sortedStudents = new ArrayList<>(students.values());
        sortedStudents.forEach(student -> student.setCompareBy(Student.CompareOptions.BALANCE));
        QuickSort.sort(sortedStudents);
        return sortedStudents;
    }

    /**
     * Obtain the list of all students currently in the case cash system.
     *
     * @return List of all students currently in the case cash system.
     */
    public List<Student> getStudents() {
        return students.values().stream().toList();
    }

    public static class Student implements Comparable<Student> {
        /**
         * Current nzame of this student.
         */
        private String name;

        /**
         * Current balance of this student.
         */
        private int balance = 0;

        /**
         * How to compare this given student to any other given student. Affects this.compareTo(Student other)
         */
        private CompareOptions compareBy = CompareOptions.NAME;

        public enum CompareOptions {
            NAME, BALANCE
        }

        private Student(String name) {
            setName(name);
        }

        private Student(String name, int initialBalance) {
            setName(name);
            updateBalance(initialBalance);
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public int getBalance() {
            return balance;
        }

        public void updateBalance(int newAmount) {
            if (newAmount < 0)
                throw new Student.NegativeBalanceError();
            this.balance = newAmount;
        }

        @Override
        public int compareTo(Student other) {
            return compareTo(other, getCompareBy());
        }

        public int compareTo(Student other, Student.CompareOptions by) {
            return switch (by) {
                case NAME -> this.getName().compareTo(other.getName());
                case BALANCE -> this.getBalance() - other.getBalance();
            };
        }

        public CompareOptions getCompareBy() {
            return compareBy;
        }

        public void setCompareBy(CompareOptions compareBy) {
            this.compareBy = compareBy;
        }

        public static class NegativeBalanceError extends RuntimeException {}
    }
}
