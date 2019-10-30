using System.Collections.Immutable;

namespace CaisseEnregistreuse
{
    public class ItemReference
    {
        public readonly string Name;
        public readonly Price Price;

        public ItemReference(string name, double price)
        {
            Name = name;
            Price = Price.ValueOf(price);
        }

        private ItemReference(string itemCode, Price unitPrice)
        {
            Name = itemCode;
            Price = unitPrice;
        }

        public bool MatchByItemCode(string name)
        {
            return Name == name;
        }

        public static ItemReferenceBuilder Builder()
        {
            return new ItemReferenceBuilder();
        }

        public sealed class ItemReferenceBuilder
        {
            private string _itemCode;
            private Price _unitPrice;

            internal ItemReferenceBuilder() {}

            public ItemReferenceBuilder WithItemCode(string itemCode)
            {
                this._itemCode = itemCode;
                return this;
            }

            public ItemReferenceBuilder WithUnitPrice(double unitPrice)
            {
                return WithUnitPrice(Price.ValueOf(unitPrice));
            }

            ItemReferenceBuilder WithUnitPrice(Price unitPrice)
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