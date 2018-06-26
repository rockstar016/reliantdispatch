package com.rock.reliantdispatch.Constants;

public class DamageConfig {
    public static final int SCRATCHED = 0;
    public static final int MULTIPLE_SCRATCHED = 1;
    public static final int DENT = 2;
    public static final int PAINT_CHIP = 3;
    public static final int MAJOR_DAMAGE = 4;
    public static final int CRACKED = 5;
    public static final int SCUFFED = 6;
    public static final int RUBBED = 7;
    public static final int FOREIGN_FLUIOD = 8;
    public static final int MISSING = 9;
    public static final int BROKEN = 10;
    public static final int FADED = 11;
    public static final int FLAT_TIRE = 12;
    public static final int GOUGE = 13;
    public static final int HAIL_DAMAGE = 14;
    public static final int LOOSE_CONTENTS = 15;
    public static final int RUST = 16;
    public static final int OTHER = 17;

    public static String[] damageListProvider = {"S - Scratched", "MS - Multiple Scratches",
                                                 "D - Dent", "PC - Paint Chip",
                                                 "MD - Major Damage", "CR - Cracked",
                                                 "SC - Scuffed", "R - Rubbed",
                                                 "FF - Foreign Fluid", "M - Missing",
                                                 "BR - Broken", "F - Faded",
                                                 "FT - Flat Tire", "G - Gouge",
                                                 "HD - Hail Damage", "LC - Loose Contents",
                                                 "RU - Rust", "O - Other"};
    public static String[] damageStringProvider = { "S", "MS",
                                                    "D", "PC",
                                                    "MD", "CR",
                                                    "SC", "R",
                                                    "FF", "M",
                                                    "BR", "F",
                                                    "FT", "G",
                                                    "HD", "LC",
                                                    "RU", "O"};

}
