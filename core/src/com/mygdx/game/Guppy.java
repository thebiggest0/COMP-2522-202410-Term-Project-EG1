package com.mygdx.game;

import java.util.Objects;

/**
 * Demonstrates how guppy can be generated.
 *
 * @author BCIT
 * @author Cai Chun Yan (A01381221)
 * @version 2024
 */
public class Guppy {
    /**
     * The age in weeks at which a fish is considered young.
     */
    public static final int YOUNG_FISH_AGE_IN_WEEKS =  10;
    /**
     * The age in weeks at which a fish is considered mature.
     */
    public static final int MATURE_FISH_AGE_IN_WEEKS = 30;
    /**
     * The maximum age in weeks a fish can live.
     */
    public static final int MAXIMUM_AGE_IN_WEEKS = 50;
    /**
     * The minimum volume of water in milliliters required for the fish's habitat.
     */
    public static final double MINIMUM_WATER_VOLUME_ML = 250.0;
    /**
     * The default genus name for the fish.
     */
    public static final String DEFAULT_GENUS = "Poecilia";
    /**
     * The default species name for the fish.
     */
    public static final String DEFAULT_SPECIES = "reticulata";
    /**
     * The default health coefficient for a fish.
     */
    public static final double DEFAULT_HEALTH_COEFFICIENT = 0.5;
    /**
     * The minimum value for the health coefficient.
     */
    public static final double MINIMUM_HEALTH_COEFFICIENT = 0.0;
    /**
     * The maximum value for the health coefficient.
     */
    public static final double MAXIMUM_HEALTH_COEFFICIENT = 1.0;
    /**
     * The first generation of the species.
     */
    public static final int FIRST_GENERATION = 0;
    private static int numberOfGuppiesBorn = 0;
    private final String genus;
    private final String species;
    private int ageInWeeks;
    private final boolean isFemale;
    private int generationNumber;
    private boolean isAlive;
    private double healthCoefficient;
    private final int identificationNumber;
    /**
     * Constructs a Guppy object.
     */
    public Guppy() {
        this.genus = DEFAULT_GENUS;
        this.species = DEFAULT_SPECIES;
        this.isFemale = true;
        this.isAlive = true;
        this.healthCoefficient = DEFAULT_HEALTH_COEFFICIENT;
        numberOfGuppiesBorn += 1;
        this.identificationNumber = numberOfGuppiesBorn;
    }

    /**
     * Constructs a Guppy object.
     *
     * @param newGenus a string representing the genus of the Guppy
     * @param newSpecies a string representing the species of the Guppy
     * @param newAgeInWeeks an int representing the age of the Guppy in weeks
     * @param newIsFemale a boolean representing the gender of the Guppy, where true indicates female
     * @param newGenerationNumber an int representing the generation number of the Guppy
     * @param newHealthCoefficient a double representing the health coefficient of the Guppy
     */
    public Guppy(final String newGenus, final String newSpecies, final int newAgeInWeeks, final boolean newIsFemale,
                 final int newGenerationNumber, final double newHealthCoefficient) {
        if (newGenus == null) {
            this.genus = DEFAULT_GENUS;
        } else if (!newGenus.trim().isEmpty()) {
            this.genus = newGenus.trim().substring(0, 1).toUpperCase() + newGenus.trim().substring(1).toLowerCase();
        } else {
            this.genus = DEFAULT_GENUS;
        }
        if (newSpecies == null) {
            this.species = DEFAULT_SPECIES;
        } else if (!newSpecies.trim().isEmpty()) {
            this.species = newSpecies.trim().toLowerCase();
        } else {
            this.species = DEFAULT_SPECIES;
        }
        this.ageInWeeks = Math.max(Math.min(newAgeInWeeks, MAXIMUM_AGE_IN_WEEKS), 0);
        this.isFemale = newIsFemale;
        this.generationNumber = Math.max(newGenerationNumber, 0);
        this.healthCoefficient = Math.min(MAXIMUM_HEALTH_COEFFICIENT, Math.max(newHealthCoefficient,
                MINIMUM_HEALTH_COEFFICIENT));
        this.isAlive = this.healthCoefficient > MINIMUM_HEALTH_COEFFICIENT;
        numberOfGuppiesBorn += 1;
        this.identificationNumber = numberOfGuppiesBorn;
    }

    /**
     * Returns the genus for this Guppy.
     *
     * @return genus a string
     */
    public String getGenus() {
        return genus;
    }

    /**
     * Returns the species for this Guppy.
     *
     * @return species a string
     */
    public String getSpecies() {
        return species;
    }

    /**
     * Returns the ageInWeeks for this Guppy.
     *
     * @return ageInWeeks an int
     */
    public int getAgeInWeeks() {
        return ageInWeeks;
    }

    /**
     * Returns the isFemale for this Guppy.
     *
     * @return isFemale a boolean
     */
    public boolean getIsFemale() {
        return isFemale;
    }

