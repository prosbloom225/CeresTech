package com.prosbloom.cerestech.machines.multiblock;

import com.gregtechceu.gtceu.api.data.chemical.material.Material;
import com.gregtechceu.gtceu.api.machine.IMachineBlockEntity;
import com.gregtechceu.gtceu.common.block.FusionCasingBlock;
import com.gregtechceu.gtceu.common.data.GTBlocks;
import com.gregtechceu.gtceu.common.data.GTMaterials;
import com.gregtechceu.gtceu.common.machine.multiblock.electric.FusionReactorMachine;
import com.prosbloom.cerestech.data.CTMaterials;
import net.minecraft.world.level.block.Block;

import static com.gregtechceu.gtceu.api.GTValues.*;
import static com.gregtechceu.gtceu.api.pattern.Predicates.frames;
import static com.gregtechceu.gtceu.common.data.GTBlocks.*;

public class CompactFusionReactorMachine extends FusionReactorMachine {
    public CompactFusionReactorMachine(IMachineBlockEntity holder, int tier) {
        super(holder, tier);
    }

    public static Block getCasingState(int tier) {
        return switch (tier) {
            case LuV -> MACHINE_CASING_LuV.get();
            case ZPM -> MACHINE_CASING_ZPM.get();
            case UV -> MACHINE_CASING_UV.get();
            case UHV -> MACHINE_CASING_UHV.get();
            default -> MACHINE_CASING_UEV.get();
        };
    }

    public static Material getFrameState(int tier) {
        return switch (tier) {
            case LuV -> GTMaterials.NaquadahAlloy;
            case ZPM -> GTMaterials.Duranium;
            case UV -> GTMaterials.Neutronium;
            case UHV -> CTMaterials.InfinityCatalyst;
            default -> CTMaterials.Infinity;
        };
    }


    public static final String[] L0 = {
            "                                               ",
            "                                               ", "                    FCCCCCF                    ",
            "                    FCIBICF                    ", "                    FCCCCCF                    ",
            "                                               ", "                                               ",
            "                                               ", "                                               ",
            "                                               ", "                                               ",
            "                                               ", "                                               ",
            "                                               ", "                                               ",
            "                                               ", "                                               ",
            "                                               ", "                                               ",
            "                                               ", "  FFF                                     FFF  ",
            "  CCC                                     CCC  ", "  CIC                                     CIC  ",
            "  CBC                                     CBC  ", "  CIC                                     CIC  ",
            "  CCC                                     CCC  ", "  FFF                                     FFF  ",
            "                                               ", "                                               ",
            "                                               ", "                                               ",
            "                                               ", "                                               ",
            "                                               ", "                                               ",
            "                                               ", "                                               ",
            "                                               ", "                                               ",
            "                                               ", "                                               ",
            "                                               ", "                    FCCCCCF                    ",
            "                    FCIBICF                    ", "                    FCCCCCF                    ",
            "                                               ", "                                               ", };

    public static final String[] L1 = {
            "                                               ",
            "                    FCBBBCF                    ", "                   CC     CC                   ",
            "                CCCCC     CCCCC                ", "              CCCCCCC     CCCCCCC              ",
            "            CCCCCCC FCBBBCF CCCCCCC            ", "           CCCCC               CCCCC           ",
            "          CCCC                   CCCC          ", "         CCC                       CCC         ",
            "        CCC                         CCC        ", "       CCC                           CCC       ",
            "      CCC                             CCC      ", "     CCC                               CCC     ",
            "     CCC                               CCC     ", "    CCC                                 CCC    ",
            "    CCC                                 CCC    ", "   CCC                                   CCC   ",
            "   CCC                                   CCC   ", "   CCC                                   CCC   ",
            "  CCC                                     CCC  ", " FCCCF                                   FCCCF ",
            " C   C                                   C   C ", " B   B                                   B   B ",
            " B   B                                   B   B ", " B   B                                   B   B ",
            " C   C                                   C   C ", " FCCCF                                   FCCCF ",
            "  CCC                                     CCC  ", "   CCC                                   CCC   ",
            "   CCC                                   CCC   ", "   CCC                                   CCC   ",
            "    CCC                                 CCC    ", "    CCC                                 CCC    ",
            "     CCC                               CCC     ", "     CCC                               CCC     ",
            "      CCC                             CCC      ", "       CCC                           CCC       ",
            "        CCC                         CCC        ", "         CCC                       CCC         ",
            "          CCCC                   CCCC          ", "           CCCCC               CCCCC           ",
            "            CCCCCCC FCBBBCF CCCCCCC            ", "              CCCCCCC     CCCCCCC              ",
            "                CCCCC     CCCCC                ", "                   CC     CC                   ",
            "                    FCBBBCF                    ", "                                               ", };

