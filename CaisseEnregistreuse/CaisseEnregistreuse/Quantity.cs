namespace CashRegister
{
    public class Quantity
    {
        private readonly double Value;

        private Quantity(double value)
        {
            Value = value;
        }

        public static Quantity ValueOf(double value)
        {
            return new Quantity(value);
        }

        public static double operator *(double value, Quantity quantity)
        {
            return value * quantity.Value;
        }
    }
}