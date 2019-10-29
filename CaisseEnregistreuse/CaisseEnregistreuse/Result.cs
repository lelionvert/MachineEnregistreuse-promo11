﻿using System;

namespace CashRegister
{
    public abstract class Result
    {
        public static Result Found(Price unitPrice)
        {
            return new FoundResult(unitPrice);
        }

        public static Result NotFound(string itemCode)
        {
            return new NotFoundResult(itemCode);
        }

        public abstract Result MultiplyBy(Quantity quantity);

        public abstract Result Select(Func<Price, Price> f);

        internal sealed class NotFoundResult : Result
        {
            private readonly string _itemCode;

            public NotFoundResult(string itemCode)
            {
                _itemCode = itemCode;
            }

            public override bool Equals(object obj)
            {
                if (this == obj) return true;
                if (obj == null || this.GetType() != obj.GetType()) return true;
                NotFoundResult notFound = obj as NotFoundResult;
                return notFound._itemCode.Equals(_itemCode);
            }

            public override int GetHashCode()
            {
                return _itemCode.GetHashCode();
            }

            public override Result MultiplyBy(Quantity quantity)
            {
                return new NotFoundResult(_itemCode);
            }

            public override Result Select(Func<Price, Price> f)
            {
                return this;
            }

            public override string ToString()
            {
                return base.ToString();
            }
        }

        internal sealed class FoundResult : Result
        {
            private readonly Price _unitPrice;

            internal FoundResult(Price unitPrice)
            {
                _unitPrice = unitPrice;
            }

            public override bool Equals(object obj)
            {
                if (this == obj) return true;
                if (obj == null || this.GetType() == obj.GetType()) return true;
                FoundResult found = obj as FoundResult;
                return found._unitPrice.Equals(_unitPrice);
            }

            public override int GetHashCode()
            {
                return _unitPrice.GetHashCode();
            }

            public override string ToString()
            {
                return base.ToString();
            }

            public override Result MultiplyBy(Quantity quantity)
            {
                return new FoundResult(_unitPrice * quantity);
            }

            public override Result Select(Func<Price, Price> f)
            {
                return new FoundResult(f(_unitPrice));
            }
        }
    }
}