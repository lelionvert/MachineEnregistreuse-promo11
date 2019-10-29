using System;
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
            return Aggregate(Result.NotFound(itemCode),
                (result, itemReference) =>
                    itemReference.MatchByItemCode(itemCode) ? Result.Found(itemReference.Price) : result,
                _itemReferences);
        }

        private Result Aggregate(Result notFound, Func<Result, ItemReference, Result> func,
            ItemReference[] itemReferences)
        {
            var lambdaResult = notFound;
            foreach (var itemReference in itemReferences)
            {
                lambdaResult = func(lambdaResult, itemReference);
            }

            return lambdaResult;
        }
    }
}