using System;
using System.Xml.Schema;

namespace CaisseEnregistreuse
{
    public class CaisseEnregistreuse
    {
        public static Price Total(Price price, Quantity quantity)
        {
            return price * quantity;
        }
    }
}