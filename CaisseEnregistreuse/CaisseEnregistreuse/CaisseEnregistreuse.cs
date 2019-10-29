using System;
using System.Xml.Schema;

namespace CashRegister
{
    public class CaisseEnregistreuse
    {
        public static Price Total(Price price, Quantity quantity)
        {
            return price * quantity;
        }
        
        public static Result Total(Result result, Quantity quantity)
        {
            return result.Select(price => price * quantity);
        }
    }
}