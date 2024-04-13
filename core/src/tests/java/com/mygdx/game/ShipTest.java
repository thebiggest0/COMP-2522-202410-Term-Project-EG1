package com.mygdx.game;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Here are some unit tests for your Guppy. You must pass all of these.
 * Passing all of these tests does not mean your Guppy is perfect, though.
 * How are your comments?
 * Are visibilities correct?
 * Is mutability locked down?
 * Is scope minimized?
 * Is your style correct? Are elements in the class in the correct order?
 * Are instance variables initialized in the same order as they are declared?
 */
class ShipTest {

    private Ship zeroParamShip;
    private Ship testShip;

    @BeforeEach
    public void setUp() {

        zeroParamShip = new Ship();
        testShip = new Ship(  "Poecilia",
                "reticulata",
                1,
                true,
                3,
                0.75);
    }

    @Test
    public void containsConstantCalledYOUNG_FISH_AGE_IN_WEEKS() {
        assertEquals(10, Ship.YOUNG_FISH_AGE_IN_WEEKS);
    }

    @Test
    public void containsConstantCalledMATURE_FISH_AGE_IN_WEEKS() {
        assertEquals(30, Ship.MATURE_FISH_AGE_IN_WEEKS);
    }

    @Test
    public void containsConstantCalledMAXIMUM_AGE_IN_WEEKS() {
        assertEquals(50, Ship.MAXIMUM_AGE_IN_WEEKS);
    }

    @Test
    public void containsConstantCalledMINIMUM_WATER_VOLUME_ML() {
        assertEquals(250.0, Ship.MINIMUM_WATER_VOLUME_ML, 0.0);
    }

    @Test
    public void containsConstantCalledDEFAULT_GENUS() {
        assertTrue(Ship.DEFAULT_GENUS.equals("Poecilia"));
    }

    @Test
    public void containsConstantCalledDEFAULT_SPECIES() {
        assertTrue(Ship.DEFAULT_SPECIES.equals("reticulata"));
    }

    @Test
    public void containsConstantCalledDEFAULT_HEALTH_COEFFICIENT() {
        assertEquals(0.5, Ship.DEFAULT_HEALTH_COEFFICIENT, 0.0);
    }

    @Test
    public void containsConstantCalledMINIMUM_HEALTH_COEFFICIENT() {
        assertEquals(0.0, Ship.MINIMUM_HEALTH_COEFFICIENT, 0.0);
    }

    @Test
    public void containsConstantCalledMAXIMUM_HEALTH_COEFFICIENT() {
        assertEquals(1.0, Ship.MAXIMUM_HEALTH_COEFFICIENT, 0.0);
    }

    @Test
    public void staticGuppyCounterIsCorrectlyTrackingNewbornGuppiesInZeroParamConstructor() {
        final int numberAlreadyCreated = Ship.getNumberOfGuppiesBorn();
        final int numberCreated = 100;
        for (int i = 0; i < numberCreated; ++i) {
            Ship someShip = new Ship();
        }
        assertEquals(numberAlreadyCreated + numberCreated, Ship.getNumberOfGuppiesBorn());
    }

    @Test
    public void staticGuppyCounterIsCorrectlyTrackingNewbornGuppiesInMultiParamConstructor() {
        final int numberAlreadyCreated = Ship.getNumberOfGuppiesBorn();
        final int numberCreated = 100;
        for (int i = 0; i < numberCreated; ++i)
            new Ship("Poecilia",
                    "reticulata",
                    1,
                    true,
                    3,
                    0.75);
        assertEquals(numberAlreadyCreated + numberCreated, Ship.getNumberOfGuppiesBorn());
    }

    @Test
    public void zeroParamGuppyIsSetToCorrectValues() {
        assertTrue(zeroParamShip.getGenus().equals("Poecilia"));
        assertTrue(zeroParamShip.getSpecies().equals("reticulata"));
        assertEquals(0, zeroParamShip.getAgeInWeeks());
        assertTrue(zeroParamShip.getIsFemale());
        assertEquals(0, zeroParamShip.getGenerationNumber());
        assertTrue(zeroParamShip.getIsAlive());
        assertEquals(0.5, zeroParamShip.getHealthCoefficient(), 0.0);
    }

