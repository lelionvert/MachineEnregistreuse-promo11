using System.Linq;

namespace CaisseEnregistreuse
{
    public class InMemoryCatalog : IPriceQuery
    {
        private readonly ItemReference[] _itemReferences;
        
        public InMemoryCatalog(params ItemReference[] references)
        {
            _itemReferences = references;
        }

        public Price FindPrice(string itemCode)
        {
            var item = _itemReferences.SingleOrDefault(i => i.MatchByItemCode(itemCode));

            return item?.Price;
        }
    }
}