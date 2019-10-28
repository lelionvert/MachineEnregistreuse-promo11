namespace CaisseEnregistreuse
{
    public class Quantity
    {
        public readonly int Value;

        private Quantity(int value)
        {
            Value = value;
        }

        public static Quantity ValueOf(int value)
        {
            return new Quantity(value);
        }
    }
}