    @Test
    public void multiParamGuppyIsSetToCorrectValues() {
        assertTrue(testShip.getGenus().equals("Poecilia"));
        assertTrue(testShip.getSpecies().equals("reticulata"));
        assertEquals(1, testShip.getAgeInWeeks());
        assertTrue(testShip.getIsFemale());
        assertEquals(3, testShip.getGenerationNumber());
        assertTrue(testShip.getIsAlive());
        assertEquals(0.75, testShip.getHealthCoefficient(), 0.0);
    }

    @Test
    public void identificationNumbersAreSequentialAndUniqueInZeroParamConstruction() {
        Ship first = new Ship();
        int firstID = first.getIdentificationNumber() + 1;
        int numberCreated = 100;
        for (int i = 0; i < numberCreated; ++i) {
            Ship fry = new Ship();
            assertEquals(firstID + i, fry.getIdentificationNumber());
        }
    }

    @Test
    public void identificationNumbersAreSequentialAndUniqueInMultiParamConstruction() {
        Ship first = new Ship();
        int firstID = first.getIdentificationNumber() + 1;
        int numberCreated = 100;
        for (int i = 0; i < numberCreated; ++i) {
            Ship fry = new Ship("Poecilia",
                    "reticulata",
                    1,
                    true,
                    3,
                    0.75);
            assertEquals(firstID + i, fry.getIdentificationNumber());
        }
    }

    @Test
    public void genusAndSpeciesAreCorrectlyFormattedAndStored() {
        Ship fry = new Ship("  poECILIA    ",
                "  retICUlata   ",
                1,
                true,
                3,
                0.75);
        assertTrue(fry.getGenus().equals("Poecilia"));
        assertTrue(fry.getSpecies().equals("reticulata"));
    }

    @Test
    public void nullGenusReplacedWithDefaultGenus() {
        Ship fry = new Ship(null,
                "a",
                0,
                true,
                0 ,
                0.5);
        assertTrue(fry.getGenus().equals("Poecilia"));
    }

    @Test
    public void emptyGenusReplacedWithDefaultGenus() {
        Ship fry = new Ship("    ",
                "a",
                0,
                true,
                0 ,
                0.5);
        assertTrue(fry.getGenus().equals("Poecilia"));

    }

    @Test
    public void nullSpeciesReplacedWithDefaultSpecies() {
        Ship fry = new Ship("a",
                null,
                0,
                true,
                0 ,
                0.5);
        assertTrue(fry.getSpecies().equals("reticulata"));

    }

    @Test
    public void emptySpeciesReplacedWithDefaultSpecies() {
        Ship fry = new Ship("a",
                "    ",
                0,
                true,
                0 ,
                0.5);
        assertTrue(fry.getSpecies().equals("reticulata"));

    }

    @Test
    public void negativeAgeInWeeksBecomesZero() {
            Ship fry = new Ship("a",
                "b",
                -1,
                true,
                0 ,
                0.5);
        assertTrue(fry.getAgeInWeeks() == 0);
    }

    @Test
    public void zeroAgeInWeeksRemainsZero() {
        Ship fry = new Ship("a",
                "b",
                0,
                true,
                0 ,
                0.5);
        assertTrue(fry.getAgeInWeeks() == 0);
    }

    @Test
    public void maximumAgeInWeeksIsAlive() {
        Ship fry = new Ship("a",
                "b",
                Ship.MAXIMUM_AGE_IN_WEEKS,
                true,
                0 ,
                0.5);
        assertTrue(fry.getIsAlive());
    }

    @Test
    public void overlargeAgeInWeeksIsReducedToMax() {
        Ship fry = new Ship("a",
                "b",
                (Ship.MAXIMUM_AGE_IN_WEEKS + 100),
                true,
                0 ,
                0.5);
        assertTrue(fry.getAgeInWeeks() == Ship.MAXIMUM_AGE_IN_WEEKS);
    }

    @Test
    public void negativeGenerationNumberSetToZero() {
        Ship fry = new Ship("a",
                "b",
                0,
                true,
                -1,
                0.5);
        assertTrue(fry.getGenerationNumber() == 0);
    }

    @Test
    public void zeroGenerationNumberSetToZero() {
        Ship fry = new Ship("a",
                "b",
                0,
                true, 0,
                0.5);
        assertTrue(fry.getGenerationNumber() == 0);
    }

