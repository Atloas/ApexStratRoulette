import java.io.*;
import java.util.Vector;
import java.util.Map;
import java.util.TreeMap;

public class Main
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

        
    }
}