    public static final String[] L2 = {
            "                    FCCCCCF                    ",
            "                   CC     CC                   ", "                CCCCC     CCCCC                ",
            "              CCCCCHHHHHHHHHCCCCC              ", "            CCCCHHHCC     CCHHHCCCC            ",
            "           CCCHHCCCCC     CCCCCHHCCC           ", "          ECHHCCCCC FCCCCCF CCCCCHHCE          ",
            "         CCHCCCC               CCCCHCC         ", "        CCHCCC                   CCCHCC        ",
            "       CCHCE                       ECHCC       ", "      ECHCC                         CCHCE      ",
            "     CCHCE                           ECHCC     ", "    CCHCC                             CCHCC    ",
            "    CCHCC                             CCHCC    ", "   CCHCC                               CCHCC   ",
            "   CCHCC                               CCHCC   ", "  CCHCC                                 CCHCC  ",
            "  CCHCC                                 CCHCC  ", "  CCHCC                                 CCHCC  ",
            " CCHCC                                   CCHCC ", "FCCHCCF                                 FCCHCCF",
            "C  H  C                                 C  H  C", "C  H  C                                 C  H  C",
            "C  H  C                                 C  H  C", "C  H  C                                 C  H  C",
            "C  H  C                                 C  H  C", "FCCHCCF                                 FCCHCCF",
            " CCHCC                                   CCHCC ", "  CCHCC                                 CCHCC  ",
            "  CCHCC                                 CCHCC  ", "  CCHCC                                 CCHCC  ",
            "   CCHCC                               CCHCC   ", "   CCHCC                               CCHCC   ",
            "    CCHCC                             CCHCC    ", "    CCHCC                             CCHCC    ",
            "     CCHCE                           ECHCC     ", "      ECHCC                         CCHCE      ",
            "       CCHCE                       ECHCC       ", "        CCHCCC                   CCCHCC        ",
            "         CCHCCCC               CCCCHCC         ", "          ECHHCCCCC FCCCCCF CCCCCHHCE          ",
            "           CCCHHCCCCC     CCCCCHHCCC           ", "            CCCCHHHCC     CCHHHCCCC            ",
            "              CCCCCHHHHHHHHHCCCCC              ", "                CCCCC     CCCCC                ",
            "                   CC     CC                   ", "                    FCCCCCF                    ", };

    public static final String[] L3 = {
            "                    FCIBICF                    ",
            "                   CC     CC                   ", "                CCCHHHHHHHHHCCC                ",
            "              CCHHHHHHHHHHHHHHHCC              ", "            CCHHHHHHHHHHHHHHHHHHHCC            ",
            "           CHHHHHHHCC     CCHHHHHHHC           ", "          CHHHHHCCC FCIBICF CCCHHHHHC          ",
            "         CHHHHCC               CCHHHHC         ", "        CHHHCC                   CCHHHC        ",
            "       CHHHC                       CHHHC       ", "      CHHHC                         CHHHC      ",
            "     CHHHC                           CHHHC     ", "    CHHHC                             CHHHC    ",
            "    CHHHC                             CHHHC    ", "   CHHHC                               CHHHC   ",
            "   CHHHC                               CHHHC   ", "  CHHHC                                 CHHHC  ",
            "  CHHHC                                 CHHHC  ", "  CHHHC                                 CHHHC  ",
            " CHHHC                                   CHHHC ", "FCHHHCF                                 FCHHHCF",
            "C HHH C                                 C HHH C", "I HHH I                                 I HHH I",
            "B HHH C                                 B HHH B", "I HHH I                                 I HHH I",
            "C HHH C                                 C HHH C", "FCHHHCF                                 FCHHHCF",
            " CHHHC                                   CHHHC ", "  CHHHC                                 CHHHC  ",
            "  CHHHC                                 CHHHC  ", "  CHHHC                                 CHHHC  ",
            "   CHHHC                               CHHHC   ", "   CHHHC                               CHHHC   ",
            "    CHHHC                             CHHHC    ", "    CHHHC                             CHHHC    ",
            "     CHHHC                           CHHHC     ", "      CHHHC                         CHHHC      ",
            "       CHHHC                       CHHHC       ", "        CHHHCC                   CCHHHC        ",
            "         CHHHHCC               CCHHHHC         ", "          CHHHHHCCC FCISICF CCCHHHHHC          ",
            "           CHHHHHHHCC     CCHHHHHHHC           ", "            CCHHHHHHHHHHHHHHHHHHHCC            ",
            "              CCHHHHHHHHHHHHHHHCC              ", "                CCCHHHHHHHHHCCC                ",
            "                   CC     CC                   ", "                    FCIBICF                    ", };

