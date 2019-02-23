import java.io.*;
import java.util.Vector<E>;

public class Main
{
    public static void main(String[] args)
    {
        File legendsFile = new File("legends.txt");
        File weaponsFile = new File("weapons.txt");
        BufferedReader legendsReader = new BufferedReader(legendsFile);
        BufferedReader weaponsReader = new BufferedReader(weaponsfile);
        Vector<String> legends = new Vector<String>();
        Vector<String> weapons = new Vector<String>();
        String str;

        //Populating lists
        while(str = legendsReader.readLine() != null)
            legends.add(String(str));
        while(str = weaponsReader.readLine())
            weapons.add(String(str));
    }
}