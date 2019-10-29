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
            return Aggragate(Result.NotFound(itemCode), (result, itemReference) =>
            {
                if (itemReference.MatchByItemCode(itemCode))
                {
                    return Result.Found(itemReference.Price);
                }
                else
                {
                    return result;
                }
            }, _itemReferences);
        }

        private Result Aggragate(Result notFound, Func<Result, ItemReference, Result> func, ItemReference[] itemReferences)
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