    public static final String[] L4 = {
            "                    FCCCCCF                    ",
            "                   CC     CC                   ", "                CCCCC     CCCCC                ",
            "              CCCCCHHHHHHHHHCCCCC              ", "            CCCCHHHCC     CCHHHCCCC            ",
            "           CCCHHCCCCC     CCCCCHHCCC           ", "          ECHHCCCCC FCCCCCF CCCCCHHCE          ",
            "         CCHCCCC               CCCCHCC         ", "        CCHCCC                   CCCHCC        ",
            "       CCHCE                       ECHCC       ", "      ECHCC                         CCHCE      ",
            "     CCHCE                           ECHCC     ", "    CCHCC                             CCHCC    ",
            "    CCHCC                             CCHCC    ", "   CCHCC                               CCHCC   ",
            "   CCHCC                               CCHCC   ", "  CCHCC                                 CCHCC  ",
            "  CCHCC                                 CCHCC  ", "  CCHCC                                 CCHCC  ",
            " CCHCC                                   CCHCC ", "FCCHCCF                                 FCCHCCF",
            "C  H  C                                 C  H  C", "C  H  C                                 C  H  C",
            "C  H  C                                 C  H  C", "C  H  C                                 C  H  C",
            "C  H  C                                 C  H  C", "FCCHCCF                                 FCCHCCF",
            " CCHCC                                   CCHCC ", "  CCHCC                                 CCHCC  ",
            "  CCHCC                                 CCHCC  ", "  CCHCC                                 CCHCC  ",
            "   CCHCC                               CCHCC   ", "   CCHCC                               CCHCC   ",
            "    CCHCC                             CCHCC    ", "    CCHCC                             CCHCC    ",
            "     CCHCE                           ECHCC     ", "      ECHCC                         CCHCE      ",
            "       CCHCE                       ECHCC       ", "        CCHCCC                   CCCHCC        ",
            "         CCHCCCC               CCCCHCC         ", "          ECHHCCCCC FCCCCCF CCCCCHHCE          ",
            "           CCCHHCCCCC     CCCCCHHCCC           ", "            CCCCHHHCC     CCHHHCCCC            ",
            "              CCCCCHHHHHHHHHCCCCC              ", "                CCCCC     CCCCC                ",
            "                   CC     CC                   ", "                    FCCCCCF                    ", };

    public static final String[] L5 = {
            "                                               ",
            "                    FCBBBCF                    ", "                   CC     CC                   ",
            "                CCCCC     CCCCC                ", "              CCCCCCC     CCCCCCC              ",
            "            CCCCCCC FCBBBCF CCCCCCC            ", "           CCCCC               CCCCC           ",
            "          CCCC                   CCCC          ", "         CCC                       CCC         ",
            "        CCC                         CCC        ", "       CCC                           CCC       ",
            "      CCC                             CCC      ", "     CCC                               CCC     ",
            "     CCC                               CCC     ", "    CCC                                 CCC    ",
            "    CCC                                 CCC    ", "   CCC                                   CCC   ",
            "   CCC                                   CCC   ", "   CCC                                   CCC   ",
            "  CCC                                     CCC  ", " FCCCF                                   FCCCF ",
            " C   C                                   C   C ", " B   B                                   B   B ",
            " B   B                                   B   B ", " B   B                                   B   B ",
            " C   C                                   C   C ", " FCCCF                                   FCCCF ",
            "  CCC                                     CCC  ", "   CCC                                   CCC   ",
            "   CCC                                   CCC   ", "   CCC                                   CCC   ",
            "    CCC                                 CCC    ", "    CCC                                 CCC    ",
            "     CCC                               CCC     ", "     CCC                               CCC     ",
            "      CCC                             CCC      ", "       CCC                           CCC       ",
            "        CCC                         CCC        ", "         CCC                       CCC         ",
            "          CCCC                   CCCC          ", "           CCCCC               CCCCC           ",
            "            CCCCCCC FCBBBCF CCCCCCC            ", "              CCCCCCC     CCCCCCC              ",
            "                CCCCC     CCCCC                ", "                   CC     CC                   ",
            "                    FCBBBCF                    ", "                                               ", };

    public static final String[] L6 = {
            "                                               ",
            "                                               ", "                    FCCCCCF                    ",
            "                    FCIBICF                    ", "                    FCCCCCF                    ",
            "                                               ", "                                               ",
            "                                               ", "                                               ",
            "                                               ", "                                               ",
            "                                               ", "                                               ",
            "                                               ", "                                               ",
            "                                               ", "                                               ",
            "                                               ", "                                               ",
            "                                               ", "  FFF                                     FFF  ",
            "  CCC                                     CCC  ", "  CIC                                     CIC  ",
            "  CBC                                     CBC  ", "  CIC                                     CIC  ",
            "  CCC                                     CCC  ", "  FFF                                     FFF  ",
            "                                               ", "                                               ",
            "                                               ", "                                               ",
            "                                               ", "                                               ",
            "                                               ", "                                               ",
            "                                               ", "                                               ",
            "                                               ", "                                               ",
            "                                               ", "                                               ",
            "                                               ", "                    FCCCCCF                    ",
            "                    FCIBICF                    ", "                    FCCCCCF                    ",
            "                                               ", "                                               ", };
}
