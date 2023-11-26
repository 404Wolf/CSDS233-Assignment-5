package org.casecash;

import org.junit.jupiter.api.Test;

import java.util.LinkedList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class CaseCashSystemTest {
    static CaseCashSystem getTestSystemA() {
        CaseCashSystem system = new CaseCashSystem();
        system.init("joe", 44);
        system.init("steve", 50);
        system.init("peter", 33);
        system.init("bob", 330);
        return system;
    }

    static CaseCashSystem getTestSystemB() {
        CaseCashSystem system = new CaseCashSystem();
        system.init("michael", 434);
        system.init("wonda", 520);
        system.init("marry", 233);
        system.init("joseph", 0);
        return system;
    }

    static CaseCashSystem getTestSystemC() {
        CaseCashSystem system = new CaseCashSystem();
        system.init("robert", 0);
        system.init("steve", 1);
        system.init("albert", 2);
        system.init("sam", 3 );
        return system;
    }

    @Test
    void runSimulation() {
        List<String> expectedOutput = new LinkedList<>();
        List<String> inputCommands = new LinkedList<>();

        // Create all of the students from set A
        inputCommands.add("INIT, joE, 44");
        expectedOutput.add("true");
        //
        inputCommands.add("INIT, stEve, 50");
        expectedOutput.add("true");
        //
        inputCommands.add("INIT, pEter, 33");
        expectedOutput.add("true");
        //
        inputCommands.add("INIT, bob, 330");
        expectedOutput.add("true");

        // Assert the starting balances of all the students
        inputCommands.add("GET, Joe");
        expectedOutput.add("44");
        //
        inputCommands.add("GET, stEve");
        expectedOutput.add("50");
        //
        inputCommands.add("GET, pEter");
        expectedOutput.add("33");
        //
        inputCommands.add("GET, BoB");
        expectedOutput.add("330");

        // Give someone money and assert they got it
        inputCommands.add("DEPOSIT, bob, 50");
        expectedOutput.add("true");
        inputCommands.add("GET, bob");
        expectedOutput.add("380");

        // Deposit to a student that does not exist
        inputCommands.add("DEPOSIT, sam, 555");
        expectedOutput.add("false");

        // Deposit to make someone's balance negative
        inputCommands.add("DEPOSIT, bob, -1000");
        expectedOutput.add("false");

        // Transfer money between two students
        inputCommands.add("TRANSFER, bob, joe, 30");
        expectedOutput.add("true");
        inputCommands.add("GET, bob");
        expectedOutput.add("350");
        inputCommands.add("GET, joe");
        expectedOutput.add("74");

        // Make sure students can be sorted by balance
        inputCommands.add("SORT, balance");
        expectedOutput.add("[peter, steve, joe, bob]");

        // Make sure students can be sorted by name
        inputCommands.add("SORT, name");
        expectedOutput.add("[bob, joe, peter, steve]");

        // Run the simulation
        List<String> output = CaseCashSystem.runSimulation(inputCommands);
        assertEquals(expectedOutput, output);
    }

    @Test
    void getBalance() {
        CaseCashSystem system = getTestSystemA();
        assertEquals(44, system.getBalance("joe"));
        assertEquals(50, system.getBalance("steve"));
        assertEquals(33, system.getBalance("peter"));
        assertEquals(330, system.getBalance("bob"));
    }

    @Test
    void deposit() {
        CaseCashSystem system = getTestSystemA();
        assertTrue(system.deposit("joe", 20));
        assertEquals(64, system.getBalance("joe"));
        assertFalse(system.deposit("joe", -100));
        assertTrue(system.deposit("joe", 20));
        assertEquals(84, system.getBalance("joe"));
    }

    @Test
    void transfer() {
        CaseCashSystem system = getTestSystemA();

        // Test a basic transfer
        system.transfer("joe", "bob", 20);
        assertEquals(24, system.getBalance("joe"));
        assertEquals(350, system.getBalance("bob"));

        // Assert transfers cannot endebt people
        assertFalse(system.transfer("joe", "bob", 500));
        assertEquals(24, system.getBalance("joe"));
    }

    @Test
    void withdraw() {
        CaseCashSystem system = getTestSystemA();

        system.init("joe", 44);

        system.init("steve", 50);
        assertEquals(system.getBalance("steve"), 50);
        system.withdraw("steve", 50);
        assertEquals(system.getBalance("steve"), 0);

        system.withdraw("joe", 20);
        assertEquals(system.getBalance("joe"), 24);
    }

    @Test
    void sortName() {
        CaseCashSystem system;
        List<CaseCashSystem.Student> sortedNames;

        system = getTestSystemA();
        sortedNames = system.sortName();
        assertEquals(sortedNames.get(0).getName(), "bob");
        assertEquals(sortedNames.get(1).getName(), "joe");
        assertEquals(sortedNames.get(2).getName(), "peter");
        assertEquals(sortedNames.get(3).getName(), "steve");

        system = getTestSystemB();
        sortedNames = system.sortName();
        assertEquals(sortedNames.get(0).getName(), "joseph");
        assertEquals(sortedNames.get(1).getName(), "marry");
        assertEquals(sortedNames.get(2).getName(), "michael");
        assertEquals(sortedNames.get(3).getName(), "wonda");

        system = getTestSystemC();
        sortedNames = system.sortName();
        assertEquals(sortedNames.get(0).getName(), "albert");
        assertEquals(sortedNames.get(1).getName(), "robert");
        assertEquals(sortedNames.get(2).getName(), "sam");
        assertEquals(sortedNames.get(3).getName(), "steve");
    }

    @Test
    void sortBalance() {
        CaseCashSystem system;
        List<CaseCashSystem.Student> sortedNames;

        for (int i = 0; i < 100; i++) {
            system = getTestSystemA();
            sortedNames = system.sortBalance();
            assertEquals("peter", sortedNames.get(0).getName());
            assertEquals("joe", sortedNames.get(1).getName());
            assertEquals("steve", sortedNames.get(2).getName());
            assertEquals("bob", sortedNames.get(3).getName());

            system = getTestSystemB();
            sortedNames = system.sortBalance();
            assertEquals("joseph", sortedNames.get(0).getName());
            assertEquals("marry", sortedNames.get(1).getName());
            assertEquals("michael", sortedNames.get(2).getName());
            assertEquals("wonda", sortedNames.get(3).getName());

            system = getTestSystemC();
            system.init("zed", 22222222);
            sortedNames = system.sortBalance();
            assertEquals("robert", sortedNames.get(0).getName());
            assertEquals("steve", sortedNames.get(1).getName());
            assertEquals("albert", sortedNames.get(2).getName());
            assertEquals("sam", sortedNames.get(3).getName());
            assertEquals("zed", sortedNames.get(4).getName());
        }
    }
}