    /**
     * Returns the generationNumber for this Guppy.
     *
     * @return generationNumber an int
     */
    public int getGenerationNumber() {
        return generationNumber;
    }

    /**
     * Returns the isAlive for this Guppy.
     *
     * @return isAlive a boolean
     */
    public boolean getIsAlive() {
        return isAlive;
    }

    /**
     * Returns the healthCoefficient for this Guppy.
     *
     * @return healthCoefficient a double
     */
    public double getHealthCoefficient() {
        return healthCoefficient;
    }

    /**
     * Returns the identificationNumber for this Guppy.
     *
     * @return identificationNumber an int
     */
    public int getIdentificationNumber() {
        return identificationNumber;
    }

    /**
     * Returns the numberOfGuppiesBorn for this Guppy.
     *
     * @return numberOfGuppiesBorn an int
     */
    public static int getNumberOfGuppiesBorn() {
        return numberOfGuppiesBorn;
    }

    /**
     * Sets the ageInWeeks for this Guppy.
     *
     * @param ageInWeeks an int
     */
    public void setAgeInWeeks(final int ageInWeeks) {
        if (this.isAlive) {
            if (ageInWeeks >= 0 && ageInWeeks <= MAXIMUM_AGE_IN_WEEKS) {
                this.ageInWeeks = ageInWeeks;
            }
        }
    }

    /**
     * Sets the isAlive for this Guppy.
     *
     * @param isAlive a boolean
     */
    public void setIsAlive(final boolean isAlive) {
        if (!isAlive) {
            this.isAlive = false;
        }
    }

    /**
     * Sets the healthCoefficient for this Guppy.
     *
     * @param healthCoefficient a double
     */
    public void setHealthCoefficient(final double healthCoefficient) {
        if (healthCoefficient >= MINIMUM_HEALTH_COEFFICIENT && healthCoefficient <= MAXIMUM_HEALTH_COEFFICIENT) {
            this.healthCoefficient = healthCoefficient;
        }
    }

    /**
     * Increments age by 1.
     */
    public void incrementAge() {
        this.ageInWeeks += 1;
        setIsAlive(this.ageInWeeks < MAXIMUM_AGE_IN_WEEKS);
    }

    /**
     * Returns the volume needed for this Guppy.
     *
     * @return volume needed as a double
     */
    public double getVolumeNeeded() {
        if (this.ageInWeeks < YOUNG_FISH_AGE_IN_WEEKS) {
            return MINIMUM_WATER_VOLUME_ML;
        } else if (this.ageInWeeks <= MATURE_FISH_AGE_IN_WEEKS) {
            return MINIMUM_WATER_VOLUME_ML * ageInWeeks / YOUNG_FISH_AGE_IN_WEEKS;
        } else if (this.ageInWeeks <= MAXIMUM_AGE_IN_WEEKS) {
            final double multiplier = 1.5;
            return MINIMUM_WATER_VOLUME_ML * multiplier;
        } else {
            return 0.0;
        }
    }

    /**
     * Changes the healthCoefficient for this Guppy.
     *
     * @param delta a double
     */
    public void changeHealthCoefficient(final double delta) {
        this.healthCoefficient += delta;
        if (this.healthCoefficient <= MINIMUM_HEALTH_COEFFICIENT) {
            this.healthCoefficient = 0.0;
            setIsAlive(false);
        } else if (this.healthCoefficient > MAXIMUM_HEALTH_COEFFICIENT) {
            this.healthCoefficient = MAXIMUM_HEALTH_COEFFICIENT;
        }
    }

    /**
     * Returns a String representation of this Guppy.
     *
     * @return toString description
     */
    @Override
    public String toString() {
        return "Guppy{"
                + "genus='" + genus + '\''
                + ", species='" + species + '\''
                + ", ageInWeeks=" + ageInWeeks
                + ", isFemale=" + isFemale
                + ", generationNumber=" + generationNumber
                + ", isAlive=" + isAlive
                + ", healthCoefficient=" + healthCoefficient
                + ", identificationNumber=" + identificationNumber
                + '}';
    }

    /**
     * Compares this Guppy object to another object for equality.
     *
     * @param o the object to compare against
     * @return boolean
     */
    @Override
    public boolean equals(final Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Guppy guppy = (Guppy) o;
        return ageInWeeks == guppy.ageInWeeks && isFemale == guppy.isFemale
                && generationNumber == guppy.generationNumber && isAlive == guppy.isAlive
                && Double.compare(healthCoefficient, guppy.healthCoefficient) == 0
                && identificationNumber == guppy.identificationNumber && Objects.equals(genus, guppy.genus)
                && Objects.equals(species, guppy.species);
    }

    /**
     * Generates a hash code for a Guppy object.
     * @return an integer hash code value for the object
     */
    @Override
    public int hashCode() {
        return Objects.hash(genus, species, ageInWeeks, isFemale, generationNumber, isAlive,
                healthCoefficient, identificationNumber);
    }
}
