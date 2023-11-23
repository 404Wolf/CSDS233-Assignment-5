package org.casecash;

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

    @org.junit.jupiter.api.Test
    void runSimulation() {
    }

    @org.junit.jupiter.api.Test
    void getBalance() {
        CaseCashSystem system = getTestSystemA();

    }

    @org.junit.jupiter.api.Test
    void deposit() {
    }

    @org.junit.jupiter.api.Test
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

    @org.junit.jupiter.api.Test
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

    @org.junit.jupiter.api.Test
    void sortName() {
        CaseCashSystem system;
        List<CaseCashSystem.Student> sortedNames;

        system = getTestSystemA();
        sortedNames = system.sortName();
        assertEquals(sortedNames.get(0).getName(), "bob");
        assertEquals(sortedNames.get(1).getName(), "joe");
        assertEquals(sortedNames.get(2).getName(), "steve");
        assertEquals(sortedNames.get(3).getName(), "joe");

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

    @org.junit.jupiter.api.Test
    void sortBalance() {
        CaseCashSystem system;
        List<CaseCashSystem.Student> sortedNames;

        system = getTestSystemA();
        sortedNames = system.sortBalance();
        assertEquals(sortedNames.get(0).getName(), "peter");
        assertEquals(sortedNames.get(1).getName(), "joe");
        assertEquals(sortedNames.get(2).getName(), "steve");
        assertEquals(sortedNames.get(3).getName(), "bob");

        system = getTestSystemB();
        sortedNames = system.sortBalance();
        assertEquals(sortedNames.get(0).getName(), "joseph");
        assertEquals(sortedNames.get(1).getName(), "marry");
        assertEquals(sortedNames.get(2).getName(), "michael");
        assertEquals(sortedNames.get(3).getName(), "wonda");

        system = getTestSystemC();
        sortedNames = system.sortBalance();
        assertEquals(sortedNames.get(0).getName(), "robert");
        assertEquals(sortedNames.get(1).getName(), "steve");
        assertEquals(sortedNames.get(2).getName(), "albert");
        assertEquals(sortedNames.get(3).getName(), "sam");
    }
}