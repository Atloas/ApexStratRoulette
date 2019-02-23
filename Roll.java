import java.util.Random;
import java.util.Vector;

public class Roll
{
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
}