using System;
using System.Collections;
using NFluent;
using NUnit.Framework;

namespace CashRegister
{
    public class MyDataClass
    {
        public static IEnumerable TestCases
        {
            get
            {
                yield return new TestCaseData(Price.ValueOf(1.20), Quantity.ValueOf(1), Price.ValueOf(1.20));
                yield return new TestCaseData(Price.ValueOf(2.50), Quantity.ValueOf(3), Price.ValueOf(7.50));
                yield return new TestCaseData(Price.ValueOf(12), Quantity.ValueOf(3), Price.ValueOf(36));
            }
        }

        public static IEnumerable PriceQueryTestCases
        {
            get
            {
                yield return new TestCaseData("APPLE", 1, 1.20);
                yield return new TestCaseData("BANANA", 2, 1.90);
                yield return new TestCaseData("PINEAPPLE", 1, 3.80);
            }
        }

        public static IEnumerable PriceQueryNullTestCases
        {
            get { yield return new TestCaseData("PEACH", 10, 2.40); }
        }
    }

    [TestFixture]
    public class Tests
    {
        private IPriceQuery priceQuery;
        
        [SetUp]
        public void Setup()
        {
            priceQuery = new InMemoryCatalog(
                ItemReference.Builder()
                    .WithItemCode("APPLE")
                    .WithUnitPrice(1.20)
                    .Build(),
                ItemReference.Builder()
                    .WithItemCode("BANANA")
                    .WithUnitPrice(1.90)
                    .Build(),
                ItemReference.Builder()
                    .WithItemCode("PINEAPPLE")
                    .WithUnitPrice(3.80)
                    .Build()
            );
        }

        [TestCaseSource(typeof(MyDataClass), "TestCases")]
        public void TotalTest(Price price, Quantity quantity, Price expected)
        {
            var result = CaisseEnregistreuse.Total(price, quantity);
            Check.That(result).IsEqualTo(expected);
        }

        [TestCaseSource(typeof(MyDataClass), "PriceQueryTestCases")]
        [Test]
        public void find_the_price_given_an_item_code(string itemCode, double quantity, double unitPrice)
        {
            Result total = CaisseEnregistreuse.Total(
                priceQuery.FindPrice(itemCode),
                Quantity.ValueOf(quantity)
                );

            Check.That(
                total
            ).IsEqualTo(Result.Found(Price.ValueOf(quantity * unitPrice)));
        }

        [TestCaseSource(typeof(MyDataClass), "PriceQueryNullTestCases")]
        [Test]
        public void find_the_price_given_an_null_item_code(string itemCode, double quantity, double unitPrice)
        {
            Result total = CaisseEnregistreuse.Total(
                priceQuery.FindPrice(itemCode),
                Quantity.ValueOf(quantity)
                );

            Check.That(
                total
            ).IsEqualTo(Result.NotFound(itemCode));
        }
    }
}