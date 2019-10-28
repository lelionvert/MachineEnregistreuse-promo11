using System;

namespace CaisseEnregistreuse
{
    public class Price
    {
        public readonly double Value;

        private Price(double value)
        {
            Value = value;
        }

        public static Price operator*(Price price, Quantity quantity)
        {
            return new Price(price.Value * quantity.Value);
        }

        public static Price ValueOf(double value)
        {
            return new Price(value);
        }
        
        public override int GetHashCode()
        {
            return Value.GetHashCode();
        }

        public override bool Equals(object obj)
        {
            var price = obj as Price;
            
            if (price != null)
                return Value == price.Value;
            return false;
        }
    }
}