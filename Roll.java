import java.io.*;
import java.util.Random;
import java.util.Vector;
import java.util.Map;
import java.util.TreeMap;
import java.util.List;
import java.util.Arrays;

public class Roll
{
    enum weaponRollMode {AMMO, TYPES, WEAPONS};

    public static String one(Vector<String> arg)
    {
        Random random = new Random();
        int index = random.nextInt(arg.size());
        return arg.get(index);
    }

    public static String[] manyUnique(Vector<String> arg, int count)
    {
        Random random = new Random();
        Vector<String> copy = (Vector<String>)arg.clone();
        String[] result = new String[count];
        int index;

        //In case we try to roll more times than there are items in the vector
        if(count > copy.size())
            count = copy.size();

        for(int i = 0; i < count; i++)
        {
            index = random.nextInt(copy.size());
            result[i] = copy.get(index);
            copy.remove(index);
        }

        return result;
    }

    public static String[] many(Vector<String> arg, int count)
    {
        Random random = new Random();
        String[] result = new String[count];
        int index, size = arg.size();

        for(int i = 0; i < count; i++)
        {
            index = random.nextInt(size);
            result[i] = arg.get(index);
        }

        return result;
    }

    public static String roll(String[] args)
    {
        String[] filenames = {"legends.txt", "weapons.txt", "strats.txt", "weaponTypes.txt", "ammo.txt"};
        Map<String, Vector<String>> data = new TreeMap<String, Vector<String>>();
        String result = "";

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
                result = e.getMessage();
                return result;
            }
            catch(IOException e)
            {
                result = e.getMessage();
                return result;
            }
        }

        List<String> argsAsList = Arrays.asList(args);

        Boolean flagWeapons, flagLegends, flagStrat;
        int personCount = 3;
        weaponRollMode weaponRoll = weaponRollMode.WEAPONS;

        //ARguments modify the default behaviour
        flagWeapons = !argsAsList.contains("noweapons");
        flagLegends = !argsAsList.contains("nolegends");
        flagStrat = !argsAsList.contains("nostrat");
        
        if(argsAsList.contains("1"))
            personCount = 1;
        else if(argsAsList.contains("2"))
            personCount = 2;
        else if(argsAsList.contains("3"))
            personCount = 3;
        else if(argsAsList.contains("4"))
            personCount = 4;

        if(flagWeapons)
        {
            if(argsAsList.contains("weapons"))
                weaponRoll = weaponRollMode.WEAPONS;
            else if(argsAsList.contains("ammo"))
                weaponRoll = weaponRollMode.AMMO;
            else if(argsAsList.contains("types"))
                weaponRoll = weaponRollMode.TYPES;
        }

        if(!(flagLegends || flagWeapons || flagStrat))
        {
            result = "ERROR: No rolls selected.";
            return result;
        }

        String[][] results = new String[personCount + 1][2];

        if(flagLegends)
        {
            String[] roll = manyUnique(data.get("legends.txt"), personCount);
            for(int i = 0; i < personCount; i++)
                results[i][0] = roll[i];
        }

        if(flagWeapons)
        {
            for(String[] tab:results)
            {
                String[] roll = {"", ""};
                if(weaponRollMode.valueOf("WEAPONS") == weaponRoll)
                    roll = manyUnique(data.get("weapons.txt"), 2);
                else if(weaponRollMode.valueOf("TYPES") == weaponRoll)
                    roll = many(data.get("weaponTypes.txt"), 2);
                else if(weaponRollMode.valueOf("AMMO") == weaponRoll)
                    roll = many(data.get("ammo.txt"), 2);
                tab[1] = roll[0] + ", " + roll[1];
            }
        }

        if(flagStrat)
        {
            results[results.length - 1][0] = one(data.get("strats.txt"));
        }

        for(int i = 0; i < results.length - 1; i++)
        {
            if(results.length != 1)
            result += i + 1 + ".\n";
            result += "Legend: " + (flagLegends ? results[i][0] : "Any") + "\n";
            result += "Weapons: " + (flagWeapons ? results[i][1] : "Any") + "\n";
            if(i != results.length - 2)
                result += "\n";
        }
        result += "\nStrat: " + (flagStrat ? results[results.length - 1][0] : "Any") + "\n";
        
        return result;
    }
}