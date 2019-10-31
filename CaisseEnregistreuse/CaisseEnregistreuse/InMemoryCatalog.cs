using System.Collections.Immutable;
using System.Linq;

namespace CashRegister
{
    public class InMemoryCatalog : IPriceQuery
    {
        private readonly ImmutableArray<ItemReference> _itemReferences;
        
        public InMemoryCatalog(params ItemReference[] references)
        {
            _itemReferences = references.ToImmutableArray();
        }

        public Result FindPrice(string itemCode)
        {
            var item = _itemReferences
                .Where(itemReference => itemReference.MatchByItemCode(itemCode))
                .Select(p => Result.Found(p.Price))
                .SingleOrDefault();
            return item ?? Result.NotFound(itemCode);
        }
    }
}