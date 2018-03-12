/*
 * Authors: 	Peter Ebear, David Engel, Evan Loughlin, James MacIsaac, Shane Sims
 * Project: 	Genetic Algorithm based Association Rule Learning System
 * Course: 	CPSC 599.44 - Machine Learning - Winter 2018
 * Professor:	Dr. Jörg Denzinger
 * University:	University of Calgary
 */

import Rule.FeatureRequirement;
import Rule.Rule;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Peter Ebear, David Engel, Evan Loughlin, James MacIsaac, Shane Sims
 */
public class RuleTest {

    FeatureRequirement fr1, fr2, fr3, fr4, fr5, fr6, fr7, fr8, fr9, fr10;

    public RuleTest() {
        // set up some testable FeatureRequirements
        try {
            Database db;
            fr1 = new FeatureRequirement(0, 0, 10.5f, 4.5f); //IGNORE
            fr2 = new FeatureRequirement(0, 1, 3.738f, -2.5f); //ANTECEDENT
            fr3 = new FeatureRequirement(0, 1, 3.45f, 0f); //ANTECEDENT
            fr4 = new FeatureRequirement(0, 0, 0f, 0f); //IGNORE
            fr5 = new FeatureRequirement(0, 0, 0f, 0f); //IGNORE
            fr6 = new FeatureRequirement(0, 0, 0f, 0f); //IGNORE
            fr7 = new FeatureRequirement(0, 0, 0f, 0f); //IGNORE
            fr8 = new FeatureRequirement(0, 2, 10.367f, -5.62f); //CONSEQUENT
            fr9 = new FeatureRequirement(0, 0, 0f, 0f); //IGNORE
            fr10 = new FeatureRequirement(0, 0, 0f, 0f); //IGNORE
        } catch (FeatureRequirement.InvalidFeatReqException ifre) {
            System.out.println(ifre.getMessage());
        }
    }

