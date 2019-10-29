using System;

namespace CaisseEnregistreuse
{
    public abstract class Result
    {
        public static FoundResult Found(Price unitPrice)
        {
            return new FoundResult(unitPrice);
        }

        public static NotFoundResult NotFound(string itemCode)
        {
            return new NotFoundResult(itemCode);
        }

        public sealed class NotFoundResult : Result
        {
            private readonly string _itemCode;

            public NotFoundResult(string itemCode)
            {
                _itemCode = itemCode;
            }
        }

        public sealed class FoundResult : Result
        {
            private readonly Price _unitPrice;

            internal FoundResult(Price unitPrice)
            {
                _unitPrice = unitPrice;
            }
        }
    }
}