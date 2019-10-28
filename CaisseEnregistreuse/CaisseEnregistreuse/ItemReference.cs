namespace CaisseEnregistreuse
{
    public class ItemReference
    {
        public readonly string Name;
        public readonly Price Price;
        
        public ItemReference(string name, double price)
        {
            Name = name;
            Price = Price.ValueOf(price);
        }

        public bool MatchByItemCode(string name)
        {
            return Name == name;
        }
    }
}