    @BeforeClass
    public static void setUpClass() {
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() {
    }

    @After
    public void tearDown() {
    }

    @Test
    public void testNewValidRule() {
        Rule r1 = new Rule();
        for (FeatureRequirement featureReq : r1.getFeatureReqs()) {
            assertFalse(featureReq.evaluate(5.5f)); // by default upper and lower were set to 0
            assertTrue(featureReq.evaluate(0f)); // this will work
        }
    }

    @Test
    public void testSettingFeatureRequirements() {
        Rule r2 = new Rule();

        assertFalse(r2.getFeatureReqs()[0].evaluate(5.5f)); // default wont allow this
        assertTrue(r2.getFeatureReqs()[0].evaluate(0f)); // by default this works
        assertEquals(r2.getFeatureReqs()[0].getParticipation(), 0);
        r2.replaceFeatureRequirement(0, fr1); // set the new one
        assertTrue(r2.getFeatureReqs()[0].evaluate(5.5f)); // GOOD
        assertFalse(r2.getFeatureReqs()[0].evaluate(11.5f)); //TOO HIGH
        assertFalse(r2.getFeatureReqs()[0].evaluate(3.5f)); //TOO LOW
        assertEquals(r2.getFeatureReqs()[0].getParticipation(), 0);

        assertTrue(r2.getFeatureReqs()[1].evaluate(0f)); // by default this works
        assertEquals(r2.getFeatureReqs()[1].getParticipation(), 0);
        r2.replaceFeatureRequirement(1, fr2);
        assertTrue(r2.getFeatureReqs()[1].evaluate(1.5f)); // GOOD
        assertFalse(r2.getFeatureReqs()[1].evaluate(4.0f)); //TOO HIGH
        assertFalse(r2.getFeatureReqs()[1].evaluate(-6.5f)); //TOO LOW
        assertEquals(r2.getFeatureReqs()[1].getParticipation(), 1);

        assertTrue(r2.getFeatureReqs()[2].evaluate(0f)); // by default this works
        r2.replaceFeatureRequirement(2, fr3);
        assertTrue(r2.getFeatureReqs()[2].evaluate(3.4444f)); // GOOD
        assertFalse(r2.getFeatureReqs()[2].evaluate(4.0f)); //TOO HIGH
        assertFalse(r2.getFeatureReqs()[2].evaluate(-1.0f)); //TOO LOW

        r2.replaceFeatureRequirement(3, fr4);
        r2.replaceFeatureRequirement(4, fr5);
        r2.replaceFeatureRequirement(5, fr6);
        r2.replaceFeatureRequirement(6, fr7);

        assertTrue(r2.getFeatureReqs()[7].evaluate(0f)); // by default this works
        assertEquals(r2.getFeatureReqs()[7].getParticipation(), 0);
        r2.replaceFeatureRequirement(7, fr8);
        assertTrue(r2.getFeatureReqs()[7].evaluate(7.9f)); // GOOD
        assertFalse(r2.getFeatureReqs()[7].evaluate(15.5f)); //TOO HIGH
        assertFalse(r2.getFeatureReqs()[7].evaluate(-8.9f)); //TOO LOW
        assertEquals(r2.getFeatureReqs()[7].getParticipation(), 2);

        r2.replaceFeatureRequirement(8, fr9);
        r2.replaceFeatureRequirement(9, fr10);
        assertTrue(r2.getFeatureReqs()[9].evaluate(0f)); // by default this works
    }

    @Test
    public void testAlteringFeatureRequirements() {
        Rule r3 = new Rule();

        assertFalse(r3.getFeatureReqs()[0].evaluate(5.5f)); // default wont allow this
        assertTrue(r3.getFeatureReqs()[0].evaluate(0f)); // by default this works
        assertEquals(r3.getFeatureReqs()[0].getParticipation(), 0);
        r3.updateFeatureRequirement(0, 0, 10.5f, 4.5f); // set the new values
        assertTrue(r3.getFeatureReqs()[0].evaluate(5.5f)); // GOOD
        assertFalse(r3.getFeatureReqs()[0].evaluate(11.5f)); //TOO HIGH
        assertFalse(r3.getFeatureReqs()[0].evaluate(3.5f)); //TOO LOW
        assertEquals(r3.getFeatureReqs()[0].getParticipation(), 0);

        assertTrue(r3.getFeatureReqs()[1].evaluate(0f)); // by default this works
        assertEquals(r3.getFeatureReqs()[1].getParticipation(), 0);
        r3.updateFeatureRequirement(1, 1, 3.738f, -2.5f);
        assertTrue(r3.getFeatureReqs()[1].evaluate(1.5f)); // GOOD
        assertFalse(r3.getFeatureReqs()[1].evaluate(4.0f)); //TOO HIGH
        assertFalse(r3.getFeatureReqs()[1].evaluate(-6.5f)); //TOO LOW
        assertEquals(r3.getFeatureReqs()[1].getParticipation(), 1);

        r3.replaceFeatureRequirement(2, fr3);

        r3.replaceFeatureRequirement(3, fr4);
        r3.replaceFeatureRequirement(4, fr5);
        r3.replaceFeatureRequirement(5, fr6);
        r3.replaceFeatureRequirement(6, fr7);

        assertTrue(r3.getFeatureReqs()[7].evaluate(0f)); // by default this works
        assertEquals(r3.getFeatureReqs()[7].getParticipation(), 0);
        r3.replaceFeatureRequirement(7, fr8);
        assertTrue(r3.getFeatureReqs()[7].evaluate(7.9f)); // GOOD
        assertFalse(r3.getFeatureReqs()[7].evaluate(15.5f)); //TOO HIGH
        assertFalse(r3.getFeatureReqs()[7].evaluate(-8.9f)); //TOO LOW
        assertEquals(r3.getFeatureReqs()[7].getParticipation(), 2);

        r3.replaceFeatureRequirement(8, fr9);
        r3.replaceFeatureRequirement(9, fr10);
        assertTrue(r3.getFeatureReqs()[9].evaluate(0f)); // by default this works
    }

    @Test
    public void testCopyMethod() {
        Rule r4 = new Rule();

        assertFalse(r4.getFeatureReqs()[0].evaluate(5.5f)); // default wont allow this
        assertTrue(r4.getFeatureReqs()[0].evaluate(0f)); // by default this works
        assertEquals(r4.getFeatureReqs()[0].getParticipation(), 0);
        r4.replaceFeatureRequirement(0, fr1); // set the new one
        assertTrue(r4.getFeatureReqs()[0].evaluate(5.5f)); // GOOD
        assertFalse(r4.getFeatureReqs()[0].evaluate(11.5f)); //TOO HIGH
        assertFalse(r4.getFeatureReqs()[0].evaluate(3.5f)); //TOO LOW
        assertEquals(r4.getFeatureReqs()[0].getParticipation(), 0);

        assertTrue(r4.getFeatureReqs()[1].evaluate(0f)); // by default this works
        assertEquals(r4.getFeatureReqs()[1].getParticipation(), 0);
        r4.replaceFeatureRequirement(1, fr2);
        assertTrue(r4.getFeatureReqs()[1].evaluate(1.5f)); // GOOD
        assertFalse(r4.getFeatureReqs()[1].evaluate(4.0f)); //TOO HIGH
        assertFalse(r4.getFeatureReqs()[1].evaluate(-6.5f)); //TOO LOW
        assertEquals(r4.getFeatureReqs()[1].getParticipation(), 1);

        assertTrue(r4.getFeatureReqs()[2].evaluate(0f)); // by default this works
        r4.replaceFeatureRequirement(2, fr3);
        assertTrue(r4.getFeatureReqs()[2].evaluate(3.4444f)); // GOOD
        assertFalse(r4.getFeatureReqs()[2].evaluate(4.0f)); //TOO HIGH
        assertFalse(r4.getFeatureReqs()[2].evaluate(-1.0f)); //TOO LOW

        r4.replaceFeatureRequirement(3, fr4);
        r4.replaceFeatureRequirement(4, fr5);
        r4.replaceFeatureRequirement(5, fr6);
        r4.replaceFeatureRequirement(6, fr7);

        assertTrue(r4.getFeatureReqs()[7].evaluate(0f)); // by default this works
        assertEquals(r4.getFeatureReqs()[7].getParticipation(), 0);
        r4.replaceFeatureRequirement(7, fr8);
        assertTrue(r4.getFeatureReqs()[7].evaluate(7.9f)); // GOOD
        assertFalse(r4.getFeatureReqs()[7].evaluate(15.5f)); //TOO HIGH
        assertFalse(r4.getFeatureReqs()[7].evaluate(-8.9f)); //TOO LOW
        assertEquals(r4.getFeatureReqs()[7].getParticipation(), 2);

        r4.replaceFeatureRequirement(8, fr9);
        r4.replaceFeatureRequirement(9, fr10);
        assertTrue(r4.getFeatureReqs()[9].evaluate(0f)); // by default this works

        // time to test the copying
        Rule r5 = r4.copy();

        assertTrue(r5.getFeatureReqs()[0].evaluate(5.5f)); // GOOD
        assertFalse(r5.getFeatureReqs()[0].evaluate(11.5f)); //TOO HIGH
        assertFalse(r5.getFeatureReqs()[0].evaluate(3.5f)); //TOO LOW
        assertEquals(r5.getFeatureReqs()[0].getParticipation(), 0);
        r5.updateFeatureRequirement(0, 0, 10.5f, 4.5f); // set the new values
        assertTrue(r5.getFeatureReqs()[0].evaluate(5.5f)); // GOOD
        assertFalse(r5.getFeatureReqs()[0].evaluate(11.5f)); //TOO HIGH
        assertFalse(r5.getFeatureReqs()[0].evaluate(3.5f)); //TOO LOW
        assertEquals(r5.getFeatureReqs()[0].getParticipation(), 0);

        assertTrue(r5.getFeatureReqs()[1].evaluate(1.5f)); // GOOD
        assertFalse(r5.getFeatureReqs()[1].evaluate(4.0f)); //TOO HIGH
        assertFalse(r5.getFeatureReqs()[1].evaluate(-6.5f)); //TOO LOW
        assertEquals(r5.getFeatureReqs()[1].getParticipation(), 1);
        r5.updateFeatureRequirement(1, 1, 6.234f, -9.8f);
        assertTrue(r5.getFeatureReqs()[1].evaluate(-7.56f)); // GOOD
        assertFalse(r5.getFeatureReqs()[1].evaluate(8.0f)); //TOO HIGH
        assertFalse(r5.getFeatureReqs()[1].evaluate(-12.9f)); //TOO LOW
        assertEquals(r5.getFeatureReqs()[1].getParticipation(), 1);

        r5.replaceFeatureRequirement(2, fr3);

        r5.replaceFeatureRequirement(3, fr4);
        r5.replaceFeatureRequirement(4, fr5);
        r5.replaceFeatureRequirement(5, fr6);
        r5.replaceFeatureRequirement(6, fr7);

        
        assertTrue(r5.getFeatureReqs()[7].evaluate(7.9f)); // GOOD
        assertFalse(r5.getFeatureReqs()[7].evaluate(15.5f)); //TOO HIGH
        assertFalse(r5.getFeatureReqs()[7].evaluate(-8.9f)); //TOO LOW
        assertEquals(r5.getFeatureReqs()[7].getParticipation(), 2);
        r5.replaceFeatureRequirement(7, fr8);
        assertTrue(r5.getFeatureReqs()[7].evaluate(7.9f)); // GOOD
        assertFalse(r5.getFeatureReqs()[7].evaluate(15.5f)); //TOO HIGH
        assertFalse(r5.getFeatureReqs()[7].evaluate(-8.9f)); //TOO LOW
        assertEquals(r5.getFeatureReqs()[7].getParticipation(), 2);

        r5.replaceFeatureRequirement(8, fr9);
        r5.replaceFeatureRequirement(9, fr10);
        assertTrue(r5.getFeatureReqs()[9].evaluate(0f)); // by default this works
    }
}
