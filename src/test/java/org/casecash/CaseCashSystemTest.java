package org.casecash;

import org.junit.jupiter.api.Test;

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
    }

    @Test
    void getBalance() {
        CaseCashSystem system = getTestSystemA();

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