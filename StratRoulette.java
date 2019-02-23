import java.io.*;
import java.util.Vector;
import java.util.Map;
import java.util.TreeMap;
import java.util.List;
import java.util.Arrays;

public class StratRoulette
{
    public static void main(String[] args)
    {
        String[] filenames = {"legends.txt", "weapons.txt", "strats.txt"};
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

        Boolean flagWeapons, flagLegends, flagStrat, flagSolo;

        if(args.length == 0)
        {
            flagWeapons = true;
            flagLegends = true;
            flagStrat = true;
            flagSolo = false;
        }
        else
        {
            flagWeapons = argsAsList.contains("weapons");
            flagLegends = argsAsList.contains("legends");
            flagStrat = argsAsList.contains("strat");
            flagSolo = argsAsList.contains("solo");
        }

        int personCount = flagSolo ? 1 : 3;
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
                String[] roll = Roll.manyUnique(data.get("weapons.txt"), 2);
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