using System;

namespace CaisseEnregistreuse
{
    public sealed class Price
    {
        public readonly double Value;

        public Price(double value)
        {
            Value = value;
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