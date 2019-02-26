import java.io.*;
import java.util.Random;
import java.util.Vector;
import java.util.Map;
import java.util.TreeMap;
import java.util.List;
import java.util.Arrays;

public class Roll
{
    public static String rollOne(Vector<String> arg)
    {
        Random random = new Random();
        int index = random.nextInt(arg.size());
        return arg.get(index);
    }

    public static String[] rollManyUnique(Vector<String> arg, int count)
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

    public static String[] rollMany(Vector<String> arg, int count)
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

    //args = {personCount, legends, weapons, strat}
    public static String roll(String[] args)
    {
        String dirPath = "../pools/";
        String[] fileNames = {"legends.txt", "weapons.txt", "strats.txt", "weaponTypes.txt", "ammo.txt"};
        Map<String, Vector<String>> data = new TreeMap<String, Vector<String>>();
        StringBuilder resultBuilder = new StringBuilder();

        for(String fileName:fileNames)
        {
            try
            {
                File file = new File(dirPath + fileName);
                String str;
                BufferedReader reader = new BufferedReader(new FileReader(file));

                data.put(fileName, new Vector<String>());
                
                while((str = reader.readLine()) != null)
                    data.get(fileName).add(str);
                
                reader.close();
            }
            catch(FileNotFoundException e)
            {
                return e.getMessage();
            }
            catch(IOException e)
            {
                return e.getMessage();
            }
        }

        int personCount = Integer.parseInt(args[0]);

        Boolean flagLegends = !args[1].equals("nolegends");
        Boolean flagWeapons = !args[2].equals("noweapons");
        Boolean flagStrat = !args[3].equals("nostrat");
        
        String[][] results = new String[personCount + 1][2];

        if(!(flagLegends || flagWeapons || flagStrat))
        {
            return "ERROR: No rolls selected!";
        }

        if(flagLegends)
        {
            String[] roll = rollManyUnique(data.get("legends.txt"), personCount);
            for(int i = 0; i < personCount; i++)
                results[i][0] = roll[i];
        }

        if(flagWeapons)
        {
            for(String[] tab:results)
            {
                String[] roll = {"", ""};
                if(args[2].equals("weapons"))
                    roll = rollManyUnique(data.get("weapons.txt"), 2);
                else if(args[2].equals("types"))
                    roll = rollMany(data.get("weaponTypes.txt"), 2);
                else if(args[2].equals("ammo"))
                    roll = rollMany(data.get("ammo.txt"), 2);
                tab[1] = roll[0] + ", " + roll[1];
            }
        }

        if(flagStrat)
        {
            results[results.length - 1][0] = rollOne(data.get("strats.txt"));
        }

        for(int i = 0; i < results.length - 1; i++)
        {
            if(results.length != 1)
                resultBuilder.append(i + 1 + ".\n");
            resultBuilder.append("Legend: " + (flagLegends ? results[i][0] : "Any") + "\n");
            resultBuilder.append("Weapons: " + (flagWeapons ? results[i][1] : "Any") + "\n");
            if(i != results.length - 2)
                resultBuilder.append("\n");
        }
        resultBuilder.append("\nStrat: " + (flagStrat ? results[results.length - 1][0] : "Any") + "\n");
        
        return resultBuilder.toString();
    }
}