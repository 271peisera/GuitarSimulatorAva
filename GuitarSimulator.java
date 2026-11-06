
public class GuitarSimulator 
{
public static void main(String[] args) 
{
    String keyboard = "q2we4r5ty7u8i9op-[=zxdcfvgbnjmk,.;/' ";
    GuitarString[] strings = new GuitarString[37];

    for (int i = 0; i < strings.length; i++)
    {
        strings[i] = new GuitarString(440.0 * Math.pow(1.05956, i - 24));
    }

    // Main input loop
    while (true) 
    {
        if (StdDraw.hasNextKeyTyped()) 
        {
            char key = StdDraw.nextKeyTyped();
            int index = keyboard.indexOf(key);

            if (index >= 0 && index < strings.length)
            {
                strings[index].pluck();
            }
        }

        double sample = 0.0;

        for (GuitarString string : strings)
        {
            sample += string.sample();
            string.tic();
        }

        StdAudio.play(sample);
    }
}
}