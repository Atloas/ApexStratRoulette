import java.io.*;
import java.util.Random;
import java.util.ArrayList;
import java.util.Map;
import java.util.TreeMap;

public class Roll
{
    /**
     * Randomly selects a single item from the passed ArrayList of Strings.
     * 
     * @param arg ArrayList of Strings from which an item will be chosen.
     * @return Chosen String.
     */
    public static String rollOne(ArrayList<String> arg)
    {
        Random random = new Random();
        int index = random.nextInt(arg.size());
        return arg.get(index);
    }

    /**
     * Randomly selects count items from the passed ArrayList of Strings, avoiding duplicates.
     * <p>
     * The method creates a clone of the passed ArrayList, then removes from it every item selected, so that each item can only be chosen once.
     * Passing a higher count than the size of the ArrayList will set count to the size of the ArrayList.
     * 
     * @param arg ArrayList of Strings from which items will be chosen.
     * @param count Number of items to choose.
     * @return String[] containing chosen items.
     */
    public static String[] rollManyUnique(ArrayList<String> arg, int count)
    {
        Random random = new Random();
        String[] result = new String[count];
        int index;

        //In case we try to roll more times than there are items in the ArrayList
        if(count > arg.size())
            count = arg.size();

        for(int i = 0; i < count; i++)
        {
            index = random.nextInt(arg.size() - 1);
            result[i] = arg.get(index);
            arg.remove(index);
            arg.add(result[i]);
        }

        return result;
    }

    /**
     * Randomly selects count items from the passed ArrayList of Strings, allows duplicates.
     * <p>
     * Passing a higher count than the size of the ArrayList will set count to the size of the ArrayList.
     * 
     * @param arg ArrayList of Strings from which items will be chosen.
     * @param count Number of items to choose.
     * @return String[] containing chosen items.
     */
    public static String[] rollMany(ArrayList<String> arg, int count)
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

    /**
     * Rolls strats.
     * <p>
     * Method creates a TreeMap of items contained in several text files from the pools directory, 
     * then rolls strats according to the passed argument.
     * 
     * @param args String[], contains flags that tell the method how to roll. The format is {team size, legends, weapons, strats},
     *             where each item in the array is a flag related to that respective roll.
     * @return A formatted String, ready to be printed, containing the results of the rolls.
     *         In case of error or an IOException, the message will be returned in stead.
     * @throws FileNotFoundException - if one of the required files is missing.
     * @throws IOException - if an IO exception occurs.
     */
    public static String roll(String[] args) throws FileNotFoundException, IOException
    {
        String dirPath = "resources/";
        String[] fileNames = {"legends.txt", "weapons.txt", "strats.txt", "weaponTypes.txt", "ammo.txt"};
        Map<String, ArrayList<String>> data = new TreeMap<String, ArrayList<String>>();
        StringBuilder resultBuilder = new StringBuilder();

        for(String fileName:fileNames)
        {
            try
            {
                File file = new File(dirPath + fileName);
                String str;
                BufferedReader reader = new BufferedReader(new FileReader(file));

                data.put(fileName, new ArrayList<String>());
                
                while((str = reader.readLine()) != null)
                    data.get(fileName).add(str);
                
                reader.close();
            }
            catch(FileNotFoundException e)
            {
                throw e;
            }
            catch(IOException e)
            {
                throw e;
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