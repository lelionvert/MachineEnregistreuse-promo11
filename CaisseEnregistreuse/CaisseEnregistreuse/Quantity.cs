namespace CashRegister
{
    public class Quantity
    {
        private readonly int Value;

        private Quantity(int value)
        {
            Value = value;
        }

        public static Quantity ValueOf(int value)
        {
            return new Quantity(value);
        }

        public static double operator *(double value, Quantity quantity)
        {
            return value * quantity.Value;
        }
    }
}