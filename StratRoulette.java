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
        String[] filenames = {"legends.txt", "weapons.txt", "extra.txt"};
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

        Boolean flagWeapons = argsAsList.contains("weapons");
        Boolean flagLegends = argsAsList.contains("legends");
        Boolean flagExtra = argsAsList.contains("extra");
        Boolean flagTeam = argsAsList.contains("team");

        int personCount = flagTeam ? 3 : 1;
        if(!(flagLegends || flagWeapons || flagExtra))
        {
            System.out.println("ERROR: No rolls selected.");
            return;
        }

        String[][] results = new String[personCount][3];

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
                String[] roll = Roll.many(data.get("weapons.txt"), 2);
                tab[1] = roll[0] + ", " + roll[1];
            }
        }

        if(flagExtra)
        {
            for(String[] tab:results)
                tab[2] = Roll.one(data.get("extra.txt"));
        }

        for(int i = 0; i < results.length; i++)
        {
            if(results.length != 1)
                System.out.println(i + 1 + ".");
            System.out.println("Legend: " + (flagLegends ? results[i][0] : "Any"));
            System.out.println("Weapons: " + (flagWeapons ? results[i][1] : "Any"));
            System.out.println("Extra: " + (flagExtra ? results[i][2] : "None"));
            if(i != results.length - 1)
                System.out.println("");
        }
    }
}