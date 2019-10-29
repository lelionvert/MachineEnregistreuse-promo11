using System;
using System.Xml.Schema;

namespace CaisseEnregistreuse
{
    public class CaisseEnregistreuse
    {
        public static Result Total(Result result, Quantity quantity)
        {
            return result.Select(price => price * quantity);
        }
    }
}