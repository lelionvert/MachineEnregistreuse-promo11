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
            var result = _itemReferences
                .Where(i => i.MatchByItemCode(itemCode))
                .Select(i => Result.Found(i.Price))
                .SingleOrDefault();
            return result ?? Result.NotFound(itemCode);
        }

        private static Result Aggregate(Result notFound, Func<Result, ItemReference, Result> func,
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