    @Test
    public void createGuppyWithNegativeHealthCoefficient() {
        Ship fry = new Ship("a",
                "b",
                0,
                true,
                0,
                Ship.MINIMUM_HEALTH_COEFFICIENT - 1.0);
        assertTrue(fry.getHealthCoefficient() == Ship.MINIMUM_HEALTH_COEFFICIENT);
    }

    @Test
    public void createGuppyWithOverlargeHealthCoefficient() {
        Ship fry = new Ship("a",
                "b",
                0,
                true,
                0,
                Ship.MAXIMUM_HEALTH_COEFFICIENT + 1.0);
        assertTrue(fry.getHealthCoefficient() == Ship.MAXIMUM_HEALTH_COEFFICIENT);

    }

    @Test
    public void testIncrementAge() {
        int oldAge = testShip.getAgeInWeeks();
        testShip.incrementAge();
        int newAge = testShip.getAgeInWeeks();
        assertTrue(newAge == (oldAge + 1));
    }

    @Test
    public void testIncrementAgeKillsGuppyWhenMaxAgeIsReached() {
        testShip.setAgeInWeeks(Ship.MAXIMUM_AGE_IN_WEEKS);
        assertTrue(testShip.getIsAlive());
        testShip.incrementAge();
        assertFalse(testShip.getIsAlive());
    }

    @Test
    public void ageMutatorIgnoresInvalidArguments() {
        int age = zeroParamShip.getAgeInWeeks();
        zeroParamShip.setAgeInWeeks(-1);
        assertEquals(age, zeroParamShip.getAgeInWeeks());

        zeroParamShip.setAgeInWeeks(Ship.MAXIMUM_AGE_IN_WEEKS + 1);
        assertEquals(age, zeroParamShip.getAgeInWeeks());
    }

    @Test
    public void ageMutatorAcceptsValidArguments() {
        testShip.setAgeInWeeks(0);
        assertEquals(0, testShip.getAgeInWeeks());

        testShip.setAgeInWeeks(Ship.MAXIMUM_AGE_IN_WEEKS);
        assertEquals(Ship.MAXIMUM_AGE_IN_WEEKS, testShip.getAgeInWeeks());

        testShip.setAgeInWeeks(Ship.MAXIMUM_AGE_IN_WEEKS - 10);
        assertEquals(Ship.MAXIMUM_AGE_IN_WEEKS - 10, testShip.getAgeInWeeks());
    }

    @Test
    public void healthCoefficientMutatorIgnoresInvalidArguments() {
        double healthCoefficient = zeroParamShip.getHealthCoefficient();
        zeroParamShip.setHealthCoefficient(-0.01);
        assertEquals(healthCoefficient, zeroParamShip.getHealthCoefficient(), 0.0);

        zeroParamShip.setHealthCoefficient(1.01);
        assertEquals(healthCoefficient, zeroParamShip.getHealthCoefficient(), 0.0);
    }

    @Test
    public void healthCoefficientMutatorAcceptsValidArguments() {

        testShip.setHealthCoefficient(1.0);
        assertEquals(1.0, testShip.getHealthCoefficient(), 0.0);

        testShip.setHealthCoefficient(0.5);
        assertEquals(0.5, testShip.getHealthCoefficient(), 0.0);

        testShip.setHealthCoefficient(0.0);
        assertEquals(0.0, testShip.getHealthCoefficient(), 0.0);
    }

    @Test
    public void babyFishNeedMinimalVolumeOfWater() {
        Ship fry = new Ship();
        for (int i = 0; i < Ship.YOUNG_FISH_AGE_IN_WEEKS; ++i) {
            fry.setAgeInWeeks(i);
            assertEquals(Ship.MINIMUM_WATER_VOLUME_ML, fry.getVolumeNeeded(), 0.0);
        }
    }

    @Test
    public void youngFishNeedCorrectVolumeOfWater() {
        Ship fry = new Ship();
        for (int i = Ship.YOUNG_FISH_AGE_IN_WEEKS; i <= Ship.MATURE_FISH_AGE_IN_WEEKS; ++i) {
            fry.setAgeInWeeks(i);
            double volumeNeeded = Ship.MINIMUM_WATER_VOLUME_ML * fry.getAgeInWeeks() / Ship.YOUNG_FISH_AGE_IN_WEEKS;
            assertEquals(volumeNeeded, fry.getVolumeNeeded(), 0.001);
        }
    }

