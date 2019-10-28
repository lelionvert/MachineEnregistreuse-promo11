using System.Linq;

namespace CaisseEnregistreuse
{
    public class InMemoryCatalog : IPriceQuery
    {
        public readonly ItemReference[] ItemReferences;
        
        public InMemoryCatalog(params ItemReference[] references)
        {
            ItemReferences = references;
        }

        public Price FindPrice(string itemCode)
        {
            var item = ItemReferences.SingleOrDefault(i => i.Name == itemCode);

            return item?.Price;
        }
    }
}