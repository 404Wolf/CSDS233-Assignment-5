package org.casecash;

import java.util.HashMap;
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
    public List<String> runSimulation (List<String> commands) {}

    /**
     * Initializes a student with a name and an initial account balance. InitialBalance cannot be negative.
     *
     * @param name Name of the student to create.
     * @param initialBalance Initial balance to give the student being created.
     * @return True if the student has not yet been created, false if the student with this name already exists.
     */
    public boolean init (String name, int initialBalance) {
        if
    }

    /**
     * Obtain the current balance of a given student by name.
     *
     * @param name The student to get the balance of.
     * @return The balance of the student.
     */
    public int getBalance (String name) {}

    public boolean deposit (Student student, int amount) {}

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
    public boolean transfer (Student studentA, Student studentB, int amount) {}

    /**
     * Remove money from a student account. If the withdraw fails because it would make the balance negative, the
     * balance of the student account should not be changed at all
     *
     * @return True if remove is successful, and return false if removing will result in a negative balance.
     */
    public boolean withdraw ()

    /**
     * Returns a list of student names in alphabetical order. You are not allowed to use the Java sorting
     * functions, and should write your own.
     *
     * @implNote Implements merge sort.
     */
    public List<Student> sortName() {}

    /**
     * List of students sorted by current balance.
     *
     * @implNote Implements quick sort.
     * @return a list of student names in the order of smallest balance to largest balance in their account
      */
    public List<Student> sortBalance() {}

    /**
     * Obtain the list of all students currently in the case cash system.
     *
     * @return List of all students currently in the case cash system.
     */
    public List<Student> getStudents() {
        return students.values().stream().toList();
    }

    public static class Student {
        private String name;
        private int balance;

        private Student(String name) {
            this.name = name;
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
            this.balance = newAmount;
        }
    }
}
