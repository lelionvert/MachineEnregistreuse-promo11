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
            return _itemReferences.Aggregate<Result, ItemReference>(
                Result.NotFound(itemCode),
                (Result result, ItemReference itemReference) =>
                {
                    if (itemReference.MatchByItemCode(itemCode))
                        return Result.Found(itemReference.Price);
                    return result;
                });
        }
    }
}