    @Test
    public void matureFishNeedCorrectVolumeOfWater() {
        Ship fry = new Ship();
        for (int i = Ship.MATURE_FISH_AGE_IN_WEEKS + 1; i <= Ship.MAXIMUM_AGE_IN_WEEKS; ++i) {
            fry.setAgeInWeeks(i);
            double volumeNeeded = Ship.MINIMUM_WATER_VOLUME_ML * 1.5;
            assertEquals(volumeNeeded, fry.getVolumeNeeded(), 0.001);
        }
    }

    @Test
    public void deadFishNeedNoWater() {
        Ship fry = new Ship();
        fry.setAgeInWeeks(50);
        fry.incrementAge();
        assertEquals(0.0, fry.getVolumeNeeded(), 0.0);
    }

    @Test
    public void changeHealthCoefficientWillNotPermitOverlargeHealthCoefficients() {
        testShip.changeHealthCoefficient(1.5);
        assertEquals(1.0, testShip.getHealthCoefficient(), 0.0);
    }


    @Test
    public void changeHealthCoefficientWillNotPermitNegativeHealthCoefficients() {
        testShip.changeHealthCoefficient(-1.5);
        assertEquals(0.0, testShip.getHealthCoefficient(), 0.0);
    }

    @Test
    public void changeHealthCoefficientToZeroOrLowerKillsTheGuppy() {
        testShip.changeHealthCoefficient(-1.5);
        assertFalse(testShip.getIsAlive());
    }

    @Test
    void testSetIsAliveToFalse() {
        testShip.setIsAlive(false);
        final boolean actual = testShip.getIsAlive();
        assertFalse(actual);
    }

    @Test
    void testSetIsAliveNoChangeFromDead() {
        testShip.setIsAlive(false);
        testShip.setIsAlive(true);
        final boolean actual = testShip.getIsAlive();
        assertFalse(actual);
    }

    @Test
    void testSetAgeInWeeksNoChangeIfGuppyDead() {
        final int expected = testShip.getAgeInWeeks();
        testShip.setIsAlive(false);
        testShip.setAgeInWeeks(Ship.MAXIMUM_AGE_IN_WEEKS);
        final int actual = testShip.getAgeInWeeks();
        assertEquals(expected, actual);
    }

    @Test
    void testChangeHealthCoefficientToZeroResultsGuppyDeath() {
        final double delta = -1.0;
        testShip.changeHealthCoefficient(delta);
        assertFalse(testShip.getIsAlive());
    }

    @Test
    void testToString() {
        Ship defaultShip = new Ship();
        final int numberAlreadyCreated = Ship.getNumberOfGuppiesBorn();
        final String expected = "Guppy{genus='Poecilia', species='reticulata', ageInWeeks=0, " +
                "isFemale=true, generationNumber=0, isAlive=true, healthCoefficient=0.5, " +
                "identificationNumber=" + numberAlreadyCreated + "}";
        final String actual = defaultShip.toString();
        assertEquals(expected, actual);
    }

    @Test
    public void createGuppyWithZeroHealthCoefficientIsDead() {
        Ship fry = new Ship("a",
                "b",
                0,
                true,
                0,
                Ship.MINIMUM_HEALTH_COEFFICIENT - 1.0);
        assertFalse(fry.getIsAlive());
    }

    @Test
    public void guppyWithNullGenusAndSpecies() {
        Ship fry = new Ship(null,
                null,
                0,
                true,
                0,
                Ship.MINIMUM_HEALTH_COEFFICIENT - 1.0);
        String actual = fry.getGenus();
        String expected = Ship.DEFAULT_GENUS;
        assertEquals(expected, actual);
    }

    @Test
    public void containsConstantCalledFIRST_GENERATION() {
        assertEquals(0, Ship.FIRST_GENERATION);
    }

    @Test
    public void changeHealthCoefficientWillPermitPositiveHealthCoefficients() {
        testShip.changeHealthCoefficient(0.05);
        assertEquals(0.8, testShip.getHealthCoefficient(), 0.001);
    }

    @Test
    public void changeHealthCoefficientWillPermitNegativeHealthCoefficients() {
        testShip.changeHealthCoefficient(-0.05);
        assertEquals(0.7, testShip.getHealthCoefficient(), 0.001);
    }
}
