using System;
using System.Xml.Schema;

namespace CaisseEnregistreuse
{
    public class CaisseEnregistreuse
    {
        public static Price Total(Price price, double quantity)
        {
            return new Price(price.Value * quantity);
        }
    }
}