using System.Collections.Immutable;

namespace CaisseEnregistreuse
{
    public class ItemReference
    {
        private readonly string _name;
        public readonly Price Price;
        
        public ItemReference(string name, double price)
        {
            _name = name;
            Price = Price.ValueOf(price);
        }

        private ItemReference(string itemCode, Price unitPrice)
        {
            _name = itemCode;
            Price = unitPrice;
        }

        public bool MatchByItemCode(string name)
        {
            return _name == name;
        }

        public static Builder Reference()
        {
            return new Builder();
        }

        public sealed class Builder
        {

            private string _itemCode;
            private Price _unitPrice;

            public Builder WithItemCode(string itemCode)
            {
                this._itemCode = itemCode;
                return this;
            }

            public Builder WithUnitPrice(double unitPrice)
            {
                return WithUnitPrice(Price.ValueOf(unitPrice));
            }

            Builder WithUnitPrice(Price unitPrice)
            {
                this._unitPrice = unitPrice;
                return this;
            }

            public ItemReference Build()
            {
                return new ItemReference(_itemCode, _unitPrice);
            }
        }
    }
}