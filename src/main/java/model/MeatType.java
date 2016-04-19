package model;

/**
 * Created by Rex on 19.4.2016.
 */
public enum MeatType {

    ALLIGATOR(31),
    BACON(55),
    BEEF(65),
    CHICKEN(27),
    CHUCKER(59),
    CRAB(79),
    DUCK(100),
    EELS(90),
    FISH(64),
    FRANKFURTERS(40),
    GOAT(60),
    GOOSE(71),
    GROUSE(45),
    HAM(50),
    HARE(66),
    KIDNEY(41),
    LAMB(50),
    LOBSTER(83),
    MUSSELS(20),
    MUTTON(52),
    OPOSSUM(48),
    OYSTERS(51),
    PARTRIDGE(87),
    PEMMICAN(65),
    PHEASANT(88),
    PORK(95),
    PRAWNS(41),
    PUFFIN(79),
    RABBIT(72),
    REINDEER(46),
    SALMON(52),
    SAUSAGE(49),
    SCRAPPLE(97),
    SEAL(26),
    SHARKMEAT(74),
    SHRIMP(63),
    SNAILS(81),
    SQUIRREL(74),
    SWEETBREADS(62),
    TURKEY(80),
    TURTLE(71),
    VEAL(21);

    private final int freshDays;

    MeatType(int freshDays) {
        this.freshDays = freshDays;
    }

    public int getFreshDays() {
        return freshDays;
    }
}
