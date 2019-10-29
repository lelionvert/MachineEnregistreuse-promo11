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
    }
}