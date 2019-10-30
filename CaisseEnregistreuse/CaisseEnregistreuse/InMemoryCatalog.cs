using System;
using System.Collections.Generic;
using System.Linq;

namespace CaisseEnregistreuse
{
    public class InMemoryCatalog : IPriceQuery
    {
        private readonly Dictionary<string, Price> _unitPriceByItemCode;

        public InMemoryCatalog(params ItemReference[] itemReferences)
        {
            _unitPriceByItemCode = itemReferences.ToDictionary(i => i.Name, i => i.Price);
        }

        public Result FindPrice(string itemCode)
        {
            return _unitPriceByItemCode.TryGetValue(itemCode, out Price price)
                ? Result.Found(price)
                : Result.NotFound(itemCode);
        }
    }
}