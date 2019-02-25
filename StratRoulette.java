import java.io.*;
import java.util.Vector;

import javax.lang.model.util.ElementScanner6;

import java.util.Map;
import java.util.TreeMap;
import java.util.List;
import java.util.Arrays;

public class StratRoulette
{
    enum weaponRollMode {AMMO, TYPES, WEAPONS};
    public static void main(String[] args)
    {
        String[] filenames = {"legends.txt", "weapons.txt", "strats.txt", "weaponTypes.txt", "ammo.txt"};
        Map<String, Vector<String>> data = new TreeMap<String, Vector<String>>();

        for(String filename:filenames)
        {
            try
            {
                File file = new File(filename);
                String str;
                BufferedReader reader = new BufferedReader(new FileReader(file));

                data.put(filename, new Vector<String>());
                
                while((str = reader.readLine()) != null)
                    data.get(filename).add(str);
                
                reader.close();
            }
            catch(FileNotFoundException e)
            {
                System.out.println(e);
                return;
            }
            catch(IOException e)
            {
                System.out.println(e);
                return;
            }
        }

        List<String> argsAsList = Arrays.asList(args);

        Boolean flagWeapons = true, flagLegends = true, flagStrat = true;
        int personCount = 3;
        weaponRollMode weaponRoll = weaponRollMode.WEAPONS;

        //ARguments modify the default behaviour
        if(args.length != 0)
        {
            flagWeapons = !argsAsList.contains("noweapons");
            flagLegends = !argsAsList.contains("nolegends");
            flagStrat = !argsAsList.contains("nostrat");
            
            if(argsAsList.contains("solo"))
                personCount = 1;
            else if(argsAsList.contains("duo"))
                personCount = 2;
            else if(argsAsList.contains("quad"))
                personCount = 4;

            if(flagWeapons)
            {
                if(argsAsList.contains("ammo"))
                    weaponRoll = weaponRollMode.AMMO;
                else if(argsAsList.contains("types"))
                    weaponRoll = weaponRollMode.TYPES;
            }
        }

        if(!(flagLegends || flagWeapons || flagStrat))
        {
            System.out.println("ERROR: No rolls selected.");
            return;
        }

        String[][] results = new String[personCount + 1][2];

        if(flagLegends)
        {
            String[] roll = Roll.manyUnique(data.get("legends.txt"), personCount);
            for(int i = 0; i < personCount; i++)
                results[i][0] = roll[i];
        }

        if(flagWeapons)
        {
            for(String[] tab:results)
            {
                String[] roll;
                if(weaponRollMode.valueOf("WEAPONS") == weaponRoll)
                    roll = Roll.manyUnique(data.get("weapons.txt"), 2);
                else if(weaponRollMode.valueOf("AMMO") == weaponRoll)
                    roll = Roll.many(data.get("ammo.txt"), 2);
                else
                    roll = Roll.many(data.get("weaponTypes.txt"), 2);
                tab[1] = roll[0] + ", " + roll[1];
            }
        }

        if(flagStrat)
        {
            results[results.length - 1][0] = Roll.one(data.get("strats.txt"));
        }

        for(int i = 0; i < results.length - 1; i++)
        {
            if(results.length != 1)
                System.out.println(i + 1 + ".");
            System.out.println("Legend: " + (flagLegends ? results[i][0] : "Any"));
            System.out.println("Weapons: " + (flagWeapons ? results[i][1] : "Any"));
            if(i != results.length - 2)
                System.out.println("");
        }
        System.out.println("\nStrat: " + (flagStrat ? results[results.length - 1][0] : "Any"));
    }
}