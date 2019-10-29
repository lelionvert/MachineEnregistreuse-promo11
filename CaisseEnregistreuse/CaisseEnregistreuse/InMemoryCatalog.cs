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

        public Result FindPrice(string itemCode)
        {
            var item = _itemReferences.SingleOrDefault(i => i.MatchByItemCode(itemCode));

            if (item != null)
                return new Result.FoundResult(item.Price);
            return new Result.NotFoundResult(itemCode);
